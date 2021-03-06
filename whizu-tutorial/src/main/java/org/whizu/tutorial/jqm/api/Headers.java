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
import org.whizu.jquery.mobile.ButtonBuilder;
import org.whizu.jquery.mobile.HeaderBuilder;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.PageBuilder;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/jqm/widgets/headers")
public class Headers implements JQueryMobile /* implements JqmApp */{

	@Override
	public void onLoad(Page page) {
		page.header("Huidige pagina");

		Page next = PageBuilder.createWithId("Next").build();
		Page more = PageBuilder.createWithId("more").build();
		
		next.header("Volgende pagina");

		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("My button 1")
		    .onClickOpen(next)
		    .build();
		page.add(button);
		// @formatter:on

		// @formatter:off
		Button button2 = ButtonBuilder.createWithTitle("My button 2")
				.onClickOpen(page)
				.build();
		next.add(button2);
		
		Button button3 = ButtonBuilder.createWithTitle("More")
				.onClickOpen(more)
				.build();
		next.add(button3);
		// @formatter:on

		Button b1 = ButtonBuilder.createWithTitle("button 1").inline().onClickOpen(page).build();
		Button b2 = ButtonBuilder.createAnchorButton("button 2").onClickOpen(page).build();
		Button b3 = ButtonBuilder.createAnchorButton("button 3").build();
		
		HeaderBuilder.create()
			.title("Heading")
			.button(b1)
			.button(b2)
			.button(b3)
			.build()
			.on(more);
		
		//more.header(header);
		
		// Jqm.changePage("Nieuw");
		// RequestContext.getRequest().addExpression("$idd = $(\"div:jqmData(role='page')\").eq(1).attr('id'); $.mobile.changePage('#'+$idd);");
	}
}
