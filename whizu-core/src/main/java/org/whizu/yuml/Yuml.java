package org.whizu.yuml;

/**
 * @author Rudy D'hauwe
 */
public final class Yuml {

	/**
	 * @return ActivityDiagramBuilder.create()
	 */
	public static ActivityDiagramBuilder createActivityDiagram() {
		return ActivityDiagramBuilder.create();
	}

	/**
	 * @return ActorBuilder.create()
	 */
	public static ActorBuilder createActor() {
		return ActorBuilder.create();
	}
	
	/**
	 * @return ClassDiagramBuilder.create()
	 */
	public static ClassDiagramBuilder createClassDiagram() {
		return ClassDiagramBuilder.create();
	}

	/**
	 * @return TypeBuilder.createClass(name)
	 */
	public static TypeBuilder createClassWithName(String name) {
		return TypeBuilder.createClass(name);
	}

	/**
	 * @return TypeBuilder.createInterface(name)
	 */
	public static TypeBuilder createInterfaceWithName(String name) {
		return TypeBuilder.createInterface(name);
	}
	
	/**
	 * @return UseCaseBuilder.create(note)
	 */
	public static NoteBuilder createNoteWithText(String note) {
		return NoteBuilder.create(note);
	}
	
	/**
	 * @return UseCaseBuilder.create()
	 */
	public static UseCaseBuilder createUseCase() {
		return UseCaseBuilder.create();
	}

	/**
	 * @return UseCaseDiagramBuilder.create()
	 */
	public static UseCaseDiagramBuilder createUseCaseDiagram() {
		return UseCaseDiagramBuilder.create();
	}

	private Yuml() {
	}
}
