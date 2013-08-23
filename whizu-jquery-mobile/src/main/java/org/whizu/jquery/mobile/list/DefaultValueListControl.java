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
import org.whizu.util.ListChangeListener;
import org.whizu.value.Value;
import org.whizu.value.ValueList;
import org.whizu.value.ValueObject;

//= new ValueList<T>().getListControl() ?
public class DefaultValueListControl<T extends ValueObject<T>> implements
		ListControl<T> {

	private Callback callback_;

	int i = 0;

	private ValueList<T> list_;

	private T model_;

	private Popup popup;

	public DefaultValueListControl(ValueList<T> list) {
		list_ = list;
	}

	public final void add(T element) {
		list_.add(element);
		// getPropertyChangeSupport().fireIndexedPropertyChange("ADD",
		// list_.size()-1, null, element);
	}

	@Override
	public final void addChangeListener(ListChangeListener<T> listener) {
		list_.addChangeListener(listener);
	}

	@Override
	public final ClickListener addEvent() {
		return new ClickListener() {

			@Override
			public void click() {
				handleAddEvent();
			}
		};
	}

	@Override
	@Deprecated
	public final void addPropertyChangeListener(PropertyChangeListener listener) {
		list_.addPropertyChangeListener(listener);
	}

	/**
	 * Override this method to build the HTML view for an item in the list.
	 * 
	 * @return the generated HTML content
	 */
	@Override
	public Content build(T item) {
		return item.build();
	}

	private Form createForm() {
		FormBuilder builder = FormBuilder.create();
		createForm(builder);
		Button submit = ButtonBuilder.createWithTitle("OK").build();
		builder.addButton(submit);
		// builder.onSubmit(Objects.call(this, "ifValidatePerformCallback"));
		builder.onSubmit(new ClickListener() {

			@Override
			public void click() {
				ifValidatePerformCallback();
			}
		});
		return builder.build();
	}

	/**
	 * Override this method to create the add and update form.
	 */
	protected void createForm(FormBuilder builder) {
		T vo = model_;
		for (Value<?> value : vo.getColumns()) {
			builder.addField(value);
		}
	}

	@Override
	public final T get(int index) {
		return list_.get(index);
	}

	@Override
	public final void handleAddEvent() {
		model_ = list_.createNew();
		callback_ = new Callback() {

			@Override
			public void success() {
				try {
					T addedObject = performAdd(model_);
					model_.refresh(addedObject);
					list_.add(model_);
				} catch (ValidationException e) {
					e.printStackTrace();
				}
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

		model_ = element;
		callback_ = new Callback() {

			@Override
			public void success() {
				try {
					T updatedObject = performUpdate(model_);
					model_.refresh(updatedObject);
					list_.update(model_);
				} catch (ValidationException e) {
					e.printStackTrace();
				}
			}
		};
		Form form = createForm();
		openPopup(form);
	}

	@Override
	public String id(T item) {
		return "generated-id" + (i++); // item.id();
	}

	public void ifValidatePerformCallback() {
		if (validate(model_)) {
			callback_.success();
			popup.close();
		}
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
	public final Iterator<T> iterator() {
		return list_.iterator();
	}

	protected final T model() {
		return model_;
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

	/**
	 * Override this method to add the object to the database.
	 * 
	 * @return the added object
	 */
	protected T performAdd(T model) {
		return model;
	}

	/**
	 * Override this method to update the object to the database.
	 * 
	 * @return the updated object
	 */
	protected T performUpdate(T model) {
		return model;
	}
	
	@Override
	public final int size() {
		return list_.size();
	}

	/**
	 * Override this method to validate the form input.

	 * @return true if the form input is ready for server side processing
	 */
	protected boolean validate(T model) {
		return model.validate();
	}
}
