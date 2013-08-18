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
package org.whizu.jquery;

import org.apache.commons.lang.StringEscapeUtils;
import org.whizu.content.Content;
import org.whizu.content.Identity;
import org.whizu.js.Expression;
import org.whizu.js.JavaScript;
import org.whizu.js.Script;

/**
 * @author Rudy D'hauwe
 */
class JQueryImpl extends Expression implements JQuery {

	/*
	 * static JQuery self() { return new JQueryImpl("$(this)"); }
	 */

	public static final JQuery jQuery(String selector) {
		JQueryImpl query = new JQueryImpl(selector);
		return RequestContext.getRequest().addExpression(query);
	}
	
	private final String selector_;

	JQueryImpl(Identity element) {
		selector_ = getSelector(element);
	}

	JQueryImpl(Identity... elements) {
		selector_ = getSelector(elements);
	}

	JQueryImpl(String selector) {
		selector_ = selector;
	}

	@Override
	public JQuery addClass(String style) {
		return call("addClass", style);
	}

	@Override
	public JQuery after(String... content) {
		return call("after", content);
	}

	@Override
	public JQuery append(Content content) {
		return call("append", content);
	}

	@Override
	public JQuery append(String... content) {
		return call("append", content);
	}

	@Override
	public JQuery appendTo(String target) {
		return call("appendTo", target);
	}

