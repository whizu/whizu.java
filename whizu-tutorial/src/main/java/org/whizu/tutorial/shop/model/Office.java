package org.whizu.tutorial.shop.model;

import org.whizu.annotation.Name;
import org.whizu.value.Smart;
import org.whizu.value.StringValue;

public class Office extends Entity {

	@Name("Address Label")
	public final Smart<String> address = new Smart<String>("Address");
	
	@Name("Name Label")
	public final StringValue naam = new StringValue("Name");
}
