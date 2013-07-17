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

import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.value.StringValue;
import org.whizu.widget.Container;

/**
 * @author Rudy D'hauwe
 */
public class ListItem extends Container {

	public ListItem() {
	}
	
	public ListItem(String title) {
		prepend(Html.h2(title));
	}

	public ListItem(StringValue title) {
		this(title.get());
	}

	@Override
	public Markup compile() {
		Markup markup = Html.li(this).decorate(this).add(componentList);
		return markup;
	}

	public void p(String text) {
		add(Html.p(text));
	}

	public void p(StringValue value) {
		p(value.get());
	}
}