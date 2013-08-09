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
package org.whizu.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Rudy D'hauwe
 */
public class StringsTest {

	/**
	 * Test method for
	 * {@link org.whizu.util.Strings#equalsIgnoreCase(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testEqualsIgnoreCase() {

	}

	/**
	 * Test method for
	 * {@link org.whizu.util.Strings#equalsIgnoreCaseOneOf(java.lang.String, java.lang.String[])}
	 * .
	 */
	@Test
	public void testEqualsIgnoreCaseOneOf() {
	}

	/**
	 * Test method for
	 * {@link org.whizu.util.Strings#format(java.lang.String, java.lang.Object)}
	 * .
	 */
	@Test
	public void testFormatObject1() {
		String pattern = "This is a {} pattern";
		String output = Strings.format(pattern, "blue");
		assertEquals(output, "This is a blue pattern");
	}
	
	@Test
	public void testFormatObject2() {
		String pattern = "This is a {} pattern of {} arguments";
		String output = Strings.format(pattern, "blue", "green");
		assertEquals(output, "This is a blue pattern of green arguments");
	}

	/**
	 * Test method for
	 * {@link org.whizu.util.Strings#format(java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public void testFormatObjects1() {
		String pattern = "This is a {} pattern with a second {} argument";
		String output = Strings.format(pattern, new Object[] { "blue", "green" });
		assertEquals("This is a blue pattern with a second green argument", output);
	}

	@Test
	public void testFormatObjects2() {
		String pattern = "This is a {} pattern with a second {} argument";
		String output = Strings.format(pattern, "blue", "green");
		assertEquals("This is a blue pattern with a second green argument", output);
	}
	
	@Test
	public void testFormatObjects3() {
		String pattern = "This is a {} pattern with a second {} argument and a {} third";
		String output = Strings.format(pattern, "blue", "green", "red");
		assertEquals("This is a blue pattern with a second green argument and a red third", output);
	}

	/**
	 * Test method for {@link org.whizu.util.Strings#isBlank(java.lang.String)}.
	 */
	@Test
	public void testIsBlank() {

	}

	/**
	 * Test method for
	 * {@link org.whizu.util.Strings#isCommaSeparatedListOfPackages(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsCommaSeparatedListOfPackages() {

	}

	/**
	 * Test method for
	 * {@link org.whizu.util.Strings#split(java.lang.String, char)}.
	 */
	@Test
	public void testSplit() {

	}
}
