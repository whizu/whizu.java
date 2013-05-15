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
package org.whizu.tutorial.shop.action;

import org.whizu.jquery.Callback;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Select;
import org.whizu.panel.Panel;
import org.whizu.tutorial.shop.panel.OfficeSearchPanel;
import org.whizu.ui.Action;
import org.whizu.widget.Container;

//@Action
public abstract class OpenContentPanelAction implements Action {

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCallback(Callback callback) {
		// TODO Auto-generated method stub
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
	public String getId() {
		return RequestContext.getRequest().getSession().next();
	}

	@Override
	public void handleEvent() {
		performAction(new OfficeSearchPanel());
	}
}