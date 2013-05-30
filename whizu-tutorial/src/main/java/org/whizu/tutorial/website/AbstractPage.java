package org.whizu.tutorial.website;

import org.whizu.annotation.Template;
import org.whizu.dom.Literal;
import org.whizu.html.Html;
import org.whizu.ui.Application;
import org.whizu.ui.WhizuUI;

@Template("/org/whizu/tutorial/website/template.html")
public abstract class AbstractPage implements Application {

	private WhizuUI ui_ = new WhizuUI();
	
	protected void h3(String title) {
		ui_.getDocument().add(Html.h3(title));
	}
	
	protected void p(String paragraph) {
		ui_.getDocument().add(Html.tag("p").add(paragraph));
	}
	
	protected void add(String html) {
		ui_.getDocument().add(new Literal(html));
	}
}
