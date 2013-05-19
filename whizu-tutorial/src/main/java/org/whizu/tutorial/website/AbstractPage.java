package org.whizu.tutorial.website;

import org.whizu.annotation.Template;
import org.whizu.ui.Application;
import org.whizu.ui.UI;

@Template("/org/whizu/tutorial/website/template.html")
public class AbstractPage implements Application {

	@Override
	public void init(UI ui) {
	}
}
