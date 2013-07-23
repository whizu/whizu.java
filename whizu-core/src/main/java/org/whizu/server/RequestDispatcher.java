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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.jquery.Input;
import org.whizu.jquery.Session;

/**
 * @author Rudy D'hauwe
 */
class RequestDispatcher {

	private static final Logger log = LoggerFactory.getLogger(RequestDispatcher.class);

	private static final String WHIZU_SESSION = "whizu-session";

	private RequestProcessor fallThrough_ = new FallThroughRequestProcessor();

	private Map<String, RequestProcessor> requestProcessorMap_ = new HashMap<String, RequestProcessor>();

	protected void addRequestProcessor(String uri, RequestProcessor processor) {
		requestProcessorMap_.put(uri, processor);
	}

	/**
	 * Finds the right <code>RequestProcessor</code> to process the request and
	 * dispatches the request to the request processor, if any.
	 * 
	 * @return true if the request was dispatched and processed
	 * @return false otherwise
	 */
	protected boolean dispatch(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		initRequest(request);
		RequestProcessor processor = getRequestProcessor(request);
		return processor.process(request, response);
	}

	private RequestProcessor getRequestProcessor(HttpServletRequest request) {
		String uri = request.getRequestURI();
		RequestProcessor processor = requestProcessorMap_.get(uri);
		log.debug("uri {} to be served by processor {}", uri, processor);

		if (processor == null) {
			// do more advanced stuff to find the right processor
		}

		if (processor == null) {
			processor = fallThrough_;
		}

		return processor;
	}

	// move completely to ajax server?
	private Session initRequest(HttpServletRequest request) {
		Session userSession = initSession(request);
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keys = parameterMap.keySet();
		for (String key : keys) {
			log.debug("Incoming request parameter {} equals {}", key, parameterMap.get(key)[0]);
			Input editable = userSession.getInput(key);
			if (editable != null) {
				editable.parseString(parameterMap.get(key)[0]);
			}
		}

		return userSession;
	}

	private Session initSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Session userSession = (Session) session.getAttribute(WHIZU_SESSION);
		if (userSession == null) {
			userSession = new SessionImpl();
			session.setAttribute(WHIZU_SESSION, userSession);
		}
		RequestImpl.get().session(userSession);
		return userSession;
	}
}
