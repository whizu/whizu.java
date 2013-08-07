package org.whizu.tutorial.tournament;

import org.whizu.jquery.ClickListener;
import org.whizu.jquery.OnItemClickListener;
import org.whizu.jquery.mobile.Button;
import org.whizu.jquery.mobile.ButtonBuilder;
import org.whizu.jquery.mobile.Form;
import org.whizu.jquery.mobile.FormBuilder;
import org.whizu.jquery.mobile.Popup;
import org.whizu.jquery.mobile.PopupBuilder;
import org.whizu.util.Callback;
import org.whizu.util.Objects;
import org.whizu.value.ValueList;
import org.whizu.value.ValueObject;

public abstract class FormItemActionHelper<T extends ValueObject> implements OnItemClickListener<T>, ClickListener {

	private Callback callback_;

	private int i = 0;

	private T model_;

	private Popup popup;

	private ValueList<T> list_;

	public FormItemActionHelper() {	
	}
	
	public FormItemActionHelper(ValueList<T> list) {
		list_ = list;
	}

	@Override
	public void click() {
		list_.add(this).click();
	}

	@Override
	public final void click(T model, Callback callback) {
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
		FormItemActionHelper<T> helper = this;
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
