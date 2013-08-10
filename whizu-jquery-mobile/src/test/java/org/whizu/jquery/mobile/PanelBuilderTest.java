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
package org.whizu.jquery.mobile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.whizu.html.Html;

/**
 * @author Rudy D'hauwe
 */
public class PanelBuilderTest extends AbstractJqmTest {

	private void assertResponse(String response) {
		assertEquals(response, theRequest.finish());
	}

	/**
	 * Test method for
	 * {@link org.whizu.jquery.mobile.PanelBuilder#add(org.whizu.dom.Content)}.
	 */
	@Test
	public void testAdd() {

	}

	/**
	 * Test method for
	 * {@link org.whizu.jquery.mobile.PanelBuilder#createPrototype()}.
	 */
	@Test
	public void testCreatePrototype() {

	}

	/**
	 * Test method for
	 * {@link org.whizu.jquery.mobile.PanelBuilder#createProxy()}
	 * .
	 */
	@Test
	public void testCreateProxyBuild() {

	}

	/**
	 * Test method for
	 * {@link org.whizu.jquery.mobile.PanelBuilder#createWithId(java.lang.String)}.
	 */
	@Test
	public void testCreateWithId() {
		Page page = PageBuilder.createWithId("index").build();
		Panel panel = PanelBuilder.createWithId("my-panel").add(Html.p("This is my first panel")).build();
		Button button = ButtonBuilder.createWithTitle("Open panel").onClickOpen(panel).build();
		page.add(button);
		String expected = "$p = $(\"<div data-role='page' id='index'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c1' data-rel='panel' href='#my-panel'>Open panel</a>\");$('#c1').closest(\"div[data-role=page]\").append(\"<div data-role='panel' id='my-panel'><p>This is my first panel</p></div>\");";
		assertResponse(expected);
	}
	
	/**
	 * Test method for
	 * {@link org.whizu.jquery.mobile.PanelBuilder#createWithId(java.lang.String)}.
	 */
	@Test
	public void testCreateWithId2() {
		Page page = PageBuilder.createWithId("home").build();
		Panel panel = PanelBuilder.createWithId("my-panel").add(Html.p("This is my first panel")).build();
		Button button = ButtonBuilder.createWithTitle("Open panel").onClickOpen(panel).build();
		page.add(button);
		String expected = "$p = $(\"<div data-role='page' id='home'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);$('#home').find('div[data-role=content]').append(\"<a data-role='button' id='c1' data-rel='panel' href='#my-panel'>Open panel</a>\");$('#c1').closest(\"div[data-role=page]\").append(\"<div data-role='panel' id='my-panel'><p>This is my first panel</p></div>\");";
		assertResponse(expected);
	}

	/**
	 * Test method for
	 * {@link org.whizu.jquery.mobile.PanelBuilder#disableAnimations()}.
	 */
	@Test
	public void testDisableAnimations() {

	}

	/**
	 * Test method for
	 * {@link org.whizu.jquery.mobile.PanelBuilder#disableClickOutsideToClose()}
	 * .
	 */
	@Test
	public void testDisableClickOutsideToClose() {

	}

	/**
	 * Test method for
	 * {@link org.whizu.jquery.mobile.PanelBuilder#disableSwipeToClose()}.
	 */
	@Test
	public void testDisableSwipeToClose() {

	}

	/**
	 * Test method for
	 * {@link org.whizu.jquery.mobile.PanelBuilder#fixPosition()}.
	 */
	@Test
	public void testFixPosition() {

	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PanelBuilder#left()}.
	 */
	@Test
	public void testLeft() {

	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PanelBuilder#overlay()}.
	 */
	@Test
	public void testOverlay() {

	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PanelBuilder#push()}.
	 */
	@Test
	public void testPush() {

	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PanelBuilder#reveal()}.
	 */
	@Test
	public void testReveal() {

	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PanelBuilder#right()}.
	 */
	@Test
	public void testRight() {

	}
}
