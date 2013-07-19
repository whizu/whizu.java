package org.whizu.tutorial.website;

import org.whizu.annotation.App;
import org.whizu.annotation.processing.Html;
import org.whizu.html.Title;
import org.whizu.ui.UI;

@App("/whizu/website")
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
