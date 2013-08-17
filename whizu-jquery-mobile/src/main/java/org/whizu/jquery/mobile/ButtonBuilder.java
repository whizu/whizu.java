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

import org.whizu.content.Content;
import org.whizu.content.Element;
import org.whizu.content.Identity;
import org.whizu.html.Html;
import org.whizu.jquery.ClickListener;
import org.whizu.jquery.ClickListenerImpl;
import org.whizu.jquery.JQuery;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;
import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
public final class ButtonBuilder extends ProxyBuilder<Button> {
	
	public static ButtonBuilder create() {
		return new ButtonBuilder();
	}
	
	public static ButtonBuilder createAnchorButton() {
		return create();
	}
	
	public static ButtonBuilder createAnchorButton(String title) {
		return createAnchorButton().title(title);
	}

	public static ButtonBuilder createButtonButton() {
		return new ButtonBuilder().type(ButtonType.BUTTON);
	}
	
	public static ButtonBuilder createInputButton() {
		return new ButtonBuilder().type(ButtonType.INPUT);
	}
	
	public static ButtonBuilder createInputButton(String title) {
		return createInputButton().title(title);
	}
	
	public static ButtonBuilder createResetButton() {
		return new ButtonBuilder().type(ButtonType.RESET);
	}
	
	public static ButtonBuilder createResetButton(String title) {
		return createResetButton().title(title);
	}

	public static ButtonBuilder createSubmitButton() {
		return new ButtonBuilder().type(ButtonType.SUBMIT);
	}
	
	public static ButtonBuilder createSubmitButton(String title) {
		return createSubmitButton().title(title);
	}
	
	public static ButtonBuilder createWithTitle(String title) {
		return new ButtonBuilder().title(title);
	}

	private Build build_ = new Build();

	private ButtonBuilder() {
	}

	@Override
	public Button build() {
		return buildOnce(new ButtonProxy(build_));
	}
	
	public ButtonBuilder disable() {
		build_.disable();
		return this;
	}

	public ButtonBuilder icon(DataIcon icon) {
		build_.icon(icon);
		return this;
	}
	
	public ButtonBuilder iconOnly(DataIcon icon) {
		build_.iconOnly(icon);
		return this;
	}
	
	public ButtonBuilder iconPosition(DataIconPosition position) {
		build_.iconPosition(position);
		return this;
	}

	public ButtonBuilder inline() {
		build_.inline(DataInline.TRUE);
		return this;
	}

	public ButtonBuilder mini() {
		build_.mini(DataMini.TRUE);
		return this;
	}
	
	public ButtonBuilder noIconShadow() {
		build_.iconShadow(DataIconShadow.FALSE);
		return this;
	}

	public ButtonBuilder noShadow() {
		build_.shadow(DataShadow.FALSE);
		return this;
	}

	public ButtonBuilder onClick(ClickListener listener) {
		build_.onClick(listener);
		return this;
	}

	public ButtonBuilder onClickOpen(Page page) {
		build_.onClickOpen(page);
		return this;
	}

	public ButtonBuilder onClickOpen(Panel panel) {
		build_.onClickOpen(panel);
		build_.dataRel(DataRel.PANEL);
		return this;
	}

	public ButtonBuilder onClickOpen(Popup popup) {
		build_.onClickOpen(popup);
		build_.dataRel(DataRel.POPUP);
		return this;
	}

	public ButtonBuilder onClickOpenDialog(Page page) {
		build_.onClickOpen(page);
		build_.dataRel(DataRel.DIALOG);
		return this;
	}

	public ButtonBuilder square() {
		build_.corners(DataCorners.SQUARE);
		return this;
	}

	public ButtonBuilder theme(Theme theme) {
		build_.theme(theme);
		return this;
	}

	public ButtonBuilder title(String title) {
		build_.title(title);
		return this;
	}

	public ButtonBuilder type(ButtonType type) {
		build_.type(type);
		return this;
	}

