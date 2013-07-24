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
package org.whizu.tutorial.jqm.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.whizu.annotation.App;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Jqm;
import org.whizu.jquery.mobile.Page;
import org.whizu.ui.ClickListener;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/jqm/widgets/buttons")
public class Buttons  implements JQueryMobile {

	private int x = 0;

	private int y = 0;

	@Override
	public void onLoad(Page page) {
		x = 10;
		y = 10;

		page.header("Buttons");

		// @formatter:off
		Jqm.createButton("Register")
		    .onClick(register())
		    .build()
		    .appendTo(page);
		// @formatter:on

		// @formatter:off
			Jqm.createButton("Register 3")
			    .onClick(register())
			    .build()
			    .appendTo(page);
			// @formatter:on

		// @formatter:off
//		Jqm.createButton("Register 2")
//		    .onClick(Register.class) //inner class -> will throw exception
//		    .build()
//		    .appendTo(page);
			
//		Jqm.createButton("Register 4")
//		    .onClick(RegistrationListener.class)
//		    .build()
//		    .appendTo(page);
//		// @formatter:on		
	}

	public ClickListener register() {
		final int z = x;
		x++;
		return new ClickListener() {

			@Override
			public void click() {
				x++;
				y++;
				Page page = Jqm.page();
				page.append("<p>Button clicked! Starting the registration process... " + z + "," + x + "," + y + "</p>");
			}
		};
	}

	public class Register implements ClickListener {

		public Register() {
		}

		@Override
		public void click() {
			x++;
			y++;
			Page page = Jqm.page();
			page.append("<p>Starting the registration process... " + x + "," + y + "</p>");
		}

		public class X {
		}
	}

	public static void main(String[] args) {
		Class<Register> clazz = Register.class;
		System.out.println(instantiate(clazz));
		Class<Buttons> parentClass = Buttons.class;
		System.out.println(instantiate(parentClass));
		System.out.println(instantiate(Buttons.Register.X.class));
	}

	public static <T> T instantiate(Class<T> clazz) {
		try {
			Class<?> enclosingClass = clazz.getEnclosingClass();
			if (enclosingClass == null) {
				return clazz.newInstance();
			} else {
				Constructor<T> constructor = clazz.getConstructor(enclosingClass);
				Object enclosingInstance = instantiate(enclosingClass);
				return constructor.newInstance(enclosingInstance);
			}
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
}
