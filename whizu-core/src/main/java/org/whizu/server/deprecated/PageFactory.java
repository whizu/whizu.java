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
package org.whizu.server.deprecated;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.annotation.Description;
import org.whizu.annotation.Title;
import org.whizu.annotation.processing.Html;
import org.whizu.annotation.processing.Markdown;
import org.whizu.annotation.processing.Support;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
@Deprecated
public class PageFactory implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(PageFactory.class);

	private final Class<Application> applicationClass_;

	private String description_ = Description.DEFAULT_VALUE;

	private String expires_ = null;

	private String stylesheet_;

	private String template_ = "/org/whizu/jquery/mobile/page.html";

	private String title_ = Title.DEFAULT_TITLE;

	public PageFactory(Class<Application> applicationClass) {
		applicationClass_ = applicationClass;
	}

	// public Resource createPage() {
	public Application createInstance() {
		try {
			Application application = applicationClass_.newInstance();
			log.debug("PageFactory.createInstance() of Class {}", applicationClass_);
			Field[] fields = applicationClass_.getDeclaredFields();
			Support.update();
			for (Field field : fields) {
				log.debug("Field {} has annotation @Html: {}", field.getName(), field.isAnnotationPresent(Html.class));
				if ((field.isAnnotationPresent(Html.class)) || (field.isAnnotationPresent(Markdown.class))) {
					field.setAccessible(true);
					field.set(application, Support.getValue(applicationClass_, field.getName()));
				}
			}

			return application;
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	public String description() {
		return description_;
	}

	public void description(Description description) {
		description_ = (description == null) ? Description.DEFAULT_VALUE : description.value();
	}

	public String expires() {
		return expires_;
	}
	
	public void expires(String expires) {
		expires_ = expires;
	}

	public String stylesheet() {
		return stylesheet_;
	}

	protected void stylesheet(String stylesheet) {
		stylesheet_ = stylesheet;
	}

	public String template() {
		return template_;
	}

	protected void template(String template) {
		if (template != null) {
			template_ = template;
		}
	}

	public String title() {
		return title_;
	}

	protected void title(String title) {
		title_ = title;
	}
}
