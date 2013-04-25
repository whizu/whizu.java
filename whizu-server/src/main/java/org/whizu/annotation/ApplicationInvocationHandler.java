package org.whizu.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ApplicationInvocationHandler implements InvocationHandler {
	private Object proxied;

	public ApplicationInvocationHandler(Object proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Method m = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());
		if (m.isAnnotationPresent(Css.class)) {
			System.out.println("\tIn the annotation processor");
		}
		return method.invoke(proxied, args);
	}
}