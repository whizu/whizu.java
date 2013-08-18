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
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * {@code FileIterator} enables iteration over all files in a directory and all
 * its sub directories. <br/>
 * Usage:
 * 
 * <pre>
 * FileIterator iter = new FileIterator(new File(&quot;./src&quot;));
 * File f;
 * while ((f = iter.next()) != null) {
 * 	// do something with f
 * 	assert f == iter.getCurrent();
 * }
 * </pre>
 * 
 * @author <a href="mailto:rmuller@xiam.nl">Ronald K. Muller</a>
 * @since annotation-detector 3.0.0
 */
final class FileIterator {

	private final Deque<File> stack = new LinkedList<File>();
	private int rootCount;
	private File current;

	/**
	 * Create a new {@code FileIterator} using the specified
	 * 'filesOrDirectories' as root. <br/>
	 * If 'filesOrDirectories' contains a file, the iterator just returns that
	 * single file. If 'filesOrDirectories' contains a directory, all files in
	 * that directory and its sub directories are returned (depth first).
	 * 
	 * @param filesOrDirectories
	 *            Zero or more {@link File} objects, which are iterated in the
	 *            specified order (depth first)
	 */
	public FileIterator(final File... filesOrDirectories) {
		addReverse(filesOrDirectories);
		rootCount = stack.size();
	}

	/**
	 * Return the last returned file or {@code null} if no more files are
	 * available.
	 * 
	 * @see #next()
	 */
	public File getFile() {
		return current;
	}

	/**
	 * Return {@code true} if the current file is one of the files originally
	 * specified as one of the constructor file parameters, i.e. is a root file
	 * or directory.
	 */
	public boolean isRootFile() {
		if (current == null) {
			throw new NoSuchElementException();
		}
		return stack.size() < rootCount;
	}

	/**
	 * Return the next {@link File} object or {@code null} if no more files are
	 * available.
	 * 
	 * @see #getFile()
	 */
	public File next() throws IOException {
		if (stack.isEmpty()) {
			current = null;
			return null;
		} else {
			current = stack.removeLast();
			if (current.isDirectory()) {
				if (stack.size() < rootCount) {
					rootCount = stack.size();
				}
				addReverse(current.listFiles());
				return next();
			} else {
				return current;
			}
		}
	}

	/**
	 * Add the specified files in reverse order.
	 */
	private void addReverse(final File[] files) {
		for (int i = files.length - 1; i >= 0; --i) {
			stack.add(files[i]);
		}
	}

}
