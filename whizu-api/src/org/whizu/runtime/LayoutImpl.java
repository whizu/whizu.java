/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the “EUPL”) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an “AS IS” basis and 
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
package org.whizu.runtime;

import org.whizu.dom.Component;
import org.whizu.dom.Content;
import org.whizu.dom.Foreach;
import org.whizu.html.Html;
import org.whizu.jquery.AbstractContainer;
import org.whizu.ui.Layout;

/**
 * @author Rudy D'hauwe
 */
class LayoutImpl extends AbstractContainer implements Layout {

	protected Content create(String css, final String itemClass) {
		// isRendered = true;
		return Html.div(this).css(style).css(css).width(width).add(new Foreach<Component>(componentList) {

			@Override
			public Content render(Component item) {
				item.css(itemClass); // to be tested
				return item;
				// return item.render();
				// return item.render().css(itemClass); //this works
			}
		});
	}

	@Override
	public Content create() {
		return Html.div(this).css(style).width(width).add(componentList);
	}
}
