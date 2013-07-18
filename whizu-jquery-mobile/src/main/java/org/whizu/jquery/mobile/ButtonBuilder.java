package org.whizu.jquery.mobile;

public class ButtonBuilder implements Builder<Button> {

	private Button build_;

	public ButtonBuilder(String title) {
		build_ = new Button(title);
	}

	public Button build() {
		return build_;
	}

	public ButtonBuilder onClick(Page foo) {
		build_.onClick(foo);
		return this;
	}

	public ButtonBuilder onClick(Popup popup) {
		build_.onClick(popup);
		return this;
	}
}
