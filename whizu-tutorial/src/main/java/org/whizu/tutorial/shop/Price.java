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
package org.whizu.tutorial.shop;

import java.util.Date;

/**
 * @author Rudy D'hauwe
 */
public class Price extends Entity {

	private Customer customer;
	
	private Date from;
	
	private Product product;
	
	private Date until;

	public Customer getCustomer() {
		return customer;
	}

	public Date getFrom() {
		return from;
	}

	public Product getProduct() {
		return product;
	}

	public Date getUntil() {
		return until;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setUntil(Date until) {
		this.until = until;
	}	
}
