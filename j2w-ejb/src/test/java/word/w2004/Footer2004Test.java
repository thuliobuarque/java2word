package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.Paragraph;


public class Footer2004Test extends Assert{

	@Test
	public void sanityTest(){
		Footer2004 ft = new Footer2004();
		assertEquals("", ft.getContent());
	}

	@Test
	public void addEleTest(){
		Footer2004 ft = new Footer2004();
		ft.addEle(new Paragraph("p1"));
		assertEquals(2, TestUtils.regexCount(ft.getContent(), "<*w:ftr"));
		assertEquals(1, TestUtils.regexCount(ft.getContent(), "<w:t>p1</w:t>"));	
		assertEquals(6, TestUtils.regexCount(ft.getContent(), "<w:rStyle w:val=\"PageNumber\"/>"));	
	}

	@Test
	public void addEleStringTest(){
		Footer2004 ft = new Footer2004();
		ft.addEle("<w:t>p1</w:t>");
		assertEquals(2, TestUtils.regexCount(ft.getContent(), "<*w:ftr"));
		assertEquals(1, TestUtils.regexCount(ft.getContent(), "<w:t>p1</w:t>"));	
	}
	
	
	@Test
	public void footerWithNoPageNumberTest(){
		Footer2004 ft = new Footer2004();
		ft.showPageNumber(false);
		ft.addEle(new Paragraph("p1"));
		assertEquals(2, TestUtils.regexCount(ft.getContent(), "<*w:ftr"));
		assertEquals(1, TestUtils.regexCount(ft.getContent(), "<w:t>p1</w:t>"));
		assertEquals(0, TestUtils.regexCount(ft.getContent(), "<w:rStyle w:val=\"PageNumber\"/>"));
	}
	
}
