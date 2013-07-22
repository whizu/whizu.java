package org.whizu.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.helpers.MessageFormatter;

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
