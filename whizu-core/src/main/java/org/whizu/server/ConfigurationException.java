package org.whizu.server;

import org.whizu.util.SystemException;

public class ConfigurationException extends SystemException {

	public ConfigurationException(String pattern, Object... args) {
		super(pattern, args);
	}
}
