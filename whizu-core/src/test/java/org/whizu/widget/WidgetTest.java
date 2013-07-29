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
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.whizu.dom.Content;
import org.whizu.dom.Node;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.Request;
import org.whizu.jquery.Session;

/**
 * @author Rudy D'hauwe
 */
public class WidgetTest extends AbstractTest {

	/**
	 * Test method for {@link org.whizu.widget.Widget#Widget()}.
	 */
	@Test
	public void testWidget() {
		Widget widget = new Widget() {

			@Override
			public Content compile() {
				throw new UnsupportedOperationException();
			}
		};

		assertNotNull(widget.id());
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#css(java.lang.String)}.
	 */
	@Test
	public void testCssString() {
		Widget widget = new TestWidget();
		widget.css("myClass");
		equals("<div id='c0' class='myClass '></div>", widget);
		widget = new TestWidget();
		widget.css("myClass");
		widget.css("myOtherClass");
		assertEquals(false, widget.isRendered());
		equals("<div id='c1' class='myClass myOtherClass '></div>", widget);
		assertEquals(true, widget.isRendered());
		widget.css("nextClass");
		assertEquals("$(\"#c1\").addClass(\"nextClass\");", theRequest.finish());
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#decorate()}.
	 */
	@Test
	public void testDecorate() {
		Widget widget = new TestWidget();
		widget.css("myClass");
		widget.width("120px");
		Node node = new Node("myNode");
		widget.decorate(node);
		assertEquals(false, widget.isRendered());
		equals("<myNode style='width:120px;' class='myClass '></myNode>", node);
		widget.css("myOtherClass");
		node = new Node("myNode");
		widget.decorate(node);
		assertEquals(false, widget.isRendered());
		equals("<myNode style='width:120px;' class='myClass myOtherClass '></myNode>", node);
		assertEquals(false, widget.isRendered());
		equals("<div id='c0' style='width:120px;' class='myClass myOtherClass '></div>", widget);
		assertEquals(true, widget.isRendered());
		boolean thrown = false;
		try {
			widget.decorate(node);
		} catch(IllegalStateException exc) {
			thrown = true;
		}
		assertEquals(true, thrown);
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#getRequest()}.
	 */
	@Test
	public void testGetRequest() {
		Widget widget = new TestWidget();
		Request request = widget.getRequest();
		assertNotNull(request);
		assertEquals(true, (request instanceof TestRequest));
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#getSelector()}.
	 */
	@Test
	public void testGetSelector() {
		Widget widget = new TestWidget();
		assertEquals("$(\"#c0\")", widget.getSelector());
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#getSession()}.
	 */
	@Test
	public void testGetSession() {
		Widget widget = new TestWidget();
		Session session = widget.getSession();
		assertNotNull(session);
		assertEquals(true, (session instanceof TestSession));
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#id()}.
	 */
	@Test
	public void testId() {
		Widget widget = new TestWidget();
		equals("<div id='c0'></div>", widget);
		assertEquals("c0", widget.id());
		assertEquals(true, widget.isRendered());
		widget = new TestWidget();
		equals("<div id='c1'></div>", widget);
		assertEquals("c1", widget.id());
		assertEquals(true, widget.isRendered());
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#isRendered()}.
	 */
	@Test
	public void testIsRendered() {
		Widget widget = new TestWidget();
		assertEquals(false, widget.isRendered());
		equals("<div id='c0'></div>", widget);
		assertEquals("c0", widget.id());
		assertEquals(true, widget.isRendered());
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#jQuery()}.
	 */
	@Test
	public void testJQuery() {
		Widget widget = new TestWidget();
		JQuery jQuery = widget.jQuery();
		assertEquals("$", jQuery.toJavaScript());
	}

	/**
	 * Test method for
	 * {@link org.whizu.widget.Widget#jQuery(org.whizu.dom.Identity[])}.
	 */
	@Test
	public void testJQueryIdentityArray() {
		Widget widget = new TestWidget();
		JQuery jQuery = widget.jQuery(widget);
		assertEquals("$(\"#c0\")", jQuery.toJavaScript());
		Widget otherWidget = new TestWidget();
		jQuery = widget.jQuery(widget, otherWidget);
		assertEquals("$(\"#c0,#c1\")", jQuery.toJavaScript());
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#jQuery(java.lang.String)}.
	 */
	@Test
	public void testJQueryString() {
		Widget widget = new TestWidget();
		JQuery jQuery = widget.jQuery("$(\"#myID\")");
		assertEquals("$(\"#myID\")", jQuery.toJavaScript());
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#render()}.
	 */
	@Test
	public void testRender() {
		Widget widget = new TestWidget();
		widget.css("myClass");
		widget.width("120px");
		assertEquals(false, widget.isRendered());
		String output = widget.render();
		assertEquals(true, widget.isRendered());
		assertEquals("<div id='c0' style='width:120px;' class='myClass '></div>", output);
	}

	/**
	 * Test method for {@link org.whizu.widget.Widget#width(java.lang.String)}.
	 */
	@Test
	public void testWidth() {
		Widget widget = new TestWidget();
		widget.width("120px");
		equals("<div id='c0' style='width:120px;'></div>", widget);
		widget = new TestWidget();
		widget.css("myClass");
		widget.width("120px");
		assertEquals(false, widget.isRendered());
		equals("<div id='c1' style='width:120px;' class='myClass '></div>", widget);
		assertEquals(true, widget.isRendered());
		widget.width("150px");
		assertEquals("$(\"#c1\").width(\"150px\");", theRequest.finish());
	}

	/**
	 * Test method for
	 * {@link org.whizu.widget.Widget#compile(org.whizu.jquery.Function)}.
	 */
	@Test
	public void testCompile() {
		Widget widget = new TestWidget();
		widget.css("myClass");
		widget.width("120px");
		Content markup = widget.compile();
		assertEquals(false, widget.isRendered());
		assertEquals("<div id='c0' style='width:120px;' class='myClass '></div>", markup.render());
		assertEquals(false, widget.isRendered());
	}
}
