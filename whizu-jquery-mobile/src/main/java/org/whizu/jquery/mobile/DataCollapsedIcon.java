package org.whizu.jquery.mobile;

import org.whizu.content.Decorator;
import org.whizu.content.Element;

/**
 * @author Rudy D'hauwe
 */
class DataCollapsedIcon implements Decorator {

	private static final String ATTRIBUTE_NAME = "data-collapsed-icon";
	
	private DataIcon icon_;

	protected DataCollapsedIcon(DataIcon icon) {
		icon_ = icon;
	}
	
	@Override
	public void decorate(Element element) {
		element.attr(ATTRIBUTE_NAME, icon_.value());
	}
}
