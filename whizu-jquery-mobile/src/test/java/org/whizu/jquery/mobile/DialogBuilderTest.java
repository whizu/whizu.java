package org.whizu.jquery.mobile;

import org.junit.Test;
import org.whizu.proxy.Proxy;
import org.whizu.util.Objects;

public class DialogBuilderTest extends AbstractJqmTest {

	@Test
	public void testBuild() {
		
		// @formatter:off
		Dialog dialog = DialogBuilder.createWithId("my-dialog").title("abc").title("def").build();
		// @formatter:on
		dialog.title("xyz");
		//state of dialog must be now be non-rendered
		
		//render dialog... how?
		Proxy<Dialog> proxy = Objects.cast(dialog);
		System.out.println(proxy.render());
		
		dialog.title("qvw");
		dialog.title("svw");
		//state of dialog must now be rendered
		//System.out.println(((Proxy<Dialog>)dialog).render());
	}
}
