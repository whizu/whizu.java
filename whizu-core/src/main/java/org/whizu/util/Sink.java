package org.whizu.util;


public interface Sink<T, B extends Builder<?>> {

	public B done(T build);
}