	/***************************************************************************
	 * The <code>Button</code> that is being built.
	 */
	final class Build extends BuildSupport implements Button {

		private DataCorners corners_;

		private DataDisabled disabled_;

		private DataIcon icon_;

		private DataIconPosition iconPosition_;

		private DataIconShadow iconShadow_;

		private DataInline inline_;

		private ClickListenerImpl listener_;

		private DataMini mini_;

		private Identity onClick_;

		private DataRel rel_;

		private DataShadow shadow_;

		private Theme theme_;

		private String title_;

		private ButtonType type_ = ButtonType.ANCHOR;

		private void addClickListener(ClickListener listener) {
			listener_ = new ClickListenerImpl(listener);
			session().addClickListener(listener_);
		}

		private Element applyCssIfDisabled(Element button) {
			if (DataDisabled.TRUE.equals(disabled_)) {
				button.css("ui-disabled");
			}
			return button;
		}

		private void assureExistsOnPage(Identity identity) {
			if (identity instanceof PanelProxy) {
				PanelProxy proxy = Objects.cast(identity);
				JQuery page = jQuery(this).closest("div[data-role=page]");
				proxy.assureExistsOnPage(page);
			}
			
			if (identity instanceof PopupProxy) {
				PopupProxy proxy = Objects.cast(identity);
				JQuery page = jQuery(this).closest("div[data-role=page]").find("div[data-role=content]");
				proxy.assureExistsOnPage(page);
			}
		}

		@Override
		public Content build() {
			// @formatter:off
			Element button = create().decorate(icon_, iconPosition_, theme_, mini_, rel_, inline_, corners_, shadow_, iconShadow_,	this);
			// @formatter:on

			if (onClick_ != null) {
				assureExistsOnPage(onClick_);
				button.href("#" + onClick_.id());
			}

			if (listener_ != null) {
				jQuery(this).click(listener_);
			}

			return button;
		}

		private void corners(DataCorners corners) {
			corners_ = corners;
		}

		private Element create() {
			switch (type_) {
				case ANCHOR :
					return applyCssIfDisabled(Html.a(this).decorate(DataRole.BUTTON).add(title_));
				case BUTTON :
					return Html.button(this).add(title_).decorate(disabled_);
				case ICON_ONLY :
					return applyCssIfDisabled(Html.a(this).decorate(DataRole.BUTTON, DataIconPosition.NO_TEXT));
				case INPUT :
					return Html.input(this).attr("type", "button").attr("value", title_).decorate(disabled_);
				case RESET :
					return Html.input(this).attr("type", "reset").attr("value", title_).decorate(disabled_);
				case SUBMIT :
					return Html.input(this).attr("type", "submit").attr("value", title_).decorate(disabled_);
				default :
					throw new IllegalArgumentException("Unsupported button type: " + type_);
			}
		}

		private void dataRel(DataRel dataRel) {
			rel_ = dataRel;
		}

		private void disable() {
			disabled_ = DataDisabled.TRUE;
		}

		private void icon(DataIcon icon) {
			icon_ = icon;
		}
		
		private void iconOnly(DataIcon icon) {
			icon_ = icon;
			iconPosition_ = DataIconPosition.NO_TEXT;
		}

		private void iconPosition(DataIconPosition position) {
			iconPosition_ = position;
		}

		private void iconShadow(DataIconShadow shadow) {
			iconShadow_ = shadow;
		}

		void inline(DataInline inline) {
			inline_ = inline;
		}
		
		private void mini(DataMini mini) {
			mini_ = mini;
		}

		private void onClick(ClickListener listener) {
			addClickListener(listener);
		}

		private void onClickOpen(Identity identity) {
			onClick_ = identity;
		}

		private void shadow(DataShadow shadow) {
			shadow_ = shadow;
		}

		private void theme(Theme theme) {
			theme_ = theme;
		}

		private void title(String title) {
			title_ = title;
		}

		// todo make private? see usage in Form
		protected void type(ButtonType type) {
			type_ = type;
		}
	}
}
