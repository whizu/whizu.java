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
package org.whizu.yuml;

import org.whizu.content.Content;
import org.whizu.content.Literal;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class NoteBuilder extends ProxyBuilder<Note> {

	public static NoteBuilder create(String note) {
		return new NoteBuilder().text(note);
	}

	private Build build_ = new Build();

	public NoteBuilder background(String color) {
		build_.background(color);
		return this;
	}

	@Override
	public Note build() {
		return buildOnce(build_);
	}

	private NoteBuilder text(String text) {
		build_.text_ = text;
		return this;
	}

	private class Build extends BuildSupport implements Note {

		private String background_;

		private String text_;

		private void background(String color) {
			background_ = color;
		}

		@Override
		public Content build() {
			String background = buildBackground();
			String markup = "[note:" + text_ + background + "]";
			return new Literal(markup);
		}

		private String buildBackground() {
			return (background_ == null) ? "" : "{bg:" + background_ + "}";
		}
	}
}
