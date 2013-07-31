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
import org.whizu.html.Html;
import org.whizu.jquery.ClickListener;
import org.whizu.jquery.ClickListenerImpl;
import org.whizu.jquery.Input;
import org.whizu.proxy.Proxy;
import org.whizu.util.Objects;
import org.whizu.value.DateValue;
import org.whizu.value.PasswordValue;
import org.whizu.value.StringValue;
import org.whizu.value.Value;
import org.whizu.widget.Container;

/**
 * @author Rudy D'hauwe
 */
public class Form extends Container {

	private ClickListenerImpl handler_;
	
	private ValueRenderer valueRenderer_ = new ValueRenderer();

	public Form add(Content field) {
		jQuery(this).append(field);
		return this;
	}

	public void add(PasswordValue value) {
		throw new UnsupportedOperationException();
	}

	public Button addButton(String title) {
		Button button = ButtonBuilder.createWithTitle(title).build();
		add(button);
		return button;
	}

	public void addDate(DateValue date) {
		add(new DateField(date));
	}

	public void addField(Value value) {
		Content view = valueRenderer_.visit(value);
		add(view);
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

	private void addSubmitHandler() {
		jQuery(this).submit(handler_);
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
	public Content build() {
		// @formatter:off
		Content markup = Html.form(this)
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
		session().addClickListener(handler_);
	}

	/**
	 * @param submit
	 */
	public void addButton(Button submit) {
		Proxy<Button> proxy = Objects.cast(submit);
		ButtonBuilder.Build build = Objects.cast(proxy.impl());
		build.type(ButtonType.SUBMIT);
		super.add(submit);
	}
}
