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
package org.whizu.layout;

import org.junit.Test;
import org.whizu.widget.AbstractTest;
import org.whizu.widget.Container;

/**
 * @author Rudy D'hauwe
 */
public class HorizontalLayoutTest extends AbstractTest {

	/**
	 * Test method for {@link org.whizu.layout.HorizontalLayout#compile()}.
	 */
	@Test
	public void testCompile() {
		Layout parent = new HorizontalLayout();
		Container child1 = new Container();
		parent.add(child1);
		child1.css("myClass");
		Container child2 = new Container();
		parent.add(child2);
		String expected = "<div id='c0' class='horizontal-layout '><div id='c1' class='myClass horizontal-layout-element '></div><div id='c2' class='horizontal-layout-element '></div></div>";
		equals(expected, parent);
	}
}
