package org.whizu.jquery.mobile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ButtonBuilderTest extends AbstractJqmTest {

	@Test
	public void testButtonBuilder() {
		Page next = Jqm.addPage("next");

		// @formatter:off
		/*
		Jqm.createButton("My button")
			.onClick(next)
			.build()
			.appendTo(page);
		*/
		ButtonBuild button = ButtonBuild.builder("My button")
		    .onClickOpen(next)
		    .build();
		
		page.addContent(button);
		// @formatter:on

		assertEquals(
				"$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c0' data-inline='false' data-mini='false' href='#next'>My button</a>\");",
				theRequest.finish());
	}

	@Test
	public void testJqmBuilder() {
		Page next = Jqm.addPage("next");

		// @formatter:off
		ButtonBuild button = ButtonBuilder.createWithTitle("My button")
			.onClickOpen(next)
			.build();
		page.addContent(button);
		// @formatter:on

		assertEquals(
				"$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c0' data-inline='false' data-mini='false' href='#next'>My button</a>\");",
				theRequest.finish());
	}
}
