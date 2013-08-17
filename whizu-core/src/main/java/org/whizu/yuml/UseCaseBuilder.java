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
public final class UseCaseBuilder extends ProxyBuilder<UseCase> {

	public static UseCaseBuilder create() {
		return new UseCaseBuilder();
	}

	public static UseCaseBuilder createWithName(String name) {
		return create().name(name);
	}

	private Build build_ = new Build();

	public UseCaseBuilder addNote(Note note) {
		// TODO implement this
		return this;
	}

	@Override
	public UseCase build() {
		return buildOnce(build_);
	}

	public UseCaseBuilder extend(UseCase useCase) {
		build_.extend(useCase);
		return this;
	}

	public UseCaseBuilder include(UseCase useCase) {
		build_.include(useCase);
		return this;
	}

	public UseCaseBuilder name(String name) {
		build_.name(name);
		return this;
	}

	private final class Build extends BuildSupport implements UseCase {

		private List<UseCase> extendList_ = new ArrayList<UseCase>();
		
		private List<UseCase> includeList_ = new ArrayList<UseCase>();
		
		private String name_;

		@Override
		public Content build() {
			String useCase = "(" + name_ +  ")";
			useCase = useCase + buildExtends();
			useCase = useCase + buildIncludes();
			return new Literal(useCase);
		}

		private void include(UseCase useCase) {
			includeList_.add(useCase);
		}

		private String buildExtends() {
			if (extendList_.isEmpty()) {
				return "";
			}
			
			String uses = "";
			Iterator<UseCase> it = extendList_.iterator();
			while (it.hasNext()) {
				uses = uses + ",";
				UseCase ac = it.next();
				uses = uses + "(" + ac.name() + ")<(" + name_ + ")";
			}

			return uses;
		}
		
		private String buildIncludes() {
			if (includeList_.isEmpty()) {
				return "";
			}
			
			String uses = "";
			Iterator<UseCase> it = includeList_.iterator();
			while (it.hasNext()) {
				uses = uses + ",";
				UseCase ac = it.next();
				uses = uses + "(" + name_ + ")>(" + ac.name() + ")";
			}

			return uses;
		}

		private void extend(UseCase useCase) {
			extendList_.add(useCase);
		}

		@Override
		public String name() {
			return name_;
		}

		private void name(String name) {
			name_ = name;
		}
	}
}
