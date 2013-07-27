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
package org.whizu.tutorial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.whizu.annotation.Css;
import org.whizu.annotation.Markdown;
import org.whizu.annotation.Style;
import org.whizu.dom.Element;
import org.whizu.html.Html;

/**
 * @author Rudy D'hauwe
 */
class Tester {

	/**
	 * dddxfdfgfffdddssdf
	 */
	@Markdown
	private String x;

	/**
	 * sddsf a a a a a sd* fqsd. a()< a ab
	 * sdfsdfsddffgdfdg
	 * 
	 * 00054 sddsfq
	 */
	@Markdown
	private String y;
	
	@Css("myClass")
	@Style("border:myClass")
	public Element get() {
		return Html.div();
	}

	public static void main(String[] args) throws IOException {
		Tester t = new Tester();
		System.out.println(t.get().render());

		String filePath = "j:/git/whizu.java/whizu-tutorial/src/main/java/org/whizu/tutorial/Tester.java";
		String content = new String(Files.readAllBytes(Paths.get(filePath)));

		System.out.println(content);

		Pattern pattern = Pattern.compile("(/\\*\\*)[\\s]+[\\*]([\\w|[\\.\\s\\*/,!@#$%^&*()<>]]+)(\\*/)");
		pattern = Pattern.compile("(/\\*\\*)([\\w|[\\.\\s\\*/,!@#$%^&*()<>]]+)(\\*/)[\\s]+@Markdown");
		Matcher matcher = pattern.matcher(content);
		// Check all occurance
		while (matcher.find()) {
			System.out.print("Start index: " + matcher.start());
			System.out.print("End index: " + matcher.end() + " ");
			System.out.println("|"+matcher.group(2)+"|");
		}
	}
}
