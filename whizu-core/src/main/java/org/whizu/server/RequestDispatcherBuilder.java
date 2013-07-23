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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.annotation.App;
import org.whizu.util.AnnotationScanner;
import org.whizu.util.Strings;
import org.whizu.util.TypeReporter;

/**
 * @author Rudy D'hauwe
 */
class RequestDispatcherBuilder {

	private static final String JQUERY_MOBILE_SERVER = "org.whizu.server.JQueryMobileServer";

	private static final Logger log = LoggerFactory.getLogger(RequestDispatcherBuilder.class);

	/**
	 * Configuration parameter for classpath annotation scanning. Supported values
	 * are the empty string (""), "on", "true", "off", "false", or a comma
	 * separated list of packages to scan.
	 */
	private static final String INIT_PARAM_ANNOTATION_SCANNING = "annotation-scanning";

	private static RequestDispatcherBuilder create() {
		return new RequestDispatcherBuilder();
	}

	protected static RequestDispatcherBuilder createFromFilterConfig(FilterConfig config) {
		// @formatter:off
		return create()
				.servletContext(config.getServletContext())
				.configureAnnotationScanning(config.getInitParameter(INIT_PARAM_ANNOTATION_SCANNING));
		// @formatter:on
	}

	private AnnotationScanner classpathAnnotationScanner_;

	private String servletContextPath_;

	protected RequestDispatcher build() {
		final RequestDispatcher dispatcher_ = new RequestDispatcher();
		final List<Server> serverList = getServerList();

		if (classpathAnnotationScanner_ != null) {
			classpathAnnotationScanner_.scan(App.class, new TypeReporter<App>() {

				@Override
				public void report(App app, Class<?> appClass) {
					initRequestProcessor(app.value(), appClass);
				}

				private RequestProcessor initRequestProcessor(String uri, Class<?> clazz) {
					uri = servletContextPath_ + uri;
					for (Server server : serverList) {
						if (server.accept(clazz)) {
							return server.createRequestProcessor(uri, clazz, servletContextPath_, dispatcher_);
						}
					}
					// TODO detect multiple processors are found for the same uri
					// TODO detect that an uri is mapped multiple times
					log.warn("No request processor found for {}", clazz);
					return null;
				}
/*
				private void report(String uri, Class<?> appClass) {
					RequestProcessor processor = getRequestProcessor(appClass);
					uri = servletContextPath_ + uri;
					log.debug("Mapping @App {} with class {} to {}", uri, appClass, processor);
					dispatcher_.addRequestProcessor(uri, processor);
				}

				private RequestProcessor getRequestProcessor(Class<?> clazz) {
					for (Server server : serverList) {
						if (server.accept(clazz)) {
							return server.createRequestProcessor(clazz, servletContextPath_);
						}
					}
					// TODO detect that multiple processors are found for the same
					// uri
					// TODO detect that an uri is mapped multiple times
					log.warn("No request processor found for {}", clazz);
					return null;
				}
*/				
			});
		}

		return dispatcher_;
	}

	private List<Server> getServerList() {
		List<Server> list = new ArrayList<Server>();

		try {
			@SuppressWarnings("unchecked")
			Class<Server> jqmServerClass = (Class<Server>) Class.forName(JQUERY_MOBILE_SERVER);
			Server jqmServer = jqmServerClass.newInstance();
			list.add(jqmServer);
		} catch (ClassNotFoundException e) {
			log.warn("JQueryMobile library is mising on the classpath", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	/**
	 * Configures classpath annotation scanning based on the value of the
	 * "annotation-scanning" init-param on <code>WhizuFilter</code>.
	 * 
	 * @param value
	 *            the value of the init-param
	 */
	private RequestDispatcherBuilder configureAnnotationScanning(String value) {
		if (Strings.isBlank(value)) {
			// scan the full classpath
			enableAnnotationScanning();
		} else if (Strings.equalsIgnoreCaseOneOf(value, "true", "on")) {
			// scan the full classpath
			enableAnnotationScanning();
		} else if (Strings.equalsIgnoreCaseOneOf(value, "false", "off")) {
			// don't scan
			disableAnnotationScanning();
		} else if (Strings.isCommaSeparatedListOfPackages(value)) {
			// scan a limited set of packages
			String[] packages = Strings.split(value, ',');
			enableAnnotationScanning(packages);
		} else {
			throw new ConfigurationException("Illegal value {} for init-param {}", value,
					INIT_PARAM_ANNOTATION_SCANNING);
		}

		return this;
	}

	private void disableAnnotationScanning() {
		classpathAnnotationScanner_ = null;
	}

	private void enableAnnotationScanning() {
		classpathAnnotationScanner_ = new AnnotationScanner();
	}

	private void enableAnnotationScanning(String[] packages) {
		classpathAnnotationScanner_ = new AnnotationScanner(packages);
	}

	private RequestDispatcherBuilder servletContext(ServletContext servletContext) {
		servletContextPath_ = servletContext.getContextPath();
		return this;
	}
}
