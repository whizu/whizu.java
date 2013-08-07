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

import java.lang.reflect.Method;
import java.util.Collection;

import org.whizu.content.Component;
import org.whizu.html.Html;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Select;
import org.whizu.tutorial.panel.Action;
import org.whizu.util.Callback;
import org.whizu.value.Value;

//@Action
public abstract class SearchAction<T> extends AbstractAction {

	private Class<T> clazz;

	public SearchAction(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Select(id = "right-column")
	JQuery contentPanel;

	protected void refresh() {
		contentPanel = RequestContext.getRequest().select("$(\"#right-column\")");
		contentPanel.empty();
		contentPanel.append(Html.h3(getCaption()));
		handleEvent(contentPanel);
		/* uncomment
		contentPanel.append(new Label("Create new...").css("submit").addClickListener(new ClickListener() {

			@Override
			public void click() {
				initCreate();
			}
		}));
		*/

	}

	public void handleEvent() {
		JQuery detailPanel = RequestContext.getRequest().select("$(\"#detail\")");
		detailPanel.empty();
		refresh();
	}

	private void handleEvent(JQuery panel) {
		Component filter = createFilter();
		if (filter != null) {
			panel.append(filter);
		}

		Collection<T> all = performSearch();

		for (T art : all) {
			Action action = getUpdateAction(art);
			action.setCallback(getUpdateCallback());

			Value[] columns = getColumns(art);
			if (columns != null) {
				// uncomment String value = "" + columns[0];
				// uncomment panel.append(new Hyperlink(value, action));
			} else {
				// uncomment String value = get(art, getFields()[0]);
				// uncomment panel.append(new Hyperlink(value, action));
			}
		}

		// this.table = new Table(all);
		// panel.append(this.table);

	}

	private Component createFilter() {
		/* uncomment
		String[] fields = getFields();
		FormBuilder form = new FormBuilder(fields.length);
		T model = createModel();
		if (getColumns(model) != null) {
			for (Value value : getColumns(model)) {
				form.addValue(model, value);
			}
		} else {
			for (String fieldName : fields) {
				form.addField(model, fieldName);
			}
		}
		return form.create();
		*/ return null;
	}

	protected final T createModel() {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public abstract String getCaption();

	protected abstract Action getCreateAction();

	protected abstract String[] getFields();

	protected Callback getUpdateCallback() {
		return new Callback() {

			@Override
			public void success() {
				refresh();
			}
		};
	}

	protected void initCreate() {
		Action action = getCreateAction();
		action.setCallback(getCreateCallback());
		action.handleEvent();
	}

	private Callback getCreateCallback() {
		return new Callback() {

			@Override
			public void success() {
				refresh();
			}
		};
	}

	@Override
	public void performAction() {
		handleEvent();
	}

	protected abstract Collection<T> performSearch();

	protected abstract Action getUpdateAction(T model);

	@SuppressWarnings("unused")
	private String get(final Object model, final String fieldName) {
		try {
			// return (String) FieldUtils.readDeclaredField(model, fieldName);
			Method getter = model.getClass().getMethod("get" + fieldName);
			return (String) getter.invoke(model);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Value[] getColumns(T model) {
		return null;
	}
}
