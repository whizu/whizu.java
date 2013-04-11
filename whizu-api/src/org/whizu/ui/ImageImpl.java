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
package org.whizu.ui;

import org.whizu.dom.Html;
import org.whizu.dom.Markup;
import org.whizu.widget.Widget;


class ImageImpl extends Widget implements Image {

	private String src;

	private String listenerId;

	private String tooltip;

	private String droplistenerId;

	ImageImpl(String src) {
		this.src = src;
	}

	@Override
	public Markup compile() {
		String script = "";
		if (listenerId != null) {
			String ajaxCall = "$.get('/whizu?id=" + listenerId
					+ "', function(data) { ; }, 'script');";
			script += ".click(function(event) { event.preventDefault(); " + ajaxCall + "})";
			//script += ".mouseenter(function(event) { event.preventDefault(); " + ajaxCall + "})";
		}

		//use event.originalEvent.dataTransfer
		if (droplistenerId != null) {
			String ajaxCall = "$.get('/whizu?id=" + droplistenerId
					+ "', function(data) { ; }, 'script');";
			script += ".bind ('dragover', function(event) { event.stopPropagation(); event.preventDefault(); event.dataTransfer.dropEffect = 'move'; return false; } )";
			script += ".bind ('dragenter', function(event) { event.stopPropagation(); event.preventDefault(); event.dataTransfer.dropEffect = 'copy'; return false; } )";
			script += ".bind ('drop', function(event) { alert('drop'); alert(event.originalEvent.dataTransfer.files); event.stopPropagation(); event.preventDefault(); " + ajaxCall + " return false; } )";
		}

		if (!script.equals("")) {
			jQuery(this).concat(script); // todo further refactoring
		}

		
		return Html.img(this.id()).src(src).title(tooltip).width("48px").attr("height", "48px").margin("2px").css(style)
				.css("link");
		/*
		 * if (tooltip != null) { imgNode.attr("title", tooltip); }
		 */

		/*
		 * 
		 * if (tooltip != null) { NonVoid tooltipMarkup =
		 * NonVoid.div("tooltip-"+this.getId()).attr("data-role",
		 * "popup").css("ui-content").attr("data-theme", "e").style("max-width",
		 * "300px").add(tooltip); Expression expr =
		 * jQuery().document().append(tooltipMarkup.toString()); }
		 */

	}

	@Override
	public void addClickListener(ClickListener listener) {
		ClickListenerImpl impl = new ClickListenerImpl(listener);
		listenerId = impl.getId();
		getSession().addClickListener(impl);
	}

	@Override
	public void addTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	@Override
	public void addDropHandler() {
		ClickListenerImpl impl = new ClickListenerImpl(new ClickListener() {
			
			@Override
			public void click() {
				System.out.println("...dropping");
			}
		});
		droplistenerId = impl.getId();
		getSession().addClickListener(impl);
	}
	
	@Override
	public Image css(String clazz) {
		setStyleName(clazz);
		return this;
	}
}
