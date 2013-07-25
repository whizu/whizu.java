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

import org.apache.commons.lang.StringUtils;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author Rudy D'hauwe
 */
public class Strings {

	public static boolean equalsIgnoreCase(String str1, String str2) {
		return StringUtils.equalsIgnoreCase(str1, str2);
	}
	
	public static boolean equalsIgnoreCaseOneOf(String str, String... values) {
		for (String value : values) {
			if (equalsIgnoreCase(str, value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Substitutes each {} in the pattern by the next argument.
	 */
	public static String format(String pattern, Object... args) {
		return MessageFormatter.format(pattern, args).getMessage();
	}
	
	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	/**
	 * TODO implement this feature and write a unit test.
	 * 
	 * @return true
	 */
	public static boolean isCommaSeparatedListOfPackages(String str) {
		return true;
	}

	public static String[] split(String str, char separatorChar) {
		return StringUtils.split(str, separatorChar);
	}
}
