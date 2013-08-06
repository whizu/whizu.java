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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.whizu.content.Content;
import org.whizu.content.Identity;
import org.whizu.jquery.Request;
import org.whizu.jquery.RequestContext;

/**
 * @author Rudy D'hauwe
 */
public abstract class AbstractJqmTest {

	protected TestRequest theRequest;
	
	protected Page page;

	protected Identity createIdentity(final String id) {
		return new Identity() {

			@Override
			public String id() {
				return id;
			}};
	}

	protected final void equals(String markup, Content content) {
		assertEquals(markup, content.render());
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.theRequest = new TestRequest();
		this.page = Jqm.document().index();
		
		RequestContext.setInstance(new RequestContext() {
			
			@Override
			protected Request getRequestImpl() {
				return theRequest;
			}

			@Override
			protected String getServletContextPath() {
				return "/dev";
			}
		});
	}
}
