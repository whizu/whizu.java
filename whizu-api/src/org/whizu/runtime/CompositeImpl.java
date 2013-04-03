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

import java.util.ArrayList;
import java.util.List;

import org.whizu.html.Html;
import org.whizu.jquery.Identity;
import org.whizu.ui.Composite;
import org.whizu.ui.Widget;


/**
 * 
 * 
 * @author Rudy D'hauwe
 * @param <T>
 */
class CompositeImpl extends AbstractComponent implements Composite {

	@Override
	public CompositeImpl css(String clazz) {
		setStyleName(clazz);
		return this;
	}

	protected List<AbstractComponent> componentList = new ArrayList<AbstractComponent>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.whizu.ui.Composite#add(org.whizu.ui.Widget)
	 */
	@Override
	public void add(Widget component) {
		AbstractComponent impl = (AbstractComponent) component;
		this.componentList.add(impl);

		if (this.isRendered()) {
			jQuery(this).append(impl.render().toString());
		}
	}

	@Override
	public void prepend(Widget component) {
		AbstractComponent impl = (AbstractComponent) component;
		this.componentList.add(impl);

		if (isRendered()) {
			jQuery(this).prepend(impl.getMarkup());
		}
	}

	public Html create() {
		return div(this).add(componentList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.whizu.ui.Composite#remove(org.whizu.ui.Widget)
	 */
	@Override
	public void remove(Widget component) {
		Identity element = (Identity) component;
		jQuery(element).remove();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.whizu.ui.Composite#empty()
	 */
	@Override
	public void empty() {
		jQuery(this).empty();
	}
}
