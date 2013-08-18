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
import org.whizu.content.Identity;
import org.whizu.content.JustInTime;
import org.whizu.html.Html;
import org.whizu.jquery.EventHandler;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.OnItemClickListener;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.mobile.DataIcon;
import org.whizu.jquery.mobile.DataRole;
import org.whizu.jquery.mobile.ListView;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.Popup;
import org.whizu.jquery.mobile.Theme;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;
import org.whizu.util.Callback;
import org.whizu.util.Objects;
import org.whizu.value.ValueList;
import org.whizu.value.ValueObject;

/**
 * A listview is coded as a simple unordered list containing linked list items
 * with a data-role="listview" attribute. jQuery Mobile will apply all the
 * necessary styles to transform the list into a mobile-friendly listview with
 * right arrow indicator that fills the full width of the browser window. When
 * you tap on the list item, the framework will trigger a click on the first
 * link inside the list item, issue an Ajax request for the URL in the link,
 * create the new page in the DOM, then kick off a page transition.
 * 
 * @author Rudy D'hauwe
 */
public class ListViewBuilderNg<T> extends ProxyBuilder<ListView> {

	public static <T extends ListElement> ListViewBuilderNg<T> create() {
		return createWith(new DefaultListControl<T>());
	}

	public static <T> ListViewBuilderNg<T> createWith(ListControl<T> list) {
		return new ListViewBuilderNg<T>(list);
	}

	public static <T extends ValueObject> ListViewBuilderNg<T> createWith(
			ValueList<T> list) {
		ListControl<T> listControl = new DefaultValueListControl<T>(list);
		return createWith(listControl);
	}

	private Build build_ = new Build();

	private ListControl<T> listControl_;

	private ListView proxy_;

	private ListViewBuilderNg(ListControl<T> list) {
		listControl_ = list;
		build_.add(list);
		proxy_ = new ListViewProxyNg<T>(build_);
	}

	@Override
	public ListView build() {
		return buildOnce(proxy_);
	}

	protected ListControl<T> getControl() {
		return listControl_;
	}
	
	public ListViewBuilderNg<T> onSplitButtonClick(
			OnItemClickListener<T> listener) {
		build_.onSplitButtonClick(listener);
		return this;
	}

	public ListViewBuilderNg<T> onSplitButtonClickOpen(Page page) {
		build_.onSplitButtonClickOpen(page);
		return this;
	}

	//
	// public ListViewBuilderNg<T> onItemClick(OnItemClickListener<T>
	// onItemClickListener) {
	// build_.onItemClickListener_ = Objects.cast(onItemClickListener);
	// return this;
	// }

	public ListViewBuilderNg<T> onSplitButtonClickOpen(Popup popup) {
		build_.onSplitButtonClickOpen(popup);
		return this;
	}

	public ListViewBuilderNg<T> ordered() {
		build_.ordered();
		return this;
	}

	public ListViewBuilderNg<T> splitButtonIcon(DataIcon icon) {
		build_.splitButtonIcon(icon);
		return this;
	}

	public ListViewBuilderNg<T> splitButtonTheme(Theme theme) {
		build_.splitButtonTheme(theme);
		return this;
	}

	/***************************************************************************
	 * The <code>ListView</code> that is being built.
	 */
	final class Build extends BuildSupport implements ListView {

		private ContentList contents_ = new ContentList();

		private OnItemClickListener<T> onSplitButtonClickListener_;

		private Identity onSplitButtonClickOpen_;

		private boolean ordered_ = false;

		private DataIcon splitButtonIcon_;

		private Theme splitButtonTheme_;

		private void add(ListControl<T> list) {
			contents_.add(new JustInTime(getContentBuilder(list)));
		}

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

		private void addPropertyChangeListener(final ListControl<T> list) {
			list.addPropertyChangeListener(new PropertyChangeListener() {

				private boolean isAdd(IndexedPropertyChangeEvent changeEvent,
						ListControl<T> list) {
					return ("ADD".equals(changeEvent.getPropertyName()));
					// int index = changeEvent.getIndex();
					// int max = list.size();
					// return (index == max);
				}

				private boolean isDelete(
						IndexedPropertyChangeEvent changeEvent,
						ListControl<T> list) {
					return ("DEL".equals(changeEvent.getPropertyName()));
				}

				private boolean isUpdate(
						IndexedPropertyChangeEvent changeEvent,
						ListControl<T> list) {
					int index = changeEvent.getIndex();
					int max = list.size();
					return (index < max);
				}

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					IndexedPropertyChangeEvent changeEvent = Objects.cast(evt);
					System.out.println("propertychange "
							+ changeEvent.getPropertyName());
					if ("ADD-EVENT".equals(changeEvent.getPropertyName())) {
						listControl_.handleAddEvent();
					} else if (isAdd(changeEvent, list)) {
						System.out.println("propertychange ADD");
						T vo = Objects.cast(changeEvent.getNewValue());
						Content itemContent = listControl_.build(vo);
						if (splitButtonIcon_ != null) {
							ContentList cl = new ContentList();
							cl.add(itemContent);
							cl.add(getSplitButtonLink());
							proxy_.addItem(cl);
						} else {
							proxy_.addItem(itemContent);
						}
					} else if (isDelete(changeEvent, list)) {
						int index = changeEvent.getIndex();
						System.out.println("propertychange DELETE of INDEX "
								+ index);
						proxy_.removeItem(index);
					} else if (isUpdate(changeEvent, list)) {
						int index = changeEvent.getIndex();
						System.out.println("propertychange UPDATE");
						T vo = Objects.cast(evt.getNewValue());
						Content itemContent = listControl_.build(vo);
						proxy_.replaceItem(index, itemContent);
					} else {
						throw new UnsupportedOperationException();
					}
				}
			});
		}

