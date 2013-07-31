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
import org.whizu.dom.Identity;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.proxy.Proxy;

/**
 * @author Rudy D'hauwe
 */
final class PopupProxy extends Proxy<Popup> implements Popup {

	protected PopupProxy(Popup impl) {
		super(impl);
	}

	@Override
	public void add(Content content) {
		impl().add(content);
	}
	
	@Override
	public void close() {
		impl().close();
	}
	
	@Override
	protected Popup createImpl() {
		return new PopupImpl(id());
	}

	@Override
	public String id() {
		return impl().id();
	}

	@Override
	public void css(String className) {
		impl().css(className);
	}
	
	@Override
	public void open() {
		if (impl() instanceof ContentBuilder) {
			jQuery("$.mobile.activePage").append(render()).trigger("pagecreate");
		}
		impl().open();
	}

	/***************************************************************************
	 * The target <code>Popup</code> that has been rendered.
	 */
	final class PopupImpl implements Popup {

		private String id_;

		public PopupImpl(String id) {
			id_ = id;
		}

		@Override
		public void add(Content content) {
			jQuery(this).append(content);
		}
		
		@Override
		public void close() {
			jQuery(this).call("popup", "close");
		}

		@Override
		public void css(String className) {
			jQuery(this).addClass(className);
		}

		@Override
		public String id() {
			return id_;
		}

		private JQuery jQuery(Identity identity) {
			return RequestContext.getRequest().select(identity);
		}
		
		@Override
		public void open() {
			jQuery(this).call("popup", "open");
		}
	}
}
