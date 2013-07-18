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
package org.whizu.tutorial.jqm.notebook;

import org.whizu.annotation.Listen;
import org.whizu.annotation.Style;
import org.whizu.annotation.Submit;
import org.whizu.jquery.EventHandler;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.mobile.Button;
import org.whizu.jquery.mobile.Form;
import org.whizu.jquery.mobile.JQueryMobileApp;
import org.whizu.jquery.mobile.ListItem;
import org.whizu.jquery.mobile.ListView;
import org.whizu.jquery.mobile.Page;
import org.whizu.layout.CssLayout;
import org.whizu.layout.HorizontalLayout;
import org.whizu.layout.Layout;
import org.whizu.value.StringValue;

/**
 * @author Rudy D'hauwe
 */
@Listen("/whizu/jqm/notebook")
public class Notebook extends JQueryMobileApp {

	private StringValue title = new StringValue("Title");

	private StringValue description = new StringValue("Description");

	private ListView notebook = new ListView();

	private Form form;

	@Override
	public void onLoad(Page page) {
		page.header("My notebook");
		Layout layout = new HorizontalLayout();
		Form form = createForm();
		Layout left = createLeftColumn();
		left.add(form);
		Layout right = createRightColumn();
		right.add(notebook);
		layout.add(left);
		layout.add(right);
		page.append(layout);
		page.footer("jQuery Mobile by Whizu");
	}

	@Style("width:400px")
	private Layout createLeftColumn() {
		return new CssLayout();
	}

	@Style({ "min-height:250px;margin-top:52px;margin-bottom:25px;padding-left:10px;border-left:solid 1px lightblue;width:500px;" })
	private Layout createRightColumn() {
		return new CssLayout();
	}

	@Style("margin:25px")
	Form createForm() {
		form = new Form();
		form.addText(title);
		form.addTextarea(description);
		Button button = form.addButton("Add note");
		addClickListener(button);
		return form;
	}

	@Submit
	public void addNote() {
		ListItem item = new ListItem(title);
		item.p(description);
		notebook.addItem(item);
		form.clear();
	}

	@Deprecated
	private void addClickListener(final Button button) {
		RequestContext.session().addClickListener(new EventHandler() {

			@Override
			public String id() {
				return button.id();
			}

			@Override
			public void handleEvent() {
				Notebook.this.addNote();
			}
		});
	}
}
