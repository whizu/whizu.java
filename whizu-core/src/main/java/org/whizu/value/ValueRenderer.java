package org.whizu.value;

import org.whizu.dom.Component;

public interface ValueRenderer {

	public Component render(DateValue value);

	public Component render(IntegerValue value);

	public Component render(StringValue value);

	public <T> Component render(ValueList<T> value);
	
	public <T extends ValueObject> Component render(ValueTable<T> value);
}
