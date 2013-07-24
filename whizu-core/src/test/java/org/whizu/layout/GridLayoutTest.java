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

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.whizu.widget.AbstractTest;

/**
 * @author Rudy D'hauwe
 */
public class GridLayoutTest extends AbstractTest {

	/**
	 * Test method for {@link org.whizu.layout.GridLayout#GridLayout()}.
	 */
	@Test
	public void testGridLayout() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.layout.GridLayout#GridLayout(int)}.
	 */
	@Test
	public void testGridLayoutInt() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.layout.GridLayout#compile()}.
	 */
	@Test
	public void testCompile() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.whizu.layout.GridLayout#add(org.whizu.dom.Component)}.
	 */
	@Test
	public void testAdd() {
		GridLayout grid = new GridLayout(2);
		grid.add(new Label("Label1"));
		grid.add(new Label("Label2"));
		grid.add(new Label("Label3"));

		String markup = grid.render();
		assertEquals(
				"<table id='c0' cellpadding='0' cellspacing='0'><tbody><tr><td><div id='c1'>Label1</div></td><td><div id='c2'>Label2</div></td></tr><tr><td><div id='c3'>Label3</div></td></tr></tbody></table>",
				markup);
		assertEquals(true, grid.isRendered());
	}

	/**
	 * Test method for {@link org.whizu.layout.GridLayout#empty()}.
	 */
	@Test
	public void testEmpty() {
		GridLayout grid = new GridLayout(2);
		grid.add(new Label("Label1"));
		grid.add(new Label("Label2"));
		grid.add(new Label("Label3"));

		String markup = grid.render();
		assertEquals(
				"<table id='c0' cellpadding='0' cellspacing='0'><tbody><tr><td><div id='c1'>Label1</div></td><td><div id='c2'>Label2</div></td></tr><tr><td><div id='c3'>Label3</div></td></tr></tbody></table>",
				markup);
		assertEquals(true, grid.isRendered());
		grid.empty();
		assertEquals(true, grid.isRendered());
		assertEquals("$(\"table > tbody\").empty();", theRequest.finish());
		assertEquals(true, grid.isRendered());
		grid.add(new Label("Label4"));
		grid.add(new Label("Label2"));
		grid.add(new Label("Label3"));
		assertEquals(
				"$(\"table > tbody\").append(\"<tr></tr>\");$(\"table > tbody tr:last-child\").append(\"<td><div id='c4'>Label4</div></td>\");$(\"table > tbody tr:last-child\").append(\"<td><div id='c5'>Label2</div></td>\");$(\"table > tbody\").append(\"<tr></tr>\");$(\"table > tbody tr:last-child\").append(\"<td><div id='c6'>Label3</div></td>\");",
				theRequest.finish());
	}

	/**
	 * Test method for
	 * {@link org.whizu.layout.GridLayout#prepend(org.whizu.dom.Component)}.
	 */
	@Test
	public void testPrepend() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.whizu.layout.GridLayout#remove(org.whizu.dom.Component)}.
	 */
	@Test
	public void testRemove() {
		//fail("Not yet implemented");
	}
}
