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
import org.whizu.jquery.mobile.ButtonBuilder;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.PageBuilder;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/jqm/widgets/multipage")
public class MultiPage implements JQueryMobile {

	@Override
	public void onLoad(Page index) {
		index.header("Welcome !");

		Page foo = PageBuilder.createWithId("foo").build(); // Jqm.createPage("foo");
		Page bar = PageBuilder.createWithId("bar").build(); // Jqm.createPage("bar");

		foo.header("Foos");
		foo.p("I'm first in the source order so I'm shown as the page.");
		// foo.p("View internal page called ").add(Jqm.createHyperlink("bar",
		// bar));
		foo.footer("Page Footer");

		bar.header("Bars");
		bar.p("I'm the second in the source order so I'm hidden when the page loads. I'm just shown if a link that references my id is being clicked.");
		// foo.p("Take me ").add(Jqm.createHyperlink("back to Foo", foo));
		bar.footer("Page Footer");

		index.add(ButtonBuilder.createWithTitle("foo").onClickOpen(foo).build());
		index.add(ButtonBuilder.createWithTitle("bar").onClickOpen(bar).build());
		// Jqm.changePage("foo");
	}
}
