package org.whizu.html;

import static org.junit.Assert.assertEquals;

import org.whizu.dom.Content;

public abstract class AbstractTest {

	protected final void equals(String markup, Content content) {
		assertEquals(markup, content.render());
	}
}
