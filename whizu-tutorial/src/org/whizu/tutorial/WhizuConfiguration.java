package org.whizu.tutorial;

import org.whizu.jquery.mobile.JQueryMobileApp;
import org.whizu.server.Configuration;

public class WhizuConfiguration extends Configuration {

	@Override
	public void init() {
		addApplication("/whizu/jquery/mobile/helloworld", new JQueryMobileApp());
	}
}
