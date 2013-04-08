/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the �EUPL�) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an �AS IS� basis and 
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
package org.whizu.runtime;

import org.whizu.html.Html;
import org.whizu.html.NonVoid;
import org.whizu.html.Renderable;
import org.whizu.jquery.Identity;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;
import org.whizu.ui.Component;

/**
 * @author Rudy D'hauwe
 */
public abstract class AbstractComponent implements Component, Renderable, Identity {

	private final String id;

	private boolean rendered = false;

	protected String style = null;

	protected String width = null;

	protected AbstractComponent() {
		this.id = getSession().next();
	}

	public abstract Html create();

	protected NonVoid div(AbstractComponent element) {
		return NonVoid.div(element.getId());
	}
	
	protected NonVoid input(AbstractComponent element) {
		return NonVoid.input(element.getId());
	}
	
	protected NonVoid button(AbstractComponent element) {
		return NonVoid.button(element.getId());
	}
	
	protected NonVoid a(AbstractComponent element) {
		return NonVoid.a(element.getId());
	}

	public NonVoid div(Identity identity) {
		return NonVoid.div(identity.getId());
	}

	public String getId() {
		return id;
	}

	public final String getMarkup() {
		try {
			return render().toString();
		} finally {
			setRendered(true);
		}
	}

	public Request getRequest() {
		return RequestContext.getRequest();
	}
	
	protected String getSelector() {
		return "$(\"#" + getId() + "\")";
	}

	public Session getSession() {
		return getRequest().getSession();
	}

	public boolean isRendered() {
		return rendered;
	}

	public JQuery jQuery() {
		return getRequest().select("$");
	}

	public JQuery jQuery(Identity... components) {
		return getRequest().select(components);
	}
	
	public JQuery jQuery(String selector) {
		return getRequest().select(selector);
	}

	public final Html render() {
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
	
	public void setWidth(String width) {
		this.width = width;
	}
}