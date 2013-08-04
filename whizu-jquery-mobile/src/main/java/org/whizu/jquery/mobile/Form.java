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

import org.whizu.dom.Component;
import org.whizu.dom.Content;
import org.whizu.jquery.ClickListener;
import org.whizu.value.DateValue;
import org.whizu.value.PasswordValue;
import org.whizu.value.StringValue;
import org.whizu.value.Value;

/**
 * @author Rudy D'hauwe
 */
public interface Form extends Component {

	public void add(Content field);
	
	public void add(PasswordValue value);

	public Button addButton(String title);

	public void addDate(DateValue date);

	public void addField(Value value);

	public void addFieldContain(Component component);

	public void addFlipSwitch();

	public void addListView();

	public void addSlider(int min, int max);

	public void addSlider(int min, int max, Theme theme);

	public void addText(String label);

	public void addText(StringValue value);

	public void addText(StringValue value, boolean fieldContain);

	public void addTextarea(String label);

	public void addTextarea(StringValue value);

	public void addTextarea(StringValue value, boolean fieldContain);

	public void clear();

	public void onSubmit(ClickListener handler);
	
	public void addButton(Button submit);
}
