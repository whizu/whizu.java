package org.whizu.value;

abstract class ValueBuilder<T extends ValueBuilder<T, V>, V> extends AbstractValue<V> {

	public ValueBuilder(String name) {
		super(name);
	}

	public T set(V value) {
		setValue(value);
		return getThis();
	}

	@SuppressWarnings("unchecked")
	private T getThis() {
		return (T) this;
	}
}
