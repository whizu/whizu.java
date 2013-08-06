package org.whizu.jquery.mobile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.whizu.jquery.ClickListener;
import org.whizu.value.StringValue;

public class FormsTest extends AbstractJqmTest {

	@Test
	public void testButtonBuilder() {
		Jqm.createPage("next");

		StringValue name = new StringValue("Name");

		// @formatter:off
		Header.builder()
		    .title("Forms")
		    .build()
		    .on(page);
		// @formatter:on

		Form form = FormBuilder.create().onSubmit(submit(name)).addText(name).build();
		//form.onSubmit(submit(name));
		//form.addText(name);
		page.add(form);

		// "$p = $(\"<div data-role='page' id='next'><div data-role='content'>page next</div></div>\"); $p.appendTo($.mobile.pageContainer); ;$('#index').prepend(\"<div data-role='header' id='c0'><h1>Forms</h1></div>\");$('#index').append(\"<form id='c1' method='post'><label for='c2'>Name</label><input id='c2' name='c2' value='' type='text'/></form>\");$(\"#c1\").submit(function(event) { event.preventDefault();alert('submit form');return false; });",

		String expected = "$p = $(\"<div data-role='page' id='next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$('#index').prepend(\"<div data-role='header' id='c1'><h1>Forms</h1></div>\");$('#index').find('div[data-role=content]').append(\"<form id='c2' method='post'><label for='c4'>Name</label><input id='c4' name='c4' value='' type='text'/></form>\");$('#c2').submit(function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c3', $(this).closest(\"form\").serialize(), function(data) {  }, 'script');return false; });";
		assertEquals(expected, theRequest.finish());

	}

	private ClickListener submit(final StringValue name) {
		return new ClickListener() {

			@Override
			public void click() {
				Jqm.page().add("Name entered " + name.get());
			}
		};
	}
}
