package word.w2004;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import word.api.interfaces.IElement;
import word.utils.TestUtils;
import word.w2004.elements.Paragraph;

public class ParagraphTest extends Assert {

	static Logger log = Logger.getLogger(ParagraphTest.class);

	@Test
	public void sanityTest() {
		IElement par = new Paragraph("");
		assertEquals(par.getContent(), "");
		
	}

	@Test
	public void goodParagraphTest() {
		IElement par = new Paragraph("This is my paragraph");
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:t>This is my paragraph</w:t>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:p wsp:rsidR="));
		assertEquals(2, TestUtils.regexCount(par.getContent(), "<*w:r>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:p>"));
	}

}
