package org.whizu.jquery.mobile;

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

	/**
	 * We actually DON'T WANT this behavior! Definitely not with inner classes.
	 */
	@Deprecated
	public <T extends ClickListener> ButtonBuilder onClick(Class<T> listenerClass) {
		build_.onClick(listenerClass);
		return this;
	}
}
