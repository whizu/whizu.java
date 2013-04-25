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

import org.whizu.annotation.Autowire;
import org.whizu.context.Ctx;
import org.whizu.dom.Component;
import org.whizu.dom.Decorator;
import org.whizu.dom.Element;
import org.whizu.dom.Identity;
import org.whizu.dom.Markup;
import org.whizu.jquery.Function;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;
import org.whizu.js.Script;
import org.whizu.value.Value;
import org.whizu.value.ValueRenderer;
import org.whizu.value.ValueRendererImpl;

/**
 * @author Rudy D'hauwe
 */
public abstract class Widget implements Component, Decorator {

	private enum State {
		NEW, RENDERED
	}

	private final String id;

	private State state = State.NEW;

	private List<String> cssList = new ArrayList<String>();

	protected String width = null;
	
	//@Autowired
	@Autowire
	public ValueRenderer renderer = new ValueRendererImpl();

	protected Widget() {
		this.autowire();
		this.id = getSession().next();
	}

	private void autowire() {
		Ctx.autowire(this);
	}

	@Override
	public Component css(String clazz) {
		cssList.add(clazz);

		if (this.isRendered()) {
			jQuery(this).addClass(clazz);
		}

		return this;
	}
	
	@Override
	public void style(String style) {
		// TODO Auto-generated method stub
		
	}

	protected Request getRequest() {
		return RequestContext.getRequest();
	}

	protected String getSelector() {
		return "$(\"#" + id() + "\")";
	}

	protected Session getSession() {
		return getRequest().getSession();
	}

	/**
	 * Initial creation and rendering of this widget by compiling it into a
	 * combination of static HTML markup and javascript.
	 */
	// protected abstract Markup compile();

	@Override
	public String id() {
		return id;
	}

	public final boolean isRendered() {
		return State.RENDERED.equals(state);
	}

	protected JQuery jQuery() {
		return getRequest().select("$");
	}

	protected JQuery jQuery(Identity... components) {
		return getRequest().select(components);
	}

	protected JQuery jQuery(String selector) {
		return getRequest().select(selector);
	}

	@Override
	public final String render() {
		try {
			if (isRendered()) {
				throw new IllegalStateException("This component is already rendered: " + this);
			} else {
				Markup markup = compile();
				if (markup == null) {
					return "";
				} else if (markup.equals(this)) { // TODO unnecessary code?
					// (Component doesn't implement Markup anymore)
					throw new IllegalStateException("Widget.create() must not return this - causes infinite loop");
				} else {
					return markup.render();
				}
			}
		} finally {
			this.state = State.RENDERED;
		}
	}

	@Override
	public void width(String width) {
		this.width = width; 
		
		if (this.isRendered()) {
			jQuery(this).width(width);
		}
	}

	public final Script compile(Function function) {
		return getRequest().compile(function);
	}
	
	protected final Component compile(Value value) {
		return value.render(renderer);
	}

	@Override
	public void decorate(Element element) {
		if (isRendered()) {
			throw new IllegalStateException("This component is already rendered: " + this);
		}

		element.css(cssList).width(width);
	}

	protected void init() {
	}
}
