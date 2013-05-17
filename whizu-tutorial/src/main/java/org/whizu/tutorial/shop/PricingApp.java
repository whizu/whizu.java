package org.whizu.tutorial.shop;

import org.whizu.annotation.Autowire;
import org.whizu.annotation.Page;
import org.whizu.annotation.Template;
import org.whizu.annotation.Title;
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
@Page("/whizu/shop")
@Template("/org/whizu/tutorial/shop/page.html")
@Title("My Shop")
public class PricingApp implements Application { //TODO remove "implements Application"

	//@Select(id="menu")
	@Autowire
	JQuery menu;
	
	@Select(id="right-column")
	JQuery contentPanel;
	
	//@OnLoad
	//@Setup
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
				Dialog dialog = new DialogImpl("My dialog");
				dialog.add(ui.createLabel("my dialog text"));
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
