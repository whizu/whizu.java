package org.whizu.tutorial.website;

import org.whizu.annotation.Page;
import org.whizu.html.Description;
import org.whizu.html.Title;
import org.whizu.ui.UI;

@Page("/whizu/website/about")
@Title("About")
@Description("About us")
public class About extends AbstractPage {

	@Override
	public void init(UI ui) {
		ui.getDocument().add(ui.createLabel("About"));
	}
}
