package org.whizu.server;

import javax.servlet.FilterConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.annotation.App;
import org.whizu.util.AnnotationScanner;
import org.whizu.util.Strings;
import org.whizu.util.TypeReporter;

class RequestDispatcherBuilder {

	private static final Logger log = LoggerFactory.getLogger(RequestDispatcherBuilder.class);

	/**
	 * Configuration parameter for classpath annotation scanning.
	 */
	private static final String INIT_PARAM_ANNOTATION_SCANNING = "annotation-scanning";

	private static RequestDispatcherBuilder create() {
		return new RequestDispatcherBuilder();
	}

	protected static RequestDispatcherBuilder createFromFilterConfig(FilterConfig config) {
		return create().configureAnnotationScanning(config.getInitParameter(INIT_PARAM_ANNOTATION_SCANNING));
	}

	private AnnotationScanner classpathAnnotationScanner_;

	protected RequestDispatcher build() {
		final RequestDispatcher dispatcher_ = new RequestDispatcher();
		if (classpathAnnotationScanner_ != null) {
			classpathAnnotationScanner_.scan(App.class, new TypeReporter<App>() {

				@Override
				public void report(App annotation, Class<?> annotatedClass) {
					log.debug("Configure @App on {}", annotatedClass);
					dispatcher_.addApp(annotation.value(), annotatedClass);
				}
			});
		}
		return dispatcher_;
	}

	/**
	 * Configures classpath annotation scanning based on the value of the
	 * "annotation-scanning" init-param on <code>WhizuFilter</code>.
	 * 
	 * @param value
	 *            the value of the init-param
	 */
	private RequestDispatcherBuilder configureAnnotationScanning(String value) {
		if (Strings.isBlank(value)) {
			// scan the full classpath
			enableAnnotationScanning();
		} else if (Strings.equalsIgnoreCaseOneOf(value, "true", "on")) {
			// scan the full classpath
			enableAnnotationScanning();
		} else if (Strings.equalsIgnoreCaseOneOf(value, "false", "off")) {
			// don't scan
			disableAnnotationScanning();
		} else if (Strings.isCommaSeparatedListOfPackages(value)) {
			// scan a limited set of packages
			String[] packages = Strings.split(value, ',');
			enableAnnotationScanning(packages);
		} else {
			throw new ConfigurationException("Illegal value {} for init-param {}", value,
					INIT_PARAM_ANNOTATION_SCANNING);
		}

		return this;
	}

	private void disableAnnotationScanning() {
		classpathAnnotationScanner_ = null;
	}

	private void enableAnnotationScanning() {
		classpathAnnotationScanner_ = new AnnotationScanner();
	}

	private void enableAnnotationScanning(String[] packages) {
		classpathAnnotationScanner_ = new AnnotationScanner(packages);
	}
}
