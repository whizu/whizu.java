package org.whizu.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rudy D'hauwe
 */
class FallThroughRequestProcessor extends AbstractRequestProcessor {

	@Override
	public boolean process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return false;
	}
}
