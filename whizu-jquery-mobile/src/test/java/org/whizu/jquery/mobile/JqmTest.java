package org.whizu.jquery.mobile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.whizu.util.Objects;

public class JqmTest extends AbstractJqmTest {

	@Test
	public void testHeaders() {
		Page next = Jqm.createPage("Next");
		page.header("Huidige pagina");
		Button button1 = ButtonBuilder.createWithTitle("My button 1").onClickOpen(next).build();
		page.add(button1);
		next.header("Volgende pagina");
		Button button2 = ButtonBuilder.createWithTitle("My button 2").onClickOpen(page).build();
		next.add(button2);
		String expected = "$p = $(\"<div data-role='page' id='Next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$('#index').prepend(\"<div data-role='header' id='c1'><h1>Huidige pagina</h1></div>\");$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c2' href='#Next'>My button 1</a>\");$('#Next').prepend(\"<div data-role='header' id='c3'><h1>Volgende pagina</h1></div>\");$('#Next').find('div[data-role=content]').append(\"<a data-role='button' id='c4' href='#index'>My button 2</a>\");";
		assertEquals(
				expected,
				theRequest.finish());
	}

	@Test
	public void testMultipage() {
		page.header("Welcome !");
		Page foo = Jqm.createPage("foo");
		Page bar = Jqm.createPage("bar");
		foo.header("Foos");
		foo.p("I'm first in the source order so I'm shown as the page.");
		foo.footer("Page Footer");
		bar.header("Bars");
		bar.p("I'm the second in the source order so I'm hidden when the page loads. I'm just shown if a link that references my id is being clicked.");
		bar.footer("Page Footer");
		Button button1 = ButtonBuilder.createWithTitle("foo").onClickOpen(foo).build();
		page.add(button1);
		Button button2 = ButtonBuilder.createWithTitle("bar").onClickOpen(bar).build();
		page.add(button2);
		assertEquals(
				"$('#index').prepend(\"<div data-role='header' id='c0'><h1>Welcome !</h1></div>\");$p = $(\"<div data-role='page' id='foo'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$p = $(\"<div data-role='page' id='bar'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$('#foo').prepend(\"<div data-role='header' id='c3'><h1>Foos</h1></div>\");$('#foo').find('div[data-role=content]').append(\"<p>I'm first in the source order so I'm shown as the page.</p>\");$('#foo').append(\"<div data-role='footer' id='c4' data-theme='e'><h4>Page Footer</h4></div>\");$('#bar').prepend(\"<div data-role='header' id='c5'><h1>Bars</h1></div>\");$('#bar').find('div[data-role=content]').append(\"<p>I'm the second in the source order so I'm hidden when the page loads. I'm just shown if a link that references my id is being clicked.</p>\");$('#bar').append(\"<div data-role='footer' id='c6' data-theme='e'><h4>Page Footer</h4></div>\");$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c7' href='#foo'>foo</a>\");$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c8' href='#bar'>bar</a>\");",
				theRequest.finish());
	}

	@Test
	public void testSinglepage() {
		page.header("Page Title Do it Now");
		page.p("Page content goes here. <a href='#first'>first page</a> <a href='#second'>second page</a>");
		page.footer("Page Footer");
		page = Jqm.createPage("second");
		page.header("my second header");
		page = Jqm.createPage("first");
		page.p("hello <a href='#'>home</a> <a href='#index'>index</a>");
		page.header("Cool");
		String expected = "$('#index').prepend(\"<div data-role='header' id='c0'><h1>Page Title Do it Now</h1></div>\");$('#index').find('div[data-role=content]').append(\"<p>Page content goes here. <a href='#first'>first page</a> <a href='#second'>second page</a></p>\");$('#index').append(\"<div data-role='footer' id='c1' data-theme='e'><h4>Page Footer</h4></div>\");$p = $(\"<div data-role='page' id='second'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$('#second').prepend(\"<div data-role='header' id='c3'><h1>my second header</h1></div>\");$p = $(\"<div data-role='page' id='first'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$('#first').find('div[data-role=content]').append(\"<p>hello <a href='#'>home</a> <a href='#index'>index</a></p>\");$('#first').prepend(\"<div data-role='header' id='c5'><h1>Cool</h1></div>\");";
		assertEquals(
				expected,
				theRequest.finish());
	}

	@Test
	public void testPopup() {
		Popup popup = PopupBuilder.createWithId("popup").p("My second popup with same id").build();
		String actual = "<div data-role='popup' id='popup'><p>My second popup with same id</p></div>";
		PopupProxy proxy = Objects.cast(popup);
		assertEquals(actual, proxy.render());
	}

	@Test
	public void testPopups() {
		Page next = Jqm.createPage("Next");
		Popup popup = PopupBuilder.createWithId("popup").p("My first text").build();
		// page.append(popup);
		popup = PopupBuilder.createWithId("popup").p("My second popup with same id").build();
		// page.append(popup);
		Header.builder().title("Popups").button("New").onClickOpen(popup).build().build().on(page);
		Button button1 = ButtonBuilder.createWithTitle("My button 1").onClickOpen(next).build();
		page.add(button1);
		next.header("Volgende pagina");
		Button button2 = ButtonBuilder.createWithTitle("My button 2").onClickOpen(page).build();
		next.add(button2);
		
		String actual = "$p = $(\"<div data-role='page' id='Next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$('#index').prepend(\"<div data-role='header' id='c3'><h1>Popups</h1><a data-role='button' id='c4' data-rel='popup' data-mini='true' href='#popup'>New</a></div>\");$('#c4').closest(\"div[data-role=page]\").find('div[data-role=content]').append(\"<div data-role='popup' id='popup'><p>My second popup with same id</p></div>\");$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c5' href='#Next'>My button 1</a>\");$('#Next').prepend(\"<div data-role='header' id='c6'><h1>Volgende pagina</h1></div>\");$('#Next').find('div[data-role=content]').append(\"<a data-role='button' id='c7' href='#index'>My button 2</a>\");";
		String werkte_vroeger = "$p = $(\"<div data-role='page' id='Next'><div data-role='content'></div></div>\"); $p.appendTo($.mobile.pageContainer); ;$.mobile.activePage.find('div[data-role=content]').append(\"<div data-role='popup' id='popup'><p>My first text</p></div>\");$.mobile.activePage.find('div[data-role=content]').append(\"<div data-role='popup' id='popup'><p>My second popup with same id</p></div>\");$('#index').prepend(\"<div data-role='header' id='c2'><h1>Popups</h1><a data-role='button' id='c3' data-rel='popup' data-mini='true' href='#popup'>New</a></div>\");$('#index').find('div[data-role=content]').append(\"<a data-role='button' id='c4' href='#Next'>My button 1</a>\");$('#Next').prepend(\"<div data-role='header' id='c5'><h1>Volgende pagina</h1></div>\");$('#Next').find('div[data-role=content]').append(\"<a data-role='button' id='c6' href='#index'>My button 2</a>\");";
		assertEquals(
				actual,
				theRequest.finish());
	}
}
