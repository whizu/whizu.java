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

import org.whizu.annotation.Title;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
class PageFactory {

	// private Logger log = LoggerFactory.getLogger(PageFactory.class);

	private Class<Application> applicationClass;

	private String template = "/org/whizu/jquery/mobile/page.html";

	private String title = Title.DEFAULT_TITLE;

	private String stylesheet;

	public PageFactory(Class<Application> applicationClass) {
		this.applicationClass = applicationClass;
	}

	// public Resource createPage() {
	public Application createInstance() {
		try {
			return applicationClass.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getStylesheet() {
		return stylesheet;
	}

	public String getTemplate() {
		return template;
	}

	public String getTitle() {
		return title;
	}

	protected void setStylesheet(String stylesheet) {
		this.stylesheet = stylesheet;
	}

	protected void setTemplate(String template) {
		this.template = template;
	}

	protected void setTitle(String title) {
		this.title = title;
	}
}
