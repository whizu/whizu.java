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

import org.whizu.content.Decorator;
import org.whizu.content.Element;

/**
 * @author Rudy D'hauwe
 */
public enum DataIcon implements Decorator {

	// @formatter:off
	ALERT("alert"), 
	BACK("back"), 
	BARS("bars"), 
	CHECK("check"), 
	DELETE("delete"), 
	DOWN_ARROW("arrow-d"), 
	EDIT("edit"), 
	FORWARD("forward"), 
	GEAR("gear"), 
	GRID("grid"), 
	HOME("home"), 
	INFO("info"), 
	LEFT_ARROW("arrow-l"), 
	MINUS("minus"), 
	PLUS("plus"), 
	REFRESH("refresh"), 
	RIGHT_ARROW("arrow-r"), 
	SEARCH("search"), 
	STAR("star"), 
	UP_ARROW("arrow-u");
	// @formatter:on

	private static final String ATTRIBUTE_NAME = "data-icon";

	private String value_;

	private DataIcon(String value) {
		value_ = value;
	}

	@Override
	public void decorate(Element element) {
		element.attr(ATTRIBUTE_NAME, value_);
	}

	public String value() {
		return value_;
	}
}
