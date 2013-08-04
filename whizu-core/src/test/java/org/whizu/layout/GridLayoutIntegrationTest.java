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
package org.whizu.layout;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.whizu.content.Component;
import org.whizu.content.Identity;
import org.whizu.jquery.EventHandler;
import org.whizu.jquery.Function;
import org.whizu.jquery.Input;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Session;
import org.whizu.js.Expression;
import org.whizu.js.Script;

/**
 * Tests for {@link GridLayoutIntegrationTest}.
 * 
 * @author Rudy D'hauwe
 */
@RunWith(JUnit4.class)
public class GridLayoutIntegrationTest {

	// private Log log = LogFactory.getLog(GridLayoutTest.class);

	private Component getDefaultComponent() {
		Component myComponent = new Component() {

			@Override
			public String id() {
				return "myID";
			}

			@Override
			public String render() {
				return "myComponent";
			}
		};
		return myComponent;
	}

	private void initRequestContext() {
		RequestContext.setInstance(new RequestContext() {

			@Override
			protected Request getRequestImpl() {
				return new Request() {

					@Override
					public Script compile(Function function) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Session session() {
						return new Session() {

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
								return 0;
							}

							@Override
							public boolean handleEvent(String id) {
								return false;
							}

							@Override
							public String next() {
								// TODO Auto-generated method stub
								return null;
							}

							@Override
							public void attribute(String name, Object value) {
								// TODO Auto-generated method stub
							}
						};
					}

					@Override
					public JQuery select(Identity... objs) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public JQuery select(String selector) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Expression addExpression(String js) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String define(Function f) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String evaluate(Function f) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public <T extends Expression> T addExpression(T expr) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String getParameter(String name) {
						throw new UnsupportedOperationException();
					}
				};
			}

			@Override
			protected String getServletContextPath() {
				return "/dev";
			}
		});
	}

	@Test
	public void runTest1() {
		initRequestContext();
		Component myComponent = getDefaultComponent();

		GridLayout grid = new GridLayout(2);
		grid.add(myComponent);
		grid.add(myComponent);
		grid.add(myComponent);

		String markup = grid.render();
		assertEquals(
				"<table cellpadding='0' cellspacing='0'><tbody><tr><td>myComponent</td><td>myComponent</td></tr><tr><td>myComponent</td></tr></tbody></table>",
				markup);
	}

	@Test
	public void runTest2() {
		initRequestContext();
		Component myComponent = getDefaultComponent();

		GridLayout grid = new GridLayout(2);
		grid.add(myComponent);
		grid.add(myComponent);
		grid.add(myComponent);
		grid.add(myComponent);
		grid.add(myComponent);

		String markup = grid.render();
		assertEquals(
				"<table cellpadding='0' cellspacing='0'><tbody><tr><td>myComponent</td><td>myComponent</td></tr><tr><td>myComponent</td><td>myComponent</td></tr><tr><td>myComponent</td></tr></tbody></table>",
				markup);
	}

	@Test
	public void runTest3() {
		initRequestContext();
		Component myComponent = getDefaultComponent();

		GridLayout grid = new GridLayout(1);
		grid.add(myComponent);
		grid.add(myComponent);
		grid.add(myComponent);

		String markup = grid.render();
		assertEquals(
				"<table cellpadding='0' cellspacing='0'><tbody><tr><td>myComponent</td></tr><tr><td>myComponent</td></tr><tr><td>myComponent</td></tr></tbody></table>",
				markup);
	}
}
