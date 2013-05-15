package org.whizu.tutorial.shop.panel;

import java.util.Collection;

import org.whizu.panel.SearchPanel;
import org.whizu.tutorial.shop.action.OfficeUpdateAction;
import org.whizu.tutorial.shop.dao.OfficeDAO;
import org.whizu.tutorial.shop.model.Office;
import org.whizu.ui.Action;
import org.whizu.value.Value;

public class OfficeSearchPanel extends SearchPanel<Office> {

	public OfficeSearchPanel() {
		super(Office.class);
	}

	@Override
	public String getCaption() {
		return "Office Search";
	}

	@Override
	protected Action getCreateAction() {
		return new OfficeUpdateAction(new Office());
	}

	@Override
	protected Value[] getColumns(Office model) {
		return new Value[] { model.naam, model.address };
	}

	@Override
	protected Action getUpdateAction(Office model) {
		return new OfficeUpdateAction(model);
	}

	@Override
	protected Collection<Office> performSearch() {
		return OfficeDAO.INSTANCE.findAll();
	}
}
