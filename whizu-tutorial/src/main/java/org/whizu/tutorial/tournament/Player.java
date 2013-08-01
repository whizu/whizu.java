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
package org.whizu.tutorial.tournament;

import org.whizu.dom.Content;
import org.whizu.html.Html;
import org.whizu.value.AbstractValueObject;
import org.whizu.value.DateValue;
import org.whizu.value.IntegerValue;
import org.whizu.value.StringValue;
import org.whizu.value.Value;

/**
 * @author Rudy D'hauwe
 */
public class Player extends AbstractValueObject {

	public final DateValue birthdate = new DateValue("Birth date");
	
	public final IntegerValue level = new IntegerValue("Level");
	
	public final StringValue name = new StringValue("Name");

	@Override
	public Content build() {
		return Html.h2(name.get());
	}

	public void clear() {
		name.clear();
		birthdate.clear();
	}

	@Override
	public Value[] getColumns() {
		return new Value[] { name, birthdate };
	}

	public void refresh(Player player) {
		name.refresh(player.name);
		birthdate.refresh(player.birthdate);
	}
}
