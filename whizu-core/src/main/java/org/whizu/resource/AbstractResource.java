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
package org.whizu.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author Rudy D'hauwe
 */
public abstract class AbstractResource implements Resource {

	@Override
	public String getString() throws IOException {
		InputStream in = null;

		try {
			in = getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			return out.toString();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	@Override
	public void print(OutputStream out) throws IOException {
		InputStream in = null;

		try {
			in = getInputStream();
			byte[] buffer = new byte[256];
		    int bytesRead = 0;
		    while ((bytesRead = in.read(buffer)) != -1) {
		        out.write(buffer, 0, bytesRead);
		    }
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
