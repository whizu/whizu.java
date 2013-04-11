/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the “EUPL”) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an “AS IS” basis and 
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
package org.whizu.layout;

import org.whizu.dom.Markup;

public class AbsoluteLayout extends AbstractLayout {

	@Override
	public Markup compile() {
		return create("absolute-layout", "absolute-layout-element");
	}

	/*
	//TODO move to some test case
	public static void main(String[] args) {
		final AbstractWidget impl = new AbsoluteLayoutImpl();
		System.out.println(impl.getId());
		System.out.println(impl.getSelector());
		System.out.println(impl.jQuery(impl));
		System.out.println(impl.jQuery(impl).addClass("mystyle").click(new Function() {

			@Override
			public void execute() {
				impl.jQuery(impl).addClass("test in function").empty();
			}
		}).button());
	}
	*/
}
