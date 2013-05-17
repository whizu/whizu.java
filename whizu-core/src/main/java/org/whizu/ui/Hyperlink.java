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
package org.whizu.ui;

import org.whizu.dom.Element;
import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.jquery.Function;
import org.whizu.jquery.RequestContext;
import org.whizu.js.JavaScript;
import org.whizu.widget.Widget;

public class Hyperlink extends Widget {

	private String caption;
	
	private ClickListenerImpl listener;

	public Hyperlink(final Action action) {
		this.caption = action.getCaption();
		addClickListener(new ClickListener() {
			
			@Override
			public void click() {
				action.handleEvent();
			}
		});
	}
	
	public Hyperlink(String caption, ClickListener listener) {
		this.caption = caption;
		addClickListener(listener);
	}

	public Hyperlink(String caption, Action action) {
		this.caption = caption;
		addClickListener(action);
	}

	@Override
	public Markup compile() {
		String id = RequestContext.getRequest().session().next();
		final Element anchor = Html.a().attr("href", "").add(caption).id(id);
		
		String script = "";
		if (listener != null) {
			jQuery(anchor).click(new Function() {
				@Override
				public void execute() {

					String url = "/whizu?id=" + listener.id();

					Function data = new Function() {

						@Override
						public void execute() {
							jQuery("$(this)").closest("form").serialize();
						}
					};

					Function callback = new Function("data") {

						@Override
						public void execute() {
						}
					};

					String type = "script";

					JavaScript.preventDefault();
					jQuery("$").get(url, data, callback, type);
				}
			});
		}

		if (!script.equals("")) {
			jQuery(this).concat(script); // TODO further refactoring (concat is
											// for internal use only)
		}
		
		return Html.div(id()).add(anchor);
	}

	private void addClickListener(ClickListener listener) {
		this.listener = new ClickListenerImpl(listener);
		getSession().addClickListener(this.listener);
	}
	
	private void addClickListener(final Action action) {
		ClickListener listener = new ClickListener() {
			
			@Override
			public void click() {
				action.handleEvent();
			}
		};
		addClickListener(listener);
	}
}
