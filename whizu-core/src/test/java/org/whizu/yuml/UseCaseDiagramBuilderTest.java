package org.whizu.yuml;

import org.junit.Test;
import org.whizu.widget.AbstractTest;

public class UseCaseDiagramBuilderTest extends AbstractTest {

	@Test
	public void testAddActor() {
		Actor actor = ActorBuilder.createWithName("Customer").build();
		UseCaseDiagram useCaseDiagram = UseCaseDiagramBuilder.create()
				.addActor(actor).build();
		equals("<img id='c1' href='http://yuml.me/diagram/scruffy/usecase/[Customer]'></img>",
				useCaseDiagram);
	}

	@Test
	public void testAddUseCase() {
		UseCase useCase = UseCaseBuilder.createWithName("Login").build();
		Actor actor = ActorBuilder.createWithName("Customer").use(useCase)
				.build();
		UseCaseDiagram useCaseDiagram = UseCaseDiagramBuilder.create()
				.addActor(actor).build();
		equals("<img id='c2' href='http://yuml.me/diagram/scruffy/usecase/[Customer],[Customer]-(Login)'></img>",
				useCaseDiagram);

		useCaseDiagram = UseCaseDiagramBuilder.create().addUseCase(useCase)
				.build();
		equals("<img id='c3' href='http://yuml.me/diagram/scruffy/usecase/(Login)'></img>",
				useCaseDiagram);
	}

	@Test
	public void testAddUseCaseExtends() {
		UseCase login = UseCaseBuilder.createWithName("Login").build();
		UseCase passwordReminder = UseCaseBuilder
				.createWithName("Request Password Reminder").extend(login)
				.build();
		UseCase register = UseCaseBuilder.createWithName("Register")
				.extend(login).build();

		UseCaseDiagram useCaseDiagram = UseCaseDiagramBuilder.create()
				.addUseCase(login)
				.addUseCase(passwordReminder)
				.addUseCase(register)
				.build();
		equals("<img id='c3' href='http://yuml.me/diagram/scruffy/usecase/(Login),(Request Password Reminder),(Login)<(Request Password Reminder),(Register),(Login)<(Register)'></img>",
				useCaseDiagram);
	}

	@Test
	public void testAddUseCaseIncludes() {
		UseCase confirmRegistration = UseCaseBuilder.createWithName(
				"Confirm Registration").build();
		UseCase register = UseCaseBuilder.createWithName("Register")
				.include(confirmRegistration).build();

		UseCaseDiagram useCaseDiagram = UseCaseDiagramBuilder.create()
				.addUseCase(register).build();

		equals("<img id='c2' href='http://yuml.me/diagram/scruffy/usecase/(Register),(Register)>(Confirm Registration)'></img>", useCaseDiagram);
	}

	@Test
	public void testAddManyUseCases() {
		UseCase login = UseCaseBuilder.createWithName("Login").build();
		UseCase logout = UseCaseBuilder.createWithName("Logout").build();

		// @formatter:off
		Actor actor = ActorBuilder.createWithName("Customer")
				.use(login)
				.use(logout)
				.build();

		UseCaseDiagram useCaseDiagram = UseCaseDiagramBuilder.create()
				.addActor(actor)
				.build();
		// @formatter:on

		equals("<img id='c3' href='http://yuml.me/diagram/scruffy/usecase/[Customer],[Customer]-(Login),[Customer]-(Logout)'></img>",
				useCaseDiagram);
	}

	@Test
	public void testActorInheritance() {
		// @formatter:off
		Actor user = ActorBuilder.createWithName("User").build();
		
		Actor admin = ActorBuilder.createWithName("Cms Admin")
				.extend(user)
				.build();

		UseCaseDiagram useCaseDiagram = UseCaseDiagramBuilder.create()
				.addActor(admin)
				.build();
		// @formatter:on

		equals("<img id='c2' href='http://yuml.me/diagram/scruffy/usecase/[Cms Admin],[Cms Admin]^[User]'></img>",
				useCaseDiagram);
	}

	@Test
	public void testMultipleActorInheritance() {
		// @formatter:off
		Actor user = ActorBuilder.createWithName("User").build();
		
		Actor admin = ActorBuilder.createWithName("Cms Admin")
				.extend(user)
				.build();
		
		Actor customer = ActorBuilder.createWithName("Customer")
				.extend(user)
				.build();
		
		Actor agent = ActorBuilder.createWithName("Agent")
				.extend(user)
				.build();
		
		UseCaseDiagram useCaseDiagram = UseCaseDiagramBuilder.create()
				.addActor(admin)
				.addActor(customer)
				.addActor(agent)
				.build();
		// @formatter:on

		equals("<img id='c4' href='http://yuml.me/diagram/scruffy/usecase/[Cms Admin],[Cms Admin]^[User],[Customer],[Customer]^[User],[Agent],[Agent]^[User]'></img>",
				useCaseDiagram);
	}

	@Test
	public void testCreate() {
		// fail("Not yet implemented");
	}

	@Test
	public void testBuild() {
		// fail("Not yet implemented");
	}

}
