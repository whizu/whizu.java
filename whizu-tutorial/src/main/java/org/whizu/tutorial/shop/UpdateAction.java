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
package org.whizu.tutorial.shop;

import java.lang.reflect.Field;

import org.apache.commons.lang.reflect.FieldUtils;
import org.whizu.content.Component;
import org.whizu.content.Content;
import org.whizu.html.Html;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Select;
import org.whizu.value.Value;

//@Action
public abstract class UpdateAction<T> extends AbstractAction {

	@Select(id = "detail")
	private JQuery detailPanel;

	protected T model;

	public UpdateAction(T model) {
		this.model = model;
	}

	private Content createUpdateForm(Value[] columns) {
		/* uncomment 
		FormBuilder form = new FormBuilder();
		for (Value value : columns) {
			form.addValue(model, value);
		}
		form.addButton("OK", this);
		//form.addButton("Cancel", this);
		
		form.width("300px");
		
		return form.create();
		*/ return null;
	}

	protected Component createUpdateForm(String... fieldNames) {
		/* uncomment 
		FormBuilder form = new FormBuilder();
		for (String fieldName : fieldNames) {
			form.addField(model, fieldName);
		}
		form.addButton("OK", this);
		return form.create();
		*/ return null;
	}

	@Override
	public abstract String getCaption();

	protected abstract String[] getFields();

	protected String getTitle() {
		return (getCaption() == null) ? "Nieuw" : getCaption();
	}

	public void handleEvent() { // initAction
		detailPanel = RequestContext.getRequest().select("$(\"#detail\")");
		detailPanel.empty();
		detailPanel.append(Html.h3(getTitle()));
		if (getColumns(model) != null) {
			detailPanel.append(createUpdateForm(getColumns(model)));
		} else {
			detailPanel.append(createUpdateForm(getFields()));
		}
	}

	@Override
	public final void performAction() {
		T update = performUpdate();
		refresh(model, update);
		callback();
	}

	private void refresh(T target, T source) {
		if (getColumns(target) != null) {
			refreshValues(target.getClass(), target, source);
			Class<?> superClass = target.getClass().getSuperclass();
			while (superClass != null) {
				refreshValues(superClass, target, source);
				superClass = superClass.getSuperclass();
			}

		} else {
			refresh(target.getClass(), target, source);
			Class<?> superClass = target.getClass().getSuperclass();
			while (superClass != null) {
				refresh(superClass, target, source);
				superClass = superClass.getSuperclass();
			}
		}
	}

	private void refreshValues(Class<?> clazz, T target, T source) {
		try {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				System.out.println("Writing field " + field.getName());
				Class<?> type = field.getType();
				Class<?> vc = Value.class;
				if (vc.isAssignableFrom(type)) {
					System.out.println("field " + field.getName() + " is een value");
					Value targetValue = (Value) FieldUtils.readField(field, target, true);
					Value sourceValue = (Value) FieldUtils.readField(field, target, true);
					targetValue.refresh(sourceValue);
				} else {
					System.out.println("field " + field.getName() + " met type " + type + " is GEEN value");
					FieldUtils.writeField(field, target, FieldUtils.readField(field, source, true), true);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void refresh(Class<?> clazz, T target, T source) {
		try {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				System.out.println("Writing field " + field.getName());
				FieldUtils.writeField(field, target, FieldUtils.readField(field, source, true), true);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract T performUpdate();

	protected Value[] getColumns(T model) {
		return null;
	}
}
