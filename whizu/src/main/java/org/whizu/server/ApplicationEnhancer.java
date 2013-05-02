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

import java.util.ArrayList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.annotation.Css;
import org.whizu.annotation.Style;
import org.whizu.annotation.Stylesheet;
import org.whizu.annotation.Title;
import org.whizu.dom.Component;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
public class ApplicationEnhancer {

	private Logger log = LoggerFactory.getLogger(ApplicationEnhancer.class);

	public PageFactory createFactory(String className) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Enhancing class " + className);
			}

			ClassPool classPool = ClassPool.getDefault();
			CtClass ctClass = classPool.get(className);

			String stylesheet = null;
			if (ctClass.hasAnnotation(Stylesheet.class)) {
				stylesheet = ((Stylesheet) ctClass.getAnnotation(Stylesheet.class)).value();
			}
			
			String title = Title.DEFAULT_TITLE;
			if (ctClass.hasAnnotation(Title.class)) {
				title = ((Title) ctClass.getAnnotation(Title.class)).value();
			}
			
			CtMethod[] ctMethods = ctClass.getDeclaredMethods();
			List<CtMethod> methodsToDo = new ArrayList<CtMethod>();
			for (CtMethod method : ctMethods) {
				if (method.hasAnnotation(Css.class) || (method.hasAnnotation(Style.class))) {
					if (log.isDebugEnabled()) {
						log.debug("Enhancing method " + method);
					}
					methodsToDo.add(method);
				}
			}

			for (CtMethod method : methodsToDo) {
				final Css annot = (Css) method.getAnnotation(Css.class);
				if (annot != null) {
					method.insertAfter("org.whizu.server.ApplicationEnhancer.doIt(\"" + annot.value()[0] + "\", $_);");
				}

				final Style sannot = (Style) method.getAnnotation(Style.class);
				if (sannot != null) {
					method.insertAfter("org.whizu.server.ApplicationEnhancer.style(\"" + sannot.value()[0] + "\", $_);");
				}
			}

			fixInnerClasses(ctClass);

			ClassFile ccFile = ctClass.getClassFile();
			ConstPool constpool = ccFile.getConstPool();

			// create the annotation
			AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
			Annotation annot = new Annotation("org.whizu.annotation.Bean", constpool);
			annot.addMemberValue("value", new StringMemberValue("hello", ccFile.getConstPool()));
			attr.addAnnotation(annot);
			ccFile.addAttribute(attr);

			Class<Application> newClass = getEnhancedClass(ctClass);
			PageFactory factory = new PageFactory(newClass);
			factory.setTitle(title);
			factory.setStylesheet(stylesheet);
			return factory;
		} catch (NotFoundException | CannotCompileException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		} finally {
		}
	}

	private void fixInnerClasses(CtClass ctClass) throws NotFoundException, CannotCompileException {
		CtClass[] nested = ctClass.getNestedClasses();
		for (CtClass n : nested) {
			// Adds support for anonymous/inner classes
			n.toClass();
			fixInnerClasses(n);
		}
	}

	@SuppressWarnings("unchecked")
	private Class<Application> getEnhancedClass(CtClass ctClass) {
		try {
			return ctClass.toClass();
		} catch (CannotCompileException e) {
			throw new IllegalStateException(e);
		}
	}

	public static Object doIt(Object arg) {
		return arg;
	}

	public static Object doIt(String css, Object arg) {
		if (arg instanceof Component) {
			((Component) arg).css(css);
		}
		return arg;
	}

	public static Object style(String css, Object arg) {
		if (arg instanceof Component) {
			((Component) arg).style(css);
		}
		return arg;
	}
}
