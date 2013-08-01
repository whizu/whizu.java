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

import org.whizu.dom.Content;

/**
 * A jQuery object contains a collection of Document Object Model (DOM) elements
 * that have been created from an HTML string or selected from a document. Since
 * jQuery methods often use CSS selectors to match elements from a document, the
 * set of elements in a jQuery object is often called a set of "matched elements"
 * or "selected elements".
 * 
 * @see <a href='http://api.jquery.com/Types/#jQuery'>jQuery's jQuery object</a>
 */
public interface JQuery {

	/**
	 * Adds the specified classes to each of the set of matched elements. It's
	 * important to note that this method does not replace a class. It simply adds
	 * the class, appending it to any which may already be assigned to the
	 * elements. More than one class may be added at a time, separated by a space,
	 * to the set of matched elements, like so:
	 * 
	 * <pre>
	 * jQuery(this).addClass(&quot;myClass yourClass&quot;);
	 * </pre>
	 * 
	 * @see <a href='http://api.jquery.com/addClass'>jQuery's .addClass()</a>
	 */
	public abstract JQuery addClass(String className);

	/**
	 * Insert content, specified by the parameter, after each element in the set of
	 * matched elements. The .after() and .insertAfter() methods perform the same
	 * task. The major difference is in the syntax-specifically, in the placement
	 * of the content and target. With .after(), the selector expression preceding
	 * the method is the container after which the content is inserted. With
	 * .insertAfter(), on the other hand, the content precedes the method, either
	 * as a selector expression or as markup created on the fly, and it is inserted
	 * after the target container.
	 * 
	 * @see <a href='http://api.jquery.com/after'>jQuery's .after()</a>
	 */
	public abstract JQuery after(String... content);

	/**
	 * Insert content, specified by the parameter, to the end of each element in
	 * the set of matched elements. The .append() method inserts the specified
	 * content as the last child of each element in the jQuery collection.
	 * 
	 * @see <a href='http://api.jquery.com/append'>jQuery's .append()</a>
	 */
	public abstract JQuery append(Content content);
	
	/**
	 * Insert content, specified by the parameter, to the end of each element in
	 * the set of matched elements. The .append() method inserts the specified
	 * content as the last child of each element in the jQuery collection.
	 * 
	 * @see <a href='http://api.jquery.com/append'>jQuery's .append()</a>
	 */
	public abstract JQuery append(String... content);

	/**
	 * Insert every element in the set of matched elements to the end of the
	 * target. The .append() and .appendTo() methods perform the same task. The
	 * major difference is in the syntax-specifically, in the placement of the
	 * content and target. With .append(), the selector expression preceding the
	 * method is the container into which the content is inserted. With
	 * .appendTo(), on the other hand, the content precedes the method, either as a
	 * selector expression or as markup created on the fly, and it is inserted into
	 * the target container.
	 * 
	 * @see <a href='http://api.jquery.com/appendTo'>jQuery's .appendTo()</a>
	 */
	public abstract JQuery appendTo(String target);

	/**
	 * Get the value of an attribute for the first element in the set of matched
	 * elements or set one or more attributes for every matched element. The
	 * .attr() method gets the attribute value for only the first element in the
	 * matched set. To get the value for each element individually, use a looping
	 * construct such as jQuery's .each() or .map() method.
	 * 
	 * @see <a href='http://api.jquery.com/attr'>jQuery's .attr()</a>
	 */
	public abstract String attr(String attributeName);

	/**
	 * Insert content, specified by the parameter, before each element in the set
	 * of matched elements. The .before() and .insertBefore() methods perform the
	 * same task. The major difference is in the syntax-specifically, in the
	 * placement of the content and target. With .before(), the selector expression
	 * preceding the method is the container before which the content is inserted.
	 * With .insertBefore(), on the other hand, the content precedes the method,
	 * either as a selector expression or as markup created on the fly, and it is
	 * inserted before the target container.
	 * 
	 * @see <a href='http://api.jquery.com/before'>jQuery's .before()</a>
	 */
	public abstract JQuery before(String... content);

	public abstract JQuery button();

	public abstract JQuery call(String function);

	public abstract JQuery call(String function, String arg);

	public abstract JQuery call(String function, String... args);

	public abstract JQuery callunquoted(String function, String arglist);

	public abstract JQuery click(Function function);

	public abstract JQuery closest(String name);

	public abstract JQuery concat(String... js);

	public abstract JQuery document();

