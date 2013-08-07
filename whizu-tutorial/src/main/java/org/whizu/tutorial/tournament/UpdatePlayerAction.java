package org.whizu.tutorial.tournament;

import org.whizu.jquery.mobile.FormBuilder;
import org.whizu.util.Strings;

//extends ListEditor (or: ListManager)
public class UpdatePlayerAction extends FormItemActionHelper<Player> {

	@Override
	protected void createForm(FormBuilder builder) {
		builder.addText(model().name);
		builder.addDate(model().birthdate);
	}

	@Override
	protected boolean validate(Player model) {
		return !Strings.isBlank(model.name.get());
	}
}
