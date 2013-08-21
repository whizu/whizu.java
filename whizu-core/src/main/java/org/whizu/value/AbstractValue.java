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
import java.beans.PropertyChangeSupport;

/**
 * @author Rudy D'hauwe
 */
public abstract class AbstractValue<T> implements Value<T> {

	private static final String VALUE = "value_";

	private transient PropertyChangeSupport changeSupport_;

	private final String name_;

	private boolean readOnly_;

	private T value_;

	public AbstractValue(String key) {
		name_ = key;
		value_ = getDefaultValue();
	}

	public AbstractValue(String key, T value) {
		name_ = key;
		value_ = value;
	}

	public final void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	public final void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public abstract void clear();

	protected void fireIndexedPropertyChange(int index, Object oldValue, Object newValue) {
		firePropertyChange(VALUE, index, oldValue, newValue);
	}
	
	protected void fireIndexedPropertyChange(String name, int index, Object oldValue, Object newValue) {
		firePropertyChange(name, index, oldValue, newValue);
	}

	private void firePropertyChange(String propertyName, int index, Object oldValue, Object newValue) {
		if (changeSupport_ != null) {
			changeSupport_.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
		}
	}

	private void firePropertyChange(String propertyName, T oldValue, T newValue) {
		if (changeSupport_ != null) {
			changeSupport_.firePropertyChange(propertyName, oldValue, newValue);
		}
	}

	public final T get() {
		return value_;
	}

	protected abstract T getDefaultValue();

	private PropertyChangeSupport getPropertyChangeSupport() {
		if (changeSupport_ == null) {
			changeSupport_ = new PropertyChangeSupport(this);
		}

		return changeSupport_;
	}

	@Override
	public final String name() {
		return name_;
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
	public void refresh(Value<T> obj) {
		set(obj.get());
	}

	public final void removePropertyChangeListener(PropertyChangeListener listener) {
		if (changeSupport_ != null) {
			changeSupport_.removePropertyChangeListener(listener);
		}
	}

	public final void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		if (changeSupport_ != null) {
			changeSupport_.removePropertyChangeListener(propertyName, listener);
		}
	}

	public final void set(T value) {
		firePropertyChange(VALUE, value_, value_ = value);
	}
}
