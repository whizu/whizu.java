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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.annotation.Css;
import org.whizu.annotation.Expires;
import org.whizu.annotation.Style;
import org.whizu.annotation.Stylesheet;
import org.whizu.annotation.Template;
import org.whizu.dom.Component;
import org.whizu.html.Description;
import org.whizu.html.Title;
import org.whizu.ui.Application;

/**
 * @author Rudy D'hauwe
 */
public class ApplicationEnhancer {

	private Logger log = LoggerFactory.getLogger(ApplicationEnhancer.class);

	/*
	public PageFactory createFactoryOld(String className) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Enhancing class " + className);
			}

			ClassPool classPool = ClassPool.getDefault();
			ClassLoader loader = getClass().getClassLoader();
			classPool.appendClassPath(new LoaderClassPath(loader));
			CtClass ctClass = classPool.get(className);

			String stylesheet = null;
			if (ctClass.hasAnnotation(Stylesheet.class)) {
				stylesheet = ((Stylesheet) ctClass.getAnnotation(Stylesheet.class)).value();
			}

			String title = Title.DEFAULT_TITLE;
			if (ctClass.hasAnnotation(Title.class)) {
				title = ((Title) ctClass.getAnnotation(Title.class)).value();
			}

			String expires = getExpires(ctClass);
			String template = getTemplate(ctClass);
			Description description = getAnnotation(ctClass, Description.class);

			CtMethod[] ctMethods = ctClass.getDeclaredMethods();
			List<CtMethod> methodsToDo = new ArrayList<CtMethod>();
			for (CtMethod method : ctMethods) {
				if (method.hasAnnotation(Css.class) || (method.hasAnnotation(Style.class))) {
					if (log.isDebugEnabled()) {
						log.debug("Enhancing method " + method);
					}
					methodsToDo.add(method);
				}

				if (method.hasAnnotation(Body.class)) {
					log.debug("Found @Body");
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

			CtField[] fields = ctClass.getDeclaredFields();
			for (CtField field : fields) {
				log.debug("Enhancing field " + field.getName());
				if (field.hasAnnotation(Html.class)) {
					log.debug("Enhancing field " + field.getName() + " with annotation @Html");
					CtConstructor[] constructors = ctClass.getConstructors();
					for (CtConstructor constructor : constructors) {
						constructor.insertAfter(field.getName()
								+ "=org.whizu.annotation.processing.Support.getValue(getClass(), \"" + field.getName()
								+ "\");");
					}
				}
			}

			log.debug("just before fixing inner classes");
			fixInnerClasses(ctClass);
			ClassFile ccFile = ctClass.getClassFile();
			ConstPool constpool = ccFile.getConstPool();

			// log.debug("just before creating the annotation");
			// create the annotation
			// AnnotationsAttribute attr = new AnnotationsAttribute(constpool,
			// AnnotationsAttribute.visibleTag);
			// Annotation annot = new Annotation("org.whizu.annotation.Bean",
			// constpool);
			// annot.addMemberValue("value", new StringMemberValue("hello",
			// ccFile.getConstPool()));
			// attr.addAnnotation(annot);
			// ccFile.addAttribute(attr);
			// ctClass.setName(ctClass.getName()+"Impl"); //google app engine
			// ctClass.setName(ctClass.getName() + "Impl");

			Class<Application> newClass = getEnhancedClass(ctClass);

			PageFactory factory = new PageFactory(newClass);
			factory.title(title);
			factory.expires(expires);
			factory.stylesheet(stylesheet);
			factory.template(template);
			factory.description(description);

			log.debug("Enhanced class returns {}", factory);
			return factory;
		} catch (NotFoundException e) {
			log.debug(e.getMessage(), e);
			throw new IllegalStateException(e);
		} catch (CannotCompileException e) {
			log.debug(e.getMessage(), e);
			throw new IllegalStateException(e);
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
	*/

