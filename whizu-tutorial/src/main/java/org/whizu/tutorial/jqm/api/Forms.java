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
package org.whizu.tutorial.jqm.api;

import org.whizu.annotation.App;
import org.whizu.jquery.ClickListener;
import org.whizu.jquery.mobile.Form;
import org.whizu.jquery.mobile.FormBuilder;
import org.whizu.jquery.mobile.Header;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Jqm;
import org.whizu.jquery.mobile.Page;
import org.whizu.value.StringValue;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/jqm/widgets/forms")
public class Forms implements JQueryMobile {

	private StringValue name = new StringValue("Name");
	
	@Override
	public void onLoad(Page page) {

		// @formatter:off
		Header.builder()
		    .title("Forms")
		    .build()
		    .on(page);
		// @formatter:on
		
		Form form = FormBuilder.create().onSubmit(submit()).addText(name).build();
		page.add(form);
	}

	private ClickListener submit() {
		return new ClickListener() {
			
			@Override
			public void click() {
				Jqm.page().add("Name entered " + name.get());				
			}
		};
	}
}
