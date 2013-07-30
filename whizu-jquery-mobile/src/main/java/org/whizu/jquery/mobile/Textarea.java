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
import org.whizu.dom.Content;
import org.whizu.dom.Element;
import org.whizu.html.Html;
import org.whizu.jquery.Input;
import org.whizu.value.StringValue;
import org.whizu.value.Value;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
public class Textarea extends Widget implements Input {
	
	private Logger logger = LoggerFactory.getLogger(Textarea.class);
	
	private Value model_;

	public Textarea(String label) {
		this(new StringValue(label));
	}
	
	public Textarea(Value model) {
		model_ = model;
		model_.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				jQuery(Textarea.this).val(""+model_.get());
			}
		});
	}

	@Override
	public Content build() {
		Element field = Html.textarea(this).attr("name", id());
		Element label = Html.tag("label").attr("for", field.id()).add(model_.name());
		getSession().addInput(this);
		return field.after(label);
	}

	@Override
	public void parseString(String value) {
		logger.debug("Incoming request value {}", value);
		model_.set(value);
	}
	
	@Override
	public void clear() {
		model_.clear();
	}
}
