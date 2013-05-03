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
package org.whizu.js;

import org.whizu.jquery.Function;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;

/**
 * @author Rudy D'hauwe
 */
public class JavaScript {

	/**
	 * With JavaScript, it is possible to execute some code at specified
	 * time-intervals. This is called timing events. The setInterval() method
	 * will wait a specified number of milliseconds, and then execute a
	 * specified function, and it will continue to execute the function, once at
	 * every given time-interval.
	 */
	public void setInterval(Function function, int milliseconds) {
		Request request = RequestContext.getRequest();
		Script action = request.compile(function);
		String script = "setInterval(function(){" + action.toJavaScript() + "}," + milliseconds + ")";
		request.addExpression(script);
	}

	/**
	 * With JavaScript, it is possible to execute some code at specified
	 * time-intervals. This is called timing events. The setTimeout() method
	 * will wait the specified number of milliseconds, and then execute the
	 * specified function once.
	 */
	public void setTimeout(Function function, int milliseconds) {
		Request request = RequestContext.getRequest();
		Script action = request.compile(function);
		String script = "setTimeout(function(){" + action.toJavaScript() + "}," + milliseconds + ")";
		request.addExpression(script);
	}
}
