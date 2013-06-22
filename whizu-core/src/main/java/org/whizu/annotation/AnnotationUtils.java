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
package org.whizu.annotation;

import java.io.IOException;
import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.util.Chrono;

/**
 * @author Rudy D'hauwe
 */
public class AnnotationUtils {

	private static Logger logger = LoggerFactory.getLogger(AnnotationUtils.class);

	private static void scan(AnnotationDetector.TypeReporter reporter, String packageNames) {
		try {
			if (packageNames == null) {
				new AnnotationDetector(reporter).detect();
			} else {
				new AnnotationDetector(reporter).detect(packageNames);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T extends Annotation> void scan(final Class<T> clazz, final TypeReporter<T> reporter, String packageNames) {
		if (logger.isDebugEnabled()) {
			logger.debug("Scanning the classpath for @{} annotations", clazz.getSimpleName());
		}

		Chrono chrono = Chrono.start();
		AnnotationDetector.TypeReporter typeReporter = new AnnotationDetector.TypeReporter() {

			@SuppressWarnings("unchecked")
			@Override
			public Class<? extends Annotation>[] annotations() {
				return new Class[] { clazz };
			}

			private Class<?> getClass(String className) {
				try {
					return Class.forName(className);
				} catch (ClassNotFoundException e) {
					throw new IllegalArgumentException(e);
				}
			}

			@Override
			public void reportTypeAnnotation(Class<? extends Annotation> annotationClass, String className) {
				T annotation = getClass(className).getAnnotation(clazz);
				reporter.report(annotation, className);
			}
		};

		scan(typeReporter, packageNames);

		if (logger.isDebugEnabled()) {
			logger.debug("Scanned the classpath for @{} annotations in {}ms", clazz.getSimpleName(), chrono.stop());
		}
	}
}
