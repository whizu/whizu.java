/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the "EUPL") version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an "AS IS" basis and 
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
package org.whizu.jquery.mobile;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Rudy D'hauwe
 */
public class ButtonBuilderTest extends AbstractJqmTest {

	@Test
	public void testBuild() {
		ButtonBuilder builder = ButtonBuilder.create();

		Button button = builder.build();
		equals("<a data-role='button' id='c0'></a>", button);

		boolean error = false;
		try {
			builder.build();
		} catch (IllegalStateException exc) {
			error = true;
		}
		assertEquals(true, error);
	}

	@Test
	public void testBuildOnce() {
		testBuild();
	}

	@Test
	public void testCreate() {
		Button button = ButtonBuilder.create().build();
		equals("<a data-role='button' id='c0'></a>", button);

		// @formatter:off
		Button buttonWithTitle = ButtonBuilder.create()
		    .title("Click here")
		    .build();
		// @formatter:on
		equals("<a data-role='button' id='c1'>Click here</a>", buttonWithTitle);
	}

	@Test
	public void testCreateAnchorButton() {
		Button button = ButtonBuilder.createAnchorButton().build();
		equals("<a data-role='button' id='c0'></a>", button);

		// @formatter:off
		Button buttonWithTitle = ButtonBuilder.createAnchorButton()
		    .title("Click here")
		    .build();
		// @formatter:on
		equals("<a data-role='button' id='c1'>Click here</a>", buttonWithTitle);
	}

	@Test
	public void testCreateAnchorButtonString() {
		Button buttonWithTitle = ButtonBuilder.createAnchorButton("Click")
				.build();
		equals("<a data-role='button' id='c0'>Click</a>", buttonWithTitle);
	}

	@Test
	public void testCreateButtonButton() {
		Button button = ButtonBuilder.createButtonButton().build();
		equals("<button id='c0'></button>", button);

	}

	@Test
	public void testCreateInputButton() {
		Button button = ButtonBuilder.createInputButton().build();
		equals("<input id='c0' type='button'/>", button);

		Button buttonWithTitle = ButtonBuilder.createInputButton()
				.title("Input").build();
		equals("<input id='c1' value='Input' type='button'/>", buttonWithTitle);
	}

	@Test
	public void testCreateInputButtonString() {
		Button buttonWithTitle = ButtonBuilder.createInputButton("Input")
				.build();
		equals("<input id='c0' value='Input' type='button'/>", buttonWithTitle);
	}

	@Test
	public void testCreateResetButton() {
		Button button = ButtonBuilder.createResetButton().build();
		equals("<input id='c0' type='reset'/>", button);

		Button buttonWithTitle = ButtonBuilder.createResetButton()
				.title("Reset").build();
		equals("<input id='c1' value='Reset' type='reset'/>", buttonWithTitle);
	}

	@Test
	public void testCreateResetButtonString() {
		Button buttonWithTitle = ButtonBuilder.createResetButton("Reset")
				.build();
		equals("<input id='c0' value='Reset' type='reset'/>", buttonWithTitle);
	}

	@Test
	public void testCreateSubmitButton() {
		Button button = ButtonBuilder.createSubmitButton().build();
		equals("<input id='c0' type='submit'/>", button);

		Button buttonWithTitle = ButtonBuilder.createSubmitButton()
				.title("Submit").build();
		equals("<input id='c1' value='Submit' type='submit'/>", buttonWithTitle);
	}

	@Test
	public void testCreateSubmitButtonString() {
		Button buttonWithTitle = ButtonBuilder.createSubmitButton("Submit")
				.build();
		equals("<input id='c0' value='Submit' type='submit'/>", buttonWithTitle);
	}

	@Test
	public void testCreateWithTitle() {
		Button anchor = ButtonBuilder.createWithTitle("Click here").build();
		equals("<a data-role='button' id='c0'>Click here</a>", anchor);

		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.type(ButtonType.BUTTON)
			.build();
		// @formatter:on
		equals("<button id='c1'>Click here</button>", button);

		// @formatter:off
		Button input = ButtonBuilder.createWithTitle("Click here")
			.type(ButtonType.INPUT)
			.build();
		// @formatter:on
		equals("<input id='c2' value='Click here' type='button'/>", input);

		// @formatter:off
		Button submit = ButtonBuilder.createWithTitle("Click here")
			.type(ButtonType.SUBMIT)
			.build();
		// @formatter:on
		equals("<input id='c3' value='Click here' type='submit'/>", submit);

		// @formatter:off
		Button reset = ButtonBuilder.createWithTitle("Click here")
			.type(ButtonType.RESET)
			.build();
		// @formatter:on
		equals("<input id='c4' value='Click here' type='reset'/>", reset);
	}

