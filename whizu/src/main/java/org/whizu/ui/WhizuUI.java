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
package org.whizu.ui;

import org.whizu.dom.Component;
import org.whizu.jquery.Function;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;
import org.whizu.layout.AbsoluteLayout;
import org.whizu.layout.CssLayout;
import org.whizu.layout.FlowLayout;
import org.whizu.layout.HorizontalLayout;
import org.whizu.layout.Layout;
import org.whizu.layout.VerticalLayout;
import org.whizu.value.StringValue;
import org.whizu.value.Value;

/**
 * @author Rudy D'hauwe
 */
public class WhizuUI implements UI {

	public WhizuUI() {
	}

	@Override
	public Layout createAbsoluteLayout() {
		return new AbsoluteLayout();
	}

	@Override
	public BarChart createBarChart(String[] x, Integer[] y) {
		return new BarChartImpl(x, y);
	}

	@Override
	public Button createButton(String title) {
		return new ButtonImpl(title);
	}

	@Override
	public Button createButton(String title, ClickListener clickListener) {
		return new ButtonImpl(title, clickListener);
	}

	@Override
	public Layout createCssLayout() {
		return new CssLayout();
	}

	@Override
	public Dialog createDialog(String title) {
		return new DialogImpl(title);
	}

	@Override
	public Layout createFlowLayout() {
		return new FlowLayout();
	}

	@Override
	public Form createForm() {
		return new FormImpl();
	}

	@Override
	public Layout createHorizontalLayout() {
		return new HorizontalLayout();
	}

	@Override
	public Html createHtml(String html) {
		throw new UnsupportedOperationException();
	}

	@Override
	public HtmlEditor createHtmlEditor(String html) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Image createImage(String href) {
		return new ImageImpl(href);
	}

	@Override
	public Label createLabel(String text) {
		return new LabelImpl(text);
	}

	private Label createLabel(String text, Component arg) {
		return new LabelImpl(text, arg);
	}

	@Override
	public Label createLabel(String text, Value arg) {
		Label valueLabel = createLabel(arg);
		valueLabel.css("teller");
		return createLabel(text, valueLabel);
	}

	@Override
	public Label createLabel(Value value) {
		return new LabelImpl(value);
	}

	@Override
	public Table createTable(String title) {
		return new TableImpl(title);
	}

	@Override
	public Text createText(String text) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TextArea createTextArea(String text) {
		return new TextAreaImpl(text);
	}

	@Override
	public TextField createTextField(String text) {
		return new TextFieldImpl(text);
	}
	
	@Override
	public TextField createTextField(StringValue value) {
		return new TextFieldImpl(value);
	}

	@Override
	public VerticalLayout createVerticalLayout() {
		return new VerticalLayout();
	}

	@Override
	public Document getDocument() {
		Request request = getRequest();
		Session session = request.getSession();
		Document document = (Document) session.getAttribute("document");
		if (document == null) {
			document = new DocumentImpl();
			session.setAttribute("document", document);
		}
		return document;
	}

	public Request getRequest() {
		return RequestContext.getRequest();
	}

	@Override
	public void openDialog(View wnd) {
		Component component = wnd.createView(this);
		Dialog dialog = createDialog(wnd.getTitle());
		dialog.width(wnd.getWidth());
		dialog.add(component);
		getDocument().add(dialog);
	}

	@Override
	public Window openWindow(String title) {
		return new WindowImpl(title);
	}

	@Override
	public void openWindow(View wnd) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delay(int milliseconds, Function action) {
		new Timeout(action, milliseconds);
	}
	
	@Override
	public void schedule(int milliseconds, Function action) {
		new Schedule(milliseconds, action);
	}
}
