package org.whizu.tutorial.website;

import org.whizu.annotation.Page;
import org.whizu.html.Description;
import org.whizu.html.Title;
import org.whizu.ui.UI;

@Page("/whizu/website/about")
@Title("About us - Whizu")
@Description("About us at Whizu")
public class About extends AbstractPage {

	@Override
	public void init(UI ui) {
		h3("About");

		p("Welcome at the About page.");

		p("Whizu is open source software available for <a href='/download'>download</a>. and for free "
				+ "use under the terms of the European Union Public License (EUPL) v1.1. "
				+ "Any use of this software, other than as authorized under this license " + "is strictly prohibited.");
	}

	/**
	 * Whizu is open source software available for download. and for free use
	 * under the terms of the European Union Public License (EUPL) v1.1. Any use
	 * of this software, other than as authorized under this license is strictly
	 * prohibited.
	 */
	//@Multiline
	//public static final P p1;
}
