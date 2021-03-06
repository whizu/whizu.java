package org.whizu.jquery.mobile;

import org.whizu.jquery.ClickListener;
import org.whizu.util.Builder;
import org.whizu.util.Sink;

/**
 * TODO to be replaced by inner class of HeaderButton
 * @author Rudy D'hauwe
 */
public class HeaderButtonBuilder<B extends Builder<?>> {

	private ButtonBuilder builder_;

	private Sink<Button, B> sink_;

	public HeaderButtonBuilder(String title, Sink<Button, B> sink) {
		builder_ = ButtonBuilder.createWithTitle(title);
		builder_.type(ButtonType.ANCHOR);
		sink_ = sink;
	}

	public B build() {
		Button button = builder_.mini().build();
		//button.mini = Mini.TRUE;
		return sink_.done(button);
	}

	public HeaderButtonBuilder<B> onClick(ClickListener action) {
		builder_.onClick(action);
		return this;
	}

	public HeaderButtonBuilder<B> onClickOpen(Page foo) {
		builder_.onClickOpen(foo);
		return this;
	}

	public HeaderButtonBuilder<B> onClickOpen(Popup popup) {
		builder_.onClickOpen(popup);
		return this;
	}
	
	public HeaderButtonBuilder<B> onClickOpenDialog(Page page) {
		builder_.onClickOpenDialog(page);
		return this;
	}

	public HeaderButtonBuilder<B> theme(Theme e) {
		builder_.theme(e);
		return this;
	}
}
