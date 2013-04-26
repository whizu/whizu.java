package org.whizu.annotation;

import org.whizu.ui.Application;
import org.whizu.ui.UI;

public class TestApplication implements Application {

	@Title
	public String getTitle() {
		return "the-title";
	}

	@Override
	@Css
	public void init(UI ui) {
		System.out.println("init");
	}
	
	@Css
	public void custom() {
		System.out.println("custom");
	}
}
