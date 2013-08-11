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
package org.whizu.yuml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.whizu.content.Content;
import org.whizu.content.Literal;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;
import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
public final class ClassBuilder extends ProxyBuilder<Type> {

	public static ClassBuilder createWithName(String name) {
		return new ClassBuilder().name(name);
	}

	private Build build_ = new Build();

	public ClassBuilder addAttribute(String name) {
		build_.addAttribute(name);
		return this;
	}

	public ClassBuilder addMethod(String name) {
		build_.addMethod(name);
		return this;
	}

	public ClassBuilder addNote(Note note) {
		build_.addNote(note);
		return this;
	}

	public ClassBuilder background(Color color) {
		build_.background(color.name());
		return this;
	}
	
	public ClassBuilder background(String color) {
		build_.background(color);
		return this;
	}

	@Override
	public Type build() {
		return buildOnce(build_);
	}

	public ClassBuilder dependsOn(Type clazz) {
		build_.dependsOn(clazz);
		return this;
	}

	public ClassBuilder extend(Type clazz) {
		build_.extend(clazz);
		return this;
	}

	private ClassBuilder name(String name) {
		build_.name_ = name;
		return this;
	}

	public ClassBuilder stereotype(String name) {
		build_.stereotype(name);
		return this;
	}

	public ClassBuilder uses(Type clazz) {
		return dependsOn(clazz);
	}

	/***************************************************************************
	 * The class <code>Type</code> being build.
	 */
	private class Build extends BuildSupport implements Type {

		private List<String> attributeList_ = new ArrayList<String>();

		private String background_;

		private List<Type> dependsOnList_ = new ArrayList<Type>();

		private List<Type> extendList_ = new ArrayList<Type>();
		
		private List<Type> implementList_ = new ArrayList<Type>();

		private List<String> methodList_ = new ArrayList<String>();

		private String name_;

		private Note note_;

		private String stereotype_;

		private void addAttribute(String name) {
			attributeList_.add(name);
		}

		public void addMethod(String name) {
			methodList_.add(name);
		}

		private void addNote(Note note) {
			note_ = note;
		}

		private void background(String color) {
			background_ = color;
		}

		@Override
		public Content build() {
			String background = buildBackground();
			String attributes = buildAttributes();
			String methods = buildMethods();
			String stereotypeMarkup = buildStereotype();
			String thisClass = "[" + stereotypeMarkup + name_ + attributes
					+ methods + background + "]";
			Iterator<Type> dependsOnIterator = dependsOnList_.iterator();
			if (dependsOnIterator.hasNext()) {
				Type depends = Objects.cast(dependsOnIterator.next());
				thisClass = thisClass + "-.->" + depends.getTypeMarkup();
			}

			if (note_ != null) {
				String noteRef = getClassDef() + "-" + note_.render();
				thisClass = thisClass + "," + noteRef;
			}

			if (!extendList_.isEmpty()) {
				String extendsRef = buildExtends();
				thisClass = thisClass + "," + extendsRef;
			}
			
			if (!implementList_.isEmpty()) {
				String markup = buildImplements();
				thisClass = thisClass + "," + markup;
			}

			return new Literal(thisClass);
		}

		private String buildAttributes() {
			if (attributeList_.isEmpty()) {
				return "";
			}

			Iterator<String> it = attributeList_.iterator();
			String attributes = "|" + it.next();
			while (it.hasNext()) {
				attributes = attributes + ";" + it.next();
			}

			return attributes;
		}

		private String buildBackground() {
			return (background_ == null) ? "" : "{bg:" + background_ + "}";
		}

		private String buildExtends() {
			if (extendList_.isEmpty()) {
				return "";
			}

			Iterator<Type> it = extendList_.iterator();
			String markup = ((Build) it.next()).getClassDef() + "^-"
					+ getClassDef();

			while (it.hasNext()) {
				markup = markup + "," + ((Build) it.next()).getClassDef()
						+ "^-" + getClassDef();
			}

			return markup;
		}
		
		private String buildImplements() {
			if (implementList_.isEmpty()) {
				return "";
			}

			Iterator<Type> it = implementList_.iterator();
			String markup = ((Type) it.next()).getTypeMarkup() + "^-.-"
					+ getTypeMarkup();

			while (it.hasNext()) {
				markup = markup + "," + ((Type) it.next()).getTypeMarkup()
						+ "^-.-" + getTypeMarkup();
			}

			return markup;
		}

		private String buildMethods() {
			if (methodList_.isEmpty()) {
				return "";
			}

			Iterator<String> it = methodList_.iterator();
			String methods = "|" + it.next();
			while (it.hasNext()) {
				methods = methods + ";" + it.next();
			}

			return methods;
		}

		private String buildStereotype() {
			return (stereotype_ == null) ? "" : ("&laquo;&laquo;" + stereotype_ + "&raquo;&raquo;;");
		}

		private void dependsOn(Type clazz) {
			dependsOnList_.add(clazz);
		}

		private void extend(Type clazz) {
			extendList_.add(clazz);
		}

		private String getClassDef() {
			return "[" + name_ + "]";
		}

		private void stereotype(String name) {
			stereotype_ = name;
		}

		public void implement(Type type) {
			implementList_.add(type);
		}

		@Override
		public String getTypeMarkup() {
			return getClassDef();
		}
	}

	public ClassBuilder implement(Type type) {
		build_.implement(type);
		return this;
	}
}
