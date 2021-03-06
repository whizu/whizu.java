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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.whizu.content.Component;
import org.whizu.content.Content;
import org.whizu.content.Element;
import org.whizu.content.Foreach;
import org.whizu.html.Html;
import org.whizu.widget.Widget;

class TableLayout extends Widget {

	private String title;

	private List<String> columnList = new ArrayList<String>();

	private Map<Object, Component[]> rows = new HashMap<Object, Component[]>();

	TableLayout(String title) {
		this.title = title;
	}

	public void addColumn(String name) {
		this.columnList.add(name);
	}

	public void addRow(Object key, Component... components) {
		this.rows.put(key, components);

		if (isRendered()) {
			Element tr = Html.tr().width(width).style("word-wrap", "break-word");
			for (Component value : components) {
				tr.add(Html.td().style("word-wrap", "break-word").add(value));
				/*
				 * //TODO remove cast Content m = ((AbstractComponent)
				 * value).render(); tr.add(Html.td().style("word-wrap",
				 * "break-word").add(m));
				 */
			}
			jQuery(this).find("tbody").prepend(tr);
		}
	}

	public String getTitle() {
		return title;
	}

	@Override
	public Content build() {
		// isRendered = true;
		jQuery(this).closest("div").trigger("create");

		// @formatter:off
		return Html.table(id())
				.attr("data-role", "table")
				.css("ui-responsive")
				.css("table-stroke")
				.attr("data-inset", "true")
				.attr("data-mini", "true")
				.attr("data-mode", "columntoggle")
				.width(width)
				.style("word-wrap", "break-word")
				.add(Html.thead()
						.add(Html.tr()
								.add(new Foreach<String>(columnList) {

									@Override
									public Content build(String item) {
										return Html.th(item);
									}
								})
							), 
						Html.tbody()
				     	.add(new Foreach<Component[]>(rows.values()) {

				     		@Override
				     		public Content build(Component[] item) {
				     			Element tr = Html.tr()
				     							.width(width)
				     							.style("word-wrap", "break-word");
				     			for (Component value : item) {
				     				tr.add(Html.td()
					     						.style("word-wrap", "break-word")
					     						.add(value));
				     				/*
				     				//todo remove cast
				     				Content m = ((AbstractComponent) value).render();
				     				tr.add(
				     					Html.td()
				     						.style("word-wrap", "break-word")
				     						.add(m));
				     						*/
				     			}
				     			return tr;
				     		}
				     	}));
		// @formatter:on
	}
}
