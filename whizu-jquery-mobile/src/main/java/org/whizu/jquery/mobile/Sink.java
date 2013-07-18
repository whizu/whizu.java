package org.whizu.jquery.mobile;

public interface Sink<T, B extends Builder<?>> {

	public B done(T build);
}
