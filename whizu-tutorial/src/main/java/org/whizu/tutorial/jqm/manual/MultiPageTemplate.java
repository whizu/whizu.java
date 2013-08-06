package org.whizu.tutorial.jqm.manual;

import org.whizu.annotation.App;
import org.whizu.annotation.Description;
import org.whizu.annotation.Title;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.PageBuilder;

@App("/whizu/multipage")
@Title("My page title")
@Description("My page description")
public class MultiPageTemplate implements JQueryMobile {

	@Override
	public void onLoad(Page foo) {
		Page bar = createBar(foo);
		foo.header("Foo");
		foo.p("I'm first in the source order so I'm shown as the page.");
		//foo.p("View internal page called {}", bar);
		foo.footer("My Page Footer");
	}

	private Page createBar(Page foo) {
		// @formatter:off
		//Document document = foo.document();
		Page bar = PageBuilder
				.createWithId("bar")
				.header("Bar")
				.p("I'm the second in the source order so I'm hidden when the page loads. I'm just shown if a link that references my id is being clicked.")
		//		.p(foo)
				.footer("Page footer")
				.build();
		//document.add(bar);
		return bar;
		// @formatter:on
	}
}
