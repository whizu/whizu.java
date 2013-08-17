package org.whizu.jquery.mobile.list;

import java.beans.PropertyChangeListener;
import java.util.Iterator;

import org.whizu.content.Content;
import org.whizu.jquery.ClickListener;
import org.whizu.jquery.mobile.Button;
import org.whizu.jquery.mobile.ButtonBuilder;
import org.whizu.jquery.mobile.Form;
import org.whizu.jquery.mobile.FormBuilder;
import org.whizu.jquery.mobile.Popup;
import org.whizu.jquery.mobile.PopupBuilder;
import org.whizu.util.Callback;
import org.whizu.value.Value;
import org.whizu.value.ValueList;
import org.whizu.value.ValueObject;

//= new ValueList<T>().getListControl() ?
public class DefaultValueListControl<T extends ValueObject> implements ListControl<T> {

	private Callback callback_;

	int i = 0;

	private ValueList<T> list_;

	private T model_;

	private Popup popup;

	public DefaultValueListControl(ValueList<T> list) {
		list_ = list;
	}

	public void add(T element) {
		list_.add(element);
		// getPropertyChangeSupport().fireIndexedPropertyChange("ADD",
		// list_.size()-1, null, element);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		list_.addPropertyChangeListener(listener);
	}

	@Override
	public Content build(T item) {
		return item.build();
	}

	private Form createForm() {
		FormBuilder builder = FormBuilder.create();
		createForm(builder);
		Button submit = ButtonBuilder.createWithTitle("OK").build();
		builder.addButton(submit);
		//builder.onSubmit(Objects.call(this, "ifValidatePerformCallback"));
		builder.onSubmit(new ClickListener() {
			
			@Override
			public void click() {
				ifValidatePerformCallback();
			}
		});
		return builder.build();
	}

	// @Override
	protected void createForm(FormBuilder builder) {
		T vo = model_;
		for (Value value : vo.getColumns()) {
			builder.addField(value);
		}
	}

	@Override
	public T get(int index) {
		return list_.get(index);
	}

	@Override
	public ClickListener addEvent() {
		return new ClickListener() {
			
			@Override
			public void click() {
				handleAddEvent();
			}
		};
	}

	@Override
	public void handleAddEvent() {
		System.out.println("now handle addEvent");
		model_ = list_.createNew();
		callback_ = new Callback() {

			@Override
			public void success() {
				list_.add(model_);
			}
		};
		Form form = createForm();
		openPopup(form);
	}

	@Override
	public void handleClickEvent(T element) {
		if (!isClickable(element)) {
			throw new IllegalStateException();
		}

		System.out.println("now handle clickEvent for " + element.getColumns()[0]);
		model_ = element;
		callback_ = new Callback() {

			@Override
			public void success() {
				list_.update(model_);
			}
		};
		Form form = createForm();
		openPopup(form);
	}

	@Override
	public String id(T item) {
		return "generated-id" + (i++); // item.id();
	}

	@Override
	public boolean isClickable() {
		return true;
	}

	@Override
	public boolean isClickable(T element) {
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return list_.iterator();
	}

	private void openPopup(Form form) {
		// @formatter:off
		popup = PopupBuilder.createWithId(getClass().getName().replace('.',  '_')+(i++))
				.padding("10px")
				.add(form)
				.build();
		// @formatter:on
		popup.open();
	}

	@Override
	public int size() {
		return list_.size();
	}

	public void ifValidatePerformCallback() {
		System.out.println("updating " + model_.getColumns()[0]);
		if (validate(model_)) {
			callback_.success();
			popup.close();
		}
	}

	// @Override
	protected boolean validate(T model) {
		return model.validate();
	}
}
