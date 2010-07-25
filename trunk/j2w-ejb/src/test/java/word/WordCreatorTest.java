package word;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.junit.Test;

import word.webtest.WordCreator;

public class WordCreatorTest extends AbstractFacesContext {

	protected static final int BUFFER_SIZE = 2000;

	@Test
	public void sanityTest() throws IOException {
		WordCreator wc = new WordCreator();

		FacesContext facesContext = getMockedFacesContext();

		wc.setFacesContext(facesContext);
		wc.getXml();
		wc.setXml("<test></test>");
		wc.doIt("");

		HttpServletResponse servletResponse = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();

		MyHttpServletResponseWrapper wrapper = new MyHttpServletResponseWrapper(servletResponse);
		
		System.out.println("### LEO: " + servletResponse);
		System.out.println("### LEO: " + servletResponse.getBufferSize());

		System.out.println("### LEO: " + wrapper);
		
		PrintWriter pp = servletResponse.getWriter();
		System.out.println("### LEO: " + pp);

		assertEquals("application/msword", servletResponse.getContentType());
	}

}

class MyHttpServletResponseWrapper extends HttpServletResponseWrapper {

	private StringWriter sw = new StringWriter(2000);

	public MyHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(sw);
	}

	public ServletOutputStream getOutputStream() throws IOException {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return sw.toString();
	}

}
