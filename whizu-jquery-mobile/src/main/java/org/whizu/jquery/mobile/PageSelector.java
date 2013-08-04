package org.whizu.jquery.mobile;

import org.whizu.content.Content;
import org.whizu.content.Element;
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

	public void add(Content content) {
		query().find("div[data-role=content]").append(content);
	}
	
	public void jQueryAppend(Content content) {
		query().append(content);
	}
	
	@Override
	public Element p(String text) {
		query().find("div[data-role=content]").append(text);
		return null;
	}

	@Override
	public Footer footer(String title) {
		Footer footer = new Footer(title);
		jQueryAppend(footer);
		return footer;
	}

	@Override
	public String id() {
		return id_;
	}
}
