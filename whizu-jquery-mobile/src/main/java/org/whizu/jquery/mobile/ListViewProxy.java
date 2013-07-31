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
import org.whizu.dom.ContentBuilder;
import org.whizu.dom.Element;
import org.whizu.html.Html;
import org.whizu.proxy.Proxy;
import org.whizu.proxy.ProxySupport;
import org.whizu.widget.BuildOnDemand;

/**
 * @author Rudy D'hauwe
 */
final class ListViewProxy extends Proxy<ListView> implements ListView {

	protected ListViewProxy(ListView impl) {
		super(impl);
	}

	@Override
	public void addItem(Content item) {
		impl().addItem(item);
	}

	@Override
	public void addItem(ContentBuilder builder) {
		impl().addItem(builder);
	}

	@Override
	protected ListView createImpl() {
		return new ListViewImpl(id());
	}

	@Override
	public String id() {
		return impl().id();
	}
	
	/***************************************************************************
	 * The target <code>Popup</code> that has been rendered.
	 */
	final class ListViewImpl extends ProxySupport implements ListView {

		private String id_;

		public ListViewImpl(String id) {
			id_ = id;
		}

		@Override
		public void addItem(Content item) {
			Element li = Html.li().add(item);
			jQuery(this).append(li).call("listview", "refresh");
		}

		@Override
		public void addItem(ContentBuilder builder) {
			Content item = new BuildOnDemand(builder);
			addItem(item);
		}
		
		@Override
		public String id() {
			return id_;
		}
	}
}