	@Override
	public String attr(String attributeName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public JQuery before(String... content) {
		return call("before", content);
	}

	@Override
	public JQuery button() {
		return call("button");
	}

	@Override
	public JQuery call(String function) {
		return concat(".", function, "()");
	}

	private JQuery call(String function, Content arg) {
		concat(".", function, "(\"");
		add(arg);
		concat("\")");
		return this;
	}

	public JQuery call(String function, int... args) {
		String arguments = "" + args[0] + "";
		for (int i = 1; i < args.length; i++) {
			arguments = arguments.concat(",").concat("" + args[i]);
		}
		return concat(".", function, "(", arguments, ")");
	}

	@Override
	public JQuery call(String function, String arg) {
		return concat(".", function, "(\"", arg, "\")");
	}

	@Override
	public JQuery call(String function, String... args) {
		String arguments = "\"" + args[0] + "\"";
		for (int i = 1; i < args.length; i++) {
			arguments = arguments.concat(",").concat("'").concat(args[i]).concat("'");
		}
		return concat(".", function, "(", arguments, ")");
	}

	public JQuery call(String function, String arg0, String... args) {
		String arguments = "'" + arg0 + "'";
		for (int i = 0; i < args.length; i++) {
			arguments = arguments.concat(",").concat("'").concat(args[i]).concat("'");
		}
		return concat(".", function, "(", arguments, ")");
	}

	@Override
	public JQuery callunquoted(String function, String arglist) {
		return concat(".", function, "(", arglist, ")");
	}

	@Override
	public JQuery click(final EventHandler eventHandler) {
		if (eventHandler != null) {
			click(getEventHandlerFunction(eventHandler));
		}
		return this;
	}

	@Override
	public JQuery click(Function function) {
		Script script = RequestContext.getRequest().compile(function);
		return concat(".", "click", "(function(event) { ", script.toJavaScript(), " })");
	}

	@Override
	public JQuery closest(String name) {
		return call("closest", name);
	}

	@Override
	public JQuery concat(String... js) {

		for (String part : js) {
			// this.expression += part;
			add(part);
		}
		return this;
	}

	@Override
	public JQuery document() {
		return new JQueryImpl("$('#y-body')");
	}

	@Override
	public JQuery empty() {
		return call("empty");
	}
	
	@Override
	public JQuery fadeTo(int i, int j) {
		return call("fadeTo", i, j);
	}

	@Override
	public JQuery find(String selector) {
		return concat(".", "find(", quote(selector), ")");
	}

	@Override
	public JQuery first() {
		return concat(".", "first()");
	}

	@Override
	public JQuery firstOfType(String element) {
		// return call("filter", element + ":first-of-type");
		return call("filter", element);
	}

	@Override
	public JQuery get(String url, Function data, Function callback, String type) {
		return concat(".", "get(", quote(url), ", ", RequestContext.getRequest().evaluate(data), ", ", RequestContext
				.getRequest().define(callback), ", ", quote(type), ")");
	}

	private String getContextPath() {
		return RequestContext.getContextPath();
	}

	private Function getEventHandlerFunction(final EventHandler eventHandler) {
		return new Function() {
			@Override
			public void execute() {
				JavaScript.preventDefault();

				String url = getContextPath() + "/whizu/event?id=" + eventHandler.id();

				Function data = new Function() {
					@Override
					public void execute() {
						jQuery("$(this)").closest("form").serialize();
					}
				};

				Function callback = new Function("data") {
					@Override
					public void execute() {
					}
				};

				String type = "script";
				jQuery("$").get(url, data, callback, type);
				
				JavaScript.script("return false;");
			}
		};
	}

	private Function getItemHandlerFunction(final EventHandler eventHandler) {
		return new Function() {
			@Override
			public void execute() {
				JavaScript.preventDefault();
//				System.out.println("executing");
//				JavaScript.script("alert(this.getAttribute('data-id'));");
//				JavaScript.script("alert($(this).closest('ul[data-role=listview]').find('li').length);");
//				JavaScript.script("list = $(this).closest('ul[data-role=listview]').find('li'); item = $(this).closest('li'); alert('index ' + list.index(item));");

				String url = getContextPath() + "/whizu/event?id=" + eventHandler.id();

				Function data = new Function() {
					@Override
					public void execute() {
						
						String script = "'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li'))";  //closest("form").serialize();
						//System.out.println("script is " + script);
						JavaScript.script(script);
					}
				};

				Function callback = new Function("data") {
					@Override
					public void execute() {
					}
				};

				String type = "script";
				jQuery("$").get(url, data, callback, type);
				
				JavaScript.script("return false;");
			}
		};
	}

	private String getSelector(Identity... objs) {
		String selector = "$('";
		int i = 0;
		for (Identity component : objs) {
			if (i > 0) {
				selector += ",";
			}
			selector += "#" + component.id();
			i++;
		}
		selector += "')";
		return selector;
	}

	@Override
	public JQuery html(String arg) {
		String html = StringEscapeUtils.escapeJavaScript(arg);
		return call("html", html);
	}

	@Override
	public JQuery insertAfter(String target) {
		return call("insertAfter", target);
	}

	@Override
	public JQuery insertBefore(String target) {
		return call("insertBefore", target);
	}

	@Override
	public JQuery lastChild(String element) {
		return call("filter", element + ":last-child");
	}

	@Override
	public JQuery live(String event, Function function) {
		Script script = RequestContext.getRequest().compile(function);
		return concat(".", "live", "('" + event + "', function(event) { ", script.toJavaScript(), " })");
	}

	public JQuery on(String events, Function handler) {
		Script script = RequestContext.getRequest().compile(handler);
		return concat(".", "on", "('", events, "', function(event) { ", script.toJavaScript(), " })");
	}

	@Override
	public JQuery on(String event, EventHandler eventHandler) {
		Script script = RequestContext.getRequest().compile( getItemHandlerFunction(eventHandler));
		return concat(".", "on", "('", event, "', function(event) { ", script.toJavaScript(), " })");
	}

	@Override
	public JQuery on(String events, String selector, EventHandler eventHandler) {
		Script script = RequestContext.getRequest().compile( getItemHandlerFunction(eventHandler));
		return concat(".", "on", "('", events, "', '", selector, "', function(event) { ", script.toJavaScript(), " })");
		//return concat(".", "on", "('", events, "', ", selector, ", function(event) { ", script.toJavaScript(), " })");
	}
	
	@Override
	public JQuery on(String events, JQuery selector, EventHandler eventHandler) {
		Script script = RequestContext.getRequest().compile( getItemHandlerFunction(eventHandler));
		return concat(".", "on", "('", events, "', ", selector.toJavaScript(), ", function(event) { ", script.toJavaScript(), " })");
	}

	@Override
	public JQuery onClickItem(EventHandler eventHandler) {
		if (eventHandler != null) {
			on("click", getItemHandlerFunction(eventHandler));
		}
		return this;
	}

	@Override
	public JQuery prepend(Content content) {
		return prepend(content.render());
	}

	@Override
	public JQuery prepend(String arg) {
		return call("prepend", arg);
	}

	@Override
	public JQuery prepend(String... content) {
		return call("prepend", content);
	}

	@Override
	public JQuery prependTo(String target) {
		return call("prependTo", target);
	}

	private String quote(String type) {
		return "'".concat(type).concat("'");
	}

	@Override
	public JQuery remove() {
		return call("remove");
	}

	@Override
	public JQuery remove(String selector) {
		return call("remove", selector);
	}

	@Override
	public JQuery replaceAll(String target) {
		return call("replaceAll", target);
	}

	@Override
	public JQuery replaceWith(String newContent) {
		return call("replaceWith", newContent);
	}

	@Override
	public JQuery serialize() {
		return call("serialize");
	}

	@Override
	public JQuery submit(final EventHandler eventHandler) {
		if (eventHandler != null) {
			submit(getEventHandlerFunction(eventHandler));
		}
		return this;
	}

	@Override
	public JQuery submit(Function function) {
		Script script = RequestContext.getRequest().compile(function);
		return concat(".", "submit", "(function(event) { ", script.toJavaScript(), " })");
	}

	@Override
	public JQuery text(String arg) {
		return call("text", arg);
	}

	@Override
	public JQuery toggle() {
		return call("toggle");
	}

	@Override
	public String toJavaScript() {
		// return selector.concat(expression);
		return selector_.concat(super.toJavaScript());
	}

	@Override
	public JQuery trigger(String event) {
		return call("trigger", event);
	}

	@Override
	public JQuery val(String arg) {
		return call("val", arg);
	}

	@Override
	public JQuery width(String value) {
		return call("width", value);
	}

	@Override
	public JQuery wrap(String wrappingElement) {
		return call("wrap", wrappingElement);
	}

	@Override
	public JQuery wrapAll(String wrappingElement) {
		return call("wrapAll", wrappingElement);
	}

	@Override
	public JQuery wrapInner(String wrappingElement) {
		return call("wrapInner", wrappingElement);
	}
}
