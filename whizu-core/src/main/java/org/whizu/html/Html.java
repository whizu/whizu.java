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
package org.whizu.html;

import org.whizu.dom.Component;
import org.whizu.dom.Content;
import org.whizu.dom.Element;
import org.whizu.dom.Identity;
import org.whizu.dom.Markup;
import org.whizu.dom.Node;
import org.whizu.widget.Widget;

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
	
	public static Element h2() {
		return tag("h2");
	}
	
	public static Element h3() {
		return tag("h3");
	}

	public static Content h3(String text) {
		return h3().add(text);
	}
	
	public static Content h2(String text) {
		return h2().add(text);
	}

	public static Element hr() {
		return new Node("hr", true);
	}

	public static Element img(String id) {
		return tag("img").id(id);
	}

	public static Element input() {
		return selfClosing("input");
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

	public static Element script(String src) {
		return tag("script").attr("type", "text/javascript").attr("src", src);
	}

	public static Element select(Identity element) {
		return select(element.id());
	}

	public static Element select(String id) {
		return tag("select").id(id);
	}

	protected static Element selfClosing(String name) {
		return new Node(name, true);
	}

	public static Table table(Identity element) {
		return table(element.id());
	}

	public static Table table(String id) {
		return new Table(id);
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

	public static Element td(String id) {
		return td().id(id);
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

	public static Element ul() {
		return tag("ul");
	}

	public static Element li() {
		return tag("li");
	}

	public static Element p(String text) {
		return tag("p").add(text);
	}

	public static Element ul(Identity element) {
		return ul().id(element.id());
	}

	public static Element li(Identity element) {
		return li().id(element.id());
	}
}
