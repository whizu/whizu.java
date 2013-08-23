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

import java.lang.reflect.Field;

import org.whizu.util.Objects;

/**
 * @author Rudy D'hauwe
 */
public abstract class AbstractValueObject<T extends ValueObject<T>> implements ValueObject<T> {

	public abstract Value<?>[] getColumns();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public final void refresh(T vo) {
		Field[] fields = getClass().getFields();
		for (Field field : fields) {
			if (Value.class.isAssignableFrom(field.getType())) {
				try {
					Value<?> fieldValue = Objects.cast(field.get(this));
					Value voValue = Objects.cast(field.get(vo));
					fieldValue.refresh(voValue);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					throw new UnsupportedOperationException(e);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					throw new UnsupportedOperationException(e);
				}
			}
		}
	}
}
