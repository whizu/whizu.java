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

import org.whizu.jquery.JQuery;
import org.whizu.jquery.Selector;

/**
 * @author Rudy D'hauwe
 */
public class Document {

	private Selector body = new Selector("$('body')"); //private JQuery body = new Selector("$('body')"); //Selector implements JQuery
	
	public Page activePage() {
		return new PageSelector("$.mobile.activePage");
	}

	public Page addPage(String id) {
		PageImpl page = new PageImpl(id);
		//body.append(page);
		jQuery("$.mobile.pageContainer").append(page);
		//jQuery("$.mobile").call("changePage", "#"+id());
		return page(id); //return page 
	}

	public Document append(String content) {
		body.append(content);
		return this;
	}

	private JQuery jQuery(String selector) {
		return new Selector(selector).query();
	}

	public Page page(String id) {
		return new PageSelector("$('" + id + "')");
	}
	
	public Page page() {
		return new PageSelector("div:jqmData(id='')");
	}
}
