package org.whizu.yuml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.whizu.content.Content;
import org.whizu.content.Literal;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class ActorBuilder extends ProxyBuilder<Actor> {

	public static ActorBuilder create() {
		return new ActorBuilder();
	}

	public static ActorBuilder createWithName(String name) {
		return create().name(name);
	}

	private Build build_ = new Build();

	public ActorBuilder addNote(Note note) {
		build_.addNote(note);
		return this;
	}

	@Override
	public Actor build() {
		return buildOnce(build_);
	}

	public ActorBuilder extend(Actor actor) {
		build_.extend(actor);
		return this;
	}

	public ActorBuilder name(String name) {
		build_.name(name);
		return this;
	}

	public ActorBuilder use(UseCase useCase) {
		build_.use(useCase);
		return this;
	}

	private final class Build extends BuildSupport implements Actor {

		private List<Actor> actorList_ = new ArrayList<Actor>();
			
		private String name_;		
		
		private Note note_;

		private List<UseCase> useCaseList_ = new ArrayList<UseCase>();

		private void addNote(Note note) {
			note_ = note;
		}

		@Override
		public Content build() {
			String actor = "[" + name_ +  "]";
			
			actor = actor + buildUses();
			actor = actor + buildExtends();
			
			return new Literal(actor);
		}

		private String buildExtends() {
			if (actorList_.isEmpty()) {
				return "";
			}
			
			String uses = "";
			Iterator<Actor> it = actorList_.iterator();
			while (it.hasNext()) {
				uses = uses + ",";
				Actor ac = it.next();
				uses = uses + "[" + name_ + "]^[" + ac.name() + "]";
			}

			return uses;
		}

		private String buildUses() {
			if (useCaseList_.isEmpty()) {
				return "";
			}
			
			String uses = "";
			Iterator<UseCase> it = useCaseList_.iterator();
			while (it.hasNext()) {
				uses = uses + ",";
				UseCase uc = it.next();
				uses = uses + "[" + name_ + "]-(" + uc.name() + ")";
			}
			
			return uses;
		}
		
		private void extend(Actor actor) {
			actorList_.add(actor);
		}

		@Override
		public String name() {
			return name_;
		}

		private void name(String name) {
			name_ = name;
		}

		private void use(UseCase useCase) {
			useCaseList_.add(useCase);
		}
	}
}
