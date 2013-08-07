package org.whizu.tutorial.panel;

import org.whizu.jquery.EventHandler;
import org.whizu.util.Callback;

public interface Action extends EventHandler {

	public String getCaption();
	
	public void performAction();
	
	public void setCallback(Callback callback);
}
