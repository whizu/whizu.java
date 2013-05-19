package org.whizu.tutorial.website;

import org.whizu.annotation.Page;
import org.whizu.html.Title;
import org.whizu.ui.UI;

@Page("/whizu/website")
@Title("Home")
public class Home extends AbstractPage {

	@Override
	public void init(UI ui) {
		ui.getDocument().add(ui.createLabel("Home"));
	}
}
