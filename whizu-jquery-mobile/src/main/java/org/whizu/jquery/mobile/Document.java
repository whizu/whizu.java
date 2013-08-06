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

/**
 * @author Rudy D'hauwe
 */
public class Document {

	// private Selector body = new Selector("$(\"#whizu\")"); // private JQuery
	// body = new
	// Selector("$('body')");
	// //Selector
	// implements JQuery

	// // verified
	// public Page activePage() {
	// return new PageImpl("$.mobile.activePage");
	// }

	// public void add(String string) {
	// body.append(string);
	// }

//	@Deprecated
//	public Page createPage(String id) {
//		// PageBuild page = new PageBuild(id);
//		// String ex = "$p = $(\"" + page.render() + "\");";
//		// RequestContext.getRequest().addExpression(ex +
//		// " $p.appendTo($.mobile.pageContainer); "); //
//		// $.mobile.changePage($('#second'));");
//		// return page(id); // return page
//
//		Page page = PageBuilder.createWithId(id).build();
//		PageProxy proxy = Objects.cast(page);
//		String ex = "$p = $(\"" + proxy.render() + "\");";
//		RequestContext.getRequest().addExpression(ex + " $p.appendTo($.mobile.pageContainer); "); // $.mobile.changePage($('#second'));");
//		return page;
//	}

	// // verified
	// public Page allPages() {
	// return new PageImpl("$(\"div:jqmData(role='page')\")");
	// }

	// public Document append(String content) {
	// body.append(content);
	// return this;
	// }

	// public Document append(Content content) {
	// body.jQueryAppend(content);
	// return this;
	// }

	// // verified
	// public Page first() {
	// return new PageImpl("$(\"div:jqmData(role='page')\").first()");
	// }

	// verified
	// private Page index() {
	// return page("index");
	// }

	// private JQuery jQuery(String selector) {
	// return new Selector(selector).query();
	// }

	// verified
	public Page index() {
		return page("index");
	}

	// verified
	private Page page(String id) {
		return PageBuilder.jQueryById(id);
	}
}
