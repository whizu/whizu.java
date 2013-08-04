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
import org.whizu.content.Element;
import org.whizu.html.Html;
import org.whizu.widget.Widget;

/**
 * The page is the primary unit of interaction in jQuery Mobile and is used to
 * group content into logical views that can be animated in and out of view with
 * page transitions. A HTML document may start with a single Page and the AJAX
 * navigation system will load additional pages on demand into the DOM as users
 * navigate around. Alternatively, a HTML document can be built with multiple Pages
 * inside it and the framework will transition between these local views with no
 * need to request content from the server.
 * 
 * @author Rudy D'hauwe
 */
public class PageBuilder extends Widget implements Page {

	private Element content_;

	private Footer footer_;

	private Header header_;

	public PageBuilder() {
		super();
	}

	public PageBuilder(String id) {
		super(id);
	}

	@Override
	public void add(Content content) {
		content_.add(content);
	}

	@Override
	public void append(String text) {
		content_.add(text);
	}

	@Override
	public Content build() {
		content_ = Html.div().decorate(DataRole.CONTENT);
		Element element = Html.div(this).decorate(DataRole.PAGE, this).add(header_, content_, footer_);
		return element;
	}

	public Footer footer() {
		if (footer_ == null) {
			footer_ = new Footer();
		}

		return footer_;
	}

	public PageBuilder footer(Footer footer) {
		footer_ = footer;
		return this;
	}

	public Footer footer(String title) {
		footer(new Footer(title));
		return footer_;
	}

	public Header header() {
		if (header_ == null) {
			header_ = new Header();
		}
		return header_;
	}

	public Page header(Header header) {
		header_ = header;
		return this;
	}

	public Header header(String title) {
		header_ = new Header(title);
		if (isRendered()) {
			jQuery("$.mobile.activePage").append(header_);
		}
		return header_;
	}

	public Element p(String text) {
		Element p = Html.p(text);
		content_.add(Html.p(text));
		return p;
	}
}
