package org.whizu.ui;

import org.whizu.jquery.Callback;
import org.whizu.jquery.EventHandler;

public interface Action extends EventHandler {

	public String getCaption();
	
	public void performAction();
	
	public void setCallback(Callback callback);
}
