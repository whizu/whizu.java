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

import org.whizu.jquery.ClickListener;
import org.whizu.jquery.OnItemClickListener;
import org.whizu.util.Callback;
import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
public class ValueList<VO> extends ValueBuilder<ValueList<VO>, List<VO>> implements Iterable<VO> {
				//implements ListManager (default behavior)
	
	private final Class<VO> clazz_;

	public ValueList(Class<VO> clazz) {
		super(clazz.getName());
		clazz_ = clazz;
	}

	//ButttonBuilder.create().onSubmit(list.addEvent());
	public final ClickListener addEvent() {
		return new ClickListener() {

			@Override
			public void click() {
				fireIndexedPropertyChange("ADD-EVENT", 0, null, null);
				//or: getListControl().addEvent(); 
				//(or listcontrol should have registered as propertychangelistener) and respond to the ADD-EVENT notification!
			}
		};
	}
	
	public void add(VO element) {
		value().add(element);
		fireIndexedPropertyChange("ADD", value().size(), null, element);
	}

	@SuppressWarnings("unchecked")
	public void addElement(ValueObject element) {
		add((VO) element);
	}

	/**
	 * Returns a new instance of the model object.
	 * 
	 * @return new instance of T
	 */
	public final VO createNew() {
		try {
			return (VO) clazz_.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public VO get(int index) {
		return value().get(index);
	}

	@Override
	protected List<VO> getDefaultValue() {
		return new ArrayList<VO>();
	}

	public Iterator<VO> iterator() {
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

	@Override
	public <T> T visit(Visitor<T> visitor) {
		return visitor.accept(this);
	}

	public void update(VO model) {
		int index = value().indexOf(model);
		System.out.println("notify update of item no " + index);
		fireIndexedPropertyChange(index, null, model);
	}

	public final void update(ValueObject obj) {
		VO vo = Objects.cast(obj);
		update(vo);
	}

	public final ClickListener add(final OnItemClickListener<VO> itemListener) {
		return new ClickListener() {

			@Override
			public void click() {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!clicking on ADD");
				final VO model = createNew();
				itemListener.click(model, new Callback() {

					@Override
					public void success() {
						add(model);
					}
				});
			}
		};
	}
}
