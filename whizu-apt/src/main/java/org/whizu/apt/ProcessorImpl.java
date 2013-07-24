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
package org.whizu.apt;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * @author Rudy D'hauwe
 */
@SupportedAnnotationTypes({ ProcessorImpl.MARKDOWN_TYPE })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public final class ProcessorImpl extends AbstractProcessor {

	//protected static final String HTML_TYPE = "org.whizu.annotation.Html";

	protected static final String MARKDOWN_TYPE = "org.whizu.annotation.Markdown";

	private static final String PACKAGE = "org.whizu.apt";

	private Elements elementUtils_;

	private Filer filer_;

	private Messager messager_;

	/**
	 * @param field
	 * @return
	 */
	private String getFieldName(Element field) {
		return (getPackageOf(field) + "." + field.getEnclosingElement().getSimpleName() + "." + field.getSimpleName())
				.replace('.', '_');
	}

	private String getFileName(TypeElement typeElement) {
		return typeElement.getSimpleName() + ".properties";
	}

	private Name getPackageOf(Element field) {
		return elementUtils_.getPackageOf(field.getEnclosingElement()).getQualifiedName();
	}

	private String getValue(String comment) {
		return comment.replace("\n", "\\n").replace("\r", "");
	}

	private Writer getWriter(String fileName) throws IOException {
		FileObject file = filer_.createResource(StandardLocation.CLASS_OUTPUT, PACKAGE, fileName);
		return file.openWriter();
	}

	@Override
	public void init(final ProcessingEnvironment procEnv) {
		super.init(procEnv);
		elementUtils_ = procEnv.getElementUtils();
		filer_ = procEnv.getFiler();
		messager_ = procEnv.getMessager();
	}

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		try {
			for (TypeElement typeElement : annotations) {
				process(typeElement, roundEnv.getElementsAnnotatedWith(typeElement));
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	private void process(TypeElement typeElement, Set<? extends Element> elementsAnnotatedWith) throws IOException {
		messager_.printMessage(Kind.NOTE, "Start processing @" + typeElement);
		if (elementsAnnotatedWith.size() > 0) {
			Writer writer = null;
			try {
				String fileName = getFileName(typeElement);
				writer = getWriter(fileName);
				for (Element field : elementsAnnotatedWith) {
					String docComment = elementUtils_.getDocComment(field);
					if (docComment != null) {
						String value = getValue(docComment);
						String fieldName = getFieldName(field);
						writer.append(fieldName + "=" + value + "\n");
					}
				}
			} finally {
				if (writer != null) {
					writer.close();
				}
			}
		}
	}
}
