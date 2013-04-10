/**
 * 
 */
package org.whizu.dom;


/**
 * A bunch of convenience methods for generating HTML elements.
 * 
 * @author Rudy D'hauwe
 */
public class Html {

	public static Element a() {
		return tag("a");
	}

	public static Element a(Identity element) {
		return a(element.getId());
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

	public static Element br() {
		return new Node("br");
	}

	public static Element button() {
		return tag("button");
	}

	public static Element button(Identity element) {
		return button(element.getId());
	}

	public static Element button(String id) {
		return button().id(id);
	}

	public static Element div() {
		return tag("div");
	}

	public static Element div(Identity element) {
		return div(element.getId());
	}

	public static Element div(String id) {
		return div().id(id);
	}

	public static Element form(Identity element) {
		return form(element.getId());
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

	public static Element hr() {
		return new Node("hr");
	}

	public static Element img(String id) {
		return tag("img").id(id);
	}

	public static Element input() {
		return tag("input");
	}

	public static Element input(Identity element) {
		return input(element.getId());
	}

	public static Element input(String id) {
		return input().id(id);
	}

	public static Element select(Identity element) {
		return select(element.getId());
	}

	public static Element select(String id) {
		return tag("select").id(id);
	}

	public static Element table(String id) {
		return tag("table").id(id);
	}

	public static Element tag(String name) {
		return new Node(name);
	}

	public static Element tbody() {
		return tag("tbody");
	}

	public static Element td() {
		return tag("td");
	}

	public static Element td(String item) {
		return td().add(item);
	}

	public static Element textarea(Identity element) {
		return textarea(element.getId());
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

	public static Element tr() {
		return tag("tr");
	}

	public static Element tr(Element... elements) {
		return tr().add(elements);
	}

	public static Element tr(Element td) {
		return tr().add(td);
	}
}
