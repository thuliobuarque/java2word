package word.w2004;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import word.api.interfaces.IElement;
import word.utils.TestUtils;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.ParagraphPieceStyle.Font;

public class ParagraphPieceTest extends Assert {

	@Test
	public void sanityTest() {
		IElement par = new ParagraphPiece("");
		assertEquals(par.getContent(), "");
	}

	@Test
	public void sanityTest01() {
		IElement par = new ParagraphPiece(null);
		assertEquals(par.getContent(), "");
	}

	@Test
	public void testGetContent() {
		IElement par = ParagraphPiece.with("piece01");

		//System.out.println(par.getContent());
		
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
		assertEquals(1,	TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
		
		//if there is no style, shouldn't have this
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<*w:rPr>"));
	}

	@Test
	public void testGetContentWithStyleALL() {
		IElement par = ParagraphPiece.with("piece01").withStyle()
				.setBold(true)
				.setItalic(true)
				.setUnderline(true)
				.setFont(Font.COURIER)
				.setTextColor("008000")
				.create();

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
		assertEquals(1,
				TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:u w:val=\"single\"/>")); // underline
		assertEquals(1, TestUtils.regexCount(par.getContent(), "w:color w:val=\"008000\"/>")); // underline
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<wx:font wx:val=\"Courier\"/>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));
	}
	
	
	@Test
	public void testGetContentWithStyleBold() {
		IElement par = ParagraphPiece.with("piece01").withStyle()
				.setBold(true)
				.setItalic(false)
				.setUnderline(false)
				.create();

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
		assertEquals(1,
				TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
		
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:u w:val=\"single\"/>")); // underline
		assertEquals(0, TestUtils.regexCount(par.getContent(), "w:color w:val=\"008000\"/>")); // underline
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<wx:font wx:val=\"Courier\"/>"));
		
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));
	}
	
	@Test
	public void testGetContentWithStyleItalic() {
		IElement par = ParagraphPiece.with("piece01").withStyle()
				.setBold(false)
				.setItalic(true)
				.create();

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
		assertEquals(1,
				TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
		
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
		
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:u w:val=\"single\"/>")); // underline
		assertEquals(0, TestUtils.regexCount(par.getContent(), "w:color w:val=\"008000\"/>")); // underline
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<wx:font wx:val=\"Courier\"/>"));
		
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));
	}
	
	@Test
	public void testGetContentWithStyleUnderline() {
		IElement par = ParagraphPiece.with("piece01").withStyle()
				.setUnderline(true)
				.create();

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
		assertEquals(1,
				TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
		
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:u w:val=\"single\"/>")); // underline
		assertEquals(0, TestUtils.regexCount(par.getContent(), "w:color w:val=\"008000\"/>")); // underline
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<wx:font wx:val=\"Courier\"/>"));
		
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));
	}
	
	@Test
	public void testGetContentWithStyleFont() {
		IElement par = ParagraphPiece.with("piece01").withStyle()
				.setFont(Font.COURIER)
				.create();

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
		assertEquals(1,
				TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:u w:val=\"single\"/>")); // underline
		assertEquals(0, TestUtils.regexCount(par.getContent(), "w:color w:val=\"008000\"/>")); // underline
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "<wx:font wx:val=\"Courier\"/>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));
	}
	
	@Test
	public void testGetContentWithStyleTextColor() {
		IElement par = ParagraphPiece.with("piece01").withStyle()
				.setBold(false)
				.setItalic(false)
				.setUnderline(false)
				.setTextColor("008000")
				.create();

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
		assertEquals(1,
				TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));

		assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:u w:val=\"single\"/>")); // underline
		assertEquals(1, TestUtils.regexCount(par.getContent(), "w:color w:val=\"008000\"/>")); // underline
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
		assertEquals(0, TestUtils.regexCount(par.getContent(), "<wx:font wx:val=\"Courier\"/>"));
		assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));
	}	
	
	
}
