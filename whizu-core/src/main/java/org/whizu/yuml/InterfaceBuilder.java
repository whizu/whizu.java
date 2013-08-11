package org.whizu.yuml;

import org.whizu.content.Content;
import org.whizu.content.Literal;
import org.whizu.proxy.BuildSupport;

/**
 * @author Rudy D'hauwe
 */
public final class InterfaceBuilder extends TypeBuilder<InterfaceBuilder> {

	public static InterfaceBuilder createWithName(String name) {
		return new InterfaceBuilder().name(name);
	}

	private Build build_ = new Build();

	@Override
	public Type build() {
		return buildOnce(build_);
	}

	/***************************************************************************
	 * The interface <code>Type</code> being build.
	 */
	private class Build extends BuildSupport implements Type {

		@Override
		public Content build() {
			String background = buildBackground();
			String thisClass = "[" + name() + background + "]";
			return new Literal(thisClass);
		}

		@Override
		public String getTypeMarkup() {
			return "[" + name() + "]";
		}
	}
}
