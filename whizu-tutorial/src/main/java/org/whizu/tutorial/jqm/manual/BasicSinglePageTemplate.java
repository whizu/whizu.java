package org.whizu.tutorial.jqm.manual;

import org.whizu.annotation.App;
import org.whizu.annotation.Description;
import org.whizu.annotation.Title;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Page;

@App("/whizu/page/url")
@Title("My page title")
@Description("My page description")
public class BasicSinglePageTemplate implements JQueryMobile {

    @Override
    public void onLoad(Page page) {
        page.header("My Page Title");
        page.p("Page content goes here.");
        page.footer("My Page Footer");
    }
}
