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
package org.whizu.jquery.mobile;

import org.whizu.content.Content;
import org.whizu.content.ContentList;
import org.whizu.html.Html;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class AccordionBuilder extends ProxyBuilder<Accordion> {

	public static AccordionBuilder create() {
		return new AccordionBuilder();
	}

	private Build build_ = new Build();

	private AccordionBuilder() {
	}

	@Override
	public Accordion build() {
		return buildOnce(new AccordionProxy(build_));
	}

	public AccordionBuilder contentTheme(Theme theme) {
		build_.contentTheme(theme);
		return this;
	}

	public AccordionBuilder iconCollapsed(DataIcon icon) {
		build_.iconCollapsed(icon);
		return this;
	}

	public AccordionBuilder iconExpanded(DataIcon icon) {
		build_.iconExpanded(icon);
		return this;
	}

	/**
	 * The default icon positioning of collapsible headings can be overridden by
	 * using the data-iconpos attribute, either at the collapsible-set level or
	 * on any of its collapsibles individually.
	 */
	public AccordionBuilder iconPosition(DataIconPosition position) {
		build_.iconPosition(position);
		return this;
	}

	/**
	 * For a more compact version that is useful in tight spaces, add the
	 * data-mini="true" attribute to the set.
	 */
	public AccordionBuilder mini() {
		build_.mini();
		return this;
	}

	/**
	 * For full width collapsibles without corner styling add the
	 * data-inset="false" attribute to the set. This makes the collapsible set
	 * look more like an expandable <code>Listview</code>.
	 */
	public AccordionBuilder noCorners() {
		build_.noCorners();
		return this;
	}

	/**
	 * Add the data-corners="false" attribute to get an inset collapsible set
	 * without rounded corners.
	 */
	public AccordionBuilder suare() {
		build_.square();
		return this;
	}

	public AccordionBuilder theme(Theme theme) {
		build_.theme(theme);
		return this;
	}

	/***************************************************************************
	 * The <code>Accordion</code> that is being built.
	 */
	private final class Build extends BuildSupport implements Accordion {

		private ContentList contents_ = new ContentList();

		private DataContentTheme contentTheme_;

		private DataCorners dataCorners_;

		private DataCollapsedIcon iconCollapsed_;

		private DataIcon iconExpanded_;

		private DataIconPosition iconPosition_;

		private DataInset inset_;

		private DataMini mini_;

		private Theme theme_;

		@Override
		public void addCollapsible(Collapsible collapsible) {
			contents_.add(collapsible);
		}

		@Override
		public Content build() {
			return Html
					.div(this)
					.decorate(DataRole.COLLAPSIBLE_SET, this, theme_,
							contentTheme_, inset_, mini_, iconCollapsed_,
							iconExpanded_, iconPosition_, dataCorners_)
					.add(contents_);
		}

		private void contentTheme(Theme theme) {
			contentTheme_ = new DataContentTheme(theme);
		}

		private void iconCollapsed(DataIcon icon) {
			iconCollapsed_ = new DataCollapsedIcon(icon);
		}

		private void iconExpanded(DataIcon icon) {
			iconExpanded_ = icon;
		}

		private void iconPosition(DataIconPosition position) {
			iconPosition_ = position;
		}

		private void mini() {
			mini_ = DataMini.TRUE;
		}

		private void noCorners() {
			inset_ = DataInset.FALSE;
		}

		private void square() {
			dataCorners_ = DataCorners.SQUARE;
		}

		private void theme(Theme theme) {
			theme_ = theme;
		}
	}
}
