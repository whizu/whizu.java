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
import org.whizu.content.Literal;
import org.whizu.html.Html;
import org.whizu.jquery.RequestContext;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;
import org.whizu.util.Objects;
import org.whizu.util.Strings;

/**
 * @author Rudy D'hauwe
 */
public final class PageBuilder extends ProxyBuilder<Page> {

	public static PageBuilder createWithId(String id) {
		return new PageBuilder(id);
	}

	protected static Page findById(String id) {
		return new PageProxy(id);
	}
	
	private Build build_ = new Build();

	private PageBuilder(String id) {
		build_.id(id);
	}

	public PageBuilder add(Content content) {
		build_.add(content);
		return this;
	}

	public PageBuilder add(String content) {
		build_.add(content);
		return this;
	}

	@Override
	public Page build() {
		PageProxy proxy = new PageProxy(build_);
		String ex = "$p = $(\"" + proxy.render() + "\");";
		RequestContext.getRequest().addExpression(ex + " $p.appendTo($.mobile.pageContainer);");
		return proxy;
	}

	public PageBuilder footer(String title) {
		build_.footer(title);
		return this;
	}

	public PageBuilder header(String title) {
		build_.header(title);
		return this;
	}

	public PageBuilder p(String content) {
		build_.p(content);
		return this;
	}

	public PageBuilder p(String pattern, Object... args) {
		build_.p(pattern, args);
		return this;
	}

	/***************************************************************************
	 * The <code>Page</code> that is being built.
	 */
	final class Build extends BuildSupport implements Page {

		private ContentList contents_ = new ContentList();

		private Footer footer_;

		private Header header_;

		@Override
		public void add(Content content) {
			contents_.add(content);
		}

		@Override
		public void add(String content) {
			add(new Literal(content));
		}

		@Override
		public Content build() {
			Content content = Html.div().decorate(DataRole.CONTENT).add(contents_);
			return Html.div(this).decorate(DataRole.PAGE, this).add(header_, content, footer_);
		}

		@Override
		public void footer(String title) {
			footer_ = new Footer(title); // FooterBuilder.create().title(title).build();
		}

		@Override
		public void header(Header header) {
			header_ = header;
		}

		@Override
		public void header(String title) {
			header_ = HeaderBuilder.create().title(title).build();
		}

		@Override
		public void p(String content) {
			add(Html.p(content));
		}

		@Override
		public void p(String pattern, Object... args) {
			Page page = Objects.cast(args[0]);
			String link = "<a href='#" + page.id() + "'>" + page.id() + "</a>";
			String text = Strings.format(pattern, link);
			p(text);
		}
	}
}
