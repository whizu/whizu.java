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
package org.whizu.widget;

import java.util.ArrayList;
import java.util.List;

import org.whizu.dom.Component;
import org.whizu.dom.Composite;
import org.whizu.dom.Markup;
import org.whizu.html.Html;

/**
 * @author Rudy D'hauwe
 */
public class Container extends Widget implements Composite {

	protected List<Component> componentList = new ArrayList<Component>();

	@Override
	public Container add(Component impl) {
		this.componentList.add(impl);

		if (this.isRendered()) {
			jQuery(this).append(impl);
		}

		return this;
	}

	@Override
	public Markup compile() {
		return Html.div(this).decorate(this).add(componentList);
	}

	@Override
	public void empty() {
		this.componentList.clear();

		if (this.isRendered()) {
			jQuery(this).empty();
		}
	}

	@Override
	public void prepend(Component impl) {
		this.componentList.add(0, impl);

		if (isRendered()) {
			jQuery(this).prepend(impl);
		}
	}

	@Override
	public void remove(Component element) {
		this.componentList.remove(element);
		
		if (this.isRendered()) {
			jQuery(element).remove();
		}
	}
}