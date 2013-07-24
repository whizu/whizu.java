package org.whizu.tutorial.website;

import org.whizu.annotation.Template;
import org.whizu.dom.Literal;
import org.whizu.html.Html;
import org.whizu.ui.Document;
import org.whizu.ui.DocumentImpl;


@Template("/org/whizu/tutorial/website/template.html")
public abstract class AbstractPage {
	
	private Document doc = new DocumentImpl();
	
	protected void h3(String title) {
		doc.add(Html.h3(title));
	}
	
	protected void p(String paragraph) {
		doc.add(Html.tag("p").add(paragraph));
	}
	
	protected void add(String html) {
		doc.add(new Literal(html));
	}
}
