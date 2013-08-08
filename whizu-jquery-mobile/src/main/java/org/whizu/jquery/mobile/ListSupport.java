package org.whizu.jquery.mobile;

import org.whizu.jquery.ClickListener;
import org.whizu.jquery.OnItemClickListener;
import org.whizu.util.Callback;
import org.whizu.util.Objects;
import org.whizu.value.ValueList;
import org.whizu.value.ValueObject;

/**
 * @author Rudy D'hauwe
 */
@Deprecated
public abstract class ListSupport<T extends ValueObject> implements OnItemClickListener<T>, ClickListener {

	private Callback callback_;

	private static int i = 0;

	private T model_;

	private Popup popup;

	private ValueList<T> list_;

	//for update action
	public ListSupport() {	
	}
	
	//for create action
	public ListSupport(ValueList<T> list) {
		list_ = list;
	}

	@Deprecated
	@Override
	public void click() { //add
		list_.add(this).click();
	}

	@Override
	public final void click(T model, Callback callback) { //update, add
		System.out.println("item clicked!!!!!!!!!!!!!");
		model_ = model; // model_.refresh(model);
		callback_ = callback;
		Form form = createForm();
		openPopup(form);
	}

	private Form createForm() {
		FormBuilder builder = FormBuilder.create();
		createForm(builder);
		Button submit = ButtonBuilder.createWithTitle("OK").build();
		builder.addButton(submit);
		ListSupport<T> helper = this;
		builder.onSubmit(Objects.call(helper, "updateModel"));
		return builder.build();
	}

	protected abstract void createForm(FormBuilder builder);

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

	public void updateModel() {
		System.out.println("updating " + model_.getColumns()[0]);
		if (validate(model_)) {
			callback_.success();
			popup.close();
		}
	}

	protected abstract boolean validate(T update);
}
