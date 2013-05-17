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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.jquery.Input;
import org.whizu.value.StringValue;
import org.whizu.value.Value;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
public class TextFieldImpl extends Widget implements TextField, Input {

	private String text;
	
	private Value value;

	public TextFieldImpl(String text) {
		this.text = text;
	}

	public TextFieldImpl(StringValue value) {
		this.text = value.value();
		this.value = value;
		this.value.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				text = "" + TextFieldImpl.this.value.get();
				jQuery(TextFieldImpl.this).val(text);
			}
		});
	}

	public TextFieldImpl(Value value) {
		if (value.get() == null) {
			this.text = "";
		} else {
			this.text = "" + value.get();
		}
		this.value = value;
		this.value.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				text = "" + TextFieldImpl.this.value.get();
				jQuery(TextFieldImpl.this).val(text);
			}
		});
	}

	@Override
	public Markup compile() {
		getSession().addInput(this);

		// @formatter:off
		return Html.tag("input")
						.attr("id", id())
						.attr("type", "text")
						//.width("300px")
						.attr("name", id())
						.decorate(this)
						.style("display","inline-block")
						.attr("value", text);
		// @formatter:on
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void parseString(String value) {
		this.text = value;
		if (this.value != null) {
			this.value.set(value);
		}
	}
}
