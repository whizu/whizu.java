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
package org.whizu.dom;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Rudy D'hauwe
 */
public class ContentListTest {

	/**
	 * Test method for {@link org.whizu.dom.ContentList#ContentList()}.
	 */
	@Test
	public void testContentList() {
		ContentList list = new ContentList();
		String markup = list.render();
		assertEquals("", markup);
	}

	/**
	 * Test method for {@link org.whizu.dom.ContentList#ContentList(org.whizu.dom.Content[])}.
	 */
	@Test
	public void testContentListContentArray() {
		Content first = new Literal("first|");
		Content second = new Literal("second");
		ContentList list = new ContentList(first, second);
		String markup = list.render();
		assertEquals("first|second", markup);
	}

	/**
	 * Test method for {@link org.whizu.dom.ContentList#add(org.whizu.dom.Content)}.
	 */
	@Test
	public void testAddContent() {
		Content first = new Literal("first|");
		Content second = new Literal("second");
		Content third = new Literal("|third");
		ContentList list = new ContentList(first);
		list.add(second);
		String markup = list.render();
		assertEquals("first|second", markup);
		list.add(third);
		markup = list.render();
		assertEquals("first|second|third", markup);
	}

	/**
	 * Test method for {@link org.whizu.dom.ContentList#add(java.util.List)}.
	 */
	@Test
	public void testAddListOfT() {
		Content first = new Literal("first|");
		Content second = new Literal("second");
		Content third = new Literal("|third");
		List<Content> objs = new ArrayList<Content>();
		objs.add(first);
		objs.add(second);
		objs.add(third);
		ContentList list = new ContentList();
		list.add(objs);
		String markup = list.render();
		assertEquals("first|second|third", markup);
	}

	/**
	 * Test method for {@link org.whizu.dom.ContentList#render()}.
	 */
	@Test
	public void testRender() {
		testAddContent();
		testAddListOfT();
	}
}
