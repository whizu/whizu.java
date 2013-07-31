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
package org.whizu.widget;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.whizu.dom.Component;
import org.whizu.dom.Content;
import org.whizu.dom.ContentBuilder;
import org.whizu.dom.Decorator;
import org.whizu.dom.Element;
import org.whizu.dom.Identity;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;

/**
 * @author Rudy D'hauwe
 */
public abstract class Widget implements ContentBuilder, Component, Decorator {

	protected enum State {
		NEW, RENDERED
	}

	protected String id_;

	protected State state = State.NEW;

	private List<String> cssList = new ArrayList<String>();

	protected String width = null;

	private StringBuffer style = new StringBuffer();

	protected Widget() {
		id_ = session().next();
	}

	protected Widget(String id) {
		id_ = id;
	}

	public final void id(String id) {
		id_ = id;
	}

	public Component css(String clazz) {
		cssList.add(clazz);

		if (this.isRendered()) {
			jQuery(this).addClass(clazz);
		}

		return this;
	}

	public void style(String style) {
		this.style.append(style);
		if (!StringUtils.endsWith(style, ";")) {
			this.style.append(';');
		}

		if (this.isRendered()) {
			throw new UnsupportedOperationException();
		}
	}

	protected Request request() {
		return RequestContext.getRequest();
	}

	protected String getSelector() {
		return "$(\"#" + id() + "\")";
	}

	protected Session session() {
		return request().session();
	}

	@Override
	public String id() {
		return id_;
	}

	public final boolean isRendered() {
		return State.RENDERED.equals(state);
	}

	protected JQuery jQuery() {
		return request().select("$");
	}

	protected JQuery jQuery(Identity... components) {
		return request().select(components);
	}

	protected JQuery jQuery(String selector) {
		return request().select(selector);
	}

	@Override
	public final String render() {
		try {
			if (isRendered()) {
				throw new IllegalStateException("This component is already rendered: " + this);
			}
			Content markup = build();
			if (markup == null) {
				return "";
			} else if (markup.equals(this)) { // TODO unnecessary code?
				// (Component doesn't implement Content anymore)
				throw new IllegalStateException("Widget.create() must not return this - causes infinite loop");
			} else {
				return markup.render();
			}
		} finally {
			this.state = State.RENDERED;
		}
	}

	public void width(String width) {
		this.width = width;

		if (this.isRendered()) {
			jQuery(this).width(width);
		}
	}

	@Override
	public void decorate(Element element) {
		if (isRendered()) {
			throw new IllegalStateException("This component is already rendered: " + this);
		}

		element.css(cssList).width(width).style(style.toString());
	}

	protected void init() {
	}
}
