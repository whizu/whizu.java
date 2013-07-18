package org.whizu.jquery.mobile;

public class HeaderButtonBuilder<B extends Builder<?>> {

	private Sink<Button, B> sink_;
	
	private ButtonBuilder builder_;

	public HeaderButtonBuilder(String title, Sink<Button, B> sink) {
		builder_ = Button.builder(title);
		sink_ = sink;
	}

	public B build() {
		Button button = builder_.build();
		button.mini = Mini.TRUE;
		return sink_.done(button);
	}

	public HeaderButtonBuilder<B> onClick(Page foo) {
		builder_.onClick(foo);
		return this;
	}

	public HeaderButtonBuilder<B> onClick(Popup popup) {
		builder_.onClick(popup);
		return this;
	}
}
