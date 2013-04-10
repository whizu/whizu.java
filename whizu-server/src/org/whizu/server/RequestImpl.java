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

import java.util.Stack;

import org.whizu.dom.Identity;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.Request;
import org.whizu.jquery.Script;
import org.whizu.jquery.Session;

/**
 * @author Rudy D'hauwe
 */
final class RequestImpl implements Request {

	private static ThreadLocal<RequestImpl> request = new ThreadLocal<RequestImpl>() {

		@Override
		protected RequestImpl initialValue() {
			return new RequestImpl();
		}
	};

	static RequestImpl get() {
		return request.get();
	}
	
	private Stack<ScriptImpl> scriptStack = new Stack<ScriptImpl>();

	private Session session;

	private RequestImpl() {
	}

	protected Expression addExpression(String js) {
		Expression script = new Expression(js);
		return addExpression(script);
	}

	protected <T extends Expression> T addExpression(T expr) {
		if (scriptStack.isEmpty()) {
			ScriptImpl script = new ScriptImpl();
			scriptStack.push(script);
		}

		ScriptImpl current = scriptStack.peek();
		current.addExpression(expr);
		return expr;
	}

	public Script createScript() {
		scriptStack.push(new ScriptImpl());
		return scriptStack.peek();
	}

	public String finish() {
		try {
			if (!scriptStack.isEmpty()) {
				Script current = scriptStack.pop();
				return current.toJavaScript();
			} else {
				return "";
			}
		} finally {
			request.remove();
		}
	}

	@Override
	public Session getSession() {
		return session;
	}

	protected Script pop() {
		return scriptStack.pop();
	}

	@Override
	public JQuery select(Identity... objs) {
		JQueryImpl query = new JQueryImpl(objs);
		return addExpression(query);
	}

	@Override
	public JQuery select(String selector) {
		JQueryImpl query = new JQueryImpl(selector);
		return addExpression(query);
	}

	protected final void setSession(Session session) {
		this.session = session;
	}
}
