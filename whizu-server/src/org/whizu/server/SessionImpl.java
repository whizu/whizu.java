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

import java.util.HashMap;
import java.util.Map;

import org.whizu.jquery.EventHandler;
import org.whizu.jquery.Input;
import org.whizu.jquery.Session;
import org.whizu.ui.Application;
import org.whizu.ui.WhizuUI;

/**
 * @author Rudy D'hauwe
 */
class SessionImpl implements Session {

	private static int sessionCount = 0;

	private int componentCount = 0;

	private Map<String, Object> attrMap = new HashMap<String, Object>();

	private Map<String, EventHandler> eventHandlerMap = new HashMap<String, EventHandler>();

	private Map<String, Input> inputMap = new HashMap<String, Input>();

	SessionImpl() {
		sessionCount++;
	}

	@Override
	public void addClickListener(EventHandler listener) {
		eventHandlerMap.put(listener.getId(), listener);
	}

	@Override
	public void addInput(Input input) {
		inputMap.put(input.getId(), input);
	}

	@Override
	public Object getAttribute(String name) {
		return attrMap.get(name);
	}

	@Override
	public EventHandler getEventHandler(String id) {
		return eventHandlerMap.get(id);
	}

	@Override
	public Input getInput(String id) {
		return inputMap.get(id);
	}

	@Override
	public int getSessionCount() {
		return sessionCount;
	}

	@Override
	public void handleEvent(String id) {
		EventHandler listener = getEventHandler(id);
		if (listener != null) {
			listener.handleEvent();
		} else {
			try {
				System.out.println("Finding app class " + id);
				@SuppressWarnings("unchecked")
				Class<Application> clazz = (Class<Application>) Class.forName(id);
				Application app = clazz.newInstance();
				app.init(new WhizuUI()); //TODO don't create static UI here
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String next() {
		return "c" + componentCount++;
	}

	@Override
	public void setAttribute(String name, Object value) {
		attrMap.put(name, value);
	}
}
