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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.whizu.jquery.ClickListener;
import org.whizu.util.ListChangeListener;
import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
public abstract class AbstractValueList<T> implements Value, Iterable<T> {

	private ValueListChangeSupport<T> changeSupport_;

	private final String name_;

	private boolean readOnly_;

	private List<T> value_;

	public AbstractValueList(String key) {
		name_ = key;
		value_ = getDefaultValue();
	}

	public AbstractValueList(String key, List<T> value) {
		name_ = key;
		value_ = value;
	}

	public final void add(T element) {
		value_.add(element);
		getChangeSupport().fireAdd(element);
	}

	public final void addAll(List<T> elements) {
		for (T vo : elements) {
			value_.add(vo);
		}
		getChangeSupport().fireAddAll(elements);
	}

	public final void addChangeListener(ListChangeListener<T> listener) {
		getChangeSupport().addChangeListener(listener);
	}

	/**
	 * throw new UnsupportedOperationException();
	 */
	public final void addPropertyChangeListener(PropertyChangeListener listener) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final void clear() {
		value_.clear();
		getChangeSupport().fireClear();
	}

	public final List<T> get() {
		return value();
	}

	private ValueListChangeSupport<T> getChangeSupport() {
		if (changeSupport_ == null) {
			changeSupport_ = new ValueListChangeSupport<T>();
		}

		return changeSupport_;
	}
	
	protected abstract List<T> getDefaultValue();

	public final T get(int index) {
		return value_.get(index);
	}
	
	@Override
	public final Iterator<T> iterator() {
		return value_.iterator();
	}

	@Override
	public final String name() {
		return name_;
	}

	/**
	 * throw new UnsupportedOperationException();
	 */
	@Override
	public final void parse(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean readOnly() {
		return readOnly_;
	}

	@Override
	public final void readOnly(boolean readOnly) {
		readOnly_ = readOnly;
	}

	@Override
	public final void refresh(Object obj) {
		if (obj instanceof AbstractValueList) {
			AbstractValueList<T> valueList = Objects.cast(obj);
			set(valueList.get());
		} else if (obj instanceof List)  {
			List<T> list = Objects.cast(obj);
			set(list);
		}
	}

	public final void remove(T model) {
		int index = value_.indexOf(model);
		value_.remove(model);
		getChangeSupport().fireRemove(index, model);
	}

	public final void set(Object value) {
		List<T> items = Objects.cast(value);
		if (value_.retainAll(items)) {
			getChangeSupport().fireRetainAll(items);
		}
	}
	
	public final int size() {
		return value_.size();
	}

	public final void update(T model) {
		int index = value_.indexOf(model);
		getChangeSupport().fireUpdate(index, model);
	}
	

	public final ClickListener addEvent() {
		return new ClickListener() {

			@Override
			public void click() {
				getChangeSupport().fireAddEvent();
			}
		};
	}
	
	/**
	 * @return Collections.unmodifiableList(List<T>)
	 */
	public final List<T> value() {
		return Collections.unmodifiableList(value_);
	}
}
