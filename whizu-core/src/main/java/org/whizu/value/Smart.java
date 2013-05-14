package org.whizu.value;

import org.whizu.dom.Component;

public class Smart<T> extends AbstractValue<T> {

	public Smart() {
		super("default-label");
	}
	
	public Smart(T value) {
		this();
		setValue(value);
	}

	@Override
	public void parse(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Component render(ValueRenderer renderer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected T getDefaultValue() {
		return null;
	}
}
