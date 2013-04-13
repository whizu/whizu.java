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
import java.util.List;

import org.junit.Test;

/**
 * @author Rudy D'hauwe
 */
public class NodeTest extends AbstractTest {

	class TestClass {

		private String name;

		public TestClass(String name) {
			this.name = name;
		}
	}
	
	/**
	 * Test method for {@link org.whizu.dom.Node#Node(java.lang.String)}.
	 */
	@Test
	public void testNodeString() {
		Node node = new Node("div");
		equals("<div></div>", node);
	}

	/**
	 * Test method for
	 * {@link org.whizu.dom.Node#Node(java.lang.String, boolean)}.
	 */
	@Test
	public void testNodeStringBoolean() {
		Node node = new Node("div", false);
		equals("<div></div>", node);
		node = new Node("br", true);
		equals("<br/>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#add(org.whizu.dom.Content)}.
	 */
	@Test
	public void testAddContent() {
		Node node = new Node("div");
		node.add(new Literal("text"));
		equals("<div>text</div>", node);

		node = new Node("div");
		node.add(new Literal("text"));
		node.add(new Literal("other"));
		equals("<div>textother</div>", node);

		node = new Node("div", false);
		node.add(new Literal("text"));
		equals("<div>text</div>", node);

		node = new Node("div", false);
		node.add(new Literal("text"));
		node.add(new Literal("other"));
		equals("<div>textother</div>", node);

		node = new Node("br", true);
		boolean thrown = false;
		try {
			node.add(new Literal("text"));
		} catch (IllegalStateException exc) {
			thrown = true;
		}
		assertEquals(true, thrown);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#add(org.whizu.dom.Content[])}.
	 */
	@Test
	public void testAddContentArray() {
		Node node = new Node("div");
		Content[] array = new Content[]{new Literal("text")};
		node.add(array);
		equals("<div>text</div>", node);

		node = new Node("div");
		array = new Content[]{new Literal("text"), new Literal("other")};
		node.add(array);
		equals("<div>textother</div>", node);

		node = new Node("div", false);
		array = new Content[]{new Literal("text")};
		node.add(array);
		equals("<div>text</div>", node);

		node = new Node("div", false);
		array = new Content[]{new Literal("text"), new Literal("other")};
		node.add(array);
		equals("<div>textother</div>", node);

		node = new Node("br", true);
		boolean thrown = false;
		try {
			array = new Content[]{new Literal("text")};
			node.add(array);
		} catch (IllegalStateException exc) {
			thrown = true;
		}
		assertEquals(true, thrown);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#add(org.whizu.dom.Foreach)}.
	 */
	@Test
	public void testAddForeachOfT() {
		Collection<TestClass> list = new ArrayList<TestClass>();
		list.add(new TestClass("first"));
		list.add(new TestClass("second"));
		Foreach<TestClass> foreach = new Foreach<TestClass>(list) {

			@Override
			public Content compile(TestClass item) {
				return new Literal("A-" + item.name);
			}
		};
		
		Node node = new Node("div");
		node.add(foreach);
		equals("<div>A-firstA-second</div>", node);
		
		node = new Node("br", true);
		boolean thrown = false;
		try {
			node.add(foreach);
		} catch (IllegalStateException exc) {
			thrown = true;
		}
		assertEquals(true, thrown);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#add(java.util.List)}.
	 */
	@Test
	public void testAddListOfT() {
		List<Content> list = new ArrayList<Content>();
		list.add(new Literal("first"));
		list.add(new Literal("second"));		
		Node node = new Node("div");
		node.add(list);
		equals("<div>firstsecond</div>", node);
		
		node = new Node("br", true);
		boolean thrown = false;
		try {
			node.add(list);
		} catch (IllegalStateException exc) {
			thrown = true;
		}
		assertEquals(true, thrown);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#add(java.lang.String)}.
	 */
	@Test
	public void testAddString() {
		Node node = new Node("div");
		node.add("text");
		equals("<div>text</div>", node);

		node = new Node("div");
		node.add("text");
		node.add("other");
		equals("<div>textother</div>", node);

		node = new Node("div", false);
		node.add("text");
		equals("<div>text</div>", node);

		node = new Node("div", false);
		node.add("text");
		node.add("other");
		equals("<div>textother</div>", node);

		node = new Node("br", true);
		boolean thrown = false;
		try {
			node.add("text"); // should throw exception?
		} catch (IllegalStateException exc) {
			thrown = true;
		}
		assertEquals(true, thrown);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#addCss(java.lang.String)}.
	 */
	@Test
	public void testAddCss() {
		Node node = new Node("div");
		node.addCss("class1");
		equals("<div class='class1 '></div>", node);

		node = new Node("div");
		node.addCss("class1");
		node.addCss("class2");
		equals("<div class='class1 class2 '></div>", node);
	}

	/**
	 * Test method for
	 * {@link org.whizu.dom.Node#addStyle(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddStyle() {
		Node node = new Node("div");
		node.addStyle("border", "solid 1px black");
		equals("<div style='border:solid 1px black;'></div>", node);

		node = new Node("div");
		node.addStyle("border", "solid 1px black");
		node.addStyle("height", "40px");
		equals("<div style='height:40px;border:solid 1px black;'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#after(org.whizu.dom.Content)}.
	 */
	@Test
	public void testAfter() {
		Node first = new Node("div");
		Node second = new Node("table");
		Markup result = second.after(first);
		equals("<div></div><table></table>", result);
	}

	/**
	 * Test method for
	 * {@link org.whizu.dom.Node#attr(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAttr() {
		Node node = new Node("div");
		node.attr("border", "solid 1px black");
		equals("<div border='solid 1px black'></div>", node);

		node = new Node("div");
		node.attr("border", "solid 1px black");
		node.attr("height", "40px");
		equals("<div height='40px' border='solid 1px black'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#border(java.lang.String)}.
	 */
	@Test
	public void testBorder() {
		Node node = new Node("div");
		node.border("solid 1px black");
		equals("<div style='border:solid 1px black;'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#css(java.lang.String)}.
	 */
	@Test
	public void testCss() {
		Node node = new Node("div");
		node.css("class1");
		equals("<div class='class1 '></div>", node);

		node = new Node("div");
		node.css("class1");
		node.css("class2");
		equals("<div class='class1 class2 '></div>", node);
	}

	/**
	 * Test method for
	 * {@link org.whizu.dom.Node#decorate(org.whizu.dom.Decorator[])}.
	 */
	@Test
	public void testDecorateDecoratorArray() {
		Decorator decorator1 = new Decorator() {

			@Override
			public void decorate(String name, Element element) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void decorate(Element element) {
				element.attr("myAttribute", "myValue");
			}
		};

		Decorator decorator2 = new Decorator() {

			@Override
			public void decorate(String name, Element element) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void decorate(Element element) {
				element.attr("myAttribute2", "myValue2");
			}
		};

		Node node = new Node("div");
		node.decorate(decorator1);
		equals("<div myAttribute='myValue'></div>", node);
		
		node = new Node("div");
		node.decorate(decorator1, decorator2);
		equals("<div myAttribute2='myValue2' myAttribute='myValue'></div>", node);
	}

	/**
	 * Test method for
	 * {@link org.whizu.dom.Node#decorate(java.lang.String, org.whizu.dom.Decorator)}
	 * .
	 */
	@Test
	public void testDecorateStringDecorator() {
		Decorator decorator1 = new Decorator() {

			@Override
			public void decorate(String name, Element element) {
				element.attr("myAttribute", "myValue");
			}

			@Override
			public void decorate(Element element) {
				element.attr("myDefaultAttribute", "myDefaultValue");
			}
		};

		Node node = new Node("div");
		node.decorate("myAttribute", decorator1);
		equals("<div myAttribute='myValue'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#id()}.
	 */
	@Test
	public void testId() {
		Node node = new Node("div");
		node.id("myID");
		assertEquals("myID", node.id());
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#height(java.lang.String)}.
	 */
	@Test
	public void testHeight() {
		Node node = new Node("div");
		node.height("myValue");
		equals("<div style='height:myValue;'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#id(java.lang.String)}.
	 */
	@Test
	public void testIdString() {
		Node node = new Node("div");
		node.id("myID");
		assertEquals("myID", node.id());
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#margin(java.lang.String)}.
	 */
	@Test
	public void testMargin() {
		Node node = new Node("div");
		node.margin("myValue");
		equals("<div style='margin:myValue;'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#padding(java.lang.String)}.
	 */
	@Test
	public void testPadding() {
		Node node = new Node("div");
		node.padding("myValue");
		equals("<div style='padding:myValue;'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#size(java.lang.String)}.
	 */
	@Test
	public void testSize() {
		Node node = new Node("div");
		node.size("myValue");
		equals("<div size='myValue'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#src(java.lang.String)}.
	 */
	@Test
	public void testSrc() {
		Node node = new Node("div");
		node.src("myValue");
		equals("<div src='myValue'></div>", node);
	}

	/**
	 * Test method for
	 * {@link org.whizu.dom.Node#style(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testStyle() {
		Node node = new Node("div");
		node.style("border", "solid 1px black");
		equals("<div style='border:solid 1px black;'></div>", node);

		node = new Node("div");
		node.style("border", "solid 1px black");
		node.style("height", "40px");
		equals("<div style='height:40px;border:solid 1px black;'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#title(java.lang.String)}.
	 */
	@Test
	public void testTitle() {
		Node node = new Node("div");
		node.title("myValue");
		equals("<div title='myValue'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#render()}.
	 */
	@Test
	public void testRender() {
		Node node = new Node("div");
		node.id("myID");
		node.style("border", "solid 1px blue");
		node.attr("title", "myTitle");
		node.css("class1");
		node.css("class2");
		equals("<div id='myID' title='myTitle' style='border:solid 1px blue;' class='class1 class2 '></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#width(java.lang.String)}.
	 */
	@Test
	public void testWidth() {
		Node node = new Node("div");
		node.width("myValue");
		equals("<div style='width:myValue;'></div>", node);
	}

	/**
	 * Test method for {@link org.whizu.dom.Node#wrap(java.lang.String)}.
	 */
	@Test
	public void testWrap() {
		Node first = new Node("div");
		Markup result = first.wrap("table");
		equals("<table><div></div></table>", result);
	}
}
