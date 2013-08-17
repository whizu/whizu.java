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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.jquery.ClickListener;
import org.whizu.jquery.OnItemClickListener;
import org.whizu.util.Callback;
import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
public class ValueList<VO> extends ValueBuilder<ValueList<VO>, List<VO>> implements Iterable<VO> {
				//implements ListManager (default behavior)
	
	private static final Logger log = LoggerFactory.getLogger(ValueList.class);
	
	private final Class<VO> clazz_;

	public ValueList(Class<VO> clazz) {
		super(clazz.getName());
		clazz_ = clazz;
	}

	@Deprecated
	public final ClickListener add(final OnItemClickListener<VO> itemListener) {
		log.debug("Creating an ADD event listener {}", itemListener);
		
		return new ClickListener() {

			@Override
			public void click() {
				log.debug("Handling the ADD event");
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
	
	public void add(VO element) {
		value().add(element);
		fireIndexedPropertyChange("ADD", value().size(), null, element);
	}
	
	public void addAll(List<VO> elements) {
		for (VO vo : elements) {
			add(vo);
		}
	}

	@SuppressWarnings("unchecked")
	public void addElement(ValueObject element) {
		add((VO) element);
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

	public final void update(ValueObject obj) {
		VO vo = Objects.cast(obj);
		update(vo);
	}

	public void update(VO model) {
		int index = value().indexOf(model);
		System.out.println("notify update of item no " + index);
		fireIndexedPropertyChange(index, null, model);
	}

	/**
	 * @return Collections.unmodifiableList(List<VO>)
	 */
	@Override
	public final List<VO> value() {
		return Collections.unmodifiableList(super.value());
	}

	@Override
	public <T> T visit(Visitor<T> visitor) {
		return visitor.accept(this);
	}
}
