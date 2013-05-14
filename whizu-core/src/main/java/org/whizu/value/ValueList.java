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
package org.whizu.value;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.whizu.dom.Component;

/**
 * @author Rudy D'hauwe
 */
public class ValueList<T> extends ValueBuilder<ValueList<T>, List<T>> {

	private Class<T> clazz;

	//@Embedded
	public String clazzName = "blabla";

	public List<T> value = new ArrayList<T>();

	private PropertyChangeListener listener;

	public ValueList() {
		super("list");
	}

	public ValueList(Class<T> clazz) {
		this();
		this.clazz = clazz;
		this.clazzName = clazz.getName();
		// this.sampleValueObject = createNew();
	}

	public void add(T element) {
		this.value.add(element);
		if (this.listener != null) {
			listener.propertyChange(null);
		}
		
	}

	@SuppressWarnings("unchecked")
	public void addElement(ValueObject element) {
		add((T) element);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * Returns a new instance of the model object.
	 * 
	 * @return new instance of T
	 */
	public final T createNew() {
		try {
			return (T) clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public T get(int index) {
		return value.get(index);
	}

	public String getClazzName() {
		return clazzName;
	}

	@Override
	public String getName() {
		return "lijst name";
	}

	@Override
	public List<T> getValue() {
		return (List<T>) this.value;
	}

	@Override
	public boolean isReadOnly() {
		throw new UnsupportedOperationException();
	}

	public Iterator<T> iterator() {
		return this.value.iterator();
	}

	/**
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void parse(String s) {
		throw new UnsupportedOperationException();
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	/**
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void setReadOnly(boolean newStatus) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		return this.value.size();
	}

	@Override
	public Component render(ValueRenderer renderer) {
		return renderer.render(this);
	}

	@Override
	protected List<T> getDefaultValue() {
		return new ArrayList<T>();
	}
}
