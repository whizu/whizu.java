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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rudy D'hauwe
 */
class RequestDispatcher {

	private static final Logger log = LoggerFactory.getLogger(RequestDispatcher.class);
	
	private RequestProcessor fallThrough_ = new FallThroughRequestProcessor();
	
	private Map<String, RequestProcessor> requestProcessorMap_ = new HashMap<String, RequestProcessor>();

	protected void addRequestProcessor(String uri, RequestProcessor processor) {
		requestProcessorMap_.put(uri, processor);
	}

	/**
	 * Looks up and dispatches the request to the right
	 * <code>RequestProcessor</code> if any.
	 * 
	 * @return true if the request was dispatched and processed
	 * @return false otherwise
	 */
	protected boolean dispatch(HttpServletRequest request, HttpServletResponse response) {
		RequestProcessor processor = getRequestProcessor(request);
		return processor.process(request, response);
	}
	
	private RequestProcessor getRequestProcessor(HttpServletRequest request) {
		String uri = request.getRequestURI();
		RequestProcessor processor = requestProcessorMap_.get(uri);
		log.debug("uri {} to be served by processor {}", uri, processor);
		
		if (processor == null) {
			//do more advanced stuff to find the right processor
		}
		
		if (processor == null) {
			processor = fallThrough_;
		}
		
		return processor;
	}
}
