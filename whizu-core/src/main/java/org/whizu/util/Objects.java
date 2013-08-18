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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.whizu.jquery.ClickListener;

/**
 * @author Rudy D'hauwe
 */
public class Objects {

	public static ClickListener call(final Object obj, String methodName, final Object... args) {
		try {
			Class<?>[] parameterTypes = getParameterTypes(args);
			final Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
			return new ClickListener() {
				
				@Override
				public void click() {
					try {
						if (!method.isAccessible()) {
							method.setAccessible(true);
						}
						method.invoke(obj,  args);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}
				}
			};
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public static ClickListener call(final Object obj, String methodName) {
		try {
			final Method method = obj.getClass().getMethod(methodName);
			return new ClickListener() {
				
				@Override
				public void click() {
					try {
						if (!method.isAccessible()) {
							method.setAccessible(true);
						}
						method.invoke(obj);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}
				}
			};
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Class<?>[] getParameterTypes(Object[] args) {
		Class<?>[] parameterTypes = new Class[args.length];
		for (int i=0; i<args.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}
		return parameterTypes;
	}

	@SuppressWarnings("unchecked")
	public static <T> T cast(Object obj) {
		return (T) obj;
	}

	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