		@Override
		public Content build() {
			Element element = ordered_ ? Html.ol(this) : Html.ul(this);
			element.decorate(DataRole.LISTVIEW, this).add(contents_);
			addPropertyChangeListener(listControl_);
//			final JQuery selector = jQuery(this).find(
//					"li a[data-role=splitbutton]"); // find("li").
//			final JQuery itemSelector_ = jQuery(this).find(
//					"li a[data-role=list-anchor]"); // find("li").
			final String itemSelector = "#" + id()
					+ " li a[data-role=splitbutton]";
			final String itemSelector2 = "#" + id()
					+ " li a[data-role=list-anchor]"; // find("li").
			// "$('#" + Build.this.id() +
			// "').find('li a[datarole=splitbutton]')";
			if (listControl_.isClickable()) {

				EventHandler eh = new EventHandler() {
					private String id_ = session().next();

					@Override
					public void handleEvent() {
						// JavaScript.preventDefault();
						System.out.println("handling event click on item ");

						// String script = "alert('hello ' + " +
						// selector.toJavaScript() + ".length);";
						// System.out.println(script);
						// JavaScript.script(script);
						int index = Integer.parseInt(RequestContext
								.getRequest().getParameter("data-index"));
						System.out.println("data-index " + index);
						String dataId = RequestContext.getRequest()
								.getParameter("data-id");
						System.out.println("data-id " + dataId);
						final T obj = listControl_.get(index);
						listControl_.handleClickEvent(obj);
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
				// jQuery("$(document)").on("click", "#" + id() + " li a", eh);
				jQuery("$(document)").on("click", itemSelector2, eh);
				// itemSelector.on("click", eh);
			}

			if (splitButtonIcon_ != null) {
				element.attr("data-split-icon", splitButtonIcon_.value());
				if (splitButtonTheme_ != null) {
					element.attr("data-split-theme", splitButtonTheme_.value());
				}

				jQuery(this).find("li").append(getSplitButtonLink());

				if (onSplitButtonClickListener_ != null) {
					System.out.println("adding split button click listener");
					EventHandler splitButtonEventHandler = new EventHandler() {
						private String id_ = session().next();

						@Override
						public void handleEvent() {
							System.out
									.println("handling split button event click on item");
							int index = Integer.parseInt(RequestContext
									.getRequest().getParameter("data-index"));
							System.out.println("data-index " + index);
							String dataId = RequestContext.getRequest()
									.getParameter("data-id");
							System.out.println("data-id " + dataId);
							final T obj = listControl_.get(index);
							onSplitButtonClickListener_.click(obj,
									new Callback() {

										@Override
										public void success() {
											// TODO Auto-generated catch block
											throw new UnsupportedOperationException();
										}
									});
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

					session().addClickListener(splitButtonEventHandler);
					// System.out.println("onclick split selector " +
					// selector.toJavaScript());
					// selector.on("click", splitButtonEventHandler);
					jQuery("$(document)").on("click", itemSelector,
							splitButtonEventHandler);
				}
			}

			return element;
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
					return Html.a().href("#").attr("data-role", "list-anchor")
							.attr("data-id", listControl_.id(item))
							.add(content);
				}
			};
		}

		private Content getSplitButtonLink() {
			Element a = Html.a().attr("data-role", "splitbutton");
			if (onSplitButtonClickOpen_ != null) {
				a.href("#" + onSplitButtonClickOpen_.id());
			} else {
				a.href("#");
			}
			return a;
		}

		@Override
		public void on(Page page) {
			throw new UnsupportedOperationException();
		}

		private void onSplitButtonClick(OnItemClickListener<T> listener) {
			onSplitButtonClickListener_ = listener;
		}

		private void onSplitButtonClickOpen(Identity identity) {
			onSplitButtonClickOpen_ = identity;
		}

		public void ordered() {
			ordered_ = true;
		}

		@Override
		public void removeItem(int index) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void replaceItem(int index, Content item) {
			throw new UnsupportedOperationException();
		}

		private void splitButtonIcon(DataIcon icon) {
			splitButtonIcon_ = icon;
		}

		private void splitButtonTheme(Theme theme) {
			splitButtonTheme_ = theme;
		}
	}
}
