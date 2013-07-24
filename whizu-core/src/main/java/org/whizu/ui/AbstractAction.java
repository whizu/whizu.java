package org.whizu.ui;

import org.whizu.jquery.Callback;
import org.whizu.jquery.RequestContext;

public abstract class AbstractAction implements Action {

	private Callback callback;
	
	private String id_;

	public AbstractAction() {
		id_ = RequestContext.getRequest().session().next();
	}
	
	@Override
	public final String id() {
		return id_;
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
