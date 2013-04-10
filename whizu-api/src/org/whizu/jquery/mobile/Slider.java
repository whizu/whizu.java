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
package org.whizu.jquery.mobile;

import org.whizu.html.Html;
import org.whizu.html.NonVoid;
import org.whizu.runtime.AbstractComponent;

/**
 * @author Rudy D'hauwe
 */
public class Slider extends AbstractComponent {

	private String min;

	private String max;

	private Theme theme;

	private Theme track;

	private Mini mini;

	public Slider(int min, int max) {
		this.min = "" + min;
		this.max = "" + max;
	}

	public Slider(int min, int max, Theme theme) {
		this(min, max);
		this.theme = theme;
	}
	
	@Override
	public Slider css(String clazz) {
		setStyleName(clazz);
		return this;
	}

	@Override
	public Html create() {
		// @formatter:off
		NonVoid input = input(this)
				.attr("type", "range")
				.attr("name", "label")
				.attr("value", min)
				.attr("min", min)
				.attr("max", max)
				.decorate(theme)
				.decorate("data-track-theme", track)
				.decorate(mini);
		NonVoid label = NonVoid.tag("label")
				.attr("for", input.getId())
				.add("label");
		// @formatter:on
		return label.after(input);
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public Theme getTrack() {
		return track;
	}

	public void setTrack(Theme track) {
		this.track = track;
	}

	public Mini getMini() {
		return mini;
	}

	public void setMini(Mini mini) {
		this.mini = mini;
	}
}
