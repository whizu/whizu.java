/**
 * 
 */
package org.whizu.html;

import org.whizu.dom.Content;
import org.whizu.dom.Element;
import org.whizu.dom.Identity;
import org.whizu.dom.Markup;
import org.whizu.dom.Node;

/**
 * A bunch of convenience methods for generating HTML content.
 * 
 * @author Rudy D'hauwe
 */
public class Html {

	public static Element a() {
		return tag("a");
	}

	public static Element a(Identity element) {
		return a(element.id());
	}

	public static Element a(String id) {
		return a().id(id);
	}

	public static Element body() {
		return tag("body");
	}

	public static Element body(String id) {
		return body().id(id);
	}

	public static Markup br() {
		return new Node("br", true);
	}

	public static Element button() {
		return tag("button");
	}

	public static Element button(Identity element) {
		return button(element.id());
	}

	public static Element button(String id) {
		return button().id(id);
	}

	public static Element div() {
		return tag("div");
	}

	public static Element div(Identity element) {
		return div(element.id());
	}

	public static Element div(String id) {
		return div().id(id);
	}

	public static Element form(Identity element) {
		return form(element.id());
	}
	public static Element form(String id) {
		return tag("form").id(id);
	}

	public static Element h1() {
		return tag("h1");
	}

	public static Element h1(String text) {
		return h1().add(text);
	}
	
	public static Element h3() {
		return tag("h3");
	}

	public static Content h3(String text) {
		return h3().add(text);
	}

	public static Element hr() {
		return new Node("hr", true);
	}

	public static Element img(String id) {
		return tag("img").id(id);
	}

	public static Element input() {
		return tag("input");
	}

	public static Element input(Identity element) {
		return input(element.id());
	}

	public static Element input(String id) {
		return input().id(id);
	}

	public static Element label() {
		return tag("label");
	}

	public static Element select(Identity element) {
		return select(element.id());
	}

	public static Element select(String id) {
		return tag("select").id(id);
	}

	public static Element table(Identity element) {
		return table(element.id());
	}

	public static Element table(String id) {
		return tag("table").id(id);
	}

	public static Element tag(String name) {
		return new Node(name);
	}

	public static Tbody tbody() {
		return new Tbody();
	}

	public static Td td() {
		return new Td();
	}

	public static Element td(String item) {
		return td().add(item);
	}

	public static Element textarea(Identity element) {
		return textarea(element.id());
	}

	public static Element textarea(String id) {
		return tag("textarea").id(id);
	}

	public static Element th() {
		return tag("th");
	}

	public static Element th(String text) {
		return th().add(text);
	}

	public static Element thead() {
		return tag("thead");
	}

	public static Element thead(Element... elements) {
		return thead().add(elements);
	}

	public static Tr tr() {
		return new Tr();
	}

	public static Element tr(Element... elements) {
		return tr().add(elements);
	}

	public static Element tr(Element td) {
		return tr().add(td);
	}
}
