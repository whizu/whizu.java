package org.whizu.value;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.whizu.annotations.Instance;
import org.whizu.dom.Component;
import org.whizu.layout.VerticalLayout;
import org.whizu.ui.LabelImpl;

//@Service("ValueRenderer")
@Instance("ValueRenderer")
public class ValueRendererImpl implements ValueRenderer {

	// @Autowired
	// private JQuery jQuery = new JQueryImpl();

	@Override
	public Component render(DateValue value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component render(IntegerValue value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component render(StringValue value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ValueObject> Component render(final ValueList<T> value) {
		final VerticalLayout view = new VerticalLayout();
		for (int i = 0; i < value.size(); i++) {
			VerticalLayout elementView = new VerticalLayout();
			elementView.add(new LabelImpl("" + value.get(i).getColumns()[1]));
			view.add(elementView);
		}

		value.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				VerticalLayout elementView = new VerticalLayout();
				elementView.add(new LabelImpl("" + value.get(value.size() - 1).getColumns()[1]));
				view.add(elementView);
			}
		});

		return view;
	}

	@Override
	public <T extends ValueObject> Component render(ValueTable<T> value) {
		// TODO Auto-generated method stub
		return null;
	}
}
