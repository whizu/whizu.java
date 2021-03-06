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

import org.whizu.content.Content;
import org.whizu.content.Element;
import org.whizu.content.Node;

/**
 * Base class for HTML tags with support for easy method chaining.
 * 
 * @author Rudy D'hauwe
 */
abstract class NodeBuilder<T extends NodeBuilder<T>> extends Node {

	NodeBuilder(String name) {
		super(name);
	}

	@Override
	public T add(Content element) {
		return getThis(super.add(element));
	}

	@Override
	public T add(Content... elements) {
		return getThis(super.add(elements));
	}

	@Override
	public T attr(String name, String value) {
		return getThis(super.attr(name, value));
	}

	@SuppressWarnings("unchecked")
	private T getThis(Element thiz) {
		return (T) thiz;
	}

	@Override
	public T width(String width) {
		return getThis(super.width(width));
	}
}
