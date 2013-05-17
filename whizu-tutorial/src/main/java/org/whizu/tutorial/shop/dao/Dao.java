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
package org.whizu.tutorial.shop.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.whizu.tutorial.shop.model.Entity;


public class Dao<T extends Entity> {

	Long next = 10L;
	
	private Map<Long, T> entityMap = new HashMap<Long, T>();

	public void add(T entity) {
		entityMap.put(entity.id(), entity);
	}

	public T findById(Long id) {
		return entityMap.get(id);
	}

	public Collection<T> findAll() {
		return entityMap.values();
	}

	public void delete(T entity) {
		entityMap.remove(entity);
	}

	public T update(T entity) {
		if (entity.id() == null) {
			entity.id(next++);
			add(entity);
		} else {
			entityMap.put(entity.id(), entity);
		}
		entity.lastUpdate(new Date());
		return entity;
	}
}
