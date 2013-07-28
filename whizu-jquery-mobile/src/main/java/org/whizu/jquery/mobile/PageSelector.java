package org.whizu.jquery.mobile;

import org.whizu.dom.Element;
import org.whizu.jquery.Selector;

/**
 * @author Rudy D'hauwe
 */
class PageSelector extends Selector implements Page {

	private String id_;

	public PageSelector(String selector) {
		super(selector);
	}

	public PageSelector(String id, String selector) {
		this(selector);
		id_ = id;
	}

	@Override
	public Header header(String title) {
		Header header = new Header(title);
		prepend(header);
		return header;
	}
	
	protected void header(Header header) {
		prepend(header);
	}

	@Override
	public Element p(String text) {
		query().find("div[data-role=content]").append(text);
		return null;
	}

	@Override
	public Footer footer(String title) {
		Footer footer = new Footer(title);
		append(footer);
		return footer;
	}

	@Override
	public String id() {
		return id_;
	}
}
