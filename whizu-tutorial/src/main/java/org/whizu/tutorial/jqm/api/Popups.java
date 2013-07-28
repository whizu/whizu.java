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
import org.whizu.jquery.mobile.Button;
import org.whizu.jquery.mobile.Header;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Jqm;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.Popup;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/jqm/widgets/popups")
public class Popups implements JQueryMobile {

	@Override
	public void onLoad(Page page) {
		/*
		HeaderBuilder header = page.header("Popups");
		header.addButton(new Button("hello"));
		*/
		
		Page next = Jqm.addPage("Next");
		
		Popup popup = Jqm.addPopup("popup").p("My first text").build();
		//page.append(popup);
		
		popup = Jqm.addPopup("popup").p("My second popup with same id").build();
		//page.append(popup);
		
		//Jqm.createHeader("Popups").on(page);
		// @formatter:off
		Header.builder()
		    .title("Popups")
		    .button("Popup")
		        .onClickOpen(popup)
		        .build()
		    .button("Next page")
		        .onClickOpen(next)
		        .build()
		    .build()
		    .on(page);
		// @formatter:on
		
		Button button1 = Jqm.createButton("My button 1")
			.onClickOpen(next)
			.build();
		page.append(button1);

		next.header("Volgende pagina");
		Button button2 = Jqm.createButton("My button 2")
				.onClickOpen(page)
				.build();
		next.append(button2);
	}
}
