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
package org.whizu.layout;

import org.whizu.content.Component;
import org.whizu.content.Content;
import org.whizu.content.Element;
import org.whizu.content.Literal;
import org.whizu.html.Html;
import org.whizu.html.Table;
import org.whizu.html.Tbody;
import org.whizu.html.Td;
import org.whizu.widget.Widget;

/**
 * @author Rudy D'hauwe
 */
public class GridLayout extends Widget implements Layout {

	// private Log log = LogFactory.getLog(GridLayout.class);

	private final int numberOfColumns_;

	private int column_;

	private Table grid_;

	private Tbody tbody_;

	private Element row_;

	private String[] widths_;

	public GridLayout() {
		this(1);
	}

	public GridLayout(int numberOfColumns) {
		numberOfColumns_ = numberOfColumns;
		init();
	}

	public GridLayout(String... widths) {
		numberOfColumns_ = widths.length;
		widths_ = widths;
		init();
	}

	protected void init() {
		super.init();
		grid_ = Html.table(this).attr("cellspacing", "0").attr("cellpadding", "0");
		tbody_ = grid_.tbody();
		row_ = null;
		column_ = 0;
	}

	@Override
	public Content build() {
		return grid_;
	}

	@Override
	public GridLayout add(Content component) {
		if (isRendered()) {
			if ((column_ == 0) || (column_ == numberOfColumns_)) {
				row_ = tbody_.tr();
				// row.add(Html.td().add(component));
				jQuery("$(\"table > tbody\")").append(row_);
				column_ = 1;
			} else {
				column_++;
			}
			
			Td td = Html.td().add(component);
			jQuery("$(\"table > tbody tr:last-child\")").append(td);
		} else {
			if ((column_ == 0) || (column_ == numberOfColumns_)) {
				// row = Html.tr(); tbody.add(row);
				row_ = tbody_.tr();
				column_ = 1;
			} else {
				column_++;
			}

			
			Element td = Html.td();
			
			if (widths_ != null) {
				td.style("min-width", widths_[column_-1]).style("vertical-align","top").add(component);
			} else {
				td.add(component);
			}
			
			row_.add(td);
		}

		return this;
	}

	@Override
	public void empty() {
		init();

		if (isRendered()) {
			jQuery("$(\"table > tbody\")").empty();
		}
	}

	@Override
	public void prepend(Component component) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(Component component) {
		throw new UnsupportedOperationException();
	}

	public void skip() {
		add(new Literal("")); //TODO refactor
	}
}