	public PageFactory createFactory(String className) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Enhancing class the new way " + className);
			}

			Class<Application> clazz = getApplicationClass(className);

			String stylesheet = null;
			if (clazz.isAnnotationPresent(Stylesheet.class)) {
				stylesheet = ((Stylesheet) clazz.getAnnotation(Stylesheet.class)).value();
			}

			String title = Title.DEFAULT_TITLE;
			if (clazz.isAnnotationPresent(Title.class)) {
				title = ((Title) clazz.getAnnotation(Title.class)).value();
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
		} catch (NotFoundException e) {
			log.debug(e.getMessage(), e);
			throw new IllegalStateException(e);
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

	private Class<Application> getApplicationClass(String className) {
		try {
			@SuppressWarnings("unchecked")
			Class<Application> clazz = (Class<Application>) Class.forName(className);

			boolean enhancementNecessary = false;
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(Css.class) || (method.isAnnotationPresent(Style.class))) {
					enhancementNecessary = true;
				}
			}

			if (enhancementNecessary) {
				ClassPool classPool = ClassPool.getDefault();
				ClassLoader loader = getClass().getClassLoader();
				classPool.appendClassPath(new LoaderClassPath(loader));
				CtClass ctClass = classPool.get(className);

				/*
				 * CtField[] fields = ctClass.getDeclaredFields(); for (CtField
				 * field : fields) { log.debug("Enhancing field " +
				 * field.getName()); if (field.hasAnnotation(Html.class)) {
				 * log.debug("Enhancing field " + field.getName() +
				 * " with annotation @Html"); CtConstructor[] constructors =
				 * ctClass.getConstructors(); for (CtConstructor constructor :
				 * constructors) { constructor.insertAfter(field.getName() +
				 * "=org.whizu.annotation.processing.Support.getValue(getClass(), \""
				 * + field.getName() + "\");"); } } }
				 */

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
						method.insertAfter("org.whizu.server.ApplicationEnhancer.doIt(\"" + annot.value()[0]
								+ "\", $_);");
					}

					final Style sannot = (Style) method.getAnnotation(Style.class);
					if (sannot != null) {
						method.insertAfter("org.whizu.server.ApplicationEnhancer.style(\"" + sannot.value()[0]
								+ "\", $_);");
					}
				}

				log.debug("just before fixing inner classes");
				fixInnerClasses(ctClass);
				Class<Application> newClass = getEnhancedClass(ctClass);
				return newClass;
			} else {
				log.debug("************* NO ENHANCEMENT FOR " + clazz);
			}

			return clazz;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (NotFoundException e) {
			throw new RuntimeException(e);
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		} finally {
		}
	}

	private String getTemplate(Class<?> ctClass) throws ClassNotFoundException, NotFoundException {
		Template template = getAnnotation(ctClass, Template.class);
		return (template == null) ? null : template.value();
	}

/*
	private String getTemplate(CtClass ctClass) throws ClassNotFoundException, NotFoundException {
		Template template = getAnnotation(ctClass, Template.class);
		return (template == null) ? null : template.value();
	}
	

	private String getExpires(CtClass ctClass) throws ClassNotFoundException, NotFoundException {
		Expires expires = getAnnotation(ctClass, Expires.class);
		return (expires == null) ? null : expires.value();
	}
*/
	
	private String getExpires(Class<?> ctClass) throws ClassNotFoundException, NotFoundException {
		Expires expires = getAnnotation(ctClass, Expires.class);
		return (expires == null) ? null : expires.value();
	}

	/*
	@SuppressWarnings("unchecked")
	private <T extends java.lang.annotation.Annotation> T getAnnotation(CtClass ctClass, Class<T> annotationClass)
			throws ClassNotFoundException, NotFoundException {
		if (ctClass.hasAnnotation(annotationClass)) {
			return ((T) ctClass.getAnnotation(annotationClass));
		} else {
			CtClass superClass = ctClass.getSuperclass();
			if (superClass != null) {
				return getAnnotation(superClass, annotationClass);
			} else {
				return null;
			}
		}
	}
	*/

	private <T extends java.lang.annotation.Annotation> T getAnnotation(Class<?> ctClass, Class<T> annotationClass)
			throws ClassNotFoundException, NotFoundException {
		if (ctClass.isAnnotationPresent(annotationClass)) {
			return ((T) ctClass.getAnnotation(annotationClass));
		} else {
			Class<?> superClass = ctClass.getSuperclass();
			if (superClass != null) {
				return getAnnotation(superClass, annotationClass);
			} else {
				return null;
			}
		}
	}

	// Adds support for anonymous/inner classes
	private void fixInnerClasses(CtClass ctClass) throws NotFoundException, CannotCompileException {
		CtClass[] nested = ctClass.getNestedClasses();
		for (CtClass n : nested) {
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
