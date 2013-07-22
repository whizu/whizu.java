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
package org.whizu.server;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rudy D'hauwe
 */
class RequestDispatcher {

	private ServletContext servletContext_;
	
	private Configuration config = new Configuration();
	
	/**
	 * Looks up and dispatches the request to the right
	 * <code>RequestProcessor</code> if any.
	 * 
	 * @return true if the request was dispatched and processed
	 * @return false if the request could not be dispatched
	 */
	public boolean dispatch(HttpServletRequest request, HttpServletResponse response) {
		return false;
	}

	protected void servletContext(ServletContext servletContext) {
		servletContext_ = servletContext;
	}

	protected void addApplication(String value, PageFactory factory) {
		config.addApplication(value, factory);
	}

	public void addApp(String value, Class<?> annotatedClass) {
				
	}
}
