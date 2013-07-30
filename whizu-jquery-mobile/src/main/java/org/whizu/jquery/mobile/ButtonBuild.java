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

import org.whizu.dom.Content;
import org.whizu.dom.Element;
import org.whizu.html.Html;
import org.whizu.jquery.ClickListener;
import org.whizu.jquery.ClickListenerImpl;
import org.whizu.jquery.JQuery;
import org.whizu.widget.Widget;

/**
 * Buttons are core widgets in jQuery Mobile and are used within a wide range of
 * other plugins. The button markup is flexible and can be created from links or
 * form buttons.
 * 
 * @author Rudy D'hauwe
 */
final class ButtonBuild extends Widget implements Button {

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
	}

	private DataRel dataRel_;

	private Icon icon;

	private Inline inline = Inline.FALSE;

	private ClickListenerImpl listener_;

	public Mini mini = Mini.FALSE;

	private Page onClick_;

	private Popup onClickPopup_;

	private Theme theme_; // = Theme.C;

	private String title;

	private Type type_ = Type.INPUT;

	private Panel onClickPanel_;

	public ButtonBuild(String title) {
		this.title = title;
	}

	public ButtonBuild(String title, Type type, Inline inline, Mini mini) {
		this.title = title;
		this.type_ = type;
		this.inline = inline;
		this.mini = mini;
	}

	private void addClickListener() {
		if (listener_ != null) {
			jQuery(this).click(listener_);
		} else {
			//???? Button.this.id() ???
		}
	}

	private void addClickListener(ClickListener listener) {
		listener_ = new ClickListenerImpl(listener);
		getSession().addClickListener(listener_);
	}

	@Override
	public Content build() {
		// jQuery(this).trigger("create");
		if (onClick_ != null) {
			return Html.a(this).attr("data-role", "button").attr("data-inline", inline.value)
					.attr("data-mini", mini.value).decorate(dataRel_).attr("href", "#" + onClick_.id()).add(title);
		} else if (onClickPopup_ != null) {
			return Html.a(this).attr("data-role", "button").attr("data-inline", inline.value)
					.decorate(icon, theme_, mini, this).attr("data-rel", "popup")
					.attr("href", "#" + onClickPopup_.id()).add(title);
		} else if (onClickPanel_ != null) {
			assureCreated((PanelProxy) onClickPanel_);
			return Html.a(this).attr("data-role", "button").attr("data-inline", inline.value)
					.decorate(icon, theme_, mini, this).attr("data-rel", "panel")
					.attr("href", "#" + onClickPanel_.id()).add(title);
		} else {

			switch (type_) {
			case INPUT:
				Element button = Html.input(this).attr("type", "button").attr("value", title)
						.attr("data-inline", inline.value);
				button.decorate(icon, theme_, mini, this);
				addClickListener();
				return button;
			case SUBMIT:
				Element submit = Html.input(this).attr("type", "submit").attr("value", title)
						.attr("data-inline", inline.value).attr("data-mini", mini.value);
				submit.decorate(icon, theme_, mini);
				return submit;
			case RESET:
				return Html.input(this).attr("type", "reset").attr("value", title).attr("data-inline", inline.value)
						.attr("data-mini", mini.value);
			case BUTTON:
				return Html.button(this).attr("data-inline", inline.value).attr("data-mini", mini.value).add(title);
			case ANCHOR:
				Element anchor = Html.a(this).attr("data-role", "button").attr("data-inline", inline.value)
						.attr("data-mini", mini.value).add(title);
				anchor.decorate(icon, theme_, mini, this);
				addClickListener();
				return anchor;
			default:
				throw new IllegalArgumentException("Unsupported button type: " + type_);
			}
		}
	}

	private void assureCreated(PanelProxy panel) {
		JQuery page = jQuery(this).closest("div[data-role=page]");
		panel.assureCreatedOn(page);
	}

	public void dataRel(DataRel dataRel) {
		dataRel_ = dataRel;
	}

	public ButtonBuild icon(Icon icon) {
		this.icon = icon;
		return this;
	}

	public void onClick(ClickListener listener) {
		addClickListener(listener);
	}

	public ButtonBuild onClick(Page page) {
		onClick_ = page;
		return this;
	}

	public ButtonBuild onClick(Popup popup) {
		onClickPopup_ = popup;
		return this;
	}

	public void theme(Theme theme) {
		theme_ = theme;
	}

	public void type(Type type) {
		type_ = type;
	}

	public void onClickOpen(Panel panel) {
		onClickPanel_ = panel;
	}
}