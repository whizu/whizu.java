package org.whizu.annotation;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import org.whizu.ui.Application;

public class ApplicationProxy {

	public static void main(String[] args) {
		Application instance = createProxy(new TestApplication());
		instance.init(null);
		System.out.println(instance.getTitle());
		TestApplication abc = (TestApplication) instance;
		abc.custom();
	}

	private static Application createProxy(TestApplication originalInstance) {
		try {
			Class<?> originalClass = originalInstance.getClass();
			ProxyFactory factory = new ProxyFactory();

			factory.setSuperclass(originalClass);

			MethodHandler mi = new MethodHandler() {
				public Object invoke(Object self, Method m, Method proceed, Object[] args) throws Throwable {
					System.out.println("Name: " + m.getName());
					System.out.println("invoking method " + m);
					System.out.println("invoking method " + proceed);

					return proceed.invoke(self, args); // execute the original
														// method.
				}
			};

			Class<?> proxyClass = factory.createClass();
			Object foo = proxyClass.newInstance();
			((Proxy) foo).setHandler(mi);

			return (Application) foo;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
