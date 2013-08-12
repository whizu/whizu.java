package org.whizu.yuml;

import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class UseCaseDiagramBuilder extends ProxyBuilder<UseCaseDiagram> {

	public UseCaseDiagramBuilder addActor(Actor actor) {
		// TODO implement this
		return this;
	}
	
	public UseCaseDiagramBuilder addUseCase(UseCase useCase) {
		// TODO implement this
		return this;
	}

	public static UseCaseDiagramBuilder create() {
		return new UseCaseDiagramBuilder();
	}

	@Override
	public UseCaseDiagram build() {
		// TODO implement this
		return null;
	}
}
