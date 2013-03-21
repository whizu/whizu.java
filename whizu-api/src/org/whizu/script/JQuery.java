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
package org.whizu.script;

public interface JQuery {

	public abstract JQuery addClass(String style);

	public abstract JQuery append(String arg);

	public abstract JQuery button();

	public abstract JQuery call(String function);

	public abstract JQuery call(String function, String arg);

	public abstract JQuery call(String function, String arg0, String... args);

	public abstract JQuery callunquoted(String function, String arglist);

	public abstract JQuery click(Function function);

	public abstract JQuery closest(String name);

	public abstract JQuery concat(String... js);

	public abstract JQuery document();

	public abstract JQuery empty();

	public abstract JQuery find(String selector);

	public abstract JQuery get(String url, Function data, Function callback, String type);

	public abstract JQuery html(String arg);

	public abstract JQuery prepend(String arg);

	public abstract JQuery remove();

	public abstract JQuery serialize();

	public abstract JQuery text(String arg);

	public abstract JQuery toggle();

	public abstract String toJavaScript();

	public abstract JQuery trigger(String event);

	public abstract JQuery val(String arg);
}
