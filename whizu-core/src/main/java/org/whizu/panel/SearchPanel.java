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
package org.whizu.panel;

import java.util.Collection;

import org.whizu.dom.Component;
import org.whizu.dom.Composite;
import org.whizu.html.Html;
import org.whizu.jquery.Callback;
import org.whizu.ui.Action;
import org.whizu.ui.ButtonImpl;
import org.whizu.ui.ClickListener;
import org.whizu.ui.FormBuilder;
import org.whizu.ui.Hyperlink;
import org.whizu.value.Value;

/**
 * @author Rudy D'hauwe
 */
public abstract class SearchPanel<T> implements Panel {

	private final Class<T> clazz_;

	public SearchPanel(Class<T> clazz) {
		clazz_ = clazz;
	}

	public void create(final Composite parent) {
		parent.add(Html.h3(getCaption()));

		Component filter = createFilter();
		if (filter != null) {
			parent.add(filter);
		}

		Collection<T> all = performSearch();

		// this.table = new Table(all);
		for (T art : all) {
			//table.add(columns)
			Action action = getUpdateAction(art);
			action.setCallback(getUpdateCallback(parent));
			Value[] columns = getColumns(art);
			String value = "" + columns[0];
			parent.add(new Hyperlink(value, action));
		}
		// panel.append(this.table);


		parent.add(new ButtonImpl("Create new...").addClickListener(new ClickListener() {

			@Override
			public void click() {
				initCreate(parent);
			}
		}));
	}

	protected void refresh(Composite parent) {
		parent.empty();
		create(parent);
	}

	private Component createFilter() {
		T model = createModel();
		Value[] columns = getColumns(model);
		FormBuilder form = new FormBuilder(columns.length);
		for (Value value : getColumns(model)) {
			form.addValue(model, value);
		}
		return form.create();
	}

	protected final T createModel() {
		try {
			return clazz_.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	public abstract String getCaption();

	protected abstract Action getCreateAction();

	protected Callback getUpdateCallback(final Composite parent) {
		return new Callback() {

			@Override
			public void handleEvent() {
				refresh(parent);
			}
		};
	}

	protected void initCreate(Composite parent) {
		Action action = getCreateAction();
		action.setCallback(getCreateCallback(parent));
		action.handleEvent();
	}

	private Callback getCreateCallback(final Composite parent) {
		return new Callback() {

			@Override
			public void handleEvent() {
				refresh(parent);
			}
		};
	}

	protected abstract Collection<T> performSearch();

	protected abstract Action getUpdateAction(T model);

	protected abstract Value[] getColumns(T model);
}
