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
package org.whizu.ui;

import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.jquery.RequestContext;
import org.whizu.widget.Container;

/**
 * @author Rudy D'hauwe
 */
public class DialogImpl extends Container implements Dialog {

	private final String caption_;

	public DialogImpl(String caption) {
		caption_ = caption;
	}

	@Override
	public Markup compile() {
		String script = "";
		script += "$('#lean_overlay').click(function() { $('#lean_overlay').fadeOut(200); $('#" + this.id() + "').css({'display':'none'}); });";
		script += "$('#lean_overlay').css({'display':'block',opacity:0});";
		script += "$('#lean_overlay').fadeTo(200, 0.5);";
		script += "var modal_width = $('#" + this.id() + "').outerWidth();";
		script += "$('#" + this.id() + "').css({'display':'block','position':'fixed','top':'100px','left':'50%','margin-left':-(modal_width / 2) + 'px','opacity':0,'z-index':11000});";
		script += "$('#" + this.id() + "').fadeTo(200, 1);";
		RequestContext.getRequest().addExpression(script);
		//jQuery(this).fadeTo(200, 1); //TODO this gets in front of the script????? preceedes getRequest().addExpression()??
		return Html.div(id()).attr("title", caption_).css("whizu-dialog").add(componentList);
	}

	public void close() {
		if (isRendered()) {
			jQuery(this).call("dialog", "close");
		}
	}
}
