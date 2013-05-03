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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.whizu.dom.Content;
import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.jquery.Function;
import org.whizu.value.IntegerValue;
import org.whizu.value.Value;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
public class LabelImpl extends Widget implements Label {

	private ClickListenerImpl listener;

	private String text;

	private Value value;

	public LabelImpl(String text) {
		this.text = text;
	}

	public LabelImpl(String text, ClickListener clickListener) {
		this(text);
		addClickListener(clickListener);
	}

	public LabelImpl(Value value) {
		this(value.toString());
		this.value = value;
		value.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				text = LabelImpl.this.value.toString();
				jQuery(LabelImpl.this).empty().append(text);
			}
		});
	}

	public LabelImpl(String t, Content arg) {
		// AbstractWidget impl = (AbstractWidget) arg;
		t = t.replace("$1", arg.render());
		this.text = t;
	}

	public LabelImpl(String text, IntegerValue arg) {
		Label valueLabel = new LabelImpl(arg);
		valueLabel.css("teller");
		text = text.replace("$1", valueLabel.render());
		this.text = text;
	}

	@Override
	public Label addClickListener(ClickListener listener) {
		this.listener = new ClickListenerImpl(listener);
		getSession().addClickListener(this.listener);
		return this;
	}

	@Override
	public Markup compile() {
		String script = "";
		// JQuery jQuery = jQuery(this); //Don't do this, this generates a
		// jQuery selection of this without any function on it

		if (listener != null) {
			jQuery(this).click(new Function() {
				@Override
				public void execute() {

					String url = "/whizu?id=" + listener.getId();

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

					jQuery("$").get(url, data, callback, type);
				}
			});
		}

		if (!script.equals("")) {
			jQuery(this).concat(script); // TODO further refactoring (concat is
											// for internal use only)
		}

		// isRendered = true;
		return Html.div(this).decorate(this).add(text);
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		// if (this.value != null) value.setValue(text);
		this.text = text;
		// value changed notification
		if (isRendered()) {
			// String script = ".html('";
			// + StringEscapeUtils.escapeJavaScript(text) + "');"; //TODO check
			jQuery(this).html(text);
		}
	}

	@Override
	public void toggle() {
		if (isRendered()) {
			jQuery(this).toggle();
		}
	}

	@Override
	public Label css(String clazz) {
		return (Label) super.css(clazz);
	}
}
