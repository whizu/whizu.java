package org.whizu.jquery.mobile.list;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.whizu.content.Content;
import org.whizu.jquery.ClickListener;

//= new ValueList<T>() ?
public class DefaultListControl<T extends ListElement> implements ListControl<T> {

	private List<T> list_;
	
	private transient PropertyChangeSupport changeSupport_;
	
	public DefaultListControl() {	
		list_ = new ArrayList<>();
	}
	
	public DefaultListControl(List<T> list) {
		list_ = list;
	}
	
	public void add(T element) {
		list_.add(element);
		getPropertyChangeSupport().fireIndexedPropertyChange("ADD", list_.size()-1, null, element);
	}

	@Override
	public Iterator<T> iterator() {
		return list_.iterator();
	}

	@Override
	public String id(T item) {
		return item.id();
	}

	@Override
	public boolean isClickable(T element) {
		return true;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	@Override
	public int size() {
		return list_.size();
	}

	@Override
	public Content build(T item) {
		return item.build();
	}
	
	private PropertyChangeSupport getPropertyChangeSupport() {
		if (changeSupport_ == null) {
			changeSupport_ = new PropertyChangeSupport(this);
		}

		return changeSupport_;
	}

	@Override
	public T get(int index) {
		return list_.get(index);
	}

	@Override
	public boolean isClickable() {
		return true;
	}

	@Override
	public void handleClickEvent(T element) {
		if (!isClickable(element)) {
			throw new IllegalStateException();
		}
				
	}

	@Override
	public void handleAddEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClickListener addEvent() {
		// TODO Auto-generated method stub
		return null;
	}
}
