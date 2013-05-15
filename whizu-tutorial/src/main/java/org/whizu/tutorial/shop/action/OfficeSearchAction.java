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
package org.whizu.tutorial.shop.action;

import java.util.Collection;

import org.whizu.tutorial.shop.dao.OfficeDAO;
import org.whizu.tutorial.shop.model.Office;
import org.whizu.ui.Action;
import org.whizu.value.Value;


//@Action
public class OfficeSearchAction extends SearchAction<Office> {

	public OfficeSearchAction() {
		super(Office.class);
	}

	@Override
	public String getCaption() {
		return "Office";
	}

	@Override
	protected Action getCreateAction() {
		return new OfficeUpdateAction(new Office());
	}

	@Override
	protected String[] getFields() {
		return new String[]{ "Naam", "Code"};
	}
	
	@Override
	protected Value[] getColumns(Office model) {
		return new Value[] { model.naam, model.address };
	}

	@Override
	protected Action getUpdateAction(Office model) {
		return new OfficeUpdateAction(model);
	}

	@Override
	protected Collection<Office> performSearch() {
		return OfficeDAO.INSTANCE.findAll();
	}
}
