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
 * For more details, see http://joinup.ec.europa.eu/software/page/eupl.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.util;

import java.io.IOException;
import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rudy D'hauwe
 */
class AnnotationScanner<T extends Annotation> implements Scanner<T> {

	private static Logger log = LoggerFactory.getLogger(AnnotationScanner.class);

	private static <T extends Annotation> AnnotationDetector.TypeReporter createTypeReporter(final Class<T> clazz,
			final Reporter<T> reporter) {
		return new AnnotationDetector.TypeReporter() {

			@SuppressWarnings("unchecked")
			@Override
			public Class<? extends Annotation>[] annotations() {
				return new Class[] { clazz };
			}

			private Class<?> getClass(String className) {
				try {
					return Class.forName(className);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public void reportTypeAnnotation(Class<? extends Annotation> annotationClass, String annotatedClassName) {
				Class<?> annotatedClass = getClass(annotatedClassName);
				T annotation = annotatedClass.getAnnotation(clazz);
				reporter.report(annotation, annotatedClass);
			}
		};
	}

	private Class<T> annotationClass_;

	private String[] packagesToScan_;

	public AnnotationScanner(Class<T> annotationClass) {
		annotationClass_ = annotationClass;
		packagesToScan_ = null;
	}

	public AnnotationScanner(Class<T> annotationClass, String packageName) {
		annotationClass_ = annotationClass;
		if (!Strings.isBlank(packageName)) {
			packagesToScan_ = new String[] { packageName };
		}
	}

	public AnnotationScanner(Class<T> annotationClass, String... packages) {
		annotationClass_ = annotationClass;
		packagesToScan_ = packages;
	}

	private void scan(AnnotationDetector.TypeReporter reporter) {
		Chrono chrono = Chrono.start();
		try {
			if (packagesToScan_ == null) {
				log.debug("Scanning the full classpath for {}", (Object[]) reporter.annotations());
				new AnnotationDetector(reporter).detect();
			} else {
				log.debug("Scanning the classpath for @{} within packages {}", (Object[]) reporter.annotations(),
						packagesToScan_);
				new AnnotationDetector(reporter).detect(packagesToScan_);
			}
			log.debug("Scanned the classpath for @{} annotations in {}ms", (Object[]) reporter.annotations(),
					chrono.stop());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// public void scan(final Class<T> clazz, final Reporter<T> reporter) {
	// AnnotationDetector.TypeReporter typeReporter = createTypeReporter(clazz,
	// reporter);
	// scan(typeReporter);
	// }

	@Override
	public void scan(Reporter<T> reporter) {
		AnnotationDetector.TypeReporter typeReporter = createTypeReporter(annotationClass_, reporter);
		scan(typeReporter);
	}
}
