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
package org.whizu.tutorial.layout;

import org.whizu.annotation.Page;
import org.whizu.annotation.Title;
import org.whizu.dom.Component;
import org.whizu.layout.GridLayout;
import org.whizu.ui.Application;
import org.whizu.ui.ClickListener;
import org.whizu.ui.Document;
import org.whizu.ui.UI;

/**
 * @author Rudy D'hauwe
 */
@Page("/whizu/gridlayout")
@Title("My GridLayout Tutorial")
public class GridLayoutTutorial implements Application {

	@Override
	public void init(final UI ui) {
		Document document = ui.getDocument();

		final GridLayout grid = new GridLayout(2);
		grid.add(ui.createLabel("FirstLabel")).add(ui.createLabel("SecondLabel")).add(ui.createLabel("ThirdLabel"));

		document.add(grid);

		Component emptyButton = ui.createButton("Empty").addClickListener(new ClickListener() {

			@Override
			public void click() {
				grid.empty();
			}
		});
		document.add(emptyButton);

		Component addButton = ui.createButton("Add").addClickListener(new ClickListener() {
			int i = 0;
			
			@Override
			public void click() {
				grid.add(ui.createLabel("Label " + (i++)));
			}
		});
		document.add(addButton);
	}
}
