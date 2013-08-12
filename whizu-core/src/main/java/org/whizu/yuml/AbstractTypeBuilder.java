package org.whizu.yuml;

import org.whizu.proxy.ProxyBuilder;
import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
abstract class AbstractTypeBuilder<T extends AbstractTypeBuilder<T>> extends ProxyBuilder<Type> {

	private Color background_;

	private String name_;

	public final T background(Color color) {
		background_ = color;
		return getThis();
	}
	
	protected String buildBackground() {
		return (background_ == null) ? "" : "{bg:" + background_.name() + "}";
	}
	
	protected final T getThis() {
		return Objects.cast(this);
	}
	
	protected String name() {
		return name_;
	}
	
	protected T name(String name) {
		name_ = name;
		return getThis();
	}
}
