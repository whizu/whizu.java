package org.whizu.html;

import static org.junit.Assert.*;

import org.junit.Test;
import org.whizu.dom.Content;

/**
 * @author Rudy D'hauwe
 */
public class TbodyTest {

	/**
	 * Test method for {@link org.whizu.html.Tbody#Tbody()}.
	 */
	@Test
	public void testTbody() {
		Tbody tbody = new Tbody();
		equals("<tbody></tbody>", tbody);
	}

	/**
	 * Test method for {@link org.whizu.html.Tbody#tr()}.
	 */
	@Test
	public void testTr() {
		Tbody tbody = new Tbody();
		Tr tr = tbody.tr();
		equals("<tr></tr>", tr);
		equals("<tbody><tr></tr></tbody>", tbody);
		Td td = tr.td();
		equals("<td></td>", td);
		equals("<tr><td></td></tr>", tr);
		equals("<tbody><tr><td></td></tr></tbody>", tbody);
	}
	
	private void equals(String markup, Content content) {
		assertEquals(markup, content.render());
	}
}
