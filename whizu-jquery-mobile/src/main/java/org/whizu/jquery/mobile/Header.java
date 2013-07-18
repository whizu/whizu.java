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

import java.util.ArrayList;
import java.util.List;

import org.whizu.dom.Element;
import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.widget.Widget;

/**
 * The header is a toolbar at the top of the page that usually contains the page
 * title text and optional buttons positioned to the left and/or right of the title
 * for navigation or actions.
 * 
 * @author Rudy D'hauwe
 */
public class Header extends Widget {

	private static final String LEFT = "ui-btn-left";
	
	private static final String RIGHT = "ui-btn-right";

	private List<Button> buttonList_ = new ArrayList<Button>();

	private DataFullscreen fullscreen_;

	private DataPosition position_;

	private Theme theme_;

	private Element title_;
	
	/**
	 * Creates a header without a title. The heading title in the header bar has
	 * some margin that will give the bar its height. If you choose not to use a
	 * heading, a &lt;span&gt; element will be added with class="ui-title" so that
	 * the bar can get the height and display correctly.
	 */
	public Header() {
		title_ = Html.span().css("ui-title");
	}

	/**
	 * Creates a header with a title wrapped in an H1 heading element.
	 */
	public Header(String title) {
		title_ = Html.h1(title);
	}

	public static HeaderBuilder builder() {
		return new HeaderBuilder();
	}
	
	/**
	 * Adds a button to the right side of the header.
	 */
	public Header addButton(Button button) {
		buttonList_.add(button);
		return this;
	}

	/**
	 * Adds a button to the left side of the header.
	 */
	public Header addButtonLeft(Button button) {
		button.css(LEFT);
		return addButton(button);
	}

	/**
	 * Adds a button to the right side of the header.
	 */
	public Header addButtonRight(Button button) {
		button.css(RIGHT);
		return addButton(button);
	}

	@Override
	public Markup compile() {
		//jQuery(this).closest(":jqmData(role='page')").trigger("pagecreate"); //necessary?
		return Html.div(this).decorate(DataRole.HEADER, theme_, position_, fullscreen_).add(title_).add(buttonList_);
	}

	/**
	 * The header can be set to fullscreen fixed position that overlays the
	 * toolbars over the content by adding the data-fullscreen="true" to the
	 * header.
	 */
	public Header setFullscreen() {
		fullscreen_ = DataFullscreen.TRUE;
		// setPositionFixed(); //required?
		return this;
	}

	/**
	 * The header can be set to fixed position by adding the data-position="fixed"
	 * to the header.
	 */
	public Header setPositionFixed() {
		position_ = DataPosition.FIXED;
		return this;
	}

	/**
	 * The header toolbar is themed with the "a" swatch by default (black in the
	 * default theme) but you can easily set the theme swatch color.
	 */
	public Header theme(Theme theme) {
		theme_ = theme;
		return this;
	}

	public void on(Page page) {
		((PageSelector) page).header(this);
	}

	public Header title(String title) {
		title_ = Html.h1(title);
		return this;
	}
}
