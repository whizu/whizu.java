package org.whizu.tutorial.website;

import org.whizu.annotation.App;
import org.whizu.annotation.Title;

@App("/whizu/website")
@Title("Home")
public class Home extends AbstractPage {

	/**
	 * Home
	 */
	//@Html/@Multiline
	private String home;
	
	public void init() {
		h3(home);
		p("This is a first paragraph.");
	}
}
