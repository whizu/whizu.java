package org.whizu.tutorial.shop;

import org.whizu.annotation.Listen;
import org.whizu.annotation.Template;
import org.whizu.html.Title;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.jquery.Select;
import org.whizu.tutorial.shop.action.CountrySearchAction;
import org.whizu.tutorial.shop.action.CustomerSearchAction;
import org.whizu.tutorial.shop.action.OfficeSearchAction;
import org.whizu.tutorial.shop.action.PriceSearchAction;
import org.whizu.tutorial.shop.action.ProductSearchAction;
import org.whizu.ui.AbstractAction;
import org.whizu.ui.Action;
import org.whizu.ui.Application;
import org.whizu.ui.Dialog;
import org.whizu.ui.DialogImpl;
import org.whizu.ui.Hyperlink;
import org.whizu.ui.UI;

/**
 * @author Rudy D'hauwe
 */
@Listen("/whizu/shop")
@Template("/org/whizu/tutorial/shop/page.html")
@Title("My Shop")
public class PricingApp implements Application { // TODO remove
													// "implements Application"

	// @Select(id="menu")
	JQuery menu;

	@Select(id = "right-column")
	JQuery contentPanel;

	// @OnLoad
	// @Setup
	@Override
	public void init(UI ui) {
		add(new OfficeSearchAction());
		add(new ProductSearchAction());
		add(new CustomerSearchAction());
		add(new CountrySearchAction());
		add(new PriceSearchAction());
		add(new AbstractAction() {

			@Override
			public void handleEvent() {
				Dialog dialog = new DialogImpl("My dialog", "My description");
				dialog.add(
						ui.createLabel("My dialog text lmsd qsdkjlh qkljdfh qlkflkqsdhklqsdh kql s dh fl kqsdhf lkjd hlqsd kjlqsd ldkqsh klsdqhlkqsdh klqsdjh klqsdh kqsldhf klsqdhf lkqsdh fqklsdjhf qsd klqshdf."))
						.width("400px");
				ui.getDocument().add(dialog);

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
	}

	private void add(Action action) {
		menu = RequestContext.getRequest().select("$(\"#menu\")");
		menu.append(new Hyperlink(action));
	}
}
