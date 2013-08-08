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
package org.whizu.jquery.mobile.list;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.whizu.content.Content;
import org.whizu.content.ContentBuilder;
import org.whizu.content.ContentList;
import org.whizu.content.Element;
import org.whizu.content.JustInTime;
import org.whizu.html.Html;
import org.whizu.jquery.EventHandler;
import org.whizu.jquery.OnItemClickListener;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.mobile.DataRole;
import org.whizu.jquery.mobile.ListView;
import org.whizu.jquery.mobile.Page;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;
import org.whizu.util.Objects;

/**
 * A listview is coded as a simple unordered list containing linked list items
 * with a data-role="listview" attribute. jQuery Mobile will apply all the
 * necessary styles to transform the list into a mobile-friendly listview with
 * right arrow indicator that fills the full width of the browser window. When
 * you tap on the list item, the framework will trigger a click on the first
 * link inside the list item, issue an Ajax request for the URL in the link,
 * create the new page in the DOM, then kick off a page transition.
 * 
 * 
 * @author Rudy D'hauwe
 */
public class ListViewBuilderNg<T> extends ProxyBuilder<ListView, ListViewBuilderNg<T>.Build> {

	/***************************************************************************
	 * The <code>ListView</code> that is being built.
	 */
	final class Build extends BuildSupport implements ListView {

		private ContentList contents_ = new ContentList();

		private OnItemClickListener<Object> onItemClickListener_;

		/**
		 * Add a custom content item to the list, behave differently that items
		 * in the ListSource. They are not managed by the ListSource.
		 */
		@Override
		public void addItem(Content item) {
			contents_.add(Html.li().add(item));
		}

		/**
		 * Add a custom content item to the list, behave differently that items
		 * in the ListSource. They are not managed by the ListSource.
		 */
		@Override
		public void addItem(ContentBuilder builder) {
			Content item = new JustInTime(builder);
			addItem(item);
		}

		@Override
		public Content build() {
			Element element = Html.ul(this).decorate(DataRole.LISTVIEW, this).add(contents_);
			addPropertyChangeListener(list_);
			if (list_.isClickable()) {
				EventHandler eh = new EventHandler() {
					private String id_ = session().next();

					@Override
					public void handleEvent() {
						System.out.println("handling event click on item");
						int index = Integer.parseInt(RequestContext.getRequest().getParameter("data-index"));
						System.out.println("data-index " + index);
						String dataId = RequestContext.getRequest().getParameter("data-id");
						System.out.println("data-id " + dataId);
						final T obj = list_.get(index - 1);
						list_.handleClickEvent(obj);
						// onItemClickListener_.click(obj, new Callback() {
						//
						// @Override
						// public void success() {
						// list_.update(obj);
						// }
						// });
					}

					@Override
					public String id() {
						return id_;
					}
				};
				session().addClickListener(eh);
				jQuery("$(document)").on("click", "#" + id() + " li a", eh);
			}

			return element;
		}

		private void addPropertyChangeListener(final ListControl<T> list) {
			list.addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					IndexedPropertyChangeEvent changeEvent = Objects.cast(evt);
					int index = changeEvent.getIndex();
					System.out.println("propertychange " + changeEvent.getPropertyName());
					if (isAdd(changeEvent, list)) {
						System.out.println("propertychange ADD");
						T vo = Objects.cast(changeEvent.getNewValue());
						Content itemContent = list_.build(vo);
						proxy_.addItem(itemContent);
					} else if (isUpdate(changeEvent, list)) {
						System.out.println("propertychange UPDATE");
						T vo = Objects.cast(evt.getNewValue());
						Content itemContent = list_.build(vo);
						proxy_.replaceItem(index, itemContent);
					} else if (isDelete(changeEvent, list)) {
						throw new UnsupportedOperationException();
					} else {
						throw new UnsupportedOperationException();
					}
				}

				private boolean isDelete(IndexedPropertyChangeEvent changeEvent, ListControl<T> list) {
					throw new UnsupportedOperationException();
				}

				private boolean isUpdate(IndexedPropertyChangeEvent changeEvent, ListControl<T> list) {
					int index = changeEvent.getIndex();
					int max = list.size();
					return (index < max);
				}

				private boolean isAdd(IndexedPropertyChangeEvent changeEvent, ListControl<T> list) {
					return ("ADD".equals(changeEvent.getPropertyName()));
					// int index = changeEvent.getIndex();
					// int max = list.size();
					// return (index == max);
				}
			});
		}

		@Override
		public void on(Page page) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void replaceItem(int index, Content item) {
			throw new UnsupportedOperationException();
		}

		private void add(ListControl<T> list) {
			contents_.add(new JustInTime(getContentBuilder(list)));
		}

		private ContentBuilder getContentBuilder(final ListControl<T> list) {
			return new ContentBuilder() {

				@Override
				public Content build() {
					ContentList itemsList = new ContentList();
					for (T item : list) {
						Content itemView = list.build(item);
						// Content itemView = item.build();
						if (list.isClickable(item)) {
							itemView = link(item, itemView);
						}
						itemsList.add(Html.li().add(itemView));
					}
					return itemsList;
				}

				private Content link(T item, Content content) {
					return Html.a().href("#").attr("data-id", list_.id(item)).add(content);
				}
			};
		}
	}

	public static <T extends ListElement> ListViewBuilderNg<T> create() {
		return createWith(new DefaultListControl<T>());
	}

	public static <T> ListViewBuilderNg<T> createWith(ListControl<T> list) {
		return new ListViewBuilderNg<T>(list);
	}

	private ListControl<T> list_;

	private ListView proxy_;

	private ListViewBuilderNg(ListControl<T> list) {
		list_ = list;
		build_.add(list);
		proxy_ = new ListViewProxyNg<T>(build_);
	}

	@Override
	protected Build createPrototype() {
		return new Build();
	}

	@Override
	protected ListView createProxy(ListView build) {
		return proxy_;
	}

	public ListViewBuilderNg<T> onItemClick(OnItemClickListener<T> onItemClickListener) {
		build_.onItemClickListener_ = Objects.cast(onItemClickListener);
		return this;
	}
}
