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
package org.whizu.jquery.mobile;

import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.jquery.mobile.Collapsible;
import org.whizu.jquery.mobile.Theme;
import org.whizu.widget.Widget;

/**
 * An accordion is created in jQuery Mobile by grouping a series of individual
 * collapsibles into a set. An accordion creates a collapsible set of
 * collapsible blocks of content.
 * 
 * @author Rudy D'hauwe
 */
public class Accordion extends Widget {

	//private Log log = LogFactory.getLog(Accordion.class);
	
	private Theme theme;

	//private Theme contentTheme;

	public void addCollapsible(Collapsible element) {
		jQuery(this).append(element);
	}

	@Override
	public Markup compile() {
		return Html.div(this).attr("data-role", "collapsible-set").decorate(theme);
	}

	@Override
	public Accordion css(String clazz) {
		setStyleName(clazz);
		return this;
	}
}
