package org.whizu.jquery.mobile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HeaderBuilderTest extends AbstractJqmTest {

	@Test
	public void testHeaderWithMultipleButtons() {
		Page page = Jqm.index();
		
		Header header = HeaderBuilder.create()
			.title("Heading")
			.button("button 1").build()
			.button("button 2").onClickOpen(page).build()
			.button("button 3").build()
			.build();
		
		assertEquals("<div data-role='header' id='c0'><h1>Heading</h1><a data-role='button' id='c1' data-mini='true'>button 1</a><a data-role='button' id='c2' data-mini='true' href='#index'>button 2</a><a data-role='button' id='c3' data-mini='true'>button 3</a></div>", header.render());
	}
	
	@Test
	public void testHeaderWithMultipleButtons2() {
		Button b1 = ButtonBuilder.createAnchorButton("button 1").build();
		Button b2 = ButtonBuilder.createAnchorButton("button 2").onClickOpen(page).build();
		Button b3 = ButtonBuilder.createAnchorButton("button 3").build();
		
		Header header = HeaderBuilder.create()
			.title("Heading")
			.button(b1)
			.button(b2)
			.button(b3)
			.build();
		
		assertEquals("<div data-role='header' id='c3'><h1>Heading</h1><a data-role='button' id='c0'>button 1</a><a data-role='button' id='c1' href='#index'>button 2</a><a data-role='button' id='c2'>button 3</a></div>", header.render());
	}
	
	@Test
	public void testHeaderWithMultipleButtons3() {
		Button b1 = ButtonBuilder.createAnchorButton("button 1").build();
		Button b2 = ButtonBuilder.createAnchorButton("button 2").onClickOpen(page).build();
		Button b3 = ButtonBuilder.createAnchorButton("button 3").build();
		
		Header header = HeaderBuilder.create()
			.title("Heading")
			.button(b1)
			.button(b2)
			.button("Mijn button").onClickOpen(page).build()
			.button(b3)
			.build();
		
		assertEquals("<div data-role='header' id='c3'><h1>Heading</h1><a data-role='button' id='c0'>button 1</a><a data-role='button' id='c1' href='#index'>button 2</a><a data-role='button' id='c4' data-mini='true' href='#index'>Mijn button</a><a data-role='button' id='c2'>button 3</a></div>", header.render());
	}
	
	@Test
	public void testButtons() {
		Page next = PageBuilder.createWithId("next").build();
		Popup popup = PopupBuilder.createWithId("popup").build();

		// @formatter:off
		Header.builder()
		    .title("Popups")
		    .button("Popup")
		        .onClickOpen(popup)
		        .build()
		    .button("Next page")
		        .onClickOpen(next)
		        .build()
		    .build()
		    .on(page);		
		// @formatter:on

		String actual = "$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);$('#index').prepend(\"<div data-role='header' id='c2'><h1>Popups</h1><a data-role='button' id='c3' data-rel='popup' data-mini='true' href='#popup'>Popup</a><a data-role='button' id='c4' data-mini='true' href='#next'>Next page</a></div>\");$('#c3').closest(\"div[data-role=page]\").find('div[data-role=content]').append(\"<div data-role='popup' id='popup'></div>\");";
		String werkte_vroeger = "$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);$.mobile.activePage.find('div[data-role=content]').append(\"<div data-role='popup' id='popup'></div>\");$('#index').prepend(\"<div data-role='header' id='c1'><h1>Popups</h1><a data-role='button' id='c2' data-rel='popup' data-mini='true' href='#popup'>Popup</a><a data-role='button' id='c3' data-mini='true' href='#next'>Next page</a></div>\");";

		assertEquals(actual, theRequest.finish());
	}

	@Test
	public void testButtonsMiniInline() {
		Page next = PageBuilder.createWithId("next").build();
		Popup popup = PopupBuilder.createWithId("popup").build();

		// @formatter:off
		Header.builder()
		    .title("Popups")
		    .button("Popup")
		        .onClickOpen(popup)
		        .build()
		    .button("Next page")
		        .onClickOpen(next)
		        .build()
		    .build()
		    .on(page);		
		// @formatter:on

		String werkte_vroeger = "$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);$.mobile.activePage.find('div[data-role=content]').append(\"<div data-role='popup' id='popup'></div>\");$('#index').prepend(\"<div data-role='header' id='c1'><h1>Popups</h1><a data-role='button' id='c2' data-rel='popup' data-mini='true' href='#popup'>Popup</a><a data-role='button' id='c3' data-mini='true' href='#next'>Next page</a></div>\");";
		String actual = "$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer);$('#index').prepend(\"<div data-role='header' id='c2'><h1>Popups</h1><a data-role='button' id='c3' data-rel='popup' data-mini='true' href='#popup'>Popup</a><a data-role='button' id='c4' data-mini='true' href='#next'>Next page</a></div>\");$('#c3').closest(\"div[data-role=page]\").find('div[data-role=content]').append(\"<div data-role='popup' id='popup'></div>\");";
		
		assertEquals(
				actual,
				theRequest.finish());
	}
}
