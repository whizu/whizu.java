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
package org.whizu.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.whizu.annotation.Css;
import org.whizu.annotation.Style;
import org.whizu.dom.Component;
import org.whizu.dom.Content;

/**
 * @author Rudy D'hauwe
 */
public aspect WhizuAspect {

	@Pointcut("execution(@org.whizu.annotation.Style * org..*.* (..))")
	public void styleAnnotatedMethods() {}
	
	@Pointcut("execution(@org.whizu.annotation.Css * org..*.* (..))")
	public void cssAnnotatedMethods() {}
	
	after() returning (Component result) : styleAnnotatedMethods() {
		MethodSignature signature = (MethodSignature) thisJoinPointStaticPart.getSignature();
		Method method = signature.getMethod();
		Style annotation = method.getAnnotation(Style.class);
		Component content = (Component) result;
		content.style(annotation.value()[0]);
		System.out.println("ADDED STYLE ANNOTATION " + annotation.value());
	}
	
	after() returning (Content result) : cssAnnotatedMethods() {
		MethodSignature signature = (MethodSignature) thisJoinPointStaticPart.getSignature();
		Method method = signature.getMethod();
		Css annotation = method.getAnnotation(Css.class);
		Content content = (Content) result;
		content.css(annotation.value()[0]);
		System.out.println("ADDED CSS ANNOTATION " + annotation.value());
	}
}
