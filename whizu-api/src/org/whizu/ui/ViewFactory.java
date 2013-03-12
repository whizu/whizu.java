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
package org.whizu.ui;

import org.whizu.value.StringValue;
/**
 * @author Rudy D'hauwe
 */
public interface ViewFactory {

	public Button createButton(String title);

	public Button createButton(String title, ClickListener clickListener);
	
	public Dialog createDialog(String title);
	
	public Label createLabel(String text);
	
	public Layout createAbsoluteLayout();
	
	public Layout createHorizontalLayout();
	
	public Layout createVerticalLayout();
	
	public Layout createFlowLayout();
	
	public Layout createCssLayout();

	public Image createImage(String href);

	public void openDialog(View view);

	/**
	 * (rich) text area editor.
	 */
	public TextArea createTextArea(String text);
	
	/**
	 * label (single line / string) editor.
	 */
	public TextField createTextField(String text);
	
	public Form createForm();
	
	/**
	 * HTML viewer.
	 */
	public Html createHtml(String html);
	
	public HtmlEditor createHtmlEditor(String html);
	
	/**
	 * (rich) text area viewer.
	 */
	public Text createText(String text);

	public Table createTable(String title);

	public TextField createTextField(StringValue value);
}
