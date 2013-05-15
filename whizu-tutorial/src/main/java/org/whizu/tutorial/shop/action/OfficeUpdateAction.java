package org.whizu.tutorial.shop.action;

import org.whizu.tutorial.shop.dao.OfficeDAO;
import org.whizu.tutorial.shop.model.Office;
import org.whizu.value.Value;


public class OfficeUpdateAction extends UpdateAction<Office> {

	public OfficeUpdateAction(Office agentschap) {
		super(agentschap);
	}

	@Override
	public String getCaption() {
		return "Office";
	}

	@Override
	protected String[] getFields() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Value[] getColumns(Office model) {
		return new Value[] { model.naam, model.address };
	}
	
	@Override
	protected Office performUpdate() {
		return OfficeDAO.INSTANCE.update(model);
	}
}
