package org.whizu.widget;

import org.whizu.dom.Content;
import org.whizu.dom.ContentBuilder;

class BuildOnDemand implements Content {

	private ContentBuilder builder_;

	public BuildOnDemand(ContentBuilder builder) {
		builder_ = builder;
	}
	
	@Override
	public String render() {
		Content content = builder_.build();
		return content.render();
	}
}
