package org.whizu.jquery.mobile;

import org.whizu.util.Builder;
import org.whizu.util.Sink;

public class HeaderBuilder implements Builder<Header> {

	private Header build_ = new Header();

	public HeaderBuilder title(String title) {
		build_.title(title);
		return this;
	}

	public Header build() {
		return build_;
	}

	public HeaderBuilder button(ButtonBuild button) {
		build_.addButton(button);
		return this;
	}
	
	public HeaderButtonBuilder<HeaderBuilder> button(String title) {
		return new HeaderButtonBuilder<HeaderBuilder>(title, new Sink<ButtonBuild, HeaderBuilder>() {

			@Override
			public HeaderBuilder done(ButtonBuild build) {
				build_.addButton(build);
				return HeaderBuilder.this;
			}
		});
	}

	/**
	 * @return a new Header builder.
	 */
	public static HeaderBuilder create() {
		return new HeaderBuilder();
	}
}
