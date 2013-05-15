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

	private final String key_;

	private boolean readOnly_;

	private T value_;

	public AbstractValue(String key) {
		key_ = key;
		value_ = getDefaultValue();
	}
	
	public AbstractValue(String key, T value) {
		key_ = key;
		value_ = value;
	}

	public final void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	public final void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
	}

	private void firePropertyChange(String propertyName, T oldValue, T newValue) {
		if (changeSupport_ != null) {
			changeSupport_.firePropertyChange(propertyName, oldValue, newValue);
		}
	}
	
	private void firePropertyChange(String propertyName, int index, Object oldValue, Object newValue) {
		if (changeSupport_ != null) {
			changeSupport_.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
		}
	}
	
	protected void fireIndexedPropertyChange(int index, Object oldValue, Object newValue) {
		firePropertyChange(VALUE, index, oldValue, newValue);
	}

	protected abstract T getDefaultValue();

	@Override
	public final String getName() {
		return key_;
	}

	private PropertyChangeSupport getPropertyChangeSupport() {
		if (changeSupport_ == null) {
			changeSupport_ = new PropertyChangeSupport(this);
		}

		return changeSupport_;
	}

	public final T getValue() {
		return value_;
	}

	@Override
	public final boolean isReadOnly() {
		return readOnly_;
	}

	@Override
	public void refresh(Value<T> value) {
		setValue(value.getValue());
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

	@Override
	public final void setReadOnly(boolean readOnly) {
		readOnly_ = readOnly;
	}

	public void setValue(T value) {
		firePropertyChange(VALUE, value_, value_ = value);
	}
}
