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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.whizu.annotation.Page;
import org.whizu.annotation.TypeReporter;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
class Configuration implements TypeReporter<Page> {

	private Log log = LogFactory.getLog(Configuration.class);

	private Map<String, PageFactory> applicationFactoryMap = new HashMap<String, PageFactory>();

	public void addApplication(String uri, PageFactory application) {
		applicationFactoryMap.put(uri, application);
		applicationFactoryMap.put(uri + "/", application);
	}

	/**
	 * @return null if @Page(uri) has not been defined on any class
	 */
	public PageFactory getFactory(String uri) {
		return applicationFactoryMap.get(uri);
	}
	
	/**
	 * @return null if @Page(uri) has not been defined on any class
	 */
	public Application getApplication(String uri) {
		PageFactory factory = applicationFactoryMap.get(uri);
		if (factory != null) {
			return factory.createInstance();
		} else {
			if (log.isDebugEnabled()) {
				log.debug("No @Page has been defined for uri " + uri);
			}
			return null;
		}
	}
	
	@Override
	public void report(Page annotation, String className) {
		if (log.isDebugEnabled()) {
			log.debug("@Page(\"" + annotation.value() + "\") on " + className);
		}

		ApplicationEnhancer enhancer = new ApplicationEnhancer();
		addApplication(annotation.value(), enhancer.createFactory(className));
	}
}
