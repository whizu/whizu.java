/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the "EUPL") version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an "AS IS" basis and 
 * without warranties of any kind concerning the Software, including 
 * without limitation merchantability, fitness for a particular purpose, 
 * absence of defects or errors, accuracy, and non-infringement of 
 * intellectual property rights other than copyright. This disclaimer 
 * of warranty is an essential part of the License and a condition for 
 * the grant of any rights to this Software.
 *   
 * For more  details, see <http://joinup.ec.europa.eu/software/page/eupl>.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.whizu.annotation.Title;
import org.whizu.jquery.EventHandler;
import org.whizu.jquery.Input;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;
import org.whizu.ui.Application;
import org.whizu.ui.WhizuUI;

/**
 * @author Rudy D'hauwe
 */
public class WhizuServlet extends HttpServlet {

	private Log log = LogFactory.getLog(WhizuServlet.class);

	private static final String WHIZU_SESSION = "whizu-session";

	private Configuration config = new Configuration();

	private Session assureUserSession(HttpSession session) {
		Session userSession = (Session) session.getAttribute(WHIZU_SESSION);
		if (userSession == null) {
			userSession = new SessionImpl();
			session.setAttribute(WHIZU_SESSION, userSession);
		}
		RequestImpl.get().setSession(userSession);
		return userSession;
	}

	private void debug(String message) {
		log.debug(message);
		System.out.println(message);
	}

	private String fromStream(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			out.append(line);
		}
		in.close();
		return out.toString();
	}

	private String handleCss(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		String servletPath = request.getServletPath();
		String contextPath = request.getContextPath();
		debug(uri);
		debug(servletPath);
		debug(contextPath);
		String css = uri.substring(servletPath.length());
		return stream(css);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		RequestContext.init(new RequestContext() {

			@Override
			public void autowire(Object bean) {
				// AnnotationScanner.ctx.getAutowireCapableBeanFactory().autowireBean(bean);
			}

			@Override
			protected final Request getRequestImpl() {
				return RequestImpl.get();
			}
		});

		new AnnotationScanner().scan(this.config);
	}

	// serve a new page request to an application, 
	// replace this method by a class PageResource?
	private String servePageRequest(HttpServletRequest request) {
		//getPageResource(uri).stream(response.getWriter());
		String uri = request.getRequestURI();
		debug("uri:" + uri);
		final PageFactory factory = config.getFactory(uri);
		if (factory != null) {
			final Application app = factory.createInstance();
			Class<? extends Application> clazz = app.getClass();
			InputStream in = getClass().getResourceAsStream(factory.getTemplate());
			debug("inputstream " + in);
			try {
				String content = fromStream(in);
				final String id = clazz.getName();
				content = content.replace("${id}", id);
				RequestImpl.get().getSession().addClickListener(new EventHandler() {

					@Override
					public String getId() {
						return id;
					}

					@Override
					public void handleEvent() {
						app.init(new WhizuUI());
					}
				});

				String ann = factory.getStylesheet();
				if (ann != null) {
					String link = "<link rel='stylesheet' type='text/css' href='" + ann + "' />";
					content = content.replace("${css}", link);
				} else {
					content = content.replace("${css}", "");
				}

				String title = factory.getTitle();
				if (title != null) {
					content = content.replace("${title}", title);
				} else {
					content = content.replace("${title}", Title.DEFAULT_TITLE);
				}

				return content;
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		} else {
			throw new IllegalArgumentException("No @Page has been defined for " + uri);
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		long start = System.currentTimeMillis();
		String content = "--";
		try {
			Session session = startRequest(request);
			String id = request.getParameter("id");
			if (id == null) {
				debug("running setup:" + request.getRequestURI());
				if (request.getRequestURI().endsWith(".css")) {
					content = handleCss(request, response);
				} else {
					//even better: getResource(uri).stream(response.getWriter());
					content = this.servePageRequest(request);
					//Resource resource = this.servePageRequest(request);
				}
			} else {
				session.handleEvent(id);
				content = RequestImpl.get().finish();
			}
		} finally {
			try {
				long end = System.currentTimeMillis();
				debug("Server side completed in " + (end - start) + "ms");
				debug("Sending script- " + content);
				response.getWriter().print(content);
				response.getWriter().close();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	private Session startRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Session userSession = assureUserSession(session);
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keys = parameterMap.keySet();
		for (String key : keys) {
			debug("P:" + key + "=" + parameterMap.get(key)[0]);
			Input editable = userSession.getInput(key);
			if (editable != null) {
				debug("updating editable server-side " + editable);
				editable.parseString(parameterMap.get(key)[0]);
			}
		}
		return userSession;
	}

	//replace this method by ClassPathResource
	private String stream(String path) {
		InputStream in = getClass().getResourceAsStream(path);
		try {
			return fromStream(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
