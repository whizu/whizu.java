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
enum DataSwipeToClose implements Decorator {

	// @formatter:off
	FALSE("false");
	// @formatter:on

	private static final String ATTRIBUTE_NAME = "data-swipe-close";

	private String value_;

	private DataSwipeToClose(String value) {
		value_ = value;
	}

	@Override
	public void decorate(Element element) {
		element.attr(ATTRIBUTE_NAME, value_);
	}
}
