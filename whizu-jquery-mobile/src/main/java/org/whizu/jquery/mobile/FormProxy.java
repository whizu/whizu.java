/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the "EUPL") version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an "AS IS" basis and 
 * without warranties of any kind concerning the Software, including 
 * without limitation merchantability, fitness for a particular purpose, 
 * absence of defects or errors, accuracy, and non-infringement of 
 * intellectual property rights other than copyright. This disclaimer 
 * of warranty is an essential part of the License and a condition for 
 * the grant of any rights to this Software.
 *   
 * For more  details, see <http://joinup.ec.europa.eu/software/page/eupl>.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.jquery.mobile;

import org.whizu.content.Component;
import org.whizu.content.Content;
import org.whizu.jquery.ClickListener;
import org.whizu.proxy.Proxy;
import org.whizu.proxy.ProxySupport;
import org.whizu.value.DateValue;
import org.whizu.value.PasswordValue;
import org.whizu.value.StringValue;
import org.whizu.value.Value;

/**
 * @author Rudy D'hauwe
 */
final class FormProxy extends Proxy<Form> implements Form {

	FormProxy(Form impl) {
		super(impl);
	}

	@Override
	public void add(Content field) {
		impl().add(field);
	}

	@Override
	public void add(PasswordValue value) {
		impl().add(value);
	}

	@Override
	public void addButton(Button submit) {
		impl().addButton(submit);
	}

	@Override
	public Button addButton(String title) {
		return impl().addButton(title);
	}

	@Override
	public void addDate(DateValue date) {
		impl().addDate(date);
	}

	@Override
	public void addField(Value value) {
		impl().addField(value);
	}

	@Override
	public void addFieldContain(Component component) {
		impl().addFieldContain(component);
	}

	@Override
	public void addFlipSwitch() {
		impl().addFlipSwitch();
	}

	@Override
	public void addListView() {
		impl().addListView();
	}

	@Override
	public void addSlider(int min, int max) {
		impl().addSlider(min, max);
	}

	@Override
	public void addSlider(int min, int max, Theme theme) {
		impl().addSlider(min, max, theme);
	}

	@Override
	public void addText(String label) {
		impl().addText(label);
	}

	@Override
	public void addText(StringValue value) {
		impl().addText(value);
	}

	@Override
	public void addText(StringValue value, boolean fieldContain) {
		impl().addText(value, fieldContain);
	}

	@Override
	public void addTextarea(String label) {
		impl().addTextarea(label);
	}

	@Override
	public void addTextarea(StringValue value) {
		impl().addTextarea(value);
	}

	@Override
	public void addTextarea(StringValue value, boolean fieldContain) {
		impl().addTextarea(value, fieldContain);
	}

	@Override
	public void clear() {
		impl().clear();
	}

	@Override
	protected Form createImpl() {
		return new FormImpl(id());
	}

	@Override
	public String id() {
		return impl().id();
	}

	@Override
	public void onSubmit(ClickListener handler) {
		impl().onSubmit(handler);
	}

	/***************************************************************************
     * The target <code>Form</code> that has been rendered.
	 */
	class FormImpl extends ProxySupport implements Form {

		private FormImpl(String id) {
			super(id);
		}

		@Override
		public void add(Content content) {
			jQuery(this).append(content);
		}

		@Override
		public void add(PasswordValue value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addButton(Button submit) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Button addButton(String title) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addDate(DateValue date) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addField(Value value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addFieldContain(Component component) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addFlipSwitch() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addListView() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addSlider(int min, int max) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addSlider(int min, int max, Theme theme) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addText(String label) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addText(StringValue value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addText(StringValue value, boolean fieldContain) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addTextarea(String label) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addTextarea(StringValue value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addTextarea(StringValue value, boolean fieldContain) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void onSubmit(ClickListener handler) {
			throw new UnsupportedOperationException();
		}
	}
}
