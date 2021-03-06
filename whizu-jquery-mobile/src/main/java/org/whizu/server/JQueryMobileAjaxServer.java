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
package org.whizu.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Jqm;
import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
class JQueryMobileAjaxServer implements RequestProcessor {

	private static final Logger log = LoggerFactory.getLogger(JQueryMobileAjaxServer.class);
	
	Class<JQueryMobile> appClass_;

	public JQueryMobileAjaxServer(Class<JQueryMobile> appClass) {
		appClass_ = appClass;
	}

	@Override
	public boolean process(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			JQueryMobile app = Objects.newInstance(appClass_);
			app.onLoad(Jqm.document().index());
			// works:RequestContext.getRequest().addExpression("$('#whizu').closest(\":jqmData(role='page')\").trigger('pagecreate');");
			RequestContext.getRequest().addExpression("$('#whizu').parent().trigger('pagecreate');");
			response.setHeader("X-Robots-Tag", "noindex");
			return true;
		} finally {
			String result = RequestImpl.get().finish();
			log.debug(result);
			response.getOutputStream().print(result);
		}
	}
}
