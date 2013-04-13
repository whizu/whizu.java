/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the “EUPL”) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an “AS IS” basis and 
 * without warranties of any kind concerning the Software, including 
 * without limitation merchantability, fitness for a particular purpose, 
 * absence of defects or errors, accuracy, and non-infringement of 
 * intellectual property rights other than copyright. This disclaimer 
 * of warranty is an essential part of the License and a condition for 
 * the grant of any rights to this Software.
 *   
 * For more  details, see <http://joinup.ec.europa.eu/software/page/eupl>.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.html;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.whizu.dom.Content;
import org.whizu.dom.Element;
import org.whizu.dom.Foreach;

/**
 * @author Rudy D'hauwe
 */
@RunWith(JUnit4.class)
public class HtmlTest extends AbstractTest {

	// private Log log = LogFactory.getLog(HtmlTest.class);

	@Test
	public void testTd() {
		Element td = Html.td();
		equals("<td></td>", td);
	}

	@Test
	public void test1() {
		Content content = Html.tag("div").attr("id", "my-div").attr("class", "my-class");
		equals("<div id='my-div' class='my-class'></div>", content);
	}

	@Test
	public void test2() {
		Content content = Html.tag("div").id("my-div").css("my-class");
		equals("<div id='my-div' class='my-class '></div>", content);
	}

	@Test
	public void test3() {
		// @formatter:off
		Element div = Html.div().id("my-div")
						.css("my-class")
						.border("solid 1px black")
						.padding("10px")
  						.add(Html.h1().add("my-title"));
		
		Element body = div.wrap("body").id("my-body")
						 			.add(Html.br(), 
						 					Html.hr().size("1px"))
						 			.border("solid 1px black");
		// @formatter:on
		equals("<body id='my-body' style='border:solid 1px black;'><div id='my-div' style='padding:10px;border:solid 1px black;' class='my-class '><h1>my-title</h1></div><br></br><hr size='1px'></hr></body>",
				body);
	}

	@Test
	public void test4() {
		// @formatter:off
		Element div = Html.div().id("my-div")
				.css("my-class")
				.border("solid 1px black")
				.padding("10px")
					.add(Html.h1().add("my-title"));
		
		Element body2 = Html.body().id("my-body")
						   .border("solid 1px black")
						   .add(div, 
								Html.br(), 
								Html.hr().size("1px")); 
						   
		// @formatter:on
		equals("<body id='my-body' style='border:solid 1px black;'><div id='my-div' style='padding:10px;border:solid 1px black;' class='my-class '><h1>my-title</h1></div><br></br><hr size='1px'></hr></body>",
				body2);
	}

	@Test
	public void test5() {
		// @formatter:off
		Element body3 = Html.body("my-body")
						   .border("solid 1px black")
						   .add(Html.div("my-div")
								   .css("my-class")
								   .border("solid 1px black")
								   .padding("10px")
								   .add(Html.h1("my-title")), 
								   Html.br(), 
								Html.hr().size("1px")); 
								   
		// @formatter:on
		equals("<body id='my-body' style='border:solid 1px black;'><div id='my-div' style='padding:10px;border:solid 1px black;' class='my-class '><h1>my-title</h1></div><br></br><hr size='1px'></hr></body>",
				body3);
	}

	@Test
	public void test6() {
		// @formatter:off
		Content content = Html.body("my-body").border("solid 1px black").add(
			Html.div("my-div").css("my-class").border("solid 1px black").padding("10px").add(
				Html.h1().add("my-title"),
				Html.br(), 
				Html.hr().size("1px")
			)
		);
		// @formatter:on
		equals("<body id='my-body' style='border:solid 1px black;'><div id='my-div' style='padding:10px;border:solid 1px black;' class='my-class '><h1>my-title</h1><br></br><hr size='1px'></hr></div></body>",
				content);
	}

	@Test
	public void test7() {
		List<String> list = new ArrayList<String>();
		list.add("item 1");
		list.add("item 2");

		// @formatter:off
		Content content = Html.table("my-table").add(
			Html.thead(
				Html.th("column1"),
				Html.th("column2")
			),
			Html.tbody().add(new Foreach<String>(list) {
				@Override
				public Content render(String item) {	return 
						Html.tr(
							Html.td(item),
							Html.td(item)
						);
				}}
			)
		);
		// @formatter:on
		equals("<table id='my-table'><thead><th>column1</th><th>column2</th></thead><tbody><tr><td>item 1</td><td>item 1</td></tr><tr><td>item 2</td><td>item 2</td></tr></tbody></table>",
				content);
	}
}
