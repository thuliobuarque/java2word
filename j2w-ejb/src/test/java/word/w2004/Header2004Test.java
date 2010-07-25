package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.Paragraph;


public class Header2004Test extends Assert{

	@Test
	public void sanityTest(){
		Header2004 hd = new Header2004();
		assertEquals("", hd.getContent());
	}

	@Test
	public void addEleTest(){
		Header2004 hd = new Header2004();
		hd.addEle(new Paragraph("p1"));
		assertEquals(2, TestUtils.regexCount(hd.getContent(), "<*w:hdr"));
		assertEquals(1, TestUtils.regexCount(hd.getContent(), "<w:t>p1</w:t>"));	
	}

	@Test
	public void addEleStringTest(){
		Header2004 hd = new Header2004();
		hd.addEle("<w:t>p1</w:t>");
		assertEquals(2, TestUtils.regexCount(hd.getContent(), "<*w:hdr"));
		assertEquals(1, TestUtils.regexCount(hd.getContent(), "<w:t>p1</w:t>"));	
	}
	
	@Test
	public void hideHeaderandFooterTest(){ //this has to be tested in the body...
		Header2004 hd = new Header2004();
		hd.setHideHeaderAndFooterFirstPage(true);
		assertTrue(hd.getHideHeaderAndFooterFirstPage());
	}
	
}
