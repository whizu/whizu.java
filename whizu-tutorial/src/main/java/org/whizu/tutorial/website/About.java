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
package org.whizu.tutorial.website;

import org.whizu.annotation.App;
import org.whizu.annotation.Description;
import org.whizu.annotation.Title;
import org.whizu.annotation.processing.Html;
import org.whizu.annotation.processing.Markdown;
import org.whizu.ui.UI;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/website/about")
@Title("About us - Whizu")
@Description("About us at Whizu")
public class About extends AbstractPage {

	/**
	 * About me
	 */
	@Html
	private String title;

	/**
	 * Whizu is open source software available for download and for free use
	 * under the terms of the European Union Public License (EUPL) v1.1. Any use
	 * of this software, other than as authorized under this license is strictly
	 * prohibited.
	 */
	@Html
	public String about;

	/**
	 * ### another line
	 */
	@Markdown
	private String welcome;

	@Override
	public void init(UI ui) {
		h3(title);
		p(about);
		add(welcome);
	}
}
