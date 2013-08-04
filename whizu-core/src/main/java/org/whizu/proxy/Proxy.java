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
package org.whizu.proxy;

import org.whizu.content.Content;
import org.whizu.content.ContentBuilder;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
public abstract class Proxy<T> implements Content {

	private T impl_;

	protected Proxy(T impl) {
		assert(impl instanceof ContentBuilder);
		impl_ = impl;
	}

	public void assureExistsOnPage(JQuery page) {
		if (impl_ instanceof ContentBuilder) {
			String create = render();
			page.append(create);
		}
	}

	protected abstract T createImpl();

	//TODO make protected? see usage in Form.
	public final T impl() {
		return impl_;
	}

	protected JQuery jQuery(String selector) {
		return RequestContext.getRequest().select(selector);
	}

	@Override
	public final String render() {
		ContentBuilder builder = Objects.cast(impl_);
		Content content = builder.build();
		String markup = content.render();
		impl_ = createImpl();
		return markup;
	}
}
