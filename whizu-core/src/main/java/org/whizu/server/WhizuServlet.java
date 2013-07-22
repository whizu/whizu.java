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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.annotation.App;
import org.whizu.annotation.Body;
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
import org.whizu.util.AnnotationScanner;
import org.whizu.util.Chrono;

/**
 * @author Rudy D'hauwe
 */
public class WhizuServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(WhizuServlet.class);

	private static final String PACKAGE_NAMES = "annotation-scanning";

	private static final String WHIZU_SESSION = "whizu-session";

	private final Configuration config_ = new Configuration();

	private RequestDispatcher requestProcessor_;

	/**
	 * The context path of the web application.
	 * 
	 * The context path is the portion of the request URI that is used to select
	 * the context of the request. The context path always comes first in a request
	 * URI. The path starts with a / character but does not end with a / character.
	 * For servlets in the default (root) context, this equals "".
	 * 
	 * It is possible that a servlet container may match a context by more than one
	 * context path. In such cases the HttpServletRequest.getContextPath() will
	 * return the actual context path used by the request and it may differ from
	 * the path returned by this method. This context path should be considered as
	 * the prime or preferred context path of the application.
	 */
	private String contextPath_;

	private String packageScan_ = null;

	private Session assureUserSession(HttpSession session) {
		Session userSession = (Session) session.getAttribute(WHIZU_SESSION);
		if (userSession == null) {
			userSession = new SessionImpl();
			session.setAttribute(WHIZU_SESSION, userSession);
		}
		RequestImpl.get().session(userSession);
		return userSession;
	}

	private String getParam(ServletConfig config, String name) {
		return config.getInitParameter(name);
	}

	// replace by a class StylesheetResource?
	// could be cached in production-mode (whizu-pro)
	private Resource handleCss(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String uri = request.getRequestURI();
		String servletPath = request.getServletPath();
		String path = uri.substring(servletPath.length());
		return new ClassPathResource(path);
	}

	/**
	 * Called by the servlet container to indicate that the servlet is being placed
	 * into service. 
	 */
	@Override
	public void init()
			throws ServletException {
		Chrono chrono = Chrono.start();
		log.info("WhizuServlet is starting...");
		ServletConfig config = getServletConfig();
		ServletContext context = config.getServletContext();
		contextPath_ = context.getContextPath();
		packageScan_ = getParam(config, PACKAGE_NAMES);
		RequestContext.setInstance(new RequestContextImpl());
		new AnnotationScanner(packageScan_).scan(App.class, config_);
		//AnnotationScanner.scan(App.class, config_, packageScan_);
		log.info("WhizuServlet started in {}ms", chrono.stop());
	}

	// serve a new page request to an application,
	// replace this method by a class PageResource?
	private Resource servePageRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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
					public void handleEvent() {
						app.init(new WhizuUI());
					}

					@Override
					public String id() {
						return id;
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
							log.debug("@Body" + bodyText);
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
			// IllegalArgumentException("No @App has been defined for " + uri);
			log.debug("Page {} not found", uri);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return new StringResource("Page not found");
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * Returns the part of this request's URL from the protocol name up to the
		 * query string in the first line of the HTTP request. The web container
		 * does not decode this string.
		 */
		log.debug("HttpServletRequest.requestURI {}", request.getRequestURI());

		/**
		 * Returns the portion of the request URI that indicates the context of the
		 * request. The context path always comes first in a request URI. The path
		 * starts with a "/" character but does not end with a "/" character. For
		 * servlets in the default (root) context, this method returns "". The
		 * container does not decode this string.
		 * 
		 * It is possible that a servlet container may match a context by more than
		 * one context path. In such cases this method will return the actual
		 * context path used by the request and it may differ from the path
		 * returned by the ServletContext.getContextPath() method.
		 */
		log.debug("HttpServletRequest.contextPath {}", request.getContextPath());

		/**
		 * Returns the context path of the web application. The context path is the
		 * portion of the request URI that is used to select the context of the
		 * request. The context path always comes first in a request URI. The path
		 * starts with a / character but does not end with a / character. For
		 * servlets in the default (root) context, this method returns "".
		 * 
		 * It is possible that a servlet container may match a context by more than
		 * one context path. In such cases the HttpServletRequest.getContextPath()
		 * will return the actual context path used by the request and it may
		 * differ from the path returned by this method.
		 */
		log.debug("ServletContext.contextPath {}", request.getServletContext().getContextPath());

		// log.trace("HttpServletRequest.servletPath {}",
		// request.getServletPath());
		// log.trace("HttpServletRequest.pathInfo {}", request.getPathInfo());

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
				 * "post-check=0, pre-check=0"); // Set standard HTTP/1.0 no-cache
				 * header. response.setHeader("Pragma", "no-cache");
				 */
				content = new StringResource(RequestImpl.get().finish());
			}

			if (log.isDebugEnabled()) {
				log.debug("Server side completed in {}ms", chrono.stop());
				if (content != null)
					log.debug("Streaming script {}", content.getString());
			}

			if (content != null) {
				if (!content.getString().equals("Page not found")) {
					log.debug("Streaming content of page");
					content.print(response.getOutputStream());
					response.getOutputStream().flush();
				}
			}

			// response.getWriter().close();
		} catch (RuntimeException e) {
			log.error("Whoops. An unexpected RuntimeException in WhizuServlet.", e);
			e.printStackTrace(response.getWriter());
			// throw new ServletException(e);
			// TODO stream the exception stack trace to the browser in dev mode
		} finally {
			RequestImpl.get().finish();
		}
	}
	private void setExpires(HttpServletResponse response) {
		long CACHE_DURATION_IN_SECOND = 100 * 60; // 100 minutes
		log.debug("SETTING the Expires header to {} seconds", CACHE_DURATION_IN_SECOND);
		response.setHeader("Cache-Control", "public, max-age=" + CACHE_DURATION_IN_SECOND);
		response.setDateHeader("Expires", System.currentTimeMillis() + CACHE_DURATION_IN_SECOND);
	}

	private Session startRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Session userSession = assureUserSession(session);
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keys = parameterMap.keySet();
		for (String key : keys) {
			log.debug("Incoming request parameter {} equals {}", key, parameterMap.get(key)[0]);
			Input editable = userSession.getInput(key);
			if (editable != null) {
				editable.parseString(parameterMap.get(key)[0]);
			}
		}
		return userSession;
	}
}
