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
package org.whizu.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.whizu.html.Foreach;
import org.whizu.html.Html;
import org.whizu.html.NonVoid;
import org.whizu.ui.Table;
import org.whizu.ui.Widget;

class TableImpl extends AbstractComponent implements Table {

	private String title;

	private List<String> columnList = new ArrayList<String>();

	private Map<Object, Widget[]> rows = new HashMap<Object, Widget[]>();

	TableImpl(String title) {
		this.title = title;
	}

	@Override
	public void addColumn(String name) {
		this.columnList.add(name);
	}

	@Override
	public void addRow(Object key, Widget... components) {
		this.rows.put(key, components);

		if (isRendered()) {
			NonVoid tr = NonVoid.tr().width(width).style("word-wrap", "break-word");
			for (Widget value : components) {
				Html m = ((AbstractComponent) value).render();
				tr.add(NonVoid.td().style("word-wrap", "break-word").add(m));
			}
			jQuery(this).find("tbody").prepend(tr.toString());
		}
	}

	public String getTitle() {
		return title;
	}

	@Override
	public Html create() {
		//isRendered = true;
		jQuery(this).closest("div").trigger("create");

		// @formatter:off
		return NonVoid.table(getId())
				.attr("data-role", "table")
				.css("ui-responsive")
				.css("table-stroke")
				.attr("data-inset", "true")
				.attr("data-mini", "true")
				.attr("data-mode", "columntoggle")
				.width(width)
				.style("word-wrap", "break-word")
				.add(NonVoid.thead()
						.add(NonVoid.tr()
								.add(new Foreach<String>(columnList) {

									@Override
									public Html render(String item) {
										return NonVoid.th(item);
									}
								})
							), 
				     NonVoid.tbody()
				     	.add(new Foreach<Widget[]>(rows.values()) {

				     		@Override
				     		public Html render(Widget[] item) {
				     			NonVoid tr = NonVoid.tr()
				     						.width(width)
				     						.style("word-wrap", "break-word");
				     			for (Widget value : item) {
				     				Html m = ((AbstractComponent) value).render();
				     				tr.add(
				     					NonVoid.td()
				     						.style("word-wrap", "break-word")
				     						.add(m));
				     			}
				     			return tr;
				     		}
				     	}));
		// @formatter:on
	}
	
	/* (non-Javadoc)
	 * @see org.whizu.ui.Component#css(java.lang.String)
	 */
	@Override
	public Table css(String clazz) {
		setStyleName(clazz);
		return this;
	}
}
