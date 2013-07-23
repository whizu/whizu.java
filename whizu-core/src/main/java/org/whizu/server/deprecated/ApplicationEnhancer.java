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
package org.whizu.server.deprecated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.annotation.Description;
import org.whizu.annotation.Expires;
import org.whizu.annotation.Stylesheet;
import org.whizu.annotation.Template;
import org.whizu.annotation.Title;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
@Deprecated
public class ApplicationEnhancer {

	private Logger log = LoggerFactory.getLogger(ApplicationEnhancer.class);

	public PageFactory createFactory(Class<?> appClass) {
		try {
			log.debug("Enhancing class {}", appClass);
			
			@SuppressWarnings("unchecked")
			Class<Application> clazz = (Class<Application>) appClass;
			
			String stylesheet = null;
			if (clazz.isAnnotationPresent(Stylesheet.class)) {
				stylesheet = clazz.getAnnotation(Stylesheet.class).value();
			}

			String title = Title.DEFAULT_TITLE;
			if (clazz.isAnnotationPresent(Title.class)) {
				title = clazz.getAnnotation(Title.class).value();
			}

			String expires = getExpires(clazz);
			String template = getTemplate(clazz);
			Description description = getAnnotation(clazz, Description.class);

			PageFactory factory = new PageFactory(clazz);
			factory.title(title);
			factory.expires(expires);
			factory.stylesheet(stylesheet);
			factory.template(template);
			factory.description(description);

			log.debug("Enhanced class returns {}", factory);
			return factory;
		} catch (ClassNotFoundException e) {
			log.debug(e.getMessage(), e);
			throw new IllegalStateException(e);
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			throw new IllegalStateException(e);
		} finally {
			log.debug("ApplicationEnhancer.finally()");
		}
	}

	private String getTemplate(Class<?> ctClass) throws ClassNotFoundException {
		Template template = getAnnotation(ctClass, Template.class);
		return (template == null) ? null : template.value();
	}

	private String getExpires(Class<?> ctClass) throws ClassNotFoundException {
		Expires expires = getAnnotation(ctClass, Expires.class);
		return (expires == null) ? null : expires.value();
	}

	private <T extends java.lang.annotation.Annotation> T getAnnotation(Class<?> ctClass, Class<T> annotationClass)
			throws ClassNotFoundException {
		if (ctClass.isAnnotationPresent(annotationClass)) {
			return ctClass.getAnnotation(annotationClass);
		}
		Class<?> superClass = ctClass.getSuperclass();
		if (superClass != null) {
			return getAnnotation(superClass, annotationClass);
		}
		return null;
	}
}
