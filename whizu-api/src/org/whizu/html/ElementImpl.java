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

import org.whizu.dom.Content;
import org.whizu.dom.Decorator;
import org.whizu.dom.Element;
import org.whizu.dom.Foreach;
import org.whizu.dom.Literal;

/**
 * @author Rudy D'hauwe
 */
class ElementImpl implements Element {

	public static void main(String[] args) {
		print(Html.tag("div").attr("id", "my-div").attr("class", "my-class"));
		print(Html.tag("div").id("my-div").css("my-class"));

		// @formatter:off
		Element div = Html.div().id("my-div")
						.css("my-class")
						.border("solid 1px black")
						.padding("10px")
  						.add(Html.h1().add("my-title"));
		
		Element body = div.wrap("body").id("my-body")
						 			.add(Html.br(), 
						 					Html.hr().size("1px"))
						 			.border("solid 1px black");
		// @formatter:on
		print(body);

		// @formatter:off
		Element body2 = Html.body().id("my-body")
						   .border("solid 1px black")
						   .add(div, 
								Html.br(), 
								Html.hr().size("1px")); 
						   
		// @formatter:on
		print(body2);

		// @formatter:off
		Element body3 = Html.body("my-body")
						   .border("solid 1px black")
						   .add(Html.div("my-div")
								   .css("my-class")
								   .border("solid 1px black")
								   .padding("10px")
								   .add(Html.h1("my-title")), 
								   Html.br(), 
								Html.hr().size("1px")); 
								   
		// @formatter:on
		print(body3);

		// @formatter:off
		print(Html.body("my-body").border("solid 1px black").add(
			Html.div("my-div").css("my-class").border("solid 1px black").padding("10px").add(
				Html.h1().add("my-title"),
				Html.br(), 
				Html.hr().size("1px")
			)
		)); 
		// @formatter:on

		List<String> list = new ArrayList<String>();
		list.add("item 1");
		list.add("item 2");

		// @formatter:off
		print(Html.table("my-table").add(
			Html.thead(
				Html.th("column1"),
				Html.th("column2")
			),
			Html.tbody().add(new Foreach<String>(list) {
				@Override
				public Content render(String item) {	return 
						Html.tr(
							Html.td(item),
							Html.td(item)
						);
				}}
			)
		));
		// @formatter:on
	}

	// private List<Content> afterList = new ArrayList<Content>();

	private static void print(Element element) {
		System.out.println(element.toString());
	}

	private Map<String, String> attrs = new HashMap<String, String>();

	private ContentList contents = new ContentList();

	private List<String> cssList = new ArrayList<String>();

	private String name;

	private Map<String, String> styleMap = new HashMap<String, String>();

	private String id;

	ElementImpl(String name) {
		this.name = name;
	}

	@Override
	public Element add(Content element) {
		contents.add(element);
		return this;
	}

	@Override
	public Element add(Content... elements) {
		for (Content part : elements) {
			add(part);
		}
		return this;
	}

	@Override
	public <T> Element add(Foreach<T> factory) {
		Iterator<T> it = factory.iterator();
		while (it.hasNext()) {
			T item = it.next();
			add(factory.render(item));
		}
		return this;
	}

	@Override
	public <T extends Content> Element add(List<T> content) {
		contents.add(content);
		return this;
	}

	@Override
	public Element add(String text) {
		return add(new Literal(text));
	}

	public Element addCss(String clazz) {
		cssList.add(clazz);
		return this;
	}

	public Element addStyle(String name, String style) {
		styleMap.put(name, style);
		return this;
	}

	@Override
	public Content after(Content element) {
		return new ContentList(element, this);
		// afterList.add(element);
		// return this;
	}

	@Override
	public Element attr(String name, String value) {
		if (value != null) {
			attrs.put(name, value);
		}
		return this;
	}

	@Override
	public Element border(String style) {
		return style("border", style);
	}

	@Override
	public Element css(String className) {
		return (className == null) ? this : addCss(className);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Element> T decorate(Decorator... decorators) {
		for (Decorator d : decorators) {
			if (d != null) {
				d.decorate(this);
			}
		}
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Element> T decorate(String name, Decorator decorator) {
		if (decorator != null) {
			decorator.decorate(name, this);
		}
		return (T) this;
	}

	private String getCss() {
		String style = "";
		for (String key : cssList) {
			style += key + " ";
		}
		return style;
	}

	@Override
	public String getId() {
		return id;
	}

	private String getStyle() {
		String style = "";
		for (String key : styleMap.keySet()) {
			style += key + ":" + styleMap.get(key) + ";";
		}
		return style;
	}

	public Element height(String height) {
		// attr("height", height);
		return style("height", height);
	}

	@Override
	public Element id(String id) {
		this.id = id;
		return attr("id", id);
	}

	@Override
	public Element margin(String margin) {
		return attr("margin", margin);
	}

	@Override
	public Element padding(String style) {
		return style("padding", style);
	}

	private String quote(String value) {
		return "'" + value + "'";
	}

	@Override
	public Element size(String size) {
		return attr("size", size);
	}

	@Override
	public Element src(String src) {
		return attr("src", src);
	}

	@Override
	public final String toString() {
		return stream();
	}

	@Override
	public Element style(String name, String style) {
		return addStyle(name, style);
	}

	@Override
	public Element title(String title) {
		return attr("title", title);
	}

	@Override
	public String stream() {
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

		markup += contents.stream();

		/*
		 * for (Content element : contents) { markup += element.toString(); }
		 */

		markup += "</" + name + ">";

		/*
		 * for (Content n : afterList) { markup += n.toString(); }
		 */

		return markup;
	}

	@Override
	public Element width(String width) {
		return (width == null) ? this : style("width", width);
	}

	@Override
	public Element wrap(String name) {
		ElementImpl n = new ElementImpl(name);
		return n.add(this);
	}
}
