package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.ParagraphPiece;

public class HeadingTest extends Assert{
	
	@Test
	public void testH1Style(){
		Heading1 h1 = new Heading1("222222");
		h1.getStyle().setBold(true);
		h1.getStyle().setItalic(true);
		
		assertEquals(2, TestUtils.regexCount(h1.getContent(), "<*w:rPr>"));
		assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:jc w:val=\"left\" />")); //default is left
		assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:b/>")); 
		assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:i/>")); 
		
		System.out.println(h1.getContent());
	}

	@Test
	public void testH1(){
		Heading1 h1 = new Heading1("h1");
		assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:p wsp:rsidR*"));
		assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:t>h1</w:t>"));
		assertEquals(1, TestUtils.regexCount(h1.getContent(), "</w:p>"));
		assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:pStyle w:val=\"Heading1\" />"));
	}
	
	@Test
	public void testH2(){
		Heading2 h2 = new Heading2("h2");
		assertEquals(1, TestUtils.regexCount(h2.getContent(), "<w:p wsp:rsidR*"));
		assertEquals(1, TestUtils.regexCount(h2.getContent(), "<w:t>h2</w:t>"));
		assertEquals(1, TestUtils.regexCount(h2.getContent(), "</w:p>"));
		assertEquals(1, TestUtils.regexCount(h2.getContent(), "<w:pStyle w:val=\"Heading2\" />"));
	}
	
	@Test
	public void testH3(){
		Heading3 h3 = new Heading3("h3");
		assertEquals(1, TestUtils.regexCount(h3.getContent(), "<w:p wsp:rsidR*"));
		assertEquals(1, TestUtils.regexCount(h3.getContent(), "<w:t>h3</w:t>"));
		assertEquals(1, TestUtils.regexCount(h3.getContent(), "</w:p>"));
		assertEquals(1, TestUtils.regexCount(h3.getContent(), "<w:pStyle w:val=\"Heading3\" />"));
	}

	@Test
	public void testEmpty(){
		Heading1 h1 = new Heading1("");
		assertEquals("", h1.getContent());

		Heading2 h2 = new Heading2("");
		assertEquals("", h2.getContent());
		
		Heading3 h3 = new Heading3("");
		assertEquals("", h3.getContent());
		
	}
	
}
