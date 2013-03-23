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
package org.whizu.server;

import org.apache.commons.lang.StringEscapeUtils;
import org.whizu.jquery.Function;
import org.whizu.jquery.Identity;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.Script;

/**
 * @author Rudy D'hauwe
 */
class JQueryImpl extends Expression implements JQuery {

	private String selector = "";

	JQueryImpl(Identity element) {
		this.selector = getSelector(element);
	}

	JQueryImpl(Identity... elements) {
		this.selector = getSelector(elements);
	}

	JQueryImpl(String selector) {
		this.selector = selector;
	}

	static JQuery self() {
		return new JQueryImpl("$(this)");
	}

	private String getSelector(Identity... objs) {
		String selector = "$(\"";
		int i = 0;
		for (Identity component : objs) {
			if (i > 0) {
				selector += ",";
			}
			selector += "#" + component.getId();
			i++;
		}
		selector += "\")";
		return selector;
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#concat(java.lang.String)
	 */
	@Override
	public JQuery concat(String... js) {
		for (String part : js) {
			this.expression += part;
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#toJavaScript()
	 */
	@Override
	public String toJavaScript() {
		return selector.concat(expression);
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#addClass(java.lang.String)
	 */
	@Override
	public JQuery addClass(String style) {
		return call("addClass", style);
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#call(java.lang.String, java.lang.String)
	 */
	@Override
	public JQuery call(String function, String arg) {
		return concat(".", function, "(\"", arg, "\")");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#call(java.lang.String, java.lang.String, java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#call(java.lang.String)
	 */
	@Override
	public JQuery call(String function) {
		return concat(".", function, "()");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#callunquoted(java.lang.String, java.lang.String)
	 */
	@Override
	public JQuery callunquoted(String function, String arglist) {
		return concat(".", function, "(", arglist, ")");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#toString()
	 */
	@Override
	public String toString() {
		return toJavaScript();
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#remove()
	 */
	@Override
	public JQuery remove() {
		return call("remove");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#empty()
	 */
	@Override
	public JQuery empty() {
		return call("empty");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#append(java.lang.String)
	 */
	@Override
	public JQuery append(String... content) {
		return call("append", content);
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#toggle()
	 */
	@Override
	public JQuery toggle() {
		return call("toggle");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#closest(java.lang.String)
	 */
	@Override
	public JQuery closest(String name) {
		return call("closest", name);
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#trigger(java.lang.String)
	 */
	@Override
	public JQuery trigger(String event) {
		return call("trigger", event);
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#html(java.lang.String)
	 */
	@Override
	public JQuery html(String arg) {
		String html = StringEscapeUtils.escapeJavaScript(arg); 
		return call("html", html);
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#button()
	 */
	@Override
	public JQuery button() {
		return call("button");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#click(org.whizu.script.Function)
	 */
	@Override
	public JQuery click(Function function) {
		Script script = FunctionWorker.compile(function);
		return concat(".", "click", "(function(event) { ", script.toJavaScript(), " })");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#serialize()
	 */
	@Override
	public JQuery serialize() {
		return call("serialize");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#get(java.lang.String, org.whizu.script.Function, org.whizu.script.Function, java.lang.String)
	 */
	@Override
	public JQuery get(String url, Function data, Function callback, String type) {
		return concat(".", "get(", quote(url), ", ", FunctionWorker.evaluate(data), ", ", FunctionWorker.define(callback), ", ", quote(type), ")");
	}

	private String quote(String type) {
		return "'".concat(type).concat("'");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#find(java.lang.String)
	 */
	@Override
	public JQuery find(String selector) {
		return concat(".", "find(", quote(selector), ")");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#prepend(java.lang.String)
	 */
	@Override
	public JQuery prepend(String arg) {
		return call("prepend", arg);
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#document()
	 */
	@Override
	public JQuery document() {
		return new JQueryImpl("$('#y-body')");
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#text(java.lang.String)
	 */
	@Override
	public JQuery text(String arg) {
		return call("text", arg);
	}

	/* (non-Javadoc)
	 * @see org.whizu.script.IJQuery#val(java.lang.String)
	 */
	@Override
	public JQuery val(String arg) {
		return call("val", arg);
	}

	@Override
	public JQuery prepend(String... content) {
		return call("prepend", content);
	}

	@Override
	public JQuery after(String... content) {
		return call("after", content);
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
	public JQuery insertAfter(String target) {
		return call("insertAfter", target);
	}

	@Override
	public JQuery insertBefore(String target) {
		return call("insertBefore", target);
	}

	@Override
	public JQuery prependTo(String target) {
		return call("prependTo", target);
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

	@Override
	public JQuery width(String value) {
		return call("width", value);
	}

	/**
	 * @param impl
	 */
	/*
	 * public JQuery prepend(Element impl) { return prepend(impl.render()); }
	 */

	/**
	 * @param render
	 * @return
	 */
	/*
	 * public JQuery prepend(Html render) { return prepend(render.toString()); }
	 */
}
