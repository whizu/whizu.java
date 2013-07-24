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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rudy D'hauwe
 */
public class ValueList<T> extends ValueBuilder<ValueList<T>, List<T>> {

	private final Class<T> clazz_;

	public ValueList(Class<T> clazz) {
		super(clazz.getName());
		clazz_ = clazz;
	}

	public void add(T element) {
		value().add(element);
		fireIndexedPropertyChange(value().size() - 1, null, element);
	}

	@SuppressWarnings("unchecked")
	public void addElement(ValueObject element) {
		add((T) element);
	}

	/**
	 * Returns a new instance of the model object.
	 * 
	 * @return new instance of T
	 */
	public final T createNew() {
		try {
			return (T) clazz_.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public T get(int index) {
		return value().get(index);
	}

	@Override
	protected List<T> getDefaultValue() {
		return new ArrayList<T>();
	}

	public Iterator<T> iterator() {
		return value().iterator();
	}

	/**
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void parse(String s) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		return value().size();
	}
}
