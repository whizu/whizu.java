package org.whizu.jquery.mobile;

/**
 * A listview is coded as a simple unordered list containing linked list items with
 * a data-role="listview" attribute. jQuery Mobile will apply all the necessary
 * styles to transform the list into a mobile-friendly listview with right arrow
 * indicator that fills the full width of the browser window. When you tap on the
 * list item, the framework will trigger a click on the first link inside the list
 * item, issue an Ajax request for the URL in the link, create the new page in the
 * DOM, then kick off a page transition.
 * 
 * 
 * @author Rudy D'hauwe
 */
public class Listview {

	/**
	 * Adds a search filter bar to listviews.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public void setFilter(boolean filter) {
		throw new UnsupportedOperationException();
	}

	/**
	 * A listview can be configured to automatically generate dividers for its
	 * items by adding a data-autodividers="true" attribute to any listview. By
	 * default, the text used to create dividers is the uppercased first letter of
	 * the item's text. Alternatively you can specify divider text by setting the
	 * autodividersSelector option on the listview programmatically. This feature
	 * is designed to work seamlessly with the filter.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public void setAutodividers(boolean autodividers) {
		throw new UnsupportedOperationException();
	}

	/**
	 * The filter reveal feature makes is easy to build a simple autocomplete with
	 * local data. When a filterable list has the filter reveal attribute, it will
	 * auto-hide all the list items when the search field is blank.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public void setFilterReveal(boolean filterReveal) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Adding the inset attribute to the list, applies the inset appearance which
	 * is useful for mixing a listview with other content on a page.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public void setInset(boolean inset) {
		throw new UnsupportedOperationException();
	}
}
