package org.whizu.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rudy D'hauwe
 */
class FallThroughRequestProcessor extends AbstractRequestProcessor {

	@Override
	public boolean accept(Class<?> clazz) {
		return false;
	}

	@Override
	public boolean process(HttpServletRequest request, HttpServletResponse response) {
		return false;
	}
}
