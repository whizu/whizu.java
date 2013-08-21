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
package org.whizu.jquery.mobile;

import org.whizu.content.Content;
import org.whizu.value.DateValue;
import org.whizu.value.IntegerValue;
import org.whizu.value.PasswordValue;
import org.whizu.value.StringValue;
import org.whizu.value.Value;
import org.whizu.value.ValueList;
import org.whizu.value.ValueObject;
import org.whizu.value.ValueTable;
import org.whizu.value.Visitor;

/**
 * @author Rudy D'hauwe
 */
class ValueRenderer implements Visitor<Content> {

	@Override
	public Content accept(DateValue value) {
		return new DateField(value);
	}

	@Override
	public Content accept(IntegerValue value) {
		return new Text(value);
	}

	@Override
	public Content accept(StringValue value) {
		return new Text(value);
	}

	@Override
	public Content accept(PasswordValue value) {
		return new Text(value);
	}

	@Override
	public <VO> Content accept(ValueList<VO> list) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <VO extends ValueObject> Content accept(ValueTable<VO> table) {
		throw new UnsupportedOperationException();
	}

	public Content visit(Value<?> value) {
		return value.visit(this);
	}
}
