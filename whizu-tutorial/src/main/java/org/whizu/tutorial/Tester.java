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
package org.whizu.tutorial;

import org.whizu.annotation.Css;
import org.whizu.annotation.Style;
import org.whizu.dom.Component;
import org.whizu.dom.Composite;
import org.whizu.dom.Content;
import org.whizu.dom.Element;
import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.ui.Form;

/**
 * @author Rudy D'hauwe
 */
class Tester {

	@Css("myClass")
	@Style("border:myClass")
	public Element get() {
		return Html.div();
	}
	
	@Style("border:myClass")
	public Form get2() {
		return new Form() {

			@Override
			public Composite add(Component component) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Composite add(Content content) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void empty() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void prepend(Component component) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void remove(Component component) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Markup compile() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Component css(String clazz) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void width(String width) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void style(String style) {
				System.out.println("style applied " + style);
			}

			@Override
			public String render() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String id() {
				// TODO Auto-generated method stub
				return null;
			}};
	}

	public static void main(String[] args) {
		Tester t = new Tester();
		System.out.println(t.get().render());
		System.out.println(t.get2());
	}
}
