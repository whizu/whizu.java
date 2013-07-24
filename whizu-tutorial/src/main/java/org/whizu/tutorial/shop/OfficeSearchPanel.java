package org.whizu.tutorial.shop;

import java.util.Collection;

import org.whizu.tutorial.panel.Action;
import org.whizu.tutorial.panel.SearchPanel;
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
