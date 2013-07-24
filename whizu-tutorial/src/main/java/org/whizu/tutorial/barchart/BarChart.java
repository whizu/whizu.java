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
package org.whizu.tutorial.barchart;

import java.util.Date;

import org.whizu.annotation.App;
import org.whizu.annotation.Css;
import org.whizu.annotation.Title;
import org.whizu.dom.Component;
import org.whizu.jquery.Function;
import org.whizu.js.JavaScript;
import org.whizu.layout.CssLayout;
import org.whizu.layout.HorizontalLayout;
import org.whizu.layout.Layout;
import org.whizu.layout.VerticalLayout;
import org.whizu.ui.BarChartImpl;
import org.whizu.ui.ClickListener;
import org.whizu.ui.Document;
import org.whizu.ui.DocumentImpl;
import org.whizu.ui.Form;
import org.whizu.ui.FormImpl;
import org.whizu.ui.Label;
import org.whizu.ui.LabelImpl;
import org.whizu.ui.TextFieldImpl;
import org.whizu.value.IntegerValue;
import org.whizu.value.StringValue;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/tutorial/barchart")
public class BarChart {

	final StringValue antwoord = new StringValue("Mijn hobby is...");

	final IntegerValue aantal = new IntegerValue("aantal");

	@Title
	public String getTitle() {
		return "My BarChart Tutorial";
	}

	public void init() {
		// model
		aantal.value(0);

		// user interface
		Document document = new DocumentImpl();
		Layout layout = new HorizontalLayout();

		Layout left = new CssLayout();
		left.css("left-column");
		left.add(new LabelImpl("Welkom op deze eenvoudige rondvraag.").css("tekst"));
		left.add(new LabelImpl("Het aantal reeds ontvangen antwoorden is:").css("tekst"));
		left.add(new LabelImpl(aantal).css("highlight"));
		final Layout graph = new CssLayout();
		left.add(graph);

		final Layout history = new VerticalLayout();
		Layout right = new CssLayout();
		right.css("right-column");
		right.add(new LabelImpl("Wat is jouw favoriete hobby?"));
		Form form = new FormImpl();
		form.add(new TextFieldImpl(antwoord));
		form.css("form");
		Label button = new LabelImpl("Verzenden").css("submit-button");
		button.addClickListener(new ClickListener() {
			int kort = 0;
			int lang = 0;

			@Override
			public void click() {
				aantal.increment();
				if (antwoord.value().length() > 10) {
					lang++;
				} else {
					kort++;
				}
				Layout detail = new VerticalLayout();
				detail.add(new LabelImpl(new Date().toString()).css("detail-date"));
				detail.add(new LabelImpl(antwoord.value()));
				detail.css("detail");
				history.prepend(detail);
				antwoord.clear();
				graph.empty();
				graph.add(new BarChartImpl(new String[]{"So cool", "So not cool"}, new Integer[]{kort, lang}));
				// ui.delay(2500, new Function() {
				JavaScript.setTimeout(2500, new Function() {

					@Override
					public void execute() {
						aantal.increment();
						antwoord.value("Wat is jouw hobby?");
					}
				});
			}
		});
		form.add(button);
		right.add(form);
		right.add(history);

		layout.add(left);
		layout.add(right);

		document.add(layout);
	}

	@Css("mystyle")
	public Component getLayout() {
		return new VerticalLayout();
	}
	
	class FunctionImpl extends Function {

		@Override
		public void execute() {
			aantal.increment();
			antwoord.value("Wat is jouw hobby?");
		}
	}
}
