/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the “EUPL”) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an “AS IS” basis and 
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
package org.whizu.layout;

import org.whizu.dom.Component;
import org.whizu.dom.Element;
import org.whizu.dom.Markup;
import org.whizu.html.Html;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
public class GridLayout extends Widget implements Layout {

	//private Log log = LogFactory.getLog(GridLayout.class);
	
	private int numberOfColumns;
	
	private int column = 0;

	private Element grid;

	private Element tbody;
	
	private Element row;

	public GridLayout() {
		this(1);
	}
	
	public GridLayout(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
		
		this.grid = Html.table(this).width("100%").attr("cellspacing", "0").attr("cellpadding", "0");
		this.tbody = Html.tbody();
		this.grid.add(tbody);
	}

	@Override
	public Markup compile() {
		return grid;
	}

	@Override
	public GridLayout add(Component component) {
		if (isRendered()) {
			throw new UnsupportedOperationException();
		} else {
			if ((column == 0) || (column == numberOfColumns)) {
				row = Html.tr();
				tbody.add(row);
				column = 1;
			} else {
				column++;
			}
			
			row.add(Html.td().add(component));
		}
		
		return this;
	}

	@Override
	public void empty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void prepend(Component component) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(Component component) {
		throw new UnsupportedOperationException();
	}
}
