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
import org.whizu.jquery.mobile.Document;
import org.whizu.jquery.mobile.JQueryMobileApp;
import org.whizu.jquery.mobile.Jqm;
import org.whizu.jquery.mobile.Page;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/jqm/widgets/multipage")
public class MultiPage extends JQueryMobileApp {

	@Override
	public void onLoad(Document document) {
		Page foo = document.addPage("foo");
		Page bar = document.addPage("bar");

		foo.header("Foo");
		foo.p("I'm first in the source order so I'm shown as the page.");
		foo.p("View internal page called ").add(Jqm.createHyperlink("bar", bar));
		foo.footer("Page Footer");

		bar.header("Bar");
		bar.p("I'm the second in the source order so I'm hidden when the page loads. I'm just shown if a link that references my id is being clicked.");
		foo.p("Take me ").add(Jqm.createHyperlink("back to Foo", foo));
		bar.footer("Page Footer");
	}
}
