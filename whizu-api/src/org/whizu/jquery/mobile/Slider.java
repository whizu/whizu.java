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

import org.whizu.dom.Content;
import org.whizu.dom.Element;
import org.whizu.dom.Html;
import org.whizu.jquery.AbstractComponent;

/**
 * @author Rudy D'hauwe
 */
public class Slider extends AbstractComponent {

	private String max;

	private String min;
	
	private Mini mini;

	private String step;

	private Theme theme;

	private Theme track;

	public Slider(int min, int max) {
		this.min = "" + min;
		this.max = "" + max;
	}

	public Slider(int min, int max, Theme theme) {
		this(min, max);
		this.theme = theme;
	}

	@Override
	public Content create() {
		// @formatter:off
		Element input = Html.input(this)
				.attr("type", "range")
				.attr("name", "label")
				.attr("value", min)
				.attr("min", min)
				.attr("max", max)
				.attr("step", step)
				.decorate(theme)
				.decorate("data-track-theme", track)
				.decorate(mini);
		Element label = Html.tag("label")
				.attr("for", input.getId())
				.add("label");
		// @formatter:on
		return label.after(input);
	}

	@Override
	public Slider css(String clazz) {
		setStyleName(clazz);
		return this;
	}

	public String getMax() {
		return max;
	}

	public String getMin() {
		return min;
	}

	public Mini getMini() {
		return mini;
	}

	public String getStep() {
		return step;
	}
	
	public Theme getTheme() {
		return theme;
	}

	public Theme getTrack() {
		return track;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public void setMini(Mini mini) {
		this.mini = mini;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public void setTrack(Theme track) {
		this.track = track;
	}
}
