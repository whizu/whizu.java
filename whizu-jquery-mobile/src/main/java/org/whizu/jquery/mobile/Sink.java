package org.whizu.jquery.mobile;

import org.whizu.util.Builder;

public interface Sink<T, B extends Builder<?>> {

	public B done(T build);
}
