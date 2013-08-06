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
import org.whizu.content.Literal;
import org.whizu.html.Html;
import org.whizu.proxy.Proxy;
import org.whizu.proxy.ProxySupport;

/**
 * @author Rudy D'hauwe
 */
final class PageProxy extends Proxy<Page> implements Page {

	protected PageProxy(Page impl) {
		super(impl);
	}
	
	protected PageProxy(String id) {
		super(id);
	}

	@Override
	public void add(Content content) {
		impl().add(content);
	}

	@Override
	public void add(String content) {
		impl().add(content);
	}

	@Override
	protected Page createImpl() {
		return new PageImpl(id());
	}

	@Override
	public void footer(String title) {
		impl().footer(title);
	}

	@Override
	public void header(Header header) {
		impl().header(header);
	}

	@Override
	public void header(String title) {
		impl().header(title);
	}

	@Override
	public String id() {
		return impl().id();
	}

	@Override
	protected Page jQueryById(String id) {
		return new PageImpl(id);
	}

	@Override
	public void p(String content) {
		impl().p(content);
	}

	/***************************************************************************
	 * The target <code>Page</code> that has been rendered.
	 */
	final class PageImpl extends ProxySupport implements Page {

		private PageImpl(String id) {
			super(id);
		}

		@Override
		public void add(Content content) {
			jQuery(this).find("div[data-role=content]").append(content);
		}

		@Override
		public void add(String text) {
			add(new Literal(text));
		}

		@Override
		public void footer(String title) {
			// TODO Footer footer = FooterBuilder.create().title(title).build();
			Footer footer = new Footer(title);
			jQuery(this).append(footer);
		}
		
		@Override
		public void header(Header header) {
			// jQuery("$.mobile.activePage").append(header_);
			jQuery(this).prepend(header);
		}

		@Override
		public void header(String title) {
			Header header = HeaderBuilder.create().title(title).build();
			// jQuery("$.mobile.activePage").append(header_);
			jQuery(this).prepend(header);
		}

		@Override
		public void p(String text) {
			add(Html.p(text));
		}
	}
}
