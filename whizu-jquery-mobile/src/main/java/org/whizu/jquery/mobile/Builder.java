package org.whizu.jquery.mobile;

public interface Builder<T> {

	/**
	 * TODO Verify that build() can be called multiple times and returns a
	 * different object each time. It should ;-)
	 */
	public T build();
}
