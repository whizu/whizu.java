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
package org.whizu.jquery;

import java.util.Stack;

import org.whizu.dom.Identity;
import org.whizu.js.Expression;
import org.whizu.js.Script;

/**
 * @author Rudy D'hauwe
 */
public abstract class AbstractRequest implements Request {

	private Stack<Script> scriptStack = new Stack<Script>();

	private Session session;

	protected AbstractRequest() {
	}

	public final Expression addExpression(String js) {
		Expression script = new Expression(js);
		return addExpression(script);
	}

	protected final <T extends Expression> T addExpression(T expr) {
		if (scriptStack.isEmpty()) {
			Script script = new Script();
			scriptStack.push(script);
		}

		Script current = scriptStack.peek();
		current.addExpression(expr);
		return expr;
	}

	public final Script createScript() {
		scriptStack.push(new Script());
		return scriptStack.peek();
	}

	public String finish() {
		try {
			if (!scriptStack.isEmpty()) {
				try {
					Script current = scriptStack.peek();
					String result = current.toJavaScript();
					return result;
				} finally {
					scriptStack.pop();
				}
			} else {
				return "";
			}
		} finally {
			// request.remove();
		}
	}

	@Override
	public Session getSession() {
		return session;
	}

	public final Script pop() {
		return scriptStack.pop();
	}

	@Override
	public final JQuery select(Identity... objs) {
		JQueryImpl query = new JQueryImpl(objs);
		return addExpression(query);
	}

	@Override
	public final JQuery select(String selector) {
		JQueryImpl query = new JQueryImpl(selector);
		return addExpression(query);
	}

	public final void setSession(Session session) {
		this.session = session;
	}

	@Override
	public final Script compile(Function function) {
		try {
			Script script = createScript();
			function.execute();
			return script;
		} finally {
			pop();
		}
	}

	@Override
	public String define(Function f) {
		return "function(" + f.params + ") { " + compile(f).toJavaScript() + " }";
	}

	@Override
	public String evaluate(Function f) {
		String result = compile(f).toJavaScript();
		return result.substring(0, result.length() - 1);
	}
}
