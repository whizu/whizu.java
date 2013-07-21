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

import org.whizu.jquery.mobile.Button.Type;
import org.whizu.ui.ClickListener;

/**
 * @author Rudy D'hauwe
 */
public class ButtonBuilder implements Builder<Button> {

	public static ButtonBuilder createWithTitle(String title) {
		return new ButtonBuilder(title);
	}

	private Button build_;

	public ButtonBuilder(String title) {
		build_ = new Button(title);
	}

	public Button build() {
		return build_;
	}

	public ButtonBuilder onClick(ClickListener listener) {
		build_.onClick(listener);
		return this;
	}

	public ButtonBuilder onClick(Page foo) {
		build_.onClick(foo);
		return this;
	}

	public ButtonBuilder onClick(Popup popup) {
		build_.onClick(popup);
		return this;
	}

	public void theme(Theme theme) {
		build_.theme(theme);
	}

	public void type(Type type) {
		build_.type(type);
	}
}
