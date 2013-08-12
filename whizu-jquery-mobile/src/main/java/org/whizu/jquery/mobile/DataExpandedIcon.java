package org.whizu.jquery.mobile;

import org.whizu.content.Decorator;
import org.whizu.content.Element;

/**
 * @author Rudy D'hauwe
 */
class DataExpandedIcon implements Decorator {

	private static final String ATTRIBUTE_NAME = "data-expanded-icon";
	
	private DataIcon icon_;

	protected DataExpandedIcon(DataIcon icon) {
		icon_ = icon;
	}
	
	@Override
	public void decorate(Element element) {
		element.attr(ATTRIBUTE_NAME, icon_.value());
	}
}
