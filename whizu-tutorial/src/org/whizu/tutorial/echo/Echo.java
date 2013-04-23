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
package org.whizu.tutorial.echo;

import org.whizu.annotations.App;
import org.whizu.dom.Component;
import org.whizu.layout.HorizontalLayout;
import org.whizu.layout.Layout;
import org.whizu.layout.VerticalLayout;
import org.whizu.ui.Application;
import org.whizu.ui.ClickListener;
import org.whizu.ui.Form;
import org.whizu.ui.FormImpl;
import org.whizu.ui.LabelImpl;
import org.whizu.ui.OnSubmit;
import org.whizu.ui.TextFieldImpl;
import org.whizu.ui.UI;
import org.whizu.value.IntegerValue;
import org.whizu.value.StringValue;

/**
 * @author Rudy D'hauwe
 */
@App(uri = "/whizu/tutorial/echo")
public class Echo implements Application {

	private final StringValue input = new StringValue("input");

	private final IntegerValue commentCount = new IntegerValue("count");
	
	private final CommentList commentList = new CommentList();

	@Override
	public String getTitle() {
		return "Echo";
	}

	@Override
	public void init(final UI ui) {
		ui.getDocument().add(layout());
	}

	private Layout layout() {
		return new HorizontalLayout()
				  .add(left())
				  .add(right());
	}

	private Component left() {
		return new VerticalLayout()
			.css("left-column")
			.add(new LabelImpl("Welcome to your very first meet and greet with Whizu."))
			.add(new LabelImpl("Share a thought and allow for Whizu to echo your words."))
			.add(form());
	}
	
	private Form form() {
		Form form = new FormImpl();
		form.css("form");
		form.add(textfield());
		form.add(button());
		return form;
	}

	private Component button() {
		return new LabelImpl("Share")
			.css("submit-button")
			.addClickListener(new ClickListener() {
	
				@Override
				public void click() {
					submit();
				}
			});
	}
	
	/*
	private void addDetail(StringValue message) {
		history.prepend(new VerticalLayout()
			.css("detail")
			.add(new LabelImpl(new Date().toString()).css("detail-date"))
			.add(new LabelImpl(message.getValue())));
	}
	*/
	
	private Component textfield() {
		return new TextFieldImpl(input).css("message");
	}

	private Component right() {
		return new VerticalLayout()
		  .css("right-column")
		  .add(new LabelImpl("Thank you for sharing $1 comments.", commentCount).css("tekst"))
		  .add(commentList);
	}

	@OnSubmit
	public void submit() {
		commentCount.increment();
		commentList.add(input);
		input.clear();
	}
}
