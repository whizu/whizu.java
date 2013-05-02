package org.whizu.resource;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class StringResourceTest {

	@Test
	public void testStringResource() {
		try {
			String content = "my-string";
			StringResource resource = new StringResource(content);
			InputStream in = resource.getInputStream();
			int ch = in.read();
			String result = "";
			while (ch != -1) {
				result += (char) ch;
				ch = in.read();
			}
			assertEquals(content, result);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {

		}
	}

	@Test
	public void testGetInputStream() {
		testStringResource();
	}
}
