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

import java.util.List;

/**
 * An element in the Document Object Model (DOM) having attributes, text and
 * children. It provides methods to traverse the parent and children and to get
 * access to its attributes. Due to a lot of flaws in DOM API specifications and
 * implementations, those methods are no fun to use. jQuery provides a wrapper
 * around those elements to help interacting with the DOM. But often enough you
 * will be working directly with DOM elements, or see methods that (also) accept
 * DOM elements as arguments.
 * 
 * @author Rudy D'hauwe
 * @see <a href='http://api.jquery.com/Types/#Element'>jQuery's Element type</a>
 */
public interface Element extends Content, Identity { //extends Component?

	/**
	 * Add content to this element.
	 * 
	 * @return this
	 */
	public Element add(Content content);

	/**
	 * @return this
	 */
	public <T extends Content> Element add(List<T> content);

	/**
	 * Add content to this element.
	 * 
	 * @return this
	 */
	public Element add(Content... contents);

	/**
	 * Add content to this element.
	 * 
	 * @return this
	 */
	public <T> Element add(Foreach<T> factory);

	/**
	 * Add text content to this element.
	 * 
	 * @return this
	 */
	public Element add(String literal);

	/**
	 * Set the value of an attribute of this element.
	 * 
	 * @return this
	 */
	public Element attr(String name, String value);

	/**
	 * @return this
	 */
	public Element border(String border);

	/**
	 * @return this
	 */
	public Element css(String clazz);

	/**
	 * @return this
	 */
	//public <T extends Element> T decorate(Decorator... decorators);
	public Element decorate(Decorator... decorators);

	/**
	 * @return the id of this element
	 */
	public String id();

	/**
	 * Set the id of this element.
	 * 
	 * @return this
	 */
	public Element id(String id);

	/**
	 * @return this
	 */
	public Element padding(String padding);

	/**
	 * @return this
	 */
	public Element size(String size);

	/**
	 * @return the wrapping element
	 */
	public Element wrap(String name);

	/**
	 * @return a composite containing this after element
	 */
	public Content after(Content element);

	/**
	 * @return this
	 */
	public Element width(String width);

	/**
	 * @return this
	 */
	public Element style(String name, String value);

	/**
	 * @return this
	 */
	public Element title(String title);

	/**
	 * @return this
	 */
	public Element height(String height);

	/**
	 * @return this
	 */
	public Element margin(String margin);

	/**
	 * @return this
	 */
	public Element src(String src);

	/**
	 * @return this
	 */
	public Element css(List<String> clazzList);

	/**
	 * @return this
	 */
	public void style(String style);

	/**
	 * @return this;
	 */
	public Element href(String href);
}
