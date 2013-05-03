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
import org.whizu.jquery.Function;
import org.whizu.js.JavaScript;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
class Timeout extends Widget {

	private Function function;

	private int milliseconds;

	//@JavaScript
	//private JavaScript js = new JavaScript();
	
	public Timeout(Function function, int milliseconds) {
		this.milliseconds = milliseconds;
		this.function = function;
		compile(); 
		//TODO refactor/this is a special kind of widget
		//TODO contains no markup, only client-side javascript
	}

	@Override
	public final Markup compile() {
		JavaScript js = new JavaScript();
		compile(js);
		return null;
	}

	protected void compile(JavaScript js) {
		js.setTimeout(function, milliseconds);
	}
}
