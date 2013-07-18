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
package org.whizu.tutorial.website;

import org.whizu.annotation.Listen;
import org.whizu.annotation.processing.Markdown;
import org.whizu.html.Description;
import org.whizu.html.Title;
import org.whizu.ui.UI;

/**
 * @author Rudy D'hauwe
 */
@Listen("/whizu/website/licensing")
@Title("Licensing")
@Description("Whizu is Open Source Software")
public class Licensing extends AbstractPage {

	/**
	 * ### About me
	 */
	@Markdown
	private String title;

	/**
	 * Whizu is open source software available for free [download]({}) and for
	 * free use under the terms of the European Union Public License (EUPL)
	 * v1.1. Any use of this software, other than as authorized under this
	 * license is strictly prohibited.
	 */
	@Markdown
	public String about;

	/**
	 * ### Commercial use
	 * 
	 * The EUPL v1.1 is a strong copyleft license. As such the EUPL v1.1 is
	 * compatible with the GNU General Public License (GPLv2).
	 * 
	 * Any derivative work or a distribution of any merger with the original
	 * code must be licensed under this unmodified EUPL v1.1 licence. If you
	 * have created a derivative work, and if you distribute this new work, you
	 * must apply the same EUPL licence (without modifying the licence terms) to
	 * the whole derivative work. You will have to communicate the source code
	 * of your derivative work from a free access repository. You must at least
	 * provide a link or an address allowing any licensee to open this
	 * repository and access or download the source code, as long you continue
	 * to distribute the derivative work.
	 * 
	 * ### Guidelines for users and developers
	 * 
	 * For more details about the EUPL or to obtain a copy in your language, see
	 * the European Union EUPL website. An excellent document is available for
	 * download providing a detailed description on how to use software licensed
	 * under the EUPL.
	 */
	@Markdown
	private String welcome;

	@Override
	public void init(UI ui) {
		add(title);
		add(about);
		add(welcome);
	}
}
