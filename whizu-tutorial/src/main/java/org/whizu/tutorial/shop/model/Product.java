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
package org.whizu.tutorial.shop.model;

import java.util.Date;

public class Product extends Entity {

	private String code;

	private String naam;
	
	private String omschr;

	public Product() {
	}

	public Product(long id, String code, String naam, Date lastUpdate) {
		setId(id);
		setCode(code);
		setNaam(naam);
		setLastUpdate(lastUpdate);
	}

	public String getCode() {
		return code;
	}

	public String getNaam() {
		return naam;
	}

	public String getOmschr() {
		return omschr;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setOmschr(String omschr) {
		this.omschr = omschr;
	}
}
