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

import org.whizu.tutorial.shop.dao.CountryDAO;
import org.whizu.tutorial.shop.model.Country;
import org.whizu.ui.Action;


//@Action
//@Title("Country")
public class CountrySearchAction extends SearchAction<Country> {

	public CountrySearchAction() {
		super(Country.class);
	}

	@Override
	public String getCaption() {
		return "Country";
	}

	@Override
	protected Action getCreateAction() {
		return new CountryUpdateAction(new Country());
	}

	@Override
	protected String[] getFields() {
		return new String[]{"Naam"};
	}

	@Override
	protected Action getUpdateAction(Country model) {
		return new CountryUpdateAction(model);
	}

	@Override
	protected Collection<Country> performSearch() {
		return CountryDAO.INSTANCE.findAll();
	}
}
