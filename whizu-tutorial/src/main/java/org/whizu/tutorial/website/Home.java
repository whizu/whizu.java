package org.whizu.tutorial.website;

import org.whizu.annotation.Listen;
import org.whizu.annotation.processing.Html;
import org.whizu.html.Title;
import org.whizu.ui.UI;

@Listen("/whizu/website")
@Title("Home")
public class Home extends AbstractPage {

	/**
	 * Home
	 */
	@Html
	private String home;
	
	@Override
	public void init(UI ui) {
		h3(home);
		p("This is a first paragraph.");
	}
}
