package org.whizu.tutorial.website;

import org.whizu.annotation.Page;
import org.whizu.ui.UI;

@Page("/whizu/website/about")
public class About extends AbstractPage {

	@Override
	public void init(UI ui) {
		ui.getDocument().add(ui.createLabel("About"));
	}
}
