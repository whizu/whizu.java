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
package org.whizu.jquery;

/**
 * A jQuery object contains a collection of Document Object Model (DOM) elements
 * that have been created from an HTML string or selected from a document. Since
 * jQuery methods often use CSS selectors to match elements from a document, the
 * set of elements in a jQuery object is often called a set of
 * "matched elements" or "selected elements".
 * 
 * @see <a href='http://api.jquery.com/Types/#jQuery'>jQuery's jQuery object</a>
 */
public interface JQuery extends Content {

	/**
	 * Adds the specified classes to each of the set of matched elements. It's
	 * important to note that this method does not replace a class. It simply
	 * adds the class, appending it to any which may already be assigned to the
	 * elements. More than one class may be added at a time, separated by a
	 * space, to the set of matched elements, like so:
	 * 
	 * <pre>
	 * jQuery(this).addClass(&quot;myClass yourClass&quot;);
	 * </pre>
	 * 
	 * @see <a href='http://api.jquery.com/addClass'>jQuery's .addClass()</a>
	 */
	public abstract JQuery addClass(String className);

	/**
	 * Insert content, specified by the parameter, after each element in the set
	 * of matched elements. The .after() and .insertAfter() methods perform the
	 * same task. The major difference is in the syntax—specifically, in the
	 * placement of the content and target. With .after(), the selector
	 * expression preceding the method is the container after which the content
	 * is inserted. With .insertAfter(), on the other hand, the content precedes
	 * the method, either as a selector expression or as markup created on the
	 * fly, and it is inserted after the target container.
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
	public abstract JQuery append(String... content);

	/**
	 * Insert every element in the set of matched elements to the end of the
	 * target. The .append() and .appendTo() methods perform the same task. The
	 * major difference is in the syntax-specifically, in the placement of the
	 * content and target. With .append(), the selector expression preceding the
	 * method is the container into which the content is inserted. With
	 * .appendTo(), on the other hand, the content precedes the method, either
	 * as a selector expression or as markup created on the fly, and it is
	 * inserted into the target container.
	 * 
	 * @see <a href='http://api.jquery.com/appendTo'>jQuery's .appendTo()</a>
	 */
	public abstract JQuery appendTo(String target);

	/**
	 * Get the value of an attribute for the first element in the set of matched
	 * elements or set one or more attributes for every matched element. The
	 * .attr() method gets the attribute value for only the first element in the
	 * matched set. To get the value for each element individually, use a
	 * looping construct such as jQuery's .each() or .map() method.
	 * 
	 * @see <a href='http://api.jquery.com/attr'>jQuery's .attr()</a>
	 */
	public abstract String attr(String attributeName);

	/**
	 * Insert content, specified by the parameter, before each element in the
	 * set of matched elements. The .before() and .insertBefore() methods
	 * perform the same task. The major difference is in the
	 * syntax-specifically, in the placement of the content and target. With
	 * .before(), the selector expression preceding the method is the container
	 * before which the content is inserted. With .insertBefore(), on the other
	 * hand, the content precedes the method, either as a selector expression or
	 * as markup created on the fly, and it is inserted before the target
	 * container.
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
	 * method removes not only child (and other descendant) elements, but also
	 * any text within the set of matched elements. This is because, according
	 * to the DOM specification, any string of text within an element is
	 * considered a child node of that element.
	 * 
	 * @see <a href='http://api.jquery.com/empty'>jQuery's .empty()</a>
	 */
	public abstract JQuery empty();

	/**
	 * Insert every element in the set of matched elements after the target. The
	 * .after() and .insertAfter() methods perform the same task. The major
	 * difference is in the syntax-specifically, in the placement of the content
	 * and target. With .after(), the selector expression preceding the method
	 * is the container after which the content is inserted. With
	 * .insertAfter(), on the other hand, the content precedes the method,
	 * either as a selector expression or as markup created on the fly, and it
	 * is inserted after the target container.
	 * 
	 * @see <a href='http://api.jquery.com/insertAfter'>jQuery's
	 *      .insertAfter()</a>
	 */
	public abstract JQuery insertAfter(String target);

	public abstract JQuery find(String selector);

	public abstract JQuery get(String url, Function data, Function callback,
			String type);

	public abstract JQuery html(String arg);

	/**
	 * Insert content, specified by the parameter, to the beginning of each
	 * element in the set of matched elements. The .prepend() method inserts the
	 * specified content as the first child of each element in the jQuery
	 * collection.
	 * 
	 * @see <a href='http://api.jquery.com/prepend'>jQuery's .prepend()</a>
	 */
	public abstract JQuery prepend(String content);

	/**
	 * Insert content, specified by the parameter, to the beginning of each
	 * element in the set of matched elements. The .prepend() method inserts the
	 * specified content as the first child of each element in the jQuery
	 * collection.
	 * 
	 * @see <a href='http://api.jquery.com/prepend'>jQuery's .prepend()</a>
	 */
	public abstract JQuery prepend(String... content);

	public abstract JQuery remove();

	public abstract JQuery serialize();

	public abstract JQuery text(String arg);

	public abstract JQuery toggle();

	public abstract String toJavaScript();

	public abstract JQuery trigger(String event);

	public abstract JQuery val(String arg);
}
