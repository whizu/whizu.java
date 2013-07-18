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

import org.whizu.dom.Element;
import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.jquery.Function;
import org.whizu.jquery.JQuery;
import org.whizu.js.JavaScript;
import org.whizu.ui.ClickListener;
import org.whizu.ui.ClickListenerImpl;
import org.whizu.widget.Widget;

/**
 * Buttons are core widgets in jQuery Mobile and are used within a wide range of
 * other plugins. The button markup is flexible and can be created from links or
 * form buttons.
 * 
 * @author Rudy D'hauwe
 */
public class Button extends Widget {

	// private Log log = LogFactory.getLog(Button.class);

	public enum Inline {
		FALSE("false"), TRUE("true");

		protected String value;

		Inline(String value) {
			this.value = value;
		}
	}

	public enum Type {
		ANCHOR, BUTTON, ICON_ONLY, INPUT, RESET, SUBMIT;
	};

	public static ButtonBuilder builder(String title) {
		return new ButtonBuilder(title);
	};

	private Icon icon;

	private Inline inline = Inline.FALSE;

	public Mini mini = Mini.FALSE;

	private Page onClick_;

	private Popup onClickPopup_;

	private Theme theme = Theme.C;

	private String title;

	private Type type = Type.INPUT;

	private ClickListenerImpl listener_;

	public Button(String title) {
		this.title = title;
	}

	/*
	 * @Override public Button css(String clazz) { return (Button)
	 * super.css(clazz); }
	 */

	public Button(String title, Type type, Inline inline, Mini mini) {
		this.title = title;
		this.type = type;
		this.inline = inline;
		this.mini = mini;
	}

	private void addClickListener() {
		JQuery jQuery = jQuery(this);

		// if (listener != null) {
		jQuery.click(new Function() {
			@Override
			public void execute() {
				JavaScript.preventDefault();

				String listenerId = (listener_ == null) ? Button.this.id() : listener_.id();
				String url = "http://localhost:8090/whizu?id=" + listenerId;

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
		// }
	}

	private void addClickListener(ClickListener listener) {
		listener_ = new ClickListenerImpl(listener);
		getSession().addClickListener(listener_);
	}

	public void appendTo(Page page) {
		((PageSelector) page).append(this);
	}

	@Override
	public Markup compile() {
		//jQuery(this).trigger("create");
		if (onClick_ != null) {
			return Html.a(this).attr("data-role", "button").attr("data-inline", inline.value)
					.attr("data-mini", mini.value).attr("href", "#" + onClick_.id()).add(title);
		} else if (onClickPopup_ != null) {
			return Html.a(this).attr("data-role", "button").attr("data-inline", inline.value)
					.attr("data-mini", mini.value).attr("data-rel", "popup").attr("href", "#" + onClickPopup_.id()).add(title);
		}
		else {

			switch (type) {
			case INPUT:
				Element button = Html.input(this).attr("type", "button").attr("value", title)
						.attr("data-inline", inline.value);
				button.decorate(icon, theme, mini, this);
				addClickListener();
				return button;
			case SUBMIT:
				Element submit = Html.input(this).attr("type", "submit").attr("value", title)
						.attr("data-inline", inline.value).attr("data-mini", mini.value);
				submit.decorate(icon, theme, mini);
				return submit;
			case RESET:
				return Html.input(this).attr("type", "reset").attr("value", title).attr("data-inline", inline.value)
						.attr("data-mini", mini.value);
			case BUTTON:
				return Html.button(this).attr("data-inline", inline.value).attr("data-mini", mini.value).add(title);
			case ANCHOR:
				return Html.a(this).attr("data-role", "button").attr("data-inline", inline.value)
						.attr("data-mini", mini.value).add(title);
			default:
				throw new IllegalArgumentException("Unsupported button type: " + type);
			}
		}
	}

	public Button icon(Icon icon) {
		this.icon = icon;
		return this;
	}

	public <T extends ClickListener> void onClick(Class<T> listenerClass) {
		listener_ = new ClickListenerImpl(listenerClass);
		getSession().addClickListener(listener_);		
	}
	
	public void onClick(ClickListener listener) {
		addClickListener(listener);
	}

	public Button onClick(Page page) {
		onClick_ = page;
		return this;
	}

	public Button onClick(Popup popup) {
		onClickPopup_ = popup;
		return this;
	}
}
