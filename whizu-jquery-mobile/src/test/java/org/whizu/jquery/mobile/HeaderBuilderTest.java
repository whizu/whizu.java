package org.whizu.jquery.mobile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HeaderBuilderTest extends AbstractJqmTest {

	@Test
	public void testButtons() {
		Page next = Jqm.addPage("next");
		Popup popup = Popup.builder("popup").build();

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

		assertEquals(
				"$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$.mobile.activePage.find('div[data-role=content]').append(\"<div data-role='popup' id='popup'></div>\");$('#index').prepend(\"<div data-role='header' id='c1'><h1>Popups</h1><a data-role='button' id='c2' data-inline='false' data-rel='popup' data-mini='true' href='#popup'>Popup</a><a data-role='button' id='c3' data-inline='false' data-mini='true' href='#next'>Next page</a></div>\");",
				theRequest.finish());
	}
}
