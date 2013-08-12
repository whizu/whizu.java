package org.whizu.yuml;

import org.whizu.content.Content;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class ActorBuilder extends ProxyBuilder<Actor> {

	public static ActorBuilder create() {
		return new ActorBuilder();
	}

	private Build build_ = new Build();

	public ActorBuilder addNote(Note note) {
		return this;
	}

	@Override
	public Actor build() {
		return buildOnce(build_);
	}

	public ActorBuilder extend(Actor actor) {
		// TODO implement this
		return this;
	}

	public ActorBuilder use(UseCase useCase) {
		// TODO implement this
		return this;
	}

	private final class Build extends BuildSupport implements Actor {

		@Override
		public Content build() {
			// TODO implement this
			return null;
		}
	}
}
