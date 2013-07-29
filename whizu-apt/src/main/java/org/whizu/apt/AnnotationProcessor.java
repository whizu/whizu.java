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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
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

import org.markdown4j.Markdown4jProcessor;

/**
 * @author Rudy D'hauwe
 */
@SupportedAnnotationTypes({"org.whizu.annotation.Markdown"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public final class AnnotationProcessor extends AbstractProcessor {

	private static final String FILENAME = "Markdown.properties";

	private static final String PACKAGE = "org.whizu.apt";

	private static boolean isModified_ = false;

	private static Properties properties_;

	private Elements elementUtils_;

	private Filer filer_;

	private Messager messager_;

	private Markdown4jProcessor processor_;

	private String getFieldName(Element field) {
		return (getPackageOf(field) + "." + field.getEnclosingElement().getSimpleName() + "." + field.getSimpleName());
	}

	private String getMarkdown(String value) {
		try {
			return processor_.process(value).replace("<br  />", " ");
		} catch (IOException e) {
			messager_.printMessage(Kind.ERROR, e.getMessage());
			throw new RuntimeException(e);
		}
	}

	private Name getPackageOf(Element field) {
		return elementUtils_.getPackageOf(field.getEnclosingElement()).getQualifiedName();
	}

	private String getValue(String comment) {
		return comment.replace("\r", "").replace("\n ", "\n");
	}

	@Override
	public void init(final ProcessingEnvironment procEnv) {
		messager_.printMessage(Kind.NOTE, "AptProcessor.init()");
		super.init(procEnv);
		elementUtils_ = procEnv.getElementUtils();
		filer_ = procEnv.getFiler();
		messager_ = procEnv.getMessager();
		processor_ = new Markdown4jProcessor();
		properties_ = readProperties();
	}

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		for (TypeElement typeElement : annotations) {
			process(typeElement, roundEnv.getElementsAnnotatedWith(typeElement));
		}

		if (isModified_) {
			
			writeProperties();
		}

		return true;
	}

	private void process(TypeElement typeElement, Set<? extends Element> elementsAnnotatedWith) {
		messager_.printMessage(Kind.NOTE, "Processing @" + typeElement);
		if (elementsAnnotatedWith.size() > 0) {
			for (Element field : elementsAnnotatedWith) {
				String docComment = elementUtils_.getDocComment(field);
				if (docComment != null) {
					String value = getValue(docComment);
					String fieldName = getFieldName(field);
					String current = properties_.getProperty(fieldName);
					if ((current == null) || (!current.equals(value))) {
						properties_.setProperty(fieldName, value);
						properties_.setProperty(fieldName + ".md", getMarkdown(value));
						isModified_ = true;
					}
				}
			}
		}
	}

	private Properties readProperties() {
		if (properties_ == null) {
			properties_ = new Properties();
			InputStream in = null;
			try {
				FileObject file = filer_.getResource(StandardLocation.CLASS_OUTPUT, PACKAGE, FILENAME);
				in = file.openInputStream();
				properties_.load(in);
			} catch (IOException e) {
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return properties_;
	}

	private void writeProperties() {
		OutputStream out = null;
		try {
			FileObject file = filer_.createResource(StandardLocation.CLASS_OUTPUT, PACKAGE, FILENAME);
			out = file.openOutputStream();
			properties_.store(out, FILENAME);
			isModified_ = false;
		} catch (IOException e) {
			messager_.printMessage(Kind.ERROR, e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