	@Test
	public void testDisable() {
		// @formatter:off
		Button anchor = ButtonBuilder.createWithTitle("Click here")
			.disable()
			.build();
		equals("<a data-role='button' id='c0' class='ui-disabled '>Click here</a>", anchor);

		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.type(ButtonType.BUTTON)
			.disable()
			.build();
		// @formatter:on
		equals("<button id='c1' disabled=''>Click here</button>", button);

		// @formatter:off
		Button input = ButtonBuilder.createWithTitle("Click here")
			.type(ButtonType.INPUT)
			.disable()
			.build();
		// @formatter:on
		equals("<input id='c2' value='Click here' type='button' disabled=''/>",
				input);

		// @formatter:off
		Button submit = ButtonBuilder.createWithTitle("Click here")
			.type(ButtonType.SUBMIT)
			.disable()
			.build();
		// @formatter:on
		equals("<input id='c3' value='Click here' type='submit' disabled=''/>",
				submit);

		// @formatter:off
		Button reset = ButtonBuilder.createWithTitle("Click here")
			.type(ButtonType.RESET)
			.disable()
			.build();
		// @formatter:on
		equals("<input id='c4' value='Click here' type='reset' disabled=''/>",
				reset);
	}

	@Test
	public void testIcon() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.icon(DataIcon.CHECK)
			.build();
		// @formatter:on
		equals("<a data-role='button' id='c0' data-icon='check'>Click here</a>",
				button);
	}

	@Test
	public void testIconOnly() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.iconOnly(DataIcon.CHECK)
			.build();
		// @formatter:on
		equals("<a data-role='button' id='c0' data-icon-pos='notext' data-icon='check'>Click here</a>",
				button);
	}

	@Test
	public void testIconPosition() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.icon(DataIcon.CHECK)
			.iconPosition(DataIconPosition.RIGHT)
			.build();
		// @formatter:on
		equals("<a data-role='button' id='c0' data-icon-pos='right' data-icon='check'>Click here</a>",
				button);
	}

	@Test
	public void testInline() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.inline()
			.build();
		// @formatter:on
		equals("<a data-role='button' id='c0' data-inline='true'>Click here</a>",
				button);

	}

	@Test
	public void testMini() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.mini()
			.build();
		// @formatter:on
		equals("<a data-role='button' id='c0' data-mini='true'>Click here</a>",
				button);
	}

	@Test
	public void testNoIconShadow() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.noIconShadow()
			.build();
		// @formatter:on
		equals("<a data-role='button' id='c0' data-iconshadow='false'>Click here</a>",
				button);
	}

	@Test
	public void testNoShadow() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.noShadow()
			.build();
		// @formatter:on
		equals("<a data-role='button' id='c0' data-shadow='false'>Click here</a>",
				button);
	}

	@Test
	public void testOnClick() {
		// fail("Not yet implemented");
	}

	@Test
	public void testOnClickOpenDialog() {
		// fail("Not yet implemented");
	}

	@Test
	public void testOnClickOpenPage() {
		// fail("Not yet implemented");
	}

	@Test
	public void testOnClickOpenPanel() {
		// fail("Not yet implemented");
	}

	@Test
	public void testOnClickOpenPopup() {
		// fail("Not yet implemented");
	}

	@Test
	public void testSquare() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.square()
			.build();
		// @formatter:on
		equals("<a data-role='button' id='c0' data-corners='false'>Click here</a>",
				button);
	}

	@Test
	public void testTheme() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.theme(Theme.B)
			.build();
		// @formatter:on
		equals("<a data-role='button' id='c0' data-theme='b'>Click here</a>",
				button);
	}

	@Test
	public void testType() {
		// @formatter:off
		Button button = ButtonBuilder.createWithTitle("Click here")
			.type(ButtonType.INPUT)
			.build();
		// @formatter:on
		equals("<input id='c0' value='Click here' type='button'/>", button);
	}
}
