package org.whizu.jquery.mobile;

import org.whizu.html.Html;

public class PopupBuilder implements Builder<Popup> {

	private Popup build_;

	public PopupBuilder(String id) {
		build_ = new Popup(id);
	}

	@Override
	public Popup build() {
		return build_;
	}

	public PopupBuilder p(String text) {
		build_.add(Html.p(text));
		return this;
	}
}
