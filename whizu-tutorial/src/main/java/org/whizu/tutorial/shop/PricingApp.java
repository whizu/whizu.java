package org.whizu.tutorial.shop;

import org.whizu.annotation.App;
import org.whizu.annotation.Template;
import org.whizu.annotation.Title;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Select;
import org.whizu.tutorial.panel.Action;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/shop")
@Template("/org/whizu/tutorial/shop/page.html")
@Title("My Shop")
public class PricingApp { // TODO remove
													// "implements Application"

	// @Select(id="menu")
	JQuery menu;

	@Select(id = "right-column")
	JQuery contentPanel;

	// @OnLoad
	// @Setup
		public void init() {
		add(new OfficeSearchAction());
		add(new ProductSearchAction());
		add(new CustomerSearchAction());
		add(new CountrySearchAction());
		add(new PriceSearchAction());
		/* uncomment
		add(new AbstractAction() {

			@Override
			public void handleEvent() {
				Dialog dialog = new DialogImpl("My dialog", "My description");
				dialog.add(
						new LabelImpl("My dialog text lmsd qsdkjlh qkljdfh qlkflkqsdhklqsdh kql s dh fl kqsdhf lkjd hlqsd kjlqsd ldkqsh klsdqhlkqsdh klqsdjh klqsdh kqsldhf klsqdhf lkqsdh fqklsdjhf qsd klqshdf."))
						.width("400px");
				new DocumentImpl().add(dialog);

			}

			@Override
			public void performAction() {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException();
			}

			@Override
			public String getCaption() {
				return "Open dialog";
			}
		});
		*/
	}

	private void add(Action action) {
		menu = RequestContext.getRequest().select("$(\"#menu\")");
		// uncomment menu.append(new Hyperlink(action));
	}
}
