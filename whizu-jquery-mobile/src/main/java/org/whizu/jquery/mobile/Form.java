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
package org.whizu.jquery.mobile;

import org.whizu.dom.Component;
import org.whizu.dom.Content;
import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.jquery.Function;
import org.whizu.jquery.Input;
import org.whizu.js.JavaScript;
import org.whizu.ui.ClickListener;
import org.whizu.ui.ClickListenerImpl;
import org.whizu.value.DateValue;
import org.whizu.value.PasswordValue;
import org.whizu.value.StringValue;
import org.whizu.widget.Container;

/**
 * @author Rudy D'hauwe
 */
public class Form extends Container {

	/*
	 * @Override public Form css(String clazz) { setStyleName(clazz); return
	 * this; }
	 */

	private ClickListenerImpl handler_;

	public Form add(Content field) {
		jQuery(this).append(field);
		return this;
	}

	public void add(PasswordValue value) {
		throw new UnsupportedOperationException();
	}

	public Button addButton(String title) {
		Button button = new Button(title);
		add(button);
		return button;
	}

	public void addDate(DateValue date) {
		add(new DateField(date));
	}

	public void addFieldContain(Component component) {
		FieldContain fc = new FieldContain();
		fc.add(component);
		add(fc);
	}

	public void addFlipSwitch() {
		Content field = new FlipSwitch();
		add(field);
	}

	public void addListView() {
		Content list = new ListView();
		add(list);
	}

	public void addSlider(int min, int max) {
		Content slider = new Slider(min, max);
		add(slider);
	}

	public void addSlider(int min, int max, Theme theme) {
		Content slider = new Slider(min, max, theme);
		add(slider);
	}

	/**
	 * 
	 */
	private void addSubmitHandler() {
		if (handler_ != null) {
			Function f = new Function() {

				@Override
				public void execute() {
					JavaScript.preventDefault();
					//JavaScript.alert("submit form");
					
					String url = "http://localhost:8090/whizu?id=" + handler_.id();

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
					
					JavaScript.script("return false;");
				}
			};
			
			jQuery(this).submit(f);
		}
	}

	public void addText(String label) {
		Content text = new Text(label);
		add(text);
	}

	public void addText(StringValue value) {
		addText(value, false);
	}

	public void addText(StringValue value, boolean fieldContain) {
		if (fieldContain) {
			FieldContain fc = new FieldContain();
			Text text = new Text(value);
			fc.add(text);
			add(fc);
		} else {
			Text text = new Text(value);
			add(text);
		}
	}

	public void addTextarea(String label) {
		Content text = new Textarea(label);
		add(text);
	}

	public void addTextarea(StringValue value) {
		addTextarea(value, false);
	}

	public void addTextarea(StringValue value, boolean fieldContain) {
		if (fieldContain) {
			FieldContain fc = new FieldContain();
			Textarea text = new Textarea(value);
			fc.add(text);
			add(fc);
		} else {
			Textarea text = new Textarea(value);
			add(text);
		}
	}

	public void clear() {
		for (Content c : componentList) {
			if (c instanceof Input) {
				Input i = (Input) c;
				i.clear();
			}
		}
	}

	@Override
	public Markup compile() {
		// @formatter:off
		Markup markup = Html.form(this)
			.attr("method", "post")
			//.attr("action", "form.php")
			.decorate(this)
			.add(componentList);
		// @formatter:on
		addSubmitHandler();

		return markup;
	}

	public void onSubmit(ClickListener handler) {
		handler_ = new ClickListenerImpl(handler);
		getSession().addClickListener(handler_);
	}
}
