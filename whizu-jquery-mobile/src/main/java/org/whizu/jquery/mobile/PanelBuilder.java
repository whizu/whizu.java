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
import org.whizu.content.ContentList;
import org.whizu.html.Html;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class PanelBuilder extends ProxyBuilder<Panel, PanelBuilder.Build> {

	public static PanelBuilder createWithId(String id) {
		return new PanelBuilder(id);
	}

	private PanelBuilder(String id) {
		build_.id(id);
	}

	public PanelBuilder add(Content content) {
		build_.add(content);
		return this;
	}

	@Override
	protected Build createPrototype() {
		return new Build();
	}

	@Override
	protected Panel createProxy(Build build) {
		return new PanelProxy(build);
	}

	/**
	 * Panels will by default animate if the browser supports 3D transforms. Call
	 * this method to turn off panel animations for all devices, by setting the
	 * "data-animate" attribute of this panel to "false".
	 */
	public PanelBuilder disableAnimations() {
		build_.disableAnimations(DataAnimate.FALSE);
		return this;
	}

	/**
	 * By default, panels can also be closed by clicking outside the panel onto the
	 * page contents. Call this method to prevent this behavior, by setting the
	 * "data-dismissible" attribute of the panel to "false". It's possible to have
	 * the panel and page sit side-by-side at wider screen widths and prevent the
	 * click-out-to-close behavior only above a certain screen width by applying a
	 * media query. See the responsive section in the documentation for details.
	 */
	public PanelBuilder disableClickOutsideToClose() {
		build_.disableClickOutsideToClose(DataDismissible.FALSE);
		return this;
	}

	/**
	 * Clicking the link that opened the panel, swiping left or right, or tapping
	 * the Esc key will close the panel. The swipe-to-close behavior can be
	 * disabled by setting the "data-swipe-close" panel attribute to "false".
	 */
	public PanelBuilder disableSwipeToClose() {
		build_.swipeToClose(DataSwipeToClose.FALSE);
		return this;
	}

	/**
	 * Panels will by default be displayed with the "position:absolute" CSS
	 * property, meaning it will scroll with the page. When a panel is opened the
	 * framework checks to see if the bottom of the panel contents is in view. If
	 * not, it scrolls to the top of the page. Call this method to set a panel to
	 * CSS property "position:fixed", so its contents will appear no matter how far
	 * down the page you're scrolled. The "data-position-fixed" attribute of the
	 * panel will be set to "true".
	 */
	public PanelBuilder fixPosition() {
		build_.position(DataPositionFixed.TRUE);
		return this;
	}

	/**
	 * The position of the panel on the screen is set by the "data-position"
	 * attribute. The default value of the attribute is "left", meaning it will
	 * appear from the left edge of the screen.
	 */
	public PanelBuilder left() {
		build_.position(DataPosition.LEFT);
		return this;
	}

	/**
	 * The display mode of the panel is set by the "data-display" attribute.
	 * Setting the value of the attribute to "overlay", meaning the panel will
	 * apear on top of the page contents.
	 */
	public PanelBuilder overlay() {
		build_.display(DataDisplay.OVERLAY);
		return this;
	}

	/**
	 * The display mode of the panel is set by the "data-display" attribute.
	 * Setting the value of the attribute to "push", meaning both the panel and the
	 * page will be animated at the same time.
	 */
	public PanelBuilder push() {
		build_.display(DataDisplay.PUSH);
		return this;
	}

	/**
	 * The display mode of the panel is set by the "data-display" attribute. The
	 * value of the attribute defaults to "reveal", meaning the panel will sit
	 * under the page and reveal as the page slides away.
	 */
	public PanelBuilder reveal() {
		build_.display(DataDisplay.REVEAL);
		return this;
	}

	/**
	 * The position of the panel on the screen is set by the "data-position"
	 * attribute. Setting the value of the attribute to "right", meaning it will
	 * appear from the right edge of the screen.
	 */
	public PanelBuilder right() {
		build_.position(DataPosition.RIGHT);
		return this;
	}

	/***************************************************************************
	 * The <code>Panel</code> that is being built.
	 */
	final class Build extends BuildSupport implements Panel {

		private DataAnimate animate_;

		private ContentList contents_ = new ContentList();

		private DataDismissible dismissible_;

		private DataDisplay display_;

		private DataPosition position_;

		private DataPositionFixed positionFixed_;

		private DataSwipeToClose swipeToClose_;

		@Override
		public Content build() {
			// @formatter:off
			return Html.div(this)
					.decorate(DataRole.PANEL)
					.decorate(animate_, dismissible_, display_, position_, positionFixed_, swipeToClose_)
					.add(contents_);
			// @formatter:off
		}

		@Override
		public void add(Content content) {
			contents_.add(content);
		}

		private void disableAnimations(DataAnimate animate) {
			animate_ = animate;
		}
		
		private void disableClickOutsideToClose(DataDismissible dismissible) {
			dismissible_ = dismissible;
		}

		private void display(DataDisplay display) {
			display_ = display;
		}

		private void position(DataPosition position) {
			position_ = position;
		}

		private void position(DataPositionFixed positionFixed) {
			positionFixed_ = positionFixed;
		}

		private void swipeToClose(DataSwipeToClose swipeToClose) {
			swipeToClose_ = swipeToClose;
		}
	}
}
