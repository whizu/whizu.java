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
package org.whizu.jquery;

public abstract class RequestContext {

	private static RequestContext INSTANCE;

	public static RequestContext getInstance() {
		return INSTANCE;
	}
	
	public static final Request getRequest() {
		return INSTANCE.getRequestImpl();
	}

	public static void setInstance(RequestContext instance) {
		INSTANCE = instance;
	}
	
	protected abstract Request getRequestImpl();

	public static Session session() {
		return getRequest().session();
	}

	public static String getContextPath() {
		return INSTANCE.getServletContextPath();
	}

	protected abstract String getServletContextPath();
}
