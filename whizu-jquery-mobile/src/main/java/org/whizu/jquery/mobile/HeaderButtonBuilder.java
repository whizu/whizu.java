package org.whizu.jquery.mobile;

import org.whizu.ui.ClickListener;

/**
 * TODO to be replaced by inner class of HeaderButton
 * @author Rudy D'hauwe
 */
public class HeaderButtonBuilder<B extends Builder<?>> {

	private Sink<Button, B> sink_;

	private ButtonBuilder builder_;

	public HeaderButtonBuilder(String title, Sink<Button, B> sink) {
		builder_ = Button.builder(title);
		builder_.type(Button.Type.ANCHOR);
		sink_ = sink;
	}

	public B build() {
		Button button = builder_.build();
		button.mini = Mini.TRUE;
		return sink_.done(button);
	}

	public HeaderButtonBuilder<B> onClick(ClickListener action) {
		builder_.onClick(action);
		return this;
	}

	public HeaderButtonBuilder<B> onClick(Page foo) {
		builder_.onClick(foo);
		return this;
	}

	public HeaderButtonBuilder<B> onClick(Popup popup) {
		builder_.onClick(popup);
		return this;
	}

	public HeaderButtonBuilder<B> theme(Theme e) {
		builder_.theme(e);
		return this;
	}
}
