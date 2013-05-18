package org.whizu.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

import org.whizu.layout.GridLayout;
import org.whizu.value.StringValue;
import org.whizu.value.Value;

public class FormBuilder {

	private Form form;

	private GridLayout layout;

	private WhizuUI ui = new WhizuUI();

	public FormBuilder() {
		this(1);
	}

	public FormBuilder(int columns) {
		this.form = new FormImpl();
		this.layout = new GridLayout(2 * columns);
	}

	public void addButton(String caption, final Action action) {
		addButton(caption, new ClickListener() {

			@Override
			public void click() {
				action.performAction();
			}
		});
	}
	public void addButton(String caption, ClickListener listener) {
		layout.skip();
		//layout.add(ui.createLabel(caption).css("submit").addClickListener(listener));
		layout.add(ui.createButton(caption).addClickListener(listener).css("whizu-button"));
	}

	public void addField(Object model, String fieldName) {
		addTextField(model, fieldName);
	}

	public void addValue(Object model, Value value) {
		layout.add(ui.createLabel(getLabelName(model, value))); // todo refactor
		layout.add(ui.createTextField(value));
	}

	private String getLabelName(Object model, Value value) {
		return value.name();
		/*
		try {
			Field[] fields = model.getClass().getFields();
			for (Field field : fields) {
				Object obj = FieldUtils.readField(field, model, true);
				if (obj == value) {
					if (field.isAnnotationPresent(Name.class)) {
						return field.getAnnotation(Name.class).value();
					} else {
						return field.getName();
					}
				}
			}
			return value.getName();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		*/
	}

	public void addTextField(Object model, String fieldName) {
		layout.add(ui.createLabel(fieldName));
		layout.add(ui.createTextField(getValue(model, fieldName)));
	}

	public Form create() {
		form.add(layout);
		return form;
	}

	private String get(final Object model, final String fieldName) {
		try {
			// return (String) FieldUtils.readField(model, fieldName);
			Method getter = model.getClass().getMethod("get" + fieldName);
			return (String) getter.invoke(model);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private StringValue getValue(final Object model, final String fieldName) {
		String s = get(model, fieldName);
		final StringValue value = new StringValue(fieldName, s);
		value.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				set(model, fieldName, value.value());
			}
		});
		return value;
	}

	private void set(final Object model, final String fieldName, final Object value) {
		try {
			final Method setter = model.getClass().getMethod("set" + fieldName, String.class);
			setter.invoke(model, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
