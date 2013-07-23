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
import java.lang.annotation.Annotation;

import org.whizu.annotation.Description;
import org.whizu.annotation.Stylesheet;
import org.whizu.annotation.Template;
import org.whizu.annotation.Title;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.resource.ClassPathResource;
import org.whizu.resource.Resource;
import org.whizu.resource.StringResource;
import org.whizu.util.Objects;
import org.whizu.util.Strings;

/**
 * @author Rudy D'hauwe
 */
class JQueryMobileServer extends AbstractServer<JQueryMobile> {

	class Builder {

		private Class<JQueryMobile> appClass_;
		
		private String contextPath_;

		private RequestDispatcher dispatcher_;

		private String uri_;

		private Builder(String uri, Class<JQueryMobile> appClass, String contextPath, RequestDispatcher dispatcher) {
			uri_ = uri;
			appClass_ = appClass;
			contextPath_ = contextPath;
			dispatcher_ = dispatcher;
		}

		private RequestProcessor build() {
			String template = getTemplate();
			template = template.replace("${title}", getTitle());
			template = template.replace("${description}", getDescription());
			template = template.replace("${stylesheet}", getStylesheet());
			template = template.replace("${context-path}", contextPath_);
			
			String onLoad = uri_ + "/onLoad";
			template = template.replace("${onLoad}", onLoad);
			RequestProcessor ajaxServer = new JQueryMobileAjaxServer(appClass_);
			dispatcher_.addRequestProcessor(onLoad, ajaxServer);
			
			/*
			 * final String id = appClass_.getName(); content =
			 * content.replace("${id}", id);
			 * RequestImpl.get().session().addClickListener(new EventHandler() {
			 * @Override public void handleEvent() { app.init(new WhizuUI()); }
			 * @Override public String id() { return id; } }); 
			 * 
			 * 
			 * Expires expires = getAnnotation(Expires.class);
			 * String expires = factory.expires(); if (expires != null) {
			 * setExpires(response); }
			 */
			Resource document = new StringResource(template);
			RequestProcessor documentServer = new JQueryMobileDocumentServer(document);
			dispatcher_.addRequestProcessor(uri_, documentServer);
			
			return documentServer;
		}

		private <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
			return appClass_.getAnnotation(annotationClass);
		}

		private String getDescription() {
			Description description = getAnnotation(Description.class);
			return (description == null) ? DEFAULT_DESCRIPTION : description.value();
		}

		private String getStylesheet() {
			Stylesheet stylesheet = getAnnotation(Stylesheet.class);
			return (stylesheet == null) ? "" : Strings.format(STYLESHEET_PATTERN, stylesheet.value());
		}

		private String getTemplate() {
			Template template = getAnnotation(Template.class);
			String path = (template == null) ? DEFAULT_JQUERY_MOBILE_TEMPLATE : template.value();
			try {
				Resource resource = new ClassPathResource(path);
				return resource.getString();
			} catch (IOException e) {
				throw new ConfigurationException("Document template {} is missing on the classpath", path);
			}
		}

		private String getTitle() {
			Title title = getAnnotation(Title.class);
			return (title == null) ? DEFAULT_TITLE : title.value();
		}
	}

	public static final String DEFAULT_DESCRIPTION = "Whizu is jQuery Mobile in Java";

	private static final String DEFAULT_JQUERY_MOBILE_TEMPLATE = "/org/whizu/jquery/mobile/document.html";

	private static final String DEFAULT_TITLE = "Whizu";

	public static final String STYLESHEET_PATTERN = "<link rel='stylesheet' type='text/css' href='{}'/>";

	
	@Override
	public boolean accept(Class<?> clazz) {
		return JQueryMobile.class.isAssignableFrom(clazz);
	}

	@Override
	public RequestProcessor createRequestProcessor(String uri, Class<?> appClass, String contextPath,
			RequestDispatcher dispatcher) {
		Class<JQueryMobile> jqm = Objects.cast(appClass);
		return new Builder(uri, jqm, contextPath, dispatcher).build();
	}
}
