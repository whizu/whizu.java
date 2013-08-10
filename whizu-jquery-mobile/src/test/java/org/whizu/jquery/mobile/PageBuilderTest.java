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
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.whizu.html.Html;
import org.whizu.proxy.Proxy;
import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
public class PageBuilderTest extends AbstractJqmTest {

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#createWithId(java.lang.String)}.
	 */
	@Test
	public void testCreateWithId() {
		PageBuilder builder = PageBuilder.createWithId("my-page-id");
		Page page = builder.build();
		assertEquals("my-page-id", page.id());
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
		assertEquals("$p = $(\"<div data-role='page' id='my-page-id'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);", theRequest.finish());
		Page implAfterRendering = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", implAfterRendering.getClass().getName());
	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#findById(java.lang.String)}.
	 */
	@Test
	public void testJQueryById() {
		Page page = PageBuilder.findById("my-page-id");
		assertEquals("my-page-id", page.id());
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
		assertEquals("", theRequest.finish());
		page.add("my-content");
		assertEquals("$('#my-page-id').find('div[data-role=content]').append(\"my-content\");", theRequest.finish());
	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#add(org.whizu.content.Content)}.
	 */
	@Test
	public void testAddContent() {
		PageBuilder builder = PageBuilder.createWithId("my-page-id");
		builder.add(Html.p("my-paragraph"));
		Page page = builder.build();
		assertEquals("my-page-id", page.id());
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
		assertEquals("$p = $(\"<div data-role='page' id='my-page-id'><div data-role='content'><p>my-paragraph</p></div></div>\"); $p.appendTo($.mobile.pageContainer);", theRequest.finish());
	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#add(org.whizu.content.Content)}.
	 */
	@Test
	public void testAddContent2() {
		PageBuilder builder = PageBuilder.createWithId("my-page-id");
		builder.add(Html.p("my-paragraph"));
		builder.add(Html.p("other-paragraph"));
		Page page = builder.build();
		assertEquals("my-page-id", page.id());
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
		assertEquals("$p = $(\"<div data-role='page' id='my-page-id'><div data-role='content'><p>my-paragraph</p><p>other-paragraph</p></div></div>\"); $p.appendTo($.mobile.pageContainer);", theRequest.finish());
	}
	
	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#add(java.lang.String)}.
	 */
	@Test
	public void testAddString() {
		PageBuilder builder = PageBuilder.createWithId("my-page-id");
		builder.add("<p>my-paragraph</p>");
		Page page = builder.build();
		assertEquals("my-page-id", page.id());
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
		assertEquals("$p = $(\"<div data-role='page' id='my-page-id'><div data-role='content'><p>my-paragraph</p></div></div>\"); $p.appendTo($.mobile.pageContainer);", theRequest.finish());
	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#createPrototype()}.
	 */
	@Test
	public void testCreatePrototype() {
		PageBuilder builder = PageBuilder.createWithId("my-page-id");
		Page page = builder.build();
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#createProxy()}.
	 */
	@Test
	public void testCreateProxyBuild() {
		PageBuilder builder = PageBuilder.createWithId("my-page-id");
		Page page = builder.build();
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#footer(java.lang.String)}.
	 */
	@Test
	public void testFooter() {
		Page page = PageBuilder.createWithId("my-page-id").footer("my-footer").build();
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
		assertEquals("$p = $(\"<div data-role='page' id='my-page-id'><div data-role='content'></div><div data-role='footer' id='c1' data-theme='e'><h4>my-footer</h4></div></div>\"); $p.appendTo($.mobile.pageContainer);", theRequest.finish());
	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#header(java.lang.String)}.
	 */
	@Test
	public void testHeader() {
		Page page = PageBuilder.createWithId("my-page-id").header("my-header").build();
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
		assertEquals("$p = $(\"<div data-role='page' id='my-page-id'><div data-role='header' id='c1'><h1>my-header</h1></div><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);", theRequest.finish());
	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#p(java.lang.String)}.
	 */
	@Test
	public void testPString() {
		PageBuilder builder = PageBuilder.createWithId("my-page-id");
		builder.p("my-paragraph");
		Page page = builder.build();
		assertEquals("my-page-id", page.id());
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
		assertEquals("$p = $(\"<div data-role='page' id='my-page-id'><div data-role='content'><p>my-paragraph</p></div></div>\"); $p.appendTo($.mobile.pageContainer);", theRequest.finish());		
	}

	/**
	 * Test method for {@link org.whizu.jquery.mobile.PageBuilder#p(java.lang.String, java.lang.Object[])}.
	 */
	@Test
	public void testPStringObjectArray() {
		Page foo = PageBuilder.findById("foo");
		assertEquals("foo", foo.id());
		PageBuilder builder = PageBuilder.createWithId("my-page-id");
		builder.p("Return to {}", foo);
		Page page = builder.build();
		assertEquals("my-page-id", page.id());
		assertTrue(page instanceof Proxy<?>);
		Proxy<Page> proxy = Objects.cast(page);
		Page impl = proxy.impl();
		assertEquals("org.whizu.jquery.mobile.PageProxy$PageImpl", impl.getClass().getName());
		assertEquals("$p = $(\"<div data-role='page' id='my-page-id'><div data-role='content'><p>Return to <a href='#foo'>foo</a></p></div></div>\"); $p.appendTo($.mobile.pageContainer);", theRequest.finish());		
	}
}
