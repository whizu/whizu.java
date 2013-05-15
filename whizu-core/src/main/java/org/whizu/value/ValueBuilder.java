package org.whizu.value;

abstract class ValueBuilder<T extends ValueBuilder<T, V>, V> extends AbstractValue<V> {

	public ValueBuilder(String key) {
		super(key);
	}

	public ValueBuilder(String key, V value) {
		super(key, value);
	}

	@SuppressWarnings("unchecked")
	protected T getThis() {
		return (T) this;
	}
}
