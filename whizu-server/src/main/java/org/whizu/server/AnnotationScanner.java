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
import java.lang.annotation.Annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.whizu.annotation.AnnotationDetector;
import org.whizu.annotation.Page;
import org.whizu.annotation.Autowire;

/**
 * @author Rudy D'hauwe
 */
class AnnotationScanner {

	private Log log = LogFactory.getLog(AnnotationScanner.class);

	public void scan(final Configuration config) {
		long start = System.currentTimeMillis();
		log.debug("Scanning classpath for annotations");

		AnnotationDetector.FieldReporter reporter2 = new AnnotationDetector.FieldReporter() {

			@SuppressWarnings("unchecked")
			@Override
			public Class<? extends Annotation>[] annotations() {
				return new Class[]{Autowire.class};
			}

			@Override
			public void reportFieldAnnotation(Class<? extends Annotation> annotation, String className, String fieldName) {
			}
		};

		AnnotationDetector.TypeReporter reporter = new AnnotationDetector.TypeReporter() {

			@SuppressWarnings("unchecked")
			@Override
			public Class<? extends Annotation>[] annotations() {
				return new Class[]{Page.class};
			}

			@Override
			public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
				if (annotation.equals(Page.class)) {
					log.debug("@App found in " + className);
					Page ann = getClass(className).getAnnotation(Page.class);
					if (ann != null) {
						ApplicationEnhancer enhancer = new ApplicationEnhancer();
						config.addApplication(ann.value(), enhancer.createFactory(className));
					}
				} else {
				}
			}

			private Class<?> getClass(String className) {
				try {
					return Class.forName(className);
				} catch (ClassNotFoundException e) {
					throw new IllegalArgumentException();
				}
			}
		};

		final AnnotationDetector cf = new AnnotationDetector(reporter);
		try {
			cf.detect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		AnnotationDetector cf2 = new AnnotationDetector(reporter2);
		try {
			log.debug("now detecting @Autowired");
			cf2.detect("org.whizu");
		} catch (IOException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		log.debug("first scanning took " + (end - start));
	}
}
