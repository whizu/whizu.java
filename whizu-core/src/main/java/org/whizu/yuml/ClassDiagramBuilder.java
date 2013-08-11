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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.whizu.content.Content;
import org.whizu.html.Html;
import org.whizu.proxy.BuildSupport;
import org.whizu.proxy.ProxyBuilder;

/**
 * @author Rudy D'hauwe
 */
public final class ClassDiagramBuilder extends ProxyBuilder<ClassDiagram> {

	public static ClassDiagramBuilder create() {
		return new ClassDiagramBuilder();
	}

	private Build build_ = new Build();

	public ClassDiagramBuilder addClass(Type clazz) {
		build_.addModel(clazz);
		return this;
	}

	public ClassDiagramBuilder addNote(Note note) {
		build_.addModel(note);
		return this;
	}

	@Override
	public ClassDiagram build() {
		return buildOnce(build_);
	}

	private final class Build extends BuildSupport implements ClassDiagram {

		private List<Artifact> contentList_ = new ArrayList<Artifact>();

		private void addModel(Artifact content) {
			contentList_.add(content);
		}

		@Override
		public Content build() {
			String specs = getSpecs();
			String url = "http://yuml.me/diagram/scruffy/class/" + specs;
			System.out.println(url);
			return Html.img(id()).href(url);

		}

		private String getSpecs() {
			if (contentList_.isEmpty()) {
				return "";
			}

			Iterator<Artifact> it = contentList_.iterator();
			String attributes = it.next().render();
			while (it.hasNext()) {
				attributes = attributes + "," + it.next().render();
			}

			return attributes;
		}
	}
}
