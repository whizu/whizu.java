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

import org.whizu.content.Content;
import org.whizu.html.Html;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class DialogBuilder extends ProxyBuilder<Dialog, DialogBuilder.Build> {

	public static DialogBuilder createWithId(String id) {
		return new DialogBuilder(id);
	}

	private DialogBuilder(String id) {
		build_.id(id);
	}

	@Override
	protected Build createPrototype() {
		return new Build();
	}

	@Override
	protected Dialog createProxy(Dialog build) {
		return new DialogProxy(build);
	}

    public DialogBuilder title(String title) {
		build_.title(title);
		return this;
	}
    
	/***************************************************************************
     * The prototype <code>Dialog</code> that is being built.
	 */
	final class Build extends BuildSupport implements Dialog {

		private String title_;

		@Override
		public Content build() {
			return Html.div(this).add(Html.h1(title_));
		}

		@Override
		public void title(String title) {
			title_ = title;
		}
	}
}
