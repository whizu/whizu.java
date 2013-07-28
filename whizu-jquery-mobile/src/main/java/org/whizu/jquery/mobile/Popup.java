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
import org.whizu.widget.Container;

/**
 * @author Rudy D'hauwe
 */
public class Popup extends Container {

	public enum PositionTo {
		ORIGIN("origin"), WINDOW("window");

		protected String value;

		PositionTo(String value) {
			this.value = value;
		}
	}

	public static final PopupBuilder builder(String id) {
		return new PopupBuilder(id);
	}
	
	private PositionTo positionTo_;
	
	private Theme theme_;

	public Popup(String id) {
		id_ = id;
	}

	@Override
	public Markup compile() {
		// @formatter:off
		Element popup = Html.div(this)
						 .decorate(DataRole.POPUP)
						 .decorate(theme_, this)
						 .add(componentList);
		
		if (positionTo_ != null) {
			 popup.attr("data-position-to", positionTo_.value);
		}
		// @formatter:on 
		return popup;
	}

	public void positionToWindow() {
		positionTo_ = PositionTo.WINDOW;
	}

	public void theme(Theme theme) {
		theme_ = theme;
	}
}
