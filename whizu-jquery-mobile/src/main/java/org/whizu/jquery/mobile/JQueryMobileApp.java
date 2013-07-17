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
package org.whizu.jquery.mobile;

import org.whizu.annotation.Template;
import org.whizu.dom.Component;
import org.whizu.ui.Application;
import org.whizu.ui.UI;
import org.whizu.ui.WhizuUI;

/**
 * @author Rudy D'hauwe
 */
@Template("/org/whizu/jquery/mobile/document.html")
public abstract class JQueryMobileApp implements Application {

	@Override
	public final void init(UI ui) {
		onLoad(Jqm.document());
	}
	
	protected abstract void onLoad(Document document);
	
	public Document document() {
		return Jqm.document();
	}

	@Deprecated
	public void addHeader(String title) {
		add(new Header(title));
	}
	
	@Deprecated
	protected <T extends Component> T add(T widget) {
		new WhizuUI().getDocument().add(widget);
		return widget;
	}

	@Deprecated
	public void addFooter(String title) {
		add(new Footer(title));
	}
	
	@Deprecated
	public void addButton(String title) {
		add(new Button(title));
	}
	
	@Deprecated
	public Form addForm() {
		return add(new Form());
	}
}
