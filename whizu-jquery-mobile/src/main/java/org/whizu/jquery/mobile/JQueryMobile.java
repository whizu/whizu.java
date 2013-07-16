package org.whizu.jquery.mobile;

public class JQueryMobile {

	public static Footer createFooter() {
		return new Footer();
	}
	
	public static Footer createFooter(String title) {
		return new Footer(title);
	}

	public static Header createHeader() {
		return new Header();
	}
	
	public static Page createPage() {
		return new Page();
	}

	public static Page CreatePage(String id) {
		return new Page(id);
	}
}
