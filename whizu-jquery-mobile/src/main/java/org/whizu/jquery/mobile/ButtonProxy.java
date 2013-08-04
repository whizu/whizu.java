package org.whizu.jquery.mobile;

import org.whizu.content.Identity;
import org.whizu.jquery.JQuery;
import org.whizu.jquery.RequestContext;
import org.whizu.proxy.Proxy;

final class ButtonProxy extends Proxy<Button> implements Button {

	protected ButtonProxy(Button impl) {
		super(impl);
	}

	@Override
	protected Button createImpl() {
		return new ButtonImpl(id());
	}

	@Override
	public void css(String className) {
		impl().css(className);
	}
	
	@Override
	public String id() {
		return impl().id();
	}
	
	/***************************************************************************
     * The target <code>Button</code> that has been rendered.
	 */
	final class ButtonImpl implements Button {

		private String id_;
		
		private ButtonImpl(String id) {
			id_ = id;
		}

		@Override
		public void css(String className) {
			jQuery(this).addClass(className);
		}
		
		@Override
		public String id() {
			return id_;
		}
		
		private JQuery jQuery(Identity identity) {
			return RequestContext.getRequest().select(identity);
		}

		@Override
		public String render() {
			throw new IllegalStateException("This component is already rendered");
		}
	}
}
