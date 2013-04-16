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
package org.whizu.widget;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.whizu.jquery.RequestContext;

/**
 * @author Rudy D'hauwe
 */
public class ContainerTest extends AbstractTest {

	/**
	 * Test method for {@link org.whizu.widget.Container#add(org.whizu.dom.Component)}.
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.widget.Container#compile()}.
	 */
	@Test
	public void testCompile() {
		TestRequest request = (TestRequest) RequestContext.getRequest();
		Container parent = new Container();
		assertEquals("<div id='c0'></div>", parent.render());
		assertEquals(true, parent.isRendered());
		
		Container part = new Container();
		parent.add(part);
		assertEquals(false, part.isRendered());
		part.css("myClass"); //is now no longer completely ignored
		//Previous implementation asserts the following:
		//assertEquals(true, part.isRendered());
		//assertEquals("$(\"#c0\").append(\"<div id='c1'></div>\");", request.finish());
		//This now gives:
		assertEquals(false, part.isRendered());
		assertEquals("$(\"#c0\").append(\"<div id='c1' class='myClass '></div>\");", request.finish());
	}

	/**
	 * Test method for {@link org.whizu.widget.Container#css(java.lang.String)}.
	 */
	@Test
	public void testCssString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.widget.Container#empty()}.
	 */
	@Test
	public void testEmpty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.widget.Container#prepend(org.whizu.dom.Component)}.
	 */
	@Test
	public void testPrepend() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.widget.Container#remove(org.whizu.dom.Component)}.
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}
}