	/**
	 * Remove all child nodes of the set of matched elements from the DOM. This
	 * method removes not only child (and other descendant) elements, but also any
	 * text within the set of matched elements. This is because, according to the
	 * DOM specification, any string of text within an element is considered a
	 * child node of that element.
	 * 
	 * @see <a href='http://api.jquery.com/empty'>jQuery's .empty()</a>
	 */
	public abstract JQuery empty();

	public abstract JQuery find(String selector);

	public abstract JQuery get(String url, Function data, Function callback,
			String type);

	public abstract JQuery html(String arg);

	/**
	 * Insert every element in the set of matched elements after the target. The
	 * .after() and .insertAfter() methods perform the same task. The major
	 * difference is in the syntax-specifically, in the placement of the content
	 * and target. With .after(), the selector expression preceding the method is
	 * the container after which the content is inserted. With .insertAfter(), on
	 * the other hand, the content precedes the method, either as a selector
	 * expression or as markup created on the fly, and it is inserted after the
	 * target container.
	 * 
	 * @see <a href='http://api.jquery.com/insertAfter'>jQuery's .insertAfter()</a>
	 */
	public abstract JQuery insertAfter(String target);

	/**
	 * Insert every element in the set of matched elements before the target. The
	 * .before() and .insertBefore() methods perform the same task. The major
	 * difference is in the syntax-specifically, in the placement of the content
	 * and target. With .before(), the selector expression preceding the method is
	 * the container before which the content is inserted. With .insertBefore(), on
	 * the other hand, the content precedes the method, either as a selector
	 * expression or as markup created on the fly, and it is inserted before the
	 * target container.
	 * 
	 * @see <a href='http://api.jquery.com/insertBefore'>jQuery's
	 *      .insertBefore()</a>
	 */
	public abstract JQuery insertBefore(String target);

	public JQuery live(String event, Function function);
	
	/**
	 * Insert content, specified by the parameter, to the beginning of each element
	 * in the set of matched elements. The .prepend() method inserts the specified
	 * content as the first child of each element in the jQuery collection.
	 * 
	 * @see <a href='http://api.jquery.com/prepend'>jQuery's .prepend()</a>
	 */
	public abstract JQuery prepend(Content content);

	/**
	 * Insert content, specified by the parameter, to the beginning of each element
	 * in the set of matched elements. The .prepend() method inserts the specified
	 * content as the first child of each element in the jQuery collection.
	 * 
	 * @see <a href='http://api.jquery.com/prepend'>jQuery's .prepend()</a>
	 */
	public abstract JQuery prepend(String content);

	/**
	 * Insert content, specified by the parameter, to the beginning of each element
	 * in the set of matched elements. The .prepend() method inserts the specified
	 * content as the first child of each element in the jQuery collection.
	 * 
	 * @see <a href='http://api.jquery.com/prepend'>jQuery's .prepend()</a>
	 */
	public abstract JQuery prepend(String... content);

	/**
	 * Insert every element in the set of matched elements to the beginning of the
	 * target. The .prepend() and .prependTo() methods perform the same task. The
	 * major difference is in the syntax-specifically, in the placement of the
	 * content and target. With .prepend(), the selector expression preceding the
	 * method is the container into which the content is inserted. With
	 * .prependTo(), on the other hand, the content precedes the method, either as
	 * a selector expression or as markup created on the fly, and it is inserted
	 * into the target container.
	 * 
	 * @see <a href='http://api.jquery.com/prependTo'>jQuery's .prependTo()</a>
	 */
	public abstract JQuery prependTo(String target);

	/**
	 * Remove the set of matched elements from the DOM. Similar to .empty(), the
	 * .remove() method takes elements out of the DOM. Use .remove() when you want
	 * to remove the element itself, as well as everything inside it. In addition
	 * to the elements themselves, all bound events and jQuery data associated with
	 * the elements are removed. To remove the elements without removing data and
	 * events, use .detach() instead.
	 * 
	 * @see <a href='http://api.jquery.com/remove'>jQuery's .remove()</a>
	 */
	public abstract JQuery remove();

	/**
	 * Remove the set of matched elements from the DOM. Similar to .empty(), the
	 * .remove() method takes elements out of the DOM. Use .remove() when you want
	 * to remove the element itself, as well as everything inside it. In addition
	 * to the elements themselves, all bound events and jQuery data associated with
	 * the elements are removed. To remove the elements without removing data and
	 * events, use .detach() instead.
	 * 
	 * @see <a href='http://api.jquery.com/remove'>jQuery's .remove()</a>
	 */
	public abstract JQuery remove(String selector);

