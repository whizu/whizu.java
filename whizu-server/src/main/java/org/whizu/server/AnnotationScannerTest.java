package org.whizu.server;

import java.util.Map;

import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@Application(uri="/abc")
public class AnnotationScannerTest {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(applicationContext, false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Application.class));
		scanner.scan("org.whizu");
		applicationContext.refresh();
		Map<String, Object> result = applicationContext.getBeansWithAnnotation(Application.class);
		for (Object o : result.values()) {
			System.out.println(o.getClass());
		}
		System.out.println("done");
	}
}
