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
package org.whizu.content;

/**
 * An element in the Document Object Model (DOM) has attributes, text and
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
public interface Element extends Content {

}
