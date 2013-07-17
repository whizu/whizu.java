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
package org.whizu.jquery.mobile;

import org.whizu.dom.Content;
import org.whizu.dom.Element;

/**
 * The page is the primary unit of interaction in jQuery Mobile and is used to
 * group content into logical views that can be animated in and out of view with
 * page transitions. A HTML document may start with a single Page and the AJAX
 * navigation system will load additional pages on demand into the DOM as users
 * navigate around. Alternatively, a HTML document can be built with multiple Pages
 * inside it and the framework will transition between these local views with no
 * need to request content from the server.
 * 
 * @author Rudy D'hauwe
 */
public interface Page {

	public Header header(String title);

	public Element p(String text);

	public Footer footer(String title);

	public String id();

	public void append(Content content);

}
