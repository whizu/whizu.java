package org.whizu.server;

import org.whizu.jquery.mobile.JQueryMobile;

class JQueryMobileRequestProcessor extends AbstractRequestProcessor {

	@Override
	public boolean accept(Class<?> clazz) {
		return JQueryMobile.class.isAssignableFrom(clazz);
	}
}
