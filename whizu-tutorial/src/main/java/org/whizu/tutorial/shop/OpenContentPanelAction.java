/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the "EUPL") version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an "AS IS" basis and 
 * without warranties of any kind concerning the Software, including 
 * without limitation merchantability, fitness for a particular purpose, 
 * absence of defects or errors, accuracy, and non-infringement of 
 * intellectual property rights other than copyright. This disclaimer 
 * of warranty is an essential part of the License and a condition for 
 * the grant of any rights to this Software.
 *   
 * For more  details, see <http://joinup.ec.europa.eu/software/page/eupl>.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.tutorial.shop;

import org.whizu.jquery.Callback;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Select;
import org.whizu.tutorial.panel.Action;
import org.whizu.tutorial.panel.Panel;
import org.whizu.widget.Container;

/**
 * @author Rudy D'hauwe
 */
public abstract class OpenContentPanelAction implements Action {

	private final String id_;
	
	public OpenContentPanelAction() {
		id_ = RequestContext.getRequest().session().next();
	}
	
	@Override
	public void performAction() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCallback(Callback callback) {
		throw new UnsupportedOperationException();
	}

	@Select(id = "right-column")
	JQuery contentPanel;

	public void performAction(Panel panel) {
		JQuery detailPanel = RequestContext.getRequest().select("$(\"#detail\")");
		detailPanel.empty();
		contentPanel = RequestContext.getRequest().select("$(\"#right-column\")");
		contentPanel.empty();
		getPanel().create(new Container("right-column"));
	}

	public abstract Panel getPanel();
	
	@Override
	public String id() {
		return id_;
	}

	@Override
	public void handleEvent() {
		performAction(new OfficeSearchPanel());
	}
}
