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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.content.Content;
import org.whizu.content.Element;
import org.whizu.html.Html;
import org.whizu.jquery.Input;
import org.whizu.value.DateValue;
import org.whizu.value.Value;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
class DateField extends Widget implements Input {

	private static final Logger log = LoggerFactory.getLogger(DateField.class);
	
	private Value<?> model_;

	public DateField(String label) {
		this(new DateValue(label));
	}
	
	public DateField(Value<?> model) {
		model_ = model;
		model_.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				jQuery(DateField.this).val(""+model_.get());
			}
		});
	}

	@Override
	public Content build() {
		Element input = Html.input(this).attr("type", "date").attr("name", id()).attr("value", "" + model_.get());
		Element label = Html.tag("label").attr("for", input.id()).add(model_.name());
		session().addInput(this);
		return input.after(label);
	}

	@Override
	public void parseString(String value) {
		log.debug("Incoming request value {}", value);
		model_.parse(value);
	}

	@Override
	public void clear() {
		model_.clear();
	}
}
