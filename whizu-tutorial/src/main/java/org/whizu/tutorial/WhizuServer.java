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
package org.whizu.tutorial;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhizuServer {

	private static Logger log = LoggerFactory.getLogger(WhizuServer.class);
	
	public static void main(String[] args) {
		try {
			log.info("Starting the embedded Jetty server");
			String workingDirectory = System.getProperty("user.dir");
			String descriptor = workingDirectory + "/web/WEB-INF/web.xml";
			String resourceBase = workingDirectory + "/web";
			Server server = new Server(8090);
			WebAppContext context = new WebAppContext();
			context.setDescriptor(descriptor);
			context.setResourceBase(resourceBase);
			context.setContextPath("/dev");
			context.setParentLoaderPriority(true);
			server.setHandler(context);
			server.start();
			server.join();
		} catch (Exception exc) {
			log.error(exc.getMessage(), exc);
			throw new RuntimeException(exc);
		}
	}
}
