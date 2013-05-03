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

import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
class BarChartImpl extends Widget implements BarChart {

	private String[] x;
	
	private Integer[] y;

	public BarChartImpl(String[] x, Integer[] y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Markup compile() {
		jQuery(this)
				.callunquoted(
						"jqBarGraph",
						"{ data: new Array([" + y[0] + ",'" + x[0] + "','#333333'], [" + y[1] + ",'" + x[1]
								+ "','#666666']) }");
		return Html.div(id());
	}
}