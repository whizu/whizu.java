package org.whizu.server;

import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
class DefaultRequestProcessor extends AbstractRequestProcessor {

	@Override
	public boolean accept(Class<?> clazz) {
		return Application.class.isAssignableFrom(clazz);
	}
}
