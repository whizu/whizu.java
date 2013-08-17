package org.whizu.yuml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.whizu.content.Content;
import org.whizu.html.Html;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class UseCaseDiagramBuilder extends ProxyBuilder<UseCaseDiagram> {

	private Build build_ = new Build();
	
	public UseCaseDiagramBuilder addActor(Actor actor) {
		build_.addActor(actor);
		return this;
	}
	
	public UseCaseDiagramBuilder addUseCase(UseCase useCase) {
		build_.addUseCase(useCase);
		return this;
	}

	public static UseCaseDiagramBuilder create() {
		return new UseCaseDiagramBuilder();
	}

	@Override
	public UseCaseDiagram build() {
		return buildOnce(build_);
	}
	
	private class Build extends BuildSupport implements UseCaseDiagram {

		private List<Artifact> contentList_ = new ArrayList<Artifact>();
		
		private List<Actor> actorList_ = new ArrayList<Actor>();

		@Override
		public Content build() {
			String specs = getSpecs();
			String url = "http://yuml.me/diagram/scruffy/usecase/" + specs;
			System.out.println(url);
			return Html.img(id()).href(url);
		}

		public void addUseCase(UseCase useCase) {
			contentList_.add(useCase);
		}

		private void addActor(Actor actor) {
			actorList_.add(actor);
			contentList_.add(actor);
		}
		
		private String getSpecs() {
			if (contentList_.isEmpty()) {
				return "";
			}

			Iterator<Artifact> it = contentList_.iterator();
			String attributes = it.next().render();
			while (it.hasNext()) {
				attributes = attributes + "," + it.next().render();
			}

			return attributes;
		}
	}
}
