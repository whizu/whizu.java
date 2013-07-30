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
import org.whizu.dom.Identity;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.proxy.Proxy;

/**
 * @author Rudy D'hauwe
 */
final class PanelProxy extends Proxy<Panel> implements Panel {

	PanelProxy(Panel impl) {
		super(impl);
	}

	@Override
	public void add(Content content) {
		impl().add(content);
	}
	
	@Override
	protected final Panel createImpl() {
		return new PanelImpl(id());
	}

	@Override
	public String id() {
		return impl().id();
	}
	
	/***************************************************************************
     * The target <code>Panel</code> that has been rendered.
	 */
	final class PanelImpl implements Panel {

		private String id_;
		
		private PanelImpl(String id) {
			id_ = id;
		}

		@Override
		public void add(Content content) {
			jQuery(this).append(content);
			updateLayout();
		}

		@Override
		public String id() {
			return id_;
		}

		private JQuery jQuery(Identity identity) {
			return RequestContext.getRequest().select(identity);
		}

		private void updateLayout() {
			jQuery(this).trigger("updatelayout");
		}
	}
}
