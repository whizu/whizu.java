package org.whizu.tutorial;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class WhizuServer {

	public static void main(String[] args) {
		try {
			String workingDirectory = System.getProperty("user.dir");
			String descriptor = workingDirectory + "/web/WEB-INF/web.xml";
			String resourceBase = workingDirectory + "/web";
			Server server = new Server(8090);
			WebAppContext context = new WebAppContext();
			context.setDescriptor(descriptor);
			context.setResourceBase(resourceBase);
			context.setContextPath("/");
			context.setParentLoaderPriority(true);
			server.setHandler(context);
			server.start();
			server.join();
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new RuntimeException(exc);
		}
	}
}
