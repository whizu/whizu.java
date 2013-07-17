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
import org.whizu.jquery.mobile.Icon;
import org.whizu.jquery.mobile.Mini;
import org.whizu.jquery.mobile.Theme;
import org.whizu.js.JavaScript;
import org.whizu.widget.Widget;

/**
 * Buttons are core widgets in jQuery Mobile and are used within a wide range of
 * other plugins. The button markup is flexible and can be created from links or
 * form buttons.
 * 
 * @author Rudy D'hauwe
 */
public class ButtonBuilder extends Widget {

	// private Log log = LogFactory.getLog(Button.class);

	private String title;

	public enum Type {
		INPUT, BUTTON, SUBMIT, RESET, ANCHOR, ICON_ONLY;
	};

	public enum Inline {
		TRUE("true"), FALSE("false");

		protected String value;

		Inline(String value) {
			this.value = value;
		}
	};

	private Type type = Type.INPUT;

	private Inline inline = Inline.FALSE;

	private Mini mini = Mini.FALSE;

	private Theme theme = Theme.C;

	private Icon icon;

	private Page onClick;

	public ButtonBuilder(String title) {
		this.title = title;
	}

	public ButtonBuilder(String title, Type type, Inline inline, Mini mini) {
		this.title = title;
		this.type = type;
		this.inline = inline;
		this.mini = mini;
	}

	/*
	 * @Override public Button css(String clazz) { return (Button)
	 * super.css(clazz); }
	 */

	public ButtonBuilder icon(Icon icon) {
		this.icon = icon;
		return this;
	}

	@Override
	public Markup compile() {
		//jQuery(this).trigger("create");
		if (onClick != null) {
			return Html.a(this).attr("data-role", "button").attr("data-inline", inline.value)
					.attr("data-mini", mini.value).attr("href", "#" + onClick.id()).add(title);
		} else {

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

	private void addClickListener() {
		JQuery jQuery = jQuery(this);

		// if (listener != null) {
		jQuery.click(new Function() {
			@Override
			public void execute() {
				JavaScript.preventDefault();

				String url = "http://localhost:8090/whizu?id=" + ButtonBuilder.this.id(); // listener.id();

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

	public ButtonBuilder onClick(Page page) {
		this.onClick = page;
		return this;
	}

	public void appendTo(Page page) {
		((PageSelector) page).append(this);
	}
}