package org.whizu.jquery.mobile;

import org.whizu.jquery.mobile.Button.Type;
import org.whizu.ui.ClickListener;

public class ButtonBuilder implements Builder<Button> {

	private Button build_;

	public ButtonBuilder(String title) {
		build_ = new Button(title);
	}

	public Button build() {
		return build_;
	}

	public ButtonBuilder onClick(ClickListener listener) {
		build_.onClick(listener);
		return this;
	}

	public ButtonBuilder onClick(Page foo) {
		build_.onClick(foo);
		return this;
	}

	public ButtonBuilder onClick(Popup popup) {
		build_.onClick(popup);
		return this;
	}

	public void type(Type type) {
		build_.type(type);
	}

	public void theme(Theme theme) {
		build_.theme(theme);
	}
}
