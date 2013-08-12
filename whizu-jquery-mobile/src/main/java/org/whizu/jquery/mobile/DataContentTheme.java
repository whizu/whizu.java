package org.whizu.jquery.mobile;

import org.whizu.content.Decorator;
import org.whizu.content.Element;

/**
 * @author Rudy D'hauwe
 */
class DataContentTheme implements Decorator {

	private static final String ATTRIBUTE_NAME = "data-content-theme";
	
	private Theme theme_;

	protected DataContentTheme(Theme theme) {
		theme_ = theme;
	}
	
	@Override
	public void decorate(Element element) {
		element.attr(ATTRIBUTE_NAME, theme_.value());
	}
}
