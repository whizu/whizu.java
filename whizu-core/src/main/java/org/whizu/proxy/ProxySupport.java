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

import org.whizu.content.Identity;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;

/**
 * @author Rudy D'hauwe
 */
public class ProxySupport {

	private String id_;
	
	protected ProxySupport() {
	}

	protected ProxySupport(String id) {
		id_ = id;
	}

	public final String id() {
		return id_;
	}
	
	public final void id(String id) {
		id_ = id;
	}
	
	protected final JQuery jQuery(Identity identity) {
		return RequestContext.getRequest().select(identity);
	}

	public final String render() {
		throw new IllegalStateException("This component is already rendered");
	}
}
