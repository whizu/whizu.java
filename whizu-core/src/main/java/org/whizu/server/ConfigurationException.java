package org.whizu.server;

import org.whizu.util.SystemException;

/**
 * @author Rudy D'hauwe
 */
public class ConfigurationException extends SystemException {

	public ConfigurationException(String pattern, Object... args) {
		super(pattern, args);
	}
}
