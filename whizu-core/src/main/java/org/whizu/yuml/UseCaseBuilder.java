package org.whizu.yuml;

import org.whizu.content.Content;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class UseCaseBuilder extends ProxyBuilder<UseCase> {

	public static UseCaseBuilder create() {
		return new UseCaseBuilder();
	}

	private Build build_ = new Build();

	public UseCaseBuilder addNote(Note note) {
		// TODO implement this
		return this;
	}

	public UseCaseBuilder extend(UseCase useCase) {
		// TODO implement this
		return this;
	}

	public UseCaseBuilder include(UseCase useCase) {
		// TODO implement this
		return this;
	}

	@Override
	public UseCase build() {
		return buildOnce(build_);
	}

	private final class Build extends BuildSupport implements UseCase {

		@Override
		public Content build() {
			// TODO implement this
			return null;
		}
	}
}
