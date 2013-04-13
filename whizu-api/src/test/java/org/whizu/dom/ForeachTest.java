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
package org.whizu.dom;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

/**
 * @author Rudy D'hauwe
 */
public class ForeachTest {

	class TestClass {

		private String name;

		public TestClass(String name) {
			this.name = name;
		}
	}

	/**
	 * Test method for {@link org.whizu.dom.Foreach#compile(java.lang.Object)}.
	 */
	@Test
	public void testCompile() {
		Collection<TestClass> list = new ArrayList<TestClass>();
		list.add(new TestClass("first"));
		list.add(new TestClass("second"));
		Foreach<TestClass> foreach = new Foreach<TestClass>(list) {

			@Override
			public Content compile(TestClass item) {
				return new Literal("A-" + item.name);
			}
		};

		Iterator<TestClass> it = foreach.iterator();
		TestClass obj = it.next();
		assertEquals("A-first", foreach.compile(obj).render());
		obj = it.next();
		assertEquals("A-second", foreach.compile(obj).render());
		assertEquals(false, it.hasNext());
	}

	/**
	 * Test method for
	 * {@link org.whizu.dom.Foreach#Foreach(java.util.Collection)}.
	 */
	@Test
	public void testForeach() {
		Collection<TestClass> list = new ArrayList<TestClass>();
		list.add(new TestClass("first"));
		list.add(new TestClass("second"));
		Foreach<TestClass> foreach = new Foreach<TestClass>(list) {

			@Override
			public Content compile(TestClass item) {
				return new Literal(item.name);
			}
		};

		Iterator<TestClass> it = foreach.iterator();
		TestClass obj = it.next();
		assertEquals("first", foreach.compile(obj).render());
		obj = it.next();
		assertEquals("second", foreach.compile(obj).render());
		assertEquals(false, it.hasNext());
	}
	/**
	 * Test method for {@link org.whizu.dom.Foreach#iterator()}.
	 */
	@Test
	public void testIterator() {
		Collection<TestClass> list = new ArrayList<TestClass>();
		TestClass first = new TestClass("first");
		TestClass second = new TestClass("second");
		list.add(first);
		list.add(second);
		Foreach<TestClass> foreach = new Foreach<TestClass>(list) {

			@Override
			public Content compile(TestClass item) {
				return new Literal(item.name);
			}
		};

		Iterator<TestClass> it = foreach.iterator();
		assertEquals(first, it.next());
		assertEquals(second, it.next());
	}
}
