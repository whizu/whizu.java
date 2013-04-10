/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the “EUPL”) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an “AS IS” basis and 
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author Rudy D'hauwe
 */
public class NonVoid implements Html {

	public static NonVoid a() {
		return tag("a");
	}

	public static NonVoid body() {
		return tag("body");
	}

	public static NonVoid body(String id) {
		return body().id(id);
	}

	private static NonVoid br() {
		return tag("br");
	}

	public static NonVoid div() {
		return tag("div");
	}
	
	public static NonVoid input() {
		return tag("input");
	}
	
	public static NonVoid button() {
		return tag("button");
	}

	public static NonVoid div(String id) {
		return div().id(id);
	}

	public static NonVoid form(String id) {
		return tag("form").id(id);
	}

	public static NonVoid h1() {
		return tag("h1");
	}

	public static NonVoid h1(String text) {
		return h1().add(text);
	}

	private static NonVoid hr() {
		return tag("hr");
	}

	public static Img img(String id) {
		return new Img(id);
	}

	public static void main(String[] args) {
		tag("div").attr("id", "my-div").attr("class", "my-class").print();
		tag("div").id("my-div").css("my-class").print();

		// @formatter:off
		NonVoid div = div().id("my-div")
						.css("my-class")
						.border("solid 1px black")
						.padding("10px")
  						.add(h1().add("my-title"));
		
		NonVoid body = div.wrap("body").id("my-body")
						 			.add(br(), 
						 			     hr().size("1px"))
						 			.border("solid 1px black");
		// @formatter:on
		body.print();

		// @formatter:off
		NonVoid body2 = body().id("my-body")
						   .border("solid 1px black")
						   .add(div, 
								br(), 
								hr().size("1px")); 
						   
		// @formatter:on
		body2.print();

		// @formatter:off
		NonVoid body3 = body("my-body")
						   .border("solid 1px black")
						   .add(div("my-div")
								   .css("my-class")
								   .border("solid 1px black")
								   .padding("10px")
								   .add(h1("my-title")), 
								br(), 
								hr().size("1px")); 
								   
		// @formatter:on
		body3.print();

		// @formatter:off
		body("my-body").border("solid 1px black").add(
			div("my-div").css("my-class").border("solid 1px black").padding("10px").add(
				h1().add("my-title"),
				br(), 
				hr().size("1px")
			)
		).print(); 
		// @formatter:on

		List<String> list = new ArrayList<String>();
		list.add("item 1");
		list.add("item 2");

		// @formatter:off
		table("my-table").add(
			thead(
				th("column1"),
				th("column2")
			),
			tbody().add(new Foreach<String>(list) {
				@Override
				public NonVoid render(String item) {	return 
						tr(
							td(item),
							td(item)
						);
				}}
			)
		).print();
		// @formatter:on
	}

	public static NonVoid table(String id) {
		return tag("table").id(id);
	}

	public static NonVoid tag(String name) {
		return new NonVoid(name);
	}

	public static NonVoid tbody() {
		return tag("tbody");
	}

	public static NonVoid td() {
		return tag("td");
	}

	public static NonVoid td(String item) {
		return td().add(item);
	}

	public static NonVoid textarea(String id) {
		return tag("textarea").id(id);
	}

	public static NonVoid th() {
		return tag("th");
	}

	public static NonVoid th(String text) {
		return th().add(text);
	}

	public static NonVoid thead() {
		return tag("thead");
	}

	public static NonVoid thead(NonVoid... elements) {
		return thead().add(elements);
	}

	public static NonVoid tr() {
		return tag("tr");
	}

	public static NonVoid tr(NonVoid td) {
		return tr().add(td);
	}

	public static NonVoid tr(NonVoid... elements) {
		return tr().add(elements);
	}

	private List<Html> afterList = new ArrayList<Html>();

	private Map<String, String> attrs = new HashMap<String, String>();

	private List<Html> contents = new ArrayList<Html>();

	private List<String> cssList = new ArrayList<String>();
	
	private String name;
	
	private Map<String, String> styleMap = new HashMap<String, String>();

	private String id;

	public NonVoid(String name) {
		this.name = name;
	}

	public <T> NonVoid add(Foreach<T> factory) {
		Iterator<T> it = factory.iterator();
		while (it.hasNext()) {
			T item = it.next();
			add(factory.render(item));
		}
		return this;
	}

	public NonVoid add(Html element) {
		contents.add(element);
		return this;
	}

	public NonVoid add(Html... elements) {
		for (Html part : elements) {
			add(part);
		}
		return this;
	}
	
	public <T extends Renderable> NonVoid add(List<T> list) {
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			T item = it.next();
			add(item.render());
		}
		return this;
	}

	public NonVoid add(String text) {
		return add(new Text(text));
	}

	public NonVoid addCss(String clazz) {
		cssList.add(clazz);
		return this;
	}

	public NonVoid addStyle(String name, String style) {
		styleMap.put(name, style);
		return this;
	}
	
	public NonVoid after(Html element) {
		afterList.add(element);
		return this;
	}

	public NonVoid attr(String name, String value) {
		attrs.put(name, value);
		return this;
	}

	public NonVoid border(String style) {
		return style("border", style);
	}

	public NonVoid css(String className) {
		return (className == null) ? this : addCss(className);
	}

	private String getCss() {
		String style = "";
		for (String key : cssList) {
			style += key + " ";
		}
		return style;
	}

	private String getStyle() {
		String style = "";
		for (String key : styleMap.keySet()) {
			style += key + ":" + styleMap.get(key) + ";";
		}
		return style;
	}
	
	public NonVoid height(String height) {
		return style("height", height);
	}

	NonVoid id(String id) {
		this.id = id;
		return attr("id", id);
	}

	public NonVoid padding(String style) {
		return style("padding", style);
	}

	public void print() {
		System.out.println(toString());
	}

	private String quote(String value) {
		return "'" + value + "'";
	}

	public NonVoid size(String size) {
		return attr("size", size);
	}

	public NonVoid style(String name, String style) {
		return addStyle(name, style);
	}

	@Override
	public String toString() {
		String markup = "<" + name;
		for (String key : attrs.keySet()) {
			markup += " " + key + "=" + quote(attrs.get(key));
		}

		if (!styleMap.isEmpty()) {
			markup += " " + "style=" + quote(getStyle());
		}
		
		if (!cssList.isEmpty()) {
			markup += " " + "class=" + quote(getCss());
		}
		
		markup += ">";

		for (Html element : contents) {
			markup += element.toString();
		}

		markup += "</" + name + ">";
		
		for (Html n : afterList) {
			markup += n.toString();
		}
		
		return markup;
	}

	public NonVoid width(String width) {
		return (width == null) ? this : style("width", width);
	}

	public NonVoid wrap(String name) {
		NonVoid n = tag(name);
		return n.add(this);
	}

	public static NonVoid input(String id) {
		return input().id(id);
	}

	public static NonVoid button(String id) {
		return button().id(id);
	}

	public static NonVoid a(String id) {
		return a().id(id);
	}

	public String getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	public <T extends NonVoid> T decorate(Decorator... decorators) {
		for (Decorator d : decorators) {
			if (d != null) {
				d.decorate(this);
			}
		}
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public <T extends NonVoid> T  decorate(String name, Decorator decorator) {
		if (decorator != null) {
			decorator.decorate(name, this);
		}
		return (T) this;
	}
}
