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

/**
 * @author Rudy D'hauwe
 */
public class ValueTable<TVO extends ValueObject<TVO>> extends ValueBuilder<ValueTable<TVO>, List<TVO>> {

	private Class<TVO> clazz_;

	//@Embedded
	public String clazzName = "blabla";

	//@Transient
	//private T sampleValueObject;

	public ValueTable() {
		super("table");
	}

	public ValueTable(Class<TVO> clazz) {
		this();
		clazz_ = clazz;
		this.clazzName = clazz.getName();
		// this.sampleValueObject = createNew();
	}

	public final void add(TVO element) {
		get().add(element);
		fireIndexedPropertyChange(size()-1, null, element);
	}

	@SuppressWarnings("unchecked")
	public void addElement(ValueObject<TVO> element) {
		add((TVO) element);
	}

	/**
	 * Returns a new instance of the model object.
	 * 
	 * @return new instance of T
	 */
	public final TVO createNew() {
		try {
			return (TVO) clazz_.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public TVO get(int index) {
		return get().get(index);
	}

	public String getClazzName() {
		return clazzName;
	}

	@Override
	protected List<TVO> getDefaultValue() {
		return new ArrayList<TVO>();
	}

	public Iterator<TVO> iterator() {
		return get().iterator();
	}

	/**
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void parse(String s) {
		throw new UnsupportedOperationException();
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public int size() {
		return get().size();
	}

	@Override
	public <T> T visit(Visitor<T> visitor) {
		return visitor.accept(this);
	}

	@Override
	public void clear() {
		super.get().clear();
	}
}
