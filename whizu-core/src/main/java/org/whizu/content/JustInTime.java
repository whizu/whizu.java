package org.whizu.content;


public class JustInTime implements Content {

	private ContentBuilder builder_;

	public JustInTime(ContentBuilder builder) {
		builder_ = builder;
	}
	
	@Override
	public String render() {
		Content content = builder_.build();
		return content.render();
	}
}
