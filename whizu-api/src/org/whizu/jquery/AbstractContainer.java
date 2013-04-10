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

import java.util.ArrayList;
import java.util.List;

import org.whizu.content.Component;
import org.whizu.content.Container;
import org.whizu.content.Content;
import org.whizu.html.Html;

/**
 * @author Rudy D'hauwe
 */
public class AbstractContainer extends AbstractComponent implements Container {

	@Override
	public AbstractContainer css(String clazz) {
		setStyleName(clazz);
		return this;
	}

	protected List<Component> componentList = new ArrayList<Component>();

	@Override
	public void add(Component impl) {
		System.out.println("Adding " + impl + " to " + this + " " + this.isRendered());
		this.componentList.add(impl);

		if (this.isRendered()) {
			jQuery(this).append(impl);
		}
	}

	@Override
	public void prepend(Component impl) {
		this.componentList.add(impl);

		if (isRendered()) {
			jQuery(this).prepend(impl);
		}
	}

	public Content create() {
		System.out.println("Creating " + this + " with components " + componentList.size());
		return Html.div(this).add(componentList);
	}

	@Override
	public void remove(Component element) {
		jQuery(element).remove();
	}

	@Override
	public void empty() {
		jQuery(this).empty();
	}
}
