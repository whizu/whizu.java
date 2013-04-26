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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.whizu.annotation.Css;
import org.whizu.annotation.Style;
import org.whizu.dom.Component;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
public class ApplicationEnhancer {

	private Log log = LogFactory.getLog(ApplicationEnhancer.class);

	public PageFactory createFactory(String className) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Enhancing class " + className);
			}

			ClassPool classPool = ClassPool.getDefault();
			CtClass ctClass = classPool.get(className);
			CtClass[] nested = ctClass.getNestedClasses();
			CtMethod[] ctMethods = ctClass.getDeclaredMethods();
			List<CtMethod> methodsToDo = new ArrayList<CtMethod>();
			for (CtMethod method : ctMethods) {
				System.out.println(method);

				if (method.hasAnnotation(Css.class) || (method.hasAnnotation(Style.class))) {
					if (log.isDebugEnabled()) {
						log.debug("Enhancing method " + method);
					}
					methodsToDo.add(method);
				}
			}

			for (CtMethod method : methodsToDo) {
				System.out.println("To Enhance: " + method);
				final Css annot = (Css) method.getAnnotation(Css.class);
				if (annot != null) {
					// method.insertAfter("System.out.println($_);");
					method.insertAfter("org.whizu.server.ApplicationEnhancer.doIt(\"" + annot.value()[0] + "\", $_);");
				}

				final Style sannot = (Style) method.getAnnotation(Style.class);
				if (sannot != null) {
					method.insertAfter("org.whizu.server.ApplicationEnhancer.style(\"" + sannot.value()[0] + "\", $_);");
				}
			}

			for (CtClass n : nested) {
				System.out.println("Dealing with nested class " + n);
				n.toClass();
			}

			ClassFile ccFile = ctClass.getClassFile();
			ConstPool constpool = ccFile.getConstPool();

			// create the annotation
			AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
			Annotation annot = new Annotation("org.whizu.annotation.Bean", constpool);
			annot.addMemberValue("value", new StringMemberValue("hello", ccFile.getConstPool()));
			attr.addAnnotation(annot);
			ccFile.addAttribute(attr);

			Class<Application> newClass = getEnhancedClass(ctClass);
			return new PageFactory(newClass);
		} catch (NotFoundException | CannotCompileException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		} finally {
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
		System.out.println("do " + arg);
		return arg;
	}

	public static Object doIt(String css, Object arg) {
		System.out.println("Apply " + css + " to " + arg);
		if (arg instanceof Component) {
			((Component) arg).css(css);
		}
		return arg;
	}

	public static Object style(String css, Object arg) {
		if (arg instanceof Component) {
			System.out.println("Apply style " + css + " to " + arg);
			((Component) arg).style(css);
		}
		return arg;
	}
}
