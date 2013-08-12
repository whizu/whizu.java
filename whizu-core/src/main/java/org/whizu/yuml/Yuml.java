package org.whizu.yuml;

/**
 * @author Rudy D'hauwe
 */
public final class Yuml {

	public static ActivityDiagramBuilder createActivityDiagram() {
		return ActivityDiagramBuilder.create();
	}
	
	public static ClassDiagramBuilder createClassDiagram() {
		return ClassDiagramBuilder.create();
	}

	public static UseCaseDiagramBuilder createUseCaseDiagram() {
		return UseCaseDiagramBuilder.create();
	}
	
	private Yuml() {
	}
}
