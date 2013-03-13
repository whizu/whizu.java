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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.whizu.html.NonVoid;
import org.whizu.script.Function;
import org.whizu.script.JQuery;
import org.whizu.ui.ClickListener;
import org.whizu.ui.Label;
import org.whizu.value.StringValue;

class LabelImpl extends ComponentImpl implements Label {

	private ClickListenerImpl listener;

	private String text;

	private StringValue value;

	LabelImpl(String text) {
		this.text = text;
	}

	public LabelImpl(String text, ClickListener clickListener) {
		this(text);
		addClickListener(clickListener);
	}

	public LabelImpl(StringValue value) {
		this(value.getValue());
		this.value = value;
		value.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				text=LabelImpl.this.value.getValue();
				jQuery(LabelImpl.this).empty().append(text);
			}
		});
	}

	public void addClickListener(ClickListener listener) {
		//System.out.println("THIS LABEL " + text + " is rendered: " + isRendered);
		//if not rendered, remember that this script must be added after rendering !
		//nu gebeurt het in de verkeerde volgorde!
		this.listener = new ClickListenerImpl(listener);
		getSession().addClickListener(this.listener);
	}

	public NonVoid create() {
		String script = "";
		JQuery jQuery = jQuery(this);
		
		if (listener != null) {
			jQuery.click(new Function() {
				public void execute() {

					String url = "http://localhost:8090/ajax?id=" + listener.getId();

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
		
		if (!script.equals("")) {
			jQuery(this).concat(script); //TODO further refactoring (concat is for internal use only)
		}
		
		//isRendered = true;
		return NonVoid.div(getId()).css(style).add(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		//if (this.value != null) value.setValue(text);
		this.text = text;
		// value changed notification
		if (isRendered()) {
//			String script = ".html('";
//					+ StringEscapeUtils.escapeJavaScript(text) + "');"; //TODO check
			//System.out.println("run script " + script);
			jQuery(this).html(text);
		}
	}

	@Override
	public void toggle() {
		if (isRendered()) {
			jQuery(this).toggle();
		}
	}

	public Label css(String clazz) {
		setStyleName(clazz);
		return this;
	}
}
