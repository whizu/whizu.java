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
package org.whizu.runtime;

import org.whizu.html.Html;
import org.whizu.ui.VirtualContainer;


//@Deprecated //more intelligent way of programming this?
class VirtualContainerImpl extends CompositeImpl implements VirtualContainer {

	public void add(AbstractComponent component) {
		this.componentList.add(component);

		if (this.isRendered()) {
			jQuery(this).append(component.getMarkup());
			// jQuery(this).append(component);
		}
	}

	@Override
	public Html create() {
		Html markup = componentList.get(0).render();
		for (int i = 1; i < componentList.size(); i++) {
			markup.add(componentList.get(i).render());
		}

		return markup;
	}
}
