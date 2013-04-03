/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the “EUPL”) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an “AS IS” basis and 
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
package org.whizu.jquery.mobile;

import org.whizu.runtime.ScriptUI;
import org.whizu.ui.Application;
import org.whizu.ui.UI;
import org.whizu.ui.Widget;

/**
 * @author Rudy D'hauwe
 */
public class JQueryMobileApp implements Application {

	private UI ui = new ScriptUI();

	@Override
	public String getTitle() {
		return "jQuery Mobile";
	}

	@Override
	public void init(UI ui) {
		this.ui = ui;
		ui.getDocument().add(ui.createLabel("hello there"));
	}

	public void addLabel(String label) {
		ui.getDocument().add(ui.createLabel(label));
	}

	public void addHeader(String title) {
		add(new Header("<h2>" + title + "</h2>"));
	}
	
	protected void add(Widget widget) {
		ui.getDocument().add(widget);
	}

	public void addFooter(String title) {
		//add(new Footer(title));
	}
}