	/**
	 * Replace each target element with the set of matched elements. The
	 * .replaceAll() method is corollary to .replaceWith(), but with the source and
	 * target reversed.
	 * 
	 * @see <a href='http://api.jquery.com/replaceAll'>jQuery's .replaceAll()</a>
	 */
	public abstract JQuery replaceAll(String target);

	/**
	 * Replace each element in the set of matched elements with the provided new
	 * content and return the set of elements that was removed. The .replaceWith()
	 * method removes content from the DOM and inserts new content in its place
	 * with a single call.
	 * 
	 * @see <a href='http://api.jquery.com/replaceWith'>jQuery's .replaceWith()</a>
	 */
	public abstract JQuery replaceWith(String wrappingElement);

	/**
	 * Encode a set of form elements as a string for submission. The .serialize()
	 * method creates a text string in standard URL-encoded notation. It operates
	 * on a jQuery object representing a set of form elements. The .serialize()
	 * method can act on a jQuery object that has selected individual form
	 * elements, such as input, textarea, and select. However, it is
	 * typically easier to select the form tag itself for serialization.
	 * 
	 * @see <a href='http://api.jquery.com/serialize'>jQuery's .serialize()</a>
	 */
	public abstract JQuery serialize();

	public abstract JQuery text(String arg);

	public abstract JQuery toggle();

	public abstract String toJavaScript();

	public abstract JQuery trigger(String event);

	public abstract JQuery val(String arg);

	/**
	 * Set the CSS width of each element in the set of matched elements.
	 * 
	 * When calling .width("value"), the value can be either a string (number and
	 * unit) or a number. If only a number is provided for the value, jQuery
	 * assumes a pixel unit. If a string is provided, however, any valid CSS
	 * measurement may be used for the width (such as 100px, 50%, or auto). Note
	 * that in modern browsers, the CSS width property does not include padding,
	 * border, or margin, unless the box-sizing CSS property is used.
	 * 
	 * If no explicit unit is specified (like "em" or "%") then "px" is assumed.
	 * 
	 * Note that .width("value") sets the content width of the box regardless of
	 * the value of the CSS box-sizing property.
	 * 
	 * @see <a href='http://api.jquery.com/width'>jQuery's .width()</a>
	 */
	public abstract JQuery width(String value);

	/**
	 * Wrap an HTML structure around each element in the set of matched elements.
	 * The .wrap() function can take any string or object that could be passed to
	 * the $() factory function to specify a DOM structure. This structure may be
	 * nested several levels deep, but should contain only one inmost element. A
	 * copy of this structure will be wrapped around each of the elements in the
	 * set of matched elements. This method returns the original set of elements
	 * for chaining purposes.
	 * 
	 * @see <a href='http://api.jquery.com/wrap'>jQuery's .wrap()</a>
	 */
	public abstract JQuery wrap(String wrappingElement);

	/**
	 * Wrap an HTML structure around all elements in the set of matched elements.
	 * The .wrapAll() function can take any string or object that could be passed
	 * to the $() function to specify a DOM structure. This structure may be nested
	 * several levels deep, but should contain only one inmost element. The
	 * structure will be wrapped around all of the elements in the set of matched
	 * elements, as a single group.
	 * 
	 * @see <a href='http://api.jquery.com/wrapAll'>jQuery's .wrapAll()</a>
	 */
	public abstract JQuery wrapAll(String wrappingElement);

	/**
	 * Wrap an HTML structure around the content of each element in the set of
	 * matched elements. The .wrapInner() function can take any string or object
	 * that could be passed to the $() factory function to specify a DOM structure.
	 * This structure may be nested several levels deep, but should contain only
	 * one inmost element. The structure will be wrapped around the content of each
	 * of the elements in the set of matched elements.
	 * 
	 * @see <a href='http://api.jquery.com/wrapInner'>jQuery's .wrapInner()</a>
	 */
	public abstract JQuery wrapInner(String wrappingElement);

	public abstract JQuery firstOfType(String element);

	public abstract JQuery lastChild(String string);

	public abstract JQuery fadeTo(int i, int j);

	public abstract JQuery submit(Function f);

	public abstract JQuery click(EventHandler eventHandler);

	public abstract JQuery onClickItem(EventHandler eventHandler);
	
	public abstract JQuery submit(EventHandler eventHandler);

	public abstract JQuery on(String events, String selector, EventHandler eventHandler);
}
