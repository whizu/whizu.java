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
package org.whizu.jquery.mobile;

import org.whizu.jquery.EventHandler;
import org.whizu.jquery.Input;
import org.whizu.jquery.Session;

/**
 * @author Rudy D'hauwe
 */
public class TestSession implements Session {

	private int componentCount = 0;
	
	@Override
	public void addClickListener(EventHandler listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addInput(Input input) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object attribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventHandler getEventHandler(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Input getInput(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSessionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void handleEvent(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public String next() {
		return "c" + componentCount++;
	}

	@Override
	public void attribute(String name, Object value) {
		// TODO Auto-generated method stub
	}
}
