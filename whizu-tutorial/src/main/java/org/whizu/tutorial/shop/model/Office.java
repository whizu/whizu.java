package org.whizu.tutorial.shop.model;

import org.whizu.annotation.Name;
import org.whizu.value.Smart;
import org.whizu.value.StringValue;

public class Office extends Entity {

	@Name("Address")
	public final Smart<String> adres = new Smart<String>();
	
	@Name("Name")
	public final StringValue naam = new StringValue();
	
	public Office() {
	}

	public Office(long id, String naam) {
		this.setId(id);
		this.naam.set(naam);
		this.adres.setValue("");
	}
}
