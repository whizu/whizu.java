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

import org.whizu.content.Component;
import org.whizu.content.Content;
import org.whizu.content.ContentBuilder;

/**
 * A listview is coded as a simple unordered list containing linked list items with
 * a data-role="listview" attribute. jQuery Mobile will apply all the necessary
 * styles to transform the list into a mobile-friendly listview with right arrow
 * indicator that fills the full width of the browser window. When you tap on the
 * list item, the framework will trigger a click on the first link inside the list
 * item, issue an Ajax request for the URL in the link, create the new page in the
 * DOM, then kick off a page transition.
 * 
 * 
 * @author Rudy D'hauwe
 */
public interface ListView extends Component {

	public void addItem(Content item);
	
	public void addItem(ContentBuilder builder);

	public void replaceItem(int index, ContentBuilder item);

	public void on(Page page);
}
