package org.whizu.ui;

import org.whizu.jquery.Callback;
import org.whizu.jquery.RequestContext;

public abstract class AbstractAction implements Action {

	private Callback callback;
	
	protected UI ui = new WhizuUI();

	private String id;

	public AbstractAction() {
		this.id = RequestContext.getRequest().session().next();
	}
	
	@Override
	public final String id() {
		return id;
	}
	
	public final void setCallback(Callback callback) {
		this.callback = callback;
	}
	
	public void callback() {
		if (callback != null) {
			callback.handleEvent();
		}
	}
}
