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
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

import org.whizu.annotation.Bean;
import org.whizu.annotation.Css;
import org.whizu.dom.Component;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
public class ApplicationEnhancer {

	public Application newInstance(String className) {
		try {
			System.out.println("enhancing class " + className);
			ClassPool classPool = ClassPool.getDefault();
			CtClass ctClass = classPool.get(className);
			CtClass[] nested = ctClass.getNestedClasses();
			CtMethod[] ctMethods = ctClass.getDeclaredMethods();
			List<CtMethod> methodsToDo = new ArrayList<CtMethod>();
			for (CtMethod method : ctMethods) {
				System.out.println(method);

				if (method.hasAnnotation(Css.class)) {
					System.out.println("Enhancing " + method);

					String oldName = method.getName();
					// String newName = method.getName() + "_";
					// method.setName(newName);

					// CtMethod newmethod = CtNewMethod.make("public void " +
					// oldName + () {
					// System.out.println(\"test ok\"); }",ctclass);
					// ctclass.addMethod(newmethod);
					methodsToDo.add(method);
					// method.insertBefore("System.out.println(\"before\" " +
					// oldName + ");");
				}
			}

			for (CtMethod method : methodsToDo) {
				System.out.println("To Enhance: " + method);
				final Css annot = (Css) method.getAnnotation(Css.class);
				final String mn = method.getName();
				// method.insertBefore("System.out.println(\"before " +
				// method.getName() + "\");");
				method.insertAfter("System.out.println($_); org.whizu.server.ApplicationEnhancer.doIt(\""
						+ annot.value()[0] + "\", $_);");
				/*
				 * method.instrument(new ExprEditor() { public void
				 * edit(MethodCall m) throws CannotCompileException {
				 * m.replace("{ System.out.println(\"move" + mn +
				 * "\"); $_ = org.whizu.server.ApplicationEnhancer.doIt(\"" +
				 * annot.value()[0] + "\", $proceed($$)); }");
				 * //m.replace("{ System.out.println(\"move" + mn +
				 * "\"); $_ = $proceed($$).css(\"" + annot.uri() + "\"); }"); }
				 * });
				 */

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
			
			Application app = (Application) ctClass.toClass().newInstance();
			System.out.println("Enhanced CLASS is " + app.getClass());
			Class<?> appClass = app.getClass();
			System.out.println("Annotation is present " + appClass.isAnnotationPresent(Bean.class));
			return app;
		} catch (NotFoundException | CannotCompileException | InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
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
	
	private Application create(String className) {
		try {
			return (Application) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
}
