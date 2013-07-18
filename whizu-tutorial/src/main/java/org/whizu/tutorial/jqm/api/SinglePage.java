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

import org.whizu.annotation.Listen;
import org.whizu.jquery.mobile.JQueryMobileApp;
import org.whizu.jquery.mobile.Jqm;
import org.whizu.jquery.mobile.Page;

/**
 * @author Rudy D'hauwe
 */
@Listen("/whizu/jqm/widgets/singlepage")
public class SinglePage extends JQueryMobileApp {

	@Override
	public void onLoad(Page page) {
		//Page page = document.page();// document.index(); //document.page(); //document.activePage(); //document.page(); //document.page("index");
		page.header("Page Title Do it Now");
		page.p("Page content goes here. <a href='#first'>first page</a> <a href='#second'>second page</a>");
		page.footer("Page Footer");
	
		page = Jqm.addPage("second");
		page.header("my second header");
		
		page = Jqm.addPage("first");
		page.p("hello <a href='#'>home</a> <a href='#index'>index</a>");
		page.header("Cool");
	}
}
