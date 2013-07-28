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
package org.whizu.js;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rudy D'hauwe
 */
public final class Script {

	private final List<Expression> expressionList_ = new ArrayList<Expression>();

	private boolean isRendering_ = false;

	private int renderingPosition_;

	public Script() {
	}

	public Script(String script) {
		expressionList_.add(new Expression(script));
	}

	public String toJavaScript() {
		try {
			isRendering_ = true;
			String script = "";
			
			renderingPosition_ = 0;
			while (renderingPosition_ < expressionList_.size()) {
				Expression expr = expressionList_.get(renderingPosition_);
				script += expr.toJavaScript();
				if (!script.endsWith(";")) {
					script += ";";
				}
				renderingPosition_++;
			}
			/*
			for (Expression expr : expressionList) {
				script += expr.toJavaScript();
				if (!script.endsWith(";")) {
					script += ";";
				}
			}
			*/
			return script;
		} finally {
			isRendering_ = false;
		}
	}

	public void addExpression(Expression expr) {
		if (isRendering_) {
			expressionList_.add(renderingPosition_+1, expr);
		} else {
			expressionList_.add(expressionList_.size(), expr);
		}
	}
}
