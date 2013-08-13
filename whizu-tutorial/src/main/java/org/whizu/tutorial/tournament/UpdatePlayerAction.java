package org.whizu.tutorial.tournament;

import org.whizu.jquery.mobile.FormBuilder;
import org.whizu.jquery.mobile.ListSupport;
import org.whizu.util.Strings;

//extends ListEditor (or: ListManager)
public class UpdatePlayerAction extends ListSupport<PlayerVO> {

	@Override
	protected void createForm(FormBuilder builder) {
		builder.addText(model().name);
		builder.addDate(model().birthdate);
	}

	@Override
	protected boolean validate(PlayerVO model) {
		return !Strings.isBlank(model.name.get());
	}
}
