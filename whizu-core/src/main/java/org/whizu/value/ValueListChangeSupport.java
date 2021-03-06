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
package org.whizu.value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.whizu.util.ListChangeListener;

/**
 * @author Rudy D'hauwe
 */
class ValueListChangeSupport<T> {

	private List<ListChangeListener<T>> listenerList_ = new ArrayList<ListChangeListener<T>>();

	protected ValueListChangeSupport() {
	}

	public void addChangeListener(final ListChangeListener<T> listener) {
		listenerList_.add(listener);
	}

	public void fireAdd(T model) {
		for (ListChangeListener<T> listener : listenerList_) {
			listener.fireAdd(model);
		}
	}

	public void fireAddAll(Collection<T> elements) {
		for (ListChangeListener<T> listener : listenerList_) {
			listener.fireAddAll(elements);
		}
	}

	public void fireClear() {
		for (ListChangeListener<T> listener : listenerList_) {
			listener.fireClear();
		}
	}

	public void fireRetainAll(List<T> items) {
		for (ListChangeListener<T> listener : listenerList_) {
			listener.fireRetainAll(items);
		}
	}

	public void fireRemove(int index, T model) {
		for (ListChangeListener<T> listener : listenerList_) {
			listener.fireRemove(index, model);
		}
	}
	
	public void fireUpdate(int index, T model) {
		for (ListChangeListener<T> listener : listenerList_) {
			listener.fireUpdate(index, model);
		}
	}

	public void fireAddEvent() {
		for (ListChangeListener<T> listener : listenerList_) {
			listener.fireAddEvent();
		}
	}
}
