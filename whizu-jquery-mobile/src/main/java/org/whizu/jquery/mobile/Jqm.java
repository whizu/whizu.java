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
public final class Jqm {

	public static Page addPage(String id) {
		return document().addPage(id);
	}

	public static void addPopup(Popup popup) {
		document().activePage().append(popup);
	}

	/*
	public static void changePage(String id) {
		RequestContext.getRequest().addExpression("$.mobile.changePage('#" + id + "');");
	}
	*/

	public static PopupBuilder addPopup(String id) {
		return new PopupBuilder(id);
	}

	public static ButtonBuilder createButton(String caption) {
		return new ButtonBuilder(caption);
	}

	public static Header createHeader(String caption) {
		return new Header(caption);
	}

	public static Button createHyperlink(String caption, Page target) {
		return new Button(caption).onClick(target);
	}

	public static Document document() {
		return new Document();
	}

	public static Page page() {
		return document().page();
	}
}
