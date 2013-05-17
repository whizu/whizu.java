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

import java.util.ArrayList;
import java.util.Collection;

import org.whizu.tutorial.shop.model.Customer;
import org.whizu.ui.Action;

/**
 * @author Rudy D'hauwe
 */
public class CustomerSearchAction extends SearchAction<Customer> {

	public CustomerSearchAction() {
		super(Customer.class);
	}

	@Override
	public String getCaption() {
		return "Customer";
	}

	@Override
	protected Action getCreateAction() {
		return null;
	}

	@Override
	protected String[] getFields() {
		return new String[]{"Naam"};
	}

	@Override
	protected Collection<Customer> performSearch() {
		return new ArrayList<Customer>();
	}

	@Override
	protected Action getUpdateAction(Customer model) {
		return null;
	}
}
