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
package org.whizu.tutorial.jqm.api;

import org.whizu.annotation.App;
import org.whizu.html.Html;
import org.whizu.jquery.mobile.Button;
import org.whizu.jquery.mobile.ButtonBuilder;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Jqm;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.Panel;
import org.whizu.jquery.mobile.PanelBuilder;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/jqm/panels")
public class Panels implements JQueryMobile {

	@Override
	public void onLoad(Page index) {
		index.header("Welcome !");
		Panel panel = PanelBuilder.createWithId("my-panel").add(Html.p("This is my first panel")).build();
		Button openPanel = ButtonBuilder.createWithTitle("Open panel").onClickOpen(panel).build();
		index.addContent(openPanel);
		Page next = Jqm.addPage("next");
		index.addContent(ButtonBuilder.createWithTitle("Next page").onClickOpen(next).build());
		panel = PanelBuilder.createWithId("other-panel").add(Html.p("This is my second panel")).right().build();
		Button button = ButtonBuilder.createWithTitle("Open second panel").onClickOpen(panel).build();
		next.addContent(button);
		next.addContent(ButtonBuilder.createWithTitle("Back to home page").onClickOpen(index).build());
	}
}
