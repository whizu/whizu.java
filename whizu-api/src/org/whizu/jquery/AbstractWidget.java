/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the “EUPL”) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an “AS IS” basis and 
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
package org.whizu.jquery;

import org.whizu.content.Content;
import org.whizu.content.Decorator;
import org.whizu.content.Element;
import org.whizu.content.Identity;
import org.whizu.content.Component;
import org.whizu.html.Html;

/**
 * @author Rudy D'hauwe
 */
public abstract class AbstractWidget implements Component /*,  Renderable, Identity*/ {

	private final String id;

	private boolean rendered = false;

	protected String style = null;

	protected String width = null;

	protected AbstractWidget() {
		this.id = getSession().next();
	}

	protected Element a(AbstractWidget element) {
		return Html.a(element.getId());
	}

	protected Element button(AbstractWidget element) {
		return Html.button(element.getId());
	}
	
	public abstract Content create();
	
	protected <T extends Element> T decorate(T element, Decorator... decorators) {
		for (Decorator d : decorators) {
			if (d != null) {
				d.decorate(element);
			}
		}
		return element;
	}
	
	protected Element div(AbstractWidget element) {
		return Html.div(element.getId());
	}
	
	protected Element div(Identity identity) {
		return Html.div(identity.getId());
	}
	
	protected Element form(AbstractWidget element) {
		return Html.form(element.getId());
	}

	public String getId() {
		return id;
	}

	protected Request getRequest() {
		return RequestContext.getRequest();
	}

	protected String getSelector() {
		return "$(\"#" + getId() + "\")";
	}
	
	protected Session getSession() {
		return getRequest().getSession();
	}

	protected Element input(AbstractWidget element) {
		return Html.input(element.getId());
	}
	
	protected Element select(AbstractWidget element) {
		return Html.select(element.getId());
	}

	public boolean isRendered() {
		return rendered;
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

	public final Content render() {
		//return NonVoid.div(getId());
		if (isRendered()) {
			throw new IllegalStateException("This component is already rendered: " + this);
		} else {
			try {
				return create();
			} finally {
				setRendered(true);
			}
		}
	}

	private void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public void setStyleName(String style) {
		this.style = style;
	}
	
	public void width(String width) {
		this.width = width;
	}
	
	protected Element textarea(AbstractWidget element) {
		return Html.textarea(element.getId());
	}

	@Override
	public String stream() {
		try {
			return render().stream();
		} finally {
			setRendered(true);
		}
	}
}
