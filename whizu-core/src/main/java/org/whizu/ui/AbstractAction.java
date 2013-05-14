package org.whizu.ui;

import org.whizu.jquery.Callback;
import org.whizu.jquery.RequestContext;

public abstract class AbstractAction implements Action {

	private Callback callback;
	
	protected UI ui = new WhizuUI();

	@Override
	public final String getId() {
		return RequestContext.getRequest().getSession().next();
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
