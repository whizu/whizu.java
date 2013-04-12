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
package org.whizu.server;

import org.whizu.jquery.Function;
import org.whizu.jquery.Script;

/**
 * @author Rudy D'hauwe
 */
class FunctionWorker {

	static Script compile(Function f) {
		RequestImpl request = RequestImpl.get();
		try {
			Script script = request.createScript();
			f.execute();
			return script;
		} finally {
			request.pop();
		}
	}

	static String define(Function f) {
		return "function(" + f.params + ") { " + compile(f).toJavaScript() + " }";
	}
	
	static String evaluate(Function f) {
		String result = compile(f).toJavaScript();
		return result.substring(0, result.length()-1);
	}
}
