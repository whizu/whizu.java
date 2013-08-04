package org.whizu.util;

/**
 * @author Rudy D'hauwe
 */
public interface Sink<T, B extends Builder<?>> {

	public B done(T build);
}
