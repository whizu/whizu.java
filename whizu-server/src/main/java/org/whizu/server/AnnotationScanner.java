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

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
class AnnotationScanner {

	public void scan(Configuration config) {
		long start = System.currentTimeMillis();
		System.out.println("Scanning classpath for annotations");
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(applicationContext, false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(App.class));
		scanner.scan("");
		applicationContext.refresh();
		Map<String, Object> result = applicationContext.getBeansWithAnnotation(App.class);
		for (Object o : result.values()) {
			System.out.println(o.getClass());
			App ann = o.getClass().getAnnotation(App.class);
			if (ann != null) { 
				System.out.println(ann.uri());
				config.addApplication(ann.uri(), (Application) o);
			}
			Annotation[] annos = o.getClass().getAnnotations();
			for(Annotation a : annos)
		        System.out.println(a);
		}
		long end = System.currentTimeMillis();
		System.out.println("done annotation scanning in " + (end - start) + " ms");
	}
}
