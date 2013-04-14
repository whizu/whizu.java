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
		equals("<button></button>", Html.button());
	}

	/**
	 * Test method for
	 * {@link org.whizu.html.Html#button(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testButtonIdentity() {
		equals("<button id='myID'></button>", Html.button(createIdentity("myID")));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#button(java.lang.String)}.
	 */
	@Test
	public void testButtonString() {
		equals("<button id='myID'></button>", Html.button("myID"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#div()}.
	 */
	@Test
	public void testDiv() {
		equals("<div></div>", Html.div());
	}

	/**
	 * Test method for {@link org.whizu.html.Html#div(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testDivIdentity() {
		equals("<div id='myID'></div>", Html.div(createIdentity("myID")));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#div(java.lang.String)}.
	 */
	@Test
	public void testDivString() {
		equals("<div id='myID'></div>", Html.div("myID"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#form(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testFormIdentity() {
		equals("<form id='myID'></form>", Html.form(createIdentity("myID")));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#form(java.lang.String)}.
	 */
	@Test
	public void testFormString() {
		equals("<form id='myID'></form>", Html.form("myID"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#h1()}.
	 */
	@Test
	public void testH1() {
		equals("<h1></h1>", Html.h1());
	}

	/**
	 * Test method for {@link org.whizu.html.Html#h1(java.lang.String)}.
	 */
	@Test
	public void testH1String() {
		equals("<h1>title</h1>", Html.h1("title"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#h3()}.
	 */
	@Test
	public void testH3() {
		equals("<h3></h3>", Html.h3());
	}

	/**
	 * Test method for {@link org.whizu.html.Html#h3(java.lang.String)}.
	 */
	@Test
	public void testH3String() {
		equals("<h3>title</h3>", Html.h3("title"));
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
		equals("<img id='myID'></img>", Html.img("myID"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#input()}.
	 */
	@Test
	public void testInput() {
		equals("<input/>", Html.input());
	}

	/**
	 * Test method for {@link org.whizu.html.Html#input(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testInputIdentity() {
		equals("<input id='myID'/>", Html.input(createIdentity("myID")));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#input(java.lang.String)}.
	 */
	@Test
	public void testInputString() {
		equals("<input id='myID'/>", Html.input("myID"));
	}
	
	/**
	 * Test method for {@link org.whizu.html.Html#label()}.
	 */
	@Test
	public void testLabel() {
		equals("<label></label>", Html.label());
	}

	/**
	 * Test method for
	 * {@link org.whizu.html.Html#script(java.lang.String)}.
	 */
	@Test
	public void testScriptString() {
		equals("<script src='myScript.js' type='text/javascript'></script>", Html.script("myScript.js"));
	}
	
	/**
	 * Test method for
	 * {@link org.whizu.html.Html#select(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testSelectIdentity() {
		equals("<select id='myID'></select>", Html.select(createIdentity("myID")));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#select(java.lang.String)}.
	 */
	@Test
	public void testSelectString() {
		equals("<select id='myID'></select>", Html.select("myID"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#selfClosing(java.lang.String)}.
	 */
	@Test
	public void testSelfClosingString() {
		equals("<selfClosing/>", Html.selfClosing("selfClosing"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#table(org.whizu.dom.Identity)}
	 * .
	 */
	@Test
	public void testTableIdentity() {
		equals("<table id='myID'></table>", Html.table(createIdentity("myID")));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#table(java.lang.String)}.
	 */
	@Test
	public void testTableString() {
		equals("<table id='myID'></table>", Html.table("myID"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#tag(java.lang.String)}.
	 */
	@Test
	public void testTag() {
		equals("<mytag></mytag>", Html.tag("mytag"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#tbody()}.
	 */
	@Test
	public void testTbody() {
		equals("<tbody></tbody>", Html.tbody());
	}

	/**
	 * Test method for {@link org.whizu.html.Html#td()}.
	 */
	@Test
	public void testTd() {
		equals("<td></td>", Html.td());
	}

	/**
	 * Test method for {@link org.whizu.html.Html#td(java.lang.String)}.
	 */
	@Test
	public void testTdString() {
		equals("<td id='myID'></td>", Html.td("myID"));
	}

	/**
	 * Test method for
	 * {@link org.whizu.html.Html#textarea(org.whizu.dom.Identity)}.
	 */
	@Test
	public void testTextareaIdentity() {
		equals("<textarea id='myID'></textarea>", Html.textarea(createIdentity("myID")));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#textarea(java.lang.String)}.
	 */
	@Test
	public void testTextareaString() {
		equals("<textarea id='myID'></textarea>", Html.textarea("myID"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#th()}.
	 */
	@Test
	public void testTh() {
		equals("<th></th>", Html.th());
	}

	/**
	 * Test method for {@link org.whizu.html.Html#thead()}.
	 */
	@Test
	public void testThead() {
		equals("<thead></thead>", Html.thead());
	}

	/**
	 * Test method for
	 * {@link org.whizu.html.Html#thead(org.whizu.dom.Element[])}.
	 */
	@Test
	public void testTheadElementArray() {
		equals("<thead><h1></h1><hr/></thead>", Html.thead(Html.h1(), Html.hr()));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#th(java.lang.String)}.
	 */
	@Test
	public void testThString() {
		equals("<th>contents</th>", Html.th("contents"));
	}

	/**
	 * Test method for {@link org.whizu.html.Html#tr()}.
	 */
	@Test
	public void testTr() {
		equals("<tr></tr>", Html.tr());
	}

	/**
	 * Test method for {@link org.whizu.html.Html#tr(org.whizu.dom.Element[])}.
	 */
	@Test
	public void testTrElementArray() {
		equals("<tr><h1></h1><hr/></tr>", Html.tr(Html.h1(), Html.hr()));
	}
}
