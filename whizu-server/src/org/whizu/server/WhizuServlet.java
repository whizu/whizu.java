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

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.whizu.jquery.Input;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;
import org.whizu.runtime.ScriptUI;
import org.whizu.ui.Application;
import org.whizu.ui.UI;

/**
 * 
 */
public class WhizuServlet extends HttpServlet {

	private static final long serialVersionUID = 520182899630886403L;

	private static final String WHIZ_SESSION = "whiz-session";

	// must be in session (per user)
	private UI ui;

	// not in session (shared across users)
	private Application application;

	// session.getAttribute("ui") == null ?
	private boolean setup = false;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			RequestContext.init(new RequestContext() {
				
				@Override
				protected final Request getRequestImpl() {
					return RequestImpl.get();
				}
			});
			String className = config.getInitParameter("application");
			@SuppressWarnings("unchecked")
			Class<Application> clazz = (Class<Application>) Class.forName(className);
			this.application = clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		long start = System.currentTimeMillis();
		try {
			HttpSession session = request.getSession();
			Session userSession = assureUserSession(session);
			show(request.getParameterMap());
			String id = request.getParameter("id");
			if (id == null) {
				System.out.println("running setup:" + request.getRequestURI());
				this.setup();
			} else {
				userSession.handleEvent(id);
			}
		} finally {
			try {
				String script = RequestImpl.get().finish();
				long end = System.currentTimeMillis();
				System.out.println("Server side completed in " + (end - start) + "ms");
				System.out.println("Sending script- " + script);
				response.getWriter().print(script);
				response.getWriter().close();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	private Session assureUserSession(HttpSession session) {
		Session us = (Session) session.getAttribute(WHIZ_SESSION);
		if (us == null) {
			us = new SessionImpl();
			session.setAttribute(WHIZ_SESSION, us);
		}
		RequestImpl.get().setSession(us);
		return us;
	}

	private void show(Map<String, String[]> parameterMap) {
		Set<String> keys = parameterMap.keySet();
		for (String key : keys) {
			System.out.println("P:" + key + "=" + parameterMap.get(key)[0]);
			Session userSession = RequestImpl.get().getSession();
			Input editable = userSession.getInput(key);
			if (editable != null) {
				System.out.println("updating editable server-side " + editable);
				editable.parseString(parameterMap.get(key)[0]);
			}
		}
	}

	private void setup() {
		if (!setup) {
			ui = new ScriptUI();
			application.init(ui);
			//setup = true;
		} else {
			// another user session
			throw new IllegalStateException("Application already setup"); //recreate user interface?
			//TODO exceptions should be visible in browser window in development mode
		}
	}
}
