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
package org.whizu.proxy;

import org.whizu.content.Content;
import org.whizu.content.ContentBuilder;
import org.whizu.content.Decorator;
import org.whizu.content.Element;
import org.whizu.content.Identity;
import org.whizu.content.JustInTime;
import org.whizu.content.Stylable;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;

/**
 * @author Rudy D'hauwe
 */
public abstract class BuildSupport implements ContentBuilder, Stylable, Decorator {

	private String id_;

	private StyleSupport style_ = new StyleSupport();

	protected BuildSupport() {
		id_ = session().next();
	}

	protected BuildSupport(String id) {
		id_ = id;
	}

	public final Content buildJustInTime() {
		return new JustInTime(this);
	}

	@Override
	public void css(String className) {
		style_.css(className);
	}

	@Override
	public final void decorate(Element element) {
		style_.decorate(element);
	}

	public final String id() {
		return id_;
	}

	public final void id(String id) {
		id_ = id;
	}

	protected final JQuery jQuery() {
		return request().select("$");
	}

	protected final JQuery jQuery(Identity... components) {
		return request().select(components);
	}

	protected final JQuery jQuery(String selector) {
		return request().select(selector);
	}

	/**
	 * @see {@link org.whizu.content.Content#render()}.
	 */
	public final String render() {
		return build().render();
	}

	protected final Request request() {
		return RequestContext.getRequest();
	}

	protected final String selector() {
		return "$(\"#" + id() + "\")";
	}

	protected final Session session() {
		return request().session();
	}
	
	@Override
	public final void style(String style) {
		style_.style(style);
	}
}
