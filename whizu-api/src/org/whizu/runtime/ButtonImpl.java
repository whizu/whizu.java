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
package org.whizu.runtime;

import org.whizu.html.NonVoid;
import org.whizu.jquery.Function;
import org.whizu.jquery.JQuery;
import org.whizu.ui.Button;
import org.whizu.ui.ClickListener;


/**
 * @author Rudy D'hauwe
 */
class ButtonImpl extends AbstractComponent implements Button {

	private String caption;

	private ClickListenerImpl listener;

	ButtonImpl(String caption) {
		this.caption = caption;
	}

	ButtonImpl(String name, ClickListener clickListener) {
		this(name);
		addClickListener(clickListener);
	}

	@Override
	public NonVoid create() {
		NonVoid markup = NonVoid.div(getId()).css(style).add(caption);
		JQuery jQuery = jQuery(this).button();

		if (listener != null) {
			jQuery.click(new Function() {
				public void execute() {
					String url = "http://localhost:8090/whizu?id=" + listener.getId();

					Function data = new Function() {
						@Override
						public void execute() {
							jQuery("$(this)").closest("form").serialize();
						}};
						
					Function callback = new Function("data") {
						@Override
						public void execute() {
						}
					};

					String type = "script";
					jQuery("$").get(url, data, callback, type);
				}
			});
		}

		return markup;
	}

	public void addClickListener(ClickListener listener) {
		if (this.listener != null) {
			throw new IllegalStateException();
		} else {
			this.listener = new ClickListenerImpl(listener);
			getSession().addClickListener(this.listener);
			if (!isRendered()) {
				// the getMarkup() method will do the rendering later
			} else {
				// render now immediately
				throw new UnsupportedOperationException();
				// jQuery.select(this).click(...);
			}
		}
	}

	@Override
	public Button css(String clazz) {
		setStyleName(clazz);
		return this;
	}
}
