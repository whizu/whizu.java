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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.annotation.AnnotationUtils;
import org.whizu.annotation.Body;
import org.whizu.annotation.Listen;
import org.whizu.html.Title;
import org.whizu.jquery.EventHandler;
import org.whizu.jquery.Input;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;
import org.whizu.resource.ClassPathResource;
import org.whizu.resource.Resource;
import org.whizu.resource.StringResource;
import org.whizu.ui.Application;
import org.whizu.ui.WhizuUI;
import org.whizu.util.Chrono;

/**
 * @author Rudy D'hauwe
 */
public class WhizuServlet extends HttpServlet {

	private static final String PACKAGE_NAMES = "package-names";

	private Logger logger = LoggerFactory.getLogger(WhizuServlet.class);

	private static final String WHIZU_SESSION = "whizu-session";

	private final Configuration config_ = new Configuration();

	private String packageScan_ = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		Chrono chrono = Chrono.start();
		logger.info("WhizuServlet starting...");
		packageScan_ = getParam(config, PACKAGE_NAMES);
		RequestContext.setInstance(new RequestContextImpl());
		AnnotationUtils.scan(Listen.class, config_, packageScan_);
		logger.info("WhizuServlet started in {}ms", chrono.stop());
	}

	/**
	 * @param config
	 * @param string
	 * @return
	 */
	private String getParam(ServletConfig config, String name) {
		return config.getInitParameter(name);
	}

	private Session assureUserSession(HttpSession session) {
		Session userSession = (Session) session.getAttribute(WHIZU_SESSION);
		if (userSession == null) {
			userSession = new SessionImpl();
			session.setAttribute(WHIZU_SESSION, userSession);
		}
		RequestImpl.get().session(userSession);
		return userSession;
	}

	// replace by a class StylesheetResource?
	// could be cached in production-mode (whizu-pro)
	private Resource handleCss(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		String servletPath = request.getServletPath();
		String path = uri.substring(servletPath.length());
		return new ClassPathResource(path);
	}

	// serve a new page request to an application,
	// replace this method by a class PageResource?
	private Resource servePageRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// getPageResource(uri).stream(response.getWriter());
		String uri = request.getRequestURI();
		final PageFactory factory = config_.getFactory(uri);
		if (factory != null) {
			final Application app = factory.createInstance();
			Class<? extends Application> clazz = app.getClass();
			Resource resource = new ClassPathResource(factory.template());
			try {
				String content = resource.getString();
				final String id = clazz.getName();
				content = content.replace("${id}", id);
				RequestImpl.get().session().addClickListener(new EventHandler() {

					@Override
					public String id() {
						return id;
					}

					@Override
					public void handleEvent() {
						app.init(new WhizuUI());
					}
				});

				String ann = factory.stylesheet();
				if (ann != null) {
					String link = "<link rel='stylesheet' type='text/css' href='" + ann + "' />";
					content = content.replace("${css}", link);
				} else {
					content = content.replace("${css}", "");
				}

				String title = factory.title();
				if (title != null) {
					content = content.replace("${title}", title);
				} else {
					content = content.replace("${title}", Title.DEFAULT_TITLE);
				}

				String desc = factory.description();
				if (desc != null) {
					content = content.replace("${description}", desc);
				}

				Method[] methods = clazz.getDeclaredMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(Body.class)) {
						try {
							ClassPathResource body = (ClassPathResource) method.invoke(app);
							String bodyText = body.getString();
							logger.debug("@Body" + bodyText);
							content = content.replace("</body>", bodyText + "</body>");
						} catch (IllegalAccessException e) {
							throw new IllegalStateException(e);
						} catch (IllegalArgumentException e) {
							throw new IllegalStateException(e);
						} catch (InvocationTargetException e) {
							throw new IllegalStateException(e);
						}
					}
				}

				String expires = factory.expires();
				if (expires != null) {
					setExpires(response);
				}

				return new StringResource(content);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		} else {
			// throw new
			// IllegalArgumentException("No @Page has been defined for " + uri);
			logger.debug("Page {} not found", uri);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return new StringResource("Page not found");
		}
	}

	/**
	 * @param response
	 */
	private void setExpires(HttpServletResponse response) {
		long CACHE_DURATION_IN_SECOND = 100 * 60; //100 minutes
		logger.debug("SETTING the Expires header to {} seconds", CACHE_DURATION_IN_SECOND);
		logger.debug("SETTING the Expires header to {} seconds", CACHE_DURATION_IN_SECOND);
		response.setHeader("Cache-Control", "public, max-age=" + CACHE_DURATION_IN_SECOND);
		response.setDateHeader("Expires", System.currentTimeMillis() + CACHE_DURATION_IN_SECOND);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		Chrono chrono = Chrono.start();
		Resource content = null;
		try {
			Session session = startRequest(request);
			String id = request.getParameter("id");
			if (id == null) {
				if (request.getRequestURI().endsWith(".css")) {
					content = handleCss(request, response);
					setExpires(response);
				} else if (request.getRequestURI().endsWith(".png")) {
					content = handleCss(request, response);
					setExpires(response);
				} else {
					// Resource resource = this.servePageRequest(request);
					// getResource(uri).stream(response.getWriter());
					content = servePageRequest(request, response);
				}
			} else {
				response.setHeader("X-Robots-Tag", "noindex"); // "noarchive"?
				session.handleEvent(id);
				/*
				 * // Set standard HTTP/1.1 no-cache headers.
				 * response.setHeader("Cache-Control",
				 * "no-store, no-cache, must-revalidate"); // Set IE extended
				 * HTTP/1.1 no-cache headers (use addHeader).
				 * response.addHeader("Cache-Control",
				 * "post-check=0, pre-check=0"); // Set standard HTTP/1.0
				 * no-cache header. response.setHeader("Pragma", "no-cache");
				 */
				content = new StringResource(RequestImpl.get().finish());
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Server side completed in {}ms", chrono.stop());
				if (content != null)
					logger.debug("Streaming script {}", content.getString());
			}

			// if (true) throw new RuntimeException("fake exception");

			if (content != null) {
				if (!content.getString().equals("Page not found")) {
					logger.debug("Streaming content of page");
					content.print(response.getOutputStream());
					response.getOutputStream().flush();
				}
			}

			// response.getWriter().close();
		} catch (RuntimeException e) {
			logger.error("Whoops. An unexpected RuntimeException in WhizuServlet.", e);
			e.printStackTrace(response.getWriter());
			// throw new ServletException(e);
			//TODO stream the exception stack trace to the browser in dev mode

		} finally {
			RequestImpl.get().finish();
		}
	}

	private Session startRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Session userSession = assureUserSession(session);
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keys = parameterMap.keySet();
		for (String key : keys) {
			logger.debug("Incoming request parameter {} equals {}", key, parameterMap.get(key)[0]);
			Input editable = userSession.getInput(key);
			if (editable != null) {
				editable.parseString(parameterMap.get(key)[0]);
			}
		}
		return userSession;
	}
}
