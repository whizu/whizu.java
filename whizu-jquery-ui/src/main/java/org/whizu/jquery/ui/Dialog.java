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
package org.whizu.jquery.ui;

import org.whizu.dom.Content;
import org.whizu.html.Html;
import org.whizu.widget.Container;

public class Dialog extends Container {

	private final String caption_;

	protected Dialog(String caption) {
		caption_ = caption;
	}

	@Override
	public Content build() {
		String script = null;
		if (width == null) {
			script = ".popup('open');";
			// jQuery(this).dialog();
		} else {
			script = ".popup({ width:" + width + " });";
			// jQuery(this).dialog(width);
		}
		jQuery(this).concat(script);

		// isRendered = true;
		jQuery(this).trigger("create");
		return Html.div(id()).attr("data-role", "content").attr("title", caption_).add(componentList);
	}

	public void close() {
		if (isRendered()) {
			jQuery(this).call("dialog", "close");
		}
	}
}
