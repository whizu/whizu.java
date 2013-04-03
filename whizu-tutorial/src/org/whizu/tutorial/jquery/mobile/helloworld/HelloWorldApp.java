package org.whizu.tutorial.jquery.mobile.helloworld;

import org.whizu.jquery.mobile.JQueryMobileApp;
import org.whizu.ui.UI;

public class HelloWorldApp extends JQueryMobileApp {

	public HelloWorldApp() {
		System.out.println("*************************** in constructor");
	}

	@Override
	public void init(UI ui) {
		addHeader("my header");
		addLabel("my label");
		addFooter("my footer");
	}
}
