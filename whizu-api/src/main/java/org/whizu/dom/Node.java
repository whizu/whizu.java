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
package org.whizu.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The void elements in HTML 4.01/XHTML 1.0 Strict are area, base, br, col, hr,
 * img, input, link, meta, and param. HTML5 currently adds command, keygen, and
 * source to that list.
 * 
 * @author Rudy D'hauwe
 */
public class Node implements Element {

	private Map<String, String> attrs = new HashMap<String, String>();

	private ContentList contents = new ContentList();

	private List<String> cssList = new ArrayList<String>();

	private String name;

	private Map<String, String> styleMap = new HashMap<String, String>();

	private String id;

	private boolean selfClosing;

	public Node(String name) {
		this(name, false);
	}

	public Node(String name, boolean selfClosing) {
		this.name = name;
		this.selfClosing = selfClosing;
	}

	@Override
	public Element add(Content element) {
		if(selfClosing) {
			throw new IllegalStateException("A self-closing element must not add content");
		}
		
		contents.add(element);
		return this;
	}

	@Override
	public Element add(Content... elements) {
		if(selfClosing) {
			throw new IllegalStateException("A self-closing element must not add content");
		}
		
		for (Content part : elements) {
			add(part);
		}
		return this;
	}

	@Override
	public <T> Element add(Foreach<T> factory) {
		if(selfClosing) {
			throw new IllegalStateException("A self-closing element must not add content");
		}
		
		Iterator<T> it = factory.iterator();
		while (it.hasNext()) {
			T item = it.next();
			add(factory.compile(item));
		}
		return this;
	}

	@Override
	public <T extends Content> Element add(List<T> content) {
		if(selfClosing) {
			throw new IllegalStateException("A self-closing element must not add content");
		}
		
		contents.add(content);
		return this;
	}

	@Override
	public Element add(String text) {
		if(selfClosing) {
			throw new IllegalStateException("A self-closing element must not add content");
		}
		
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
	public Markup after(Content element) {
		return new ContentList(element, this);
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

	private String getCss() {
		String style = "";
		for (String key : cssList) {
			style += key + " ";
		}
		return style;
	}

	@Override
	public String id() {
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
		return style("margin", margin);
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
	public Element style(String name, String style) {
		return addStyle(name, style);
	}

	@Override
	public Element title(String title) {
		return attr("title", title);
	}

	@Override
	public String render() {
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

		if (selfClosing) {
			markup += "/>";
		} else {
			markup += ">";
			markup += contents.render();
			markup += "</" + name + ">";
		}
		return markup;
	}

	@Override
	public Element width(String width) {
		return (width == null) ? this : style("width", width);
	}

	@Override
	public Element wrap(String name) {
		Node n = new Node(name);
		return n.add(this);
	}

	@Override
	public Element css(List<String> cssList) {
		this.cssList.addAll(cssList);
		return this;
	}
}
