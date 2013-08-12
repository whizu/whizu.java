package org.whizu.yuml;

import org.junit.Test;
import org.whizu.widget.AbstractTest;

/**
 * @author Rudy D'hauwe
 */
public class ClassDiagramBuilderTest extends AbstractTest {

	@Test
	public void testCreate() {
		// fail("Not yet implemented");
	}

	@Test
	public void testAddClass() {
		// fail("Not yet implemented");
	}

	@Test
	public void testBuild() {
		// @formatter:off
		Note note = NoteBuilder.create("This is my cool yUML ButtonBuilder").build();
		
		Type builder = TypeBuilder.createInterface("Builder")
				.background("white")
				.build();
		
		Type content = TypeBuilder.createInterface("Content")
				.build();

		Type button = TypeBuilder.createInterface("Button")
				.background("green")
				.addMethod("render()")
				.dependsOn(builder)
				.extend(content)
				.build();
	
		Type buttonBuilder = TypeBuilder.createClass("ButtonBuilder")
				.addNote(note)
				.stereotype("Builder")
				.background("orange")
				.addAttribute("logger")
				.addAttribute("title")
				.addMethod("build()")
				.dependsOn(button)
				.dependsOn(builder)
				.build();
		
		ClassDiagram diagram = ClassDiagramBuilder.create()
				.addClass(buttonBuilder)
				.addClass(button)
				.addClass(builder)
				.build();
		// @formatter:on
		
		equals("<img id='c5' href='http://yuml.me/diagram/scruffy/class/[&laquo;&laquo;Builder&raquo;&raquo;;ButtonBuilder|logger;title|build(){bg:orange}]-.->[&laquo;&laquo;Button&raquo;&raquo;],[ButtonBuilder]-[note:This is my cool yUML ButtonBuilder],[&laquo;&laquo;Button&raquo;&raquo;|render(){bg:green}]-.->[&laquo;&laquo;Builder&raquo;&raquo;],[&laquo;&laquo;Content&raquo;&raquo;]^-[&laquo;&laquo;Button&raquo;&raquo;],[&laquo;&laquo;Builder&raquo;&raquo;{bg:white}]'></img>",
				diagram);
	}

	@Test 
	public void testClassDiagram() {
		Type builder = TypeBuilder.createInterface("Builder<Button>")
		        .background(Color.white)
		        .build();

		Type button = TypeBuilder.createInterface("Button")
		        .background(Color.orange)
		        .build();

		Type buttonBuilder = TypeBuilder.createClass("ButtonBuilder")
		        .implement(builder)
		        .dependsOn(button)
		        .addMethod("build()")
		        .background(Color.green)
		        .build();

		ClassDiagram diagram = ClassDiagramBuilder.create()
		        .addType(buttonBuilder)
		        .addType(builder)
		        .addType(button)
		        .build();
		
		equals("<img id='c3' href='http://yuml.me/diagram/scruffy/class/[ButtonBuilder|build(){bg:green}]-.->[&laquo;&laquo;Button&raquo;&raquo;],[&laquo;&laquo;Builder<Button>&raquo;&raquo;]^-.-[ButtonBuilder],[&laquo;&laquo;Builder<Button>&raquo;&raquo;{bg:white}],[&laquo;&laquo;Button&raquo;&raquo;{bg:orange}]'></img>",
				diagram);
	}
}
