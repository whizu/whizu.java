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
package org.whizu.html;

import org.junit.Test;

/**
 * @author Rudy D'hauwe
 */
public class HtmlTest extends AbstractTest {

	/**
	 * Test method for {@link org.whizu.html.Html#a()}.
	 */
	@Test
	public void testA() {
		equals("<a></a>", Html.a());
	}
	
	/**
	 * Test method for {@link org.whizu.html.Html#a(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testAIdentity() {
		equals("<a id='myID'></a>", Html.a(createIdentity("myID")));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#a(java.lang.String)}.
	 */
	@Test
	public void testAString() {
		equals("<a id='myID'></a>", Html.a("myID"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#body()}.
	 */
	@Test
	public void testBody() {
		equals("<body></body>", Html.body());
	}

	/**
	 * Test method for {@link org.whizu.html.Html#body(java.lang.String)}.
	 */
	@Test
	public void testBodyString() {
		equals("<body id='myID'></body>", Html.body("myID"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#br()}.
	 */
	@Test
	public void testBr() {
		equals("<br/>", Html.br()); //equals("<br>", Html.br()); ??
	}

	/**
	 * Test method for {@link org.whizu.html.Html#button()}.
	 */
	@Test
	public void testButton() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.whizu.html.Html#button(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testButtonIdentity() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#button(java.lang.String)}.
	 */
	@Test
	public void testButtonString() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#div()}.
	 */
	@Test
	public void testDiv() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#div(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testDivIdentity() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#div(java.lang.String)}.
	 */
	@Test
	public void testDivString() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#form(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testFormIdentity() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#form(java.lang.String)}.
	 */
	@Test
	public void testFormString() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#h1()}.
	 */
	@Test
	public void testH1() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#h1(java.lang.String)}.
	 */
	@Test
	public void testH1String() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#h3()}.
	 */
	@Test
	public void testH3() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#h3(java.lang.String)}.
	 */
	@Test
	public void testH3String() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#hr()}.
	 */
	@Test
	public void testHr() {
		equals("<hr/>", Html.hr()); //equals("<hr>", Html.hr()); ??
	}

	/**
	 * Test method for {@link org.whizu.html.Html#img(java.lang.String)}.
	 */
	@Test
	public void testImg() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#input()}.
	 */
	@Test
	public void testInput() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#input(org.whizu.dom.Identity)}
	 * .
	 */
	@Test
	public void testInputIdentity() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#input(java.lang.String)}.
	 */
	@Test
	public void testInputString() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#label()}.
	 */
	@Test
	public void testLabel() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.whizu.html.Html#select(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testSelectIdentity() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#select(java.lang.String)}.
	 */
	@Test
	public void testSelectString() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#table(org.whizu.dom.Identity)}
	 * .
	 */
	@Test
	public void testTableIdentity() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#table(java.lang.String)}.
	 */
	@Test
	public void testTableString() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#tag(java.lang.String)}.
	 */
	@Test
	public void testTag() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#tbody()}.
	 */
	@Test
	public void testTbody() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#td()}.
	 */
	@Test
	public void testTd() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#td(java.lang.String)}.
	 */
	@Test
	public void testTdString() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.whizu.html.Html#textarea(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testTextareaIdentity() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#textarea(java.lang.String)}.
	 */
	@Test
	public void testTextareaString() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#th()}.
	 */
	@Test
	public void testTh() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#th(java.lang.String)}.
	 */
	@Test
	public void testThString() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#thead()}.
	 */
	@Test
	public void testThead() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.whizu.html.Html#thead(org.whizu.dom.Element[])}.
	 */
	@Test
	public void testTheadElementArray() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#tr()}.
	 */
	@Test
	public void testTr() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#tr(org.whizu.dom.Element[])}.
	 */
	@Test
	public void testTrElementArray() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.whizu.html.Html#tr(org.whizu.dom.Element)}.
	 */
	@Test
	public void testTrElement() {
		// fail("Not yet implemented");
	}
}
