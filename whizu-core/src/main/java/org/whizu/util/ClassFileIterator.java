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
 * For more details, see http://joinup.ec.europa.eu/software/page/eupl.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * {@code ClassFileIterator} is used to iterate over all Java ClassFile files
 * available within a specific context. For every Java ClassFile ({@code .class}
 * ) an {@link InputStream} is returned.
 * 
 * @author <a href="mailto:rmuller@xiam.nl">Ronald K. Muller</a>
 * @since annotation-detector 3.0.0
 */
final class ClassFileIterator {

	private final FileIterator fileIterator;
	private ZipFileIterator zipIterator;
	private boolean isFile;

	/**
	 * Create a new {@code ClassFileIterator} returning all Java ClassFile files
	 * available from the class path (
	 * {@code System.getProperty("java.class.path")}).
	 */
	ClassFileIterator() throws IOException {
		this(classPath());
	}

	/**
	 * Create a new {@code ClassFileIterator} returning all Java ClassFile files
	 * available from the specified files and/or directories, including sub
	 * directories.
	 */
	public ClassFileIterator(final File... filesOrDirectories) throws IOException {
		fileIterator = new FileIterator(filesOrDirectories);
	}

	/**
	 * Return the name of the Java ClassFile returned from the last call to
	 * {@link #next()}. The name is either the path name of a file or the name
	 * of an ZIP/JAR file entry.
	 */
	public String getName() {
		// Both getPath() and getName() are very light weight method calls
		return zipIterator == null ? fileIterator.getFile().getPath() : zipIterator.getEntry().getName();
	}

	/**
	 * Return {@code true} if the current {@link InputStream} is reading from a
	 * plain {@link File}. Return {@code false} if the current
	 * {@link InputStream} is reading from a ZIP File Entry.
	 */
	public boolean isFile() {
		return isFile;
	}

	/**
	 * Return the next Java ClassFile as an {@code InputStream}. <br/>
	 * NOTICE: Client code MUST close the returned {@code InputStream}!
	 */
	public InputStream next() throws IOException {
		if (zipIterator == null) {
			final File file = fileIterator.next();
			if (file == null) {
				return null;
			} else {
				final String name = file.getName();
				if (name.endsWith(".class")) {
					isFile = true;
					return new FileInputStream(file);
				} else if (fileIterator.isRootFile() && endsWithIgnoreCase(name, ".jar")) {
					zipIterator = new ZipFileIterator(file);
				} // else just ignore
				return next();
			}
		} else {
			final InputStream is = zipIterator.next();
			if (is == null) {
				zipIterator = null;
				return next();
			} else {
				isFile = false;
				return is;
			}
		}
	}

	// private

	/**
	 * Returns the class path of the current JVM instance as an array of
	 * {@link File} objects.
	 */
	private static File[] classPath() {
		String classpath = System.getProperty("java.class.path");
		if ((false) && (classpath != null)) {
			final String[] fileNames = classpath.split(File.pathSeparator);
			final File[] files = new File[fileNames.length];
			for (int i = 0; i < files.length; ++i) {
				files[i] = new File(fileNames[i]);
				System.out.println("classpath entry " + files[i]);
			}
			return files;
		} else {
			ClassLoader cl = ClassFileIterator.class.getClassLoader();
			URL[] urls = ((URLClassLoader) cl).getURLs();
			final File[] files = new File[urls.length];
			int i = 0;
			for (URL url : urls) {
				files[i] = new File(url.getFile());
				i++;
			}			
			return files;
		}
	}

	private static boolean endsWithIgnoreCase(final String value, final String suffix) {
		final int n = suffix.length();
		return value.regionMatches(true, value.length() - n, suffix, 0, n);
	}

}
