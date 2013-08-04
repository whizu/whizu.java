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
import org.whizu.dom.ContentList;
import org.whizu.html.Html;
import org.whizu.proxy.ProxyBuilder;
import org.whizu.widget.BuildSupport;

/**
 * @author Rudy D'hauwe
 */
public final class AccordionBuilder extends ProxyBuilder<Accordion, AccordionBuilder.Build> {

	@Override
	protected Build createPrototype() {
		return new Build();
	}

	@Override
	protected Accordion createProxy(Build build) {
		return new AccordionProxy(build);
	}

	/***************************************************************************
	 * The <code>Accordion</code> that is being built.
	 */
	final class Build extends BuildSupport implements Accordion {

		private ContentList contents_ = new ContentList();
		
		private Theme theme_;

		@Override
		public void addCollapsible(Collapsible collapsible) {
			contents_.add(collapsible);
		}

		@Override
		public Content build() {
			return Html.div(this).decorate(DataRole.COLLAPSIBLE_SET, this, theme_).add(contents_);
		}
	}
}
