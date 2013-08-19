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
import java.util.List;

import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
public class ValueList<VO> extends ValueListBuilder<ValueList<VO>, VO> {
				//implements ListManager (default behavior)
	
	//private static final Logger log = LoggerFactory.getLogger(ValueList.class);
	
	private final Class<VO> clazz_;

	public ValueList(Class<VO> clazz) {
		super(clazz.getName());
		clazz_ = clazz;
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

	@Override
	protected List<VO> getDefaultValue() {
		return new ArrayList<VO>();
	}

	public final void update(ValueObject obj) {
		VO vo = Objects.cast(obj);
		update(vo);
	}

	@Override
	public final <T> T visit(Visitor<T> visitor) {
		return visitor.accept(this);
	}
}
