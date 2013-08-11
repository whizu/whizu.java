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
		
		Type builder = ClassBuilder.createWithName("Builder")
				.background("white")
				.build();
		
		Type content = ClassBuilder.createWithName("Content")
				.build();

		Type button = ClassBuilder.createWithName("Button")
				.background("green")
				.addMethod("render()")
				.dependsOn(builder)
				.extend(content)
				.build();
	
		Type buttonBuilder = ClassBuilder.createWithName("ButtonBuilder")
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
		
		equals("<img id='c5' href='http://yuml.me/diagram/scruffy/class/[&laquo;&laquo;Builder&raquo;&raquo;;ButtonBuilder|logger;title|build(){bg:orange}]-.->[Button],[ButtonBuilder]-[note:This is my cool yUML ButtonBuilder],[Button|render(){bg:green}]-.->[Builder],[Content]^-[Button],[Builder{bg:white}]'></img>",
				diagram);
	}

	@Test 
	public void testClassDiagram() {
		Type builder = InterfaceBuilder.createWithName("Builder<Button>")
		        .background(Color.white)
		        .build();

		Type button = InterfaceBuilder.createWithName("Button")
		        .background(Color.orange)
		        .build();

		Type buttonBuilder = ClassBuilder.createWithName("ButtonBuilder")
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
		
		equals("<img id='c3' href='http://yuml.me/diagram/scruffy/class/[ButtonBuilder|build(){bg:green}]-.->[Button],[Builder<Button>]^-.-[ButtonBuilder],[Builder<Button>{bg:white}],[Button{bg:orange}]'></img>",
				diagram);
	}
}
