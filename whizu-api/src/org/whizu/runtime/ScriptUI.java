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
package org.whizu.runtime;

import org.whizu.script.Request;
import org.whizu.script.RequestContext;
import org.whizu.script.Session;
import org.whizu.ui.BarChart;
import org.whizu.ui.Button;
import org.whizu.ui.ClickListener;
import org.whizu.ui.Dialog;
import org.whizu.ui.Document;
import org.whizu.ui.Form;
import org.whizu.ui.Html;
import org.whizu.ui.HtmlEditor;
import org.whizu.ui.Image;
import org.whizu.ui.Label;
import org.whizu.ui.Layout;
import org.whizu.ui.Table;
import org.whizu.ui.Text;
import org.whizu.ui.TextArea;
import org.whizu.ui.TextField;
import org.whizu.ui.UI;
import org.whizu.ui.View;
import org.whizu.ui.VirtualContainer;
import org.whizu.ui.Widget;
import org.whizu.ui.Window;
import org.whizu.value.StringValue;

/**
 * @author Rudy D'hauwe
 */
public class ScriptUI implements UI {

	public ScriptUI() {
	}

	@Override
	public Layout createAbsoluteLayout() {
		return new AbsoluteLayoutImpl();
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
		return new CssLayoutImpl();
	}

	@Override
	public Dialog createDialog(String title) {
		return new DialogImpl(title);
	}

	@Override
	public Layout createFlowLayout() {
		return new FlowLayoutImpl();
	}

	@Override
	public Form createForm() {
		return new FormImpl();
	}

	@Override
	public Layout createHorizontalLayout() {
		return new HorizontalLayoutImpl();
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

	@Override
	public Label createLabel(StringValue value) {
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
	public Layout createVerticalLayout() {
		return new VerticalLayoutImpl();
	}

	@Override
	public VirtualContainer createVirtualContainer() {
		return new VirtualContainerImpl();
	}
	
	public Request getRequest() {
		return RequestContext.getRequest();
	}

	@Override
	public Document getDocument() {
		Request request = getRequest();
		Session session = request.getSession();
		Document document = (Document) session.getAttribute("document");
		if (document == null) {
			System.out.println("Create new document");
			document = new DocumentImpl();
			session.setAttribute("document", document);
		}
		System.out.println("Return document " + document);
		return document;
	}

	@Override
	public void openDialog(View wnd) {
		Widget component = wnd.createView(this);
		Dialog dialog = createDialog(wnd.getTitle());
		dialog.setWidth(wnd.getWidth());
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
}
