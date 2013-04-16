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
		Container container = new Container();
		assertEquals(0, container.componentList.size());
		Container child1 = new Container();
		container.add(child1);
		assertEquals(1, container.componentList.size());
		Container child2 = new Container();
		container.add(child2);
		assertEquals(2, container.componentList.size());
		equals("<div id='c0'><div id='c1'></div><div id='c2'></div></div>", container);
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
		part.css("myClass");
		assertEquals(false, part.isRendered());
		assertEquals("$(\"#c0\").append(\"<div id='c1' class='myClass '></div>\");", request.finish());
	}

	/**
	 * Test method for {@link org.whizu.widget.Container#css(java.lang.String)}.
	 */
	@Test
	public void testCssString() {
		Container container = new Container();
		container.css("myClass");
		equals("<div id='c0' class='myClass '></div>", container);
		container = new Container();
		container.css("myClass");
		container.css("myOtherClass");
		equals("<div id='c1' class='myClass myOtherClass '></div>", container);
	}

	/**
	 * Test method for {@link org.whizu.widget.Container#empty()}.
	 */
	@Test
	public void testEmpty() {
		Container container = new Container();
		assertEquals(0, container.componentList.size());
		Container child1 = new Container();
		container.add(child1);
		assertEquals(1, container.componentList.size());
		Container child2 = new Container();
		container.add(child2);
		assertEquals(2, container.componentList.size());
		container.empty();
		assertEquals(0, container.componentList.size());
		equals("<div id='c0'></div>", container);
		container.empty();
		assertEquals(0, container.componentList.size());
	}

	/**
	 * Test method for {@link org.whizu.widget.Container#prepend(org.whizu.dom.Component)}.
	 */
	@Test
	public void testPrepend() {
		Container container = new Container();
		assertEquals(0, container.componentList.size());
		Container child1 = new Container();
		container.prepend(child1);
		assertEquals(1, container.componentList.size());
		Container child2 = new Container();
		container.prepend(child2);
		assertEquals(2, container.componentList.size());
		equals("<div id='c0'><div id='c2'></div><div id='c1'></div></div>", container);
	}

	/**
	 * Test method for {@link org.whizu.widget.Container#remove(org.whizu.dom.Component)}.
	 */
	@Test
	public void testRemove() {
		Container container = new Container();
		assertEquals(0, container.componentList.size());
		Container child1 = new Container();
		container.add(child1);
		assertEquals(1, container.componentList.size());
		Container child2 = new Container();
		container.add(child2);
		assertEquals(2, container.componentList.size());
		container.remove(child1);
		assertEquals(1, container.componentList.size());
		equals("<div id='c0'><div id='c2'></div></div>", container);
	}
}
