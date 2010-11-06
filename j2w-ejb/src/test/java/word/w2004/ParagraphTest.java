package word.w2004;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import word.api.interfaces.IElement;
import word.utils.TestUtils;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.ParagraphPieceStyle;

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
	
	@Test
	public void testPiecesOne() {
		ParagraphPiece piece01 = new ParagraphPiece("Piece01");
		Paragraph p01 = new Paragraph(piece01);
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>Piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:p wsp:rsidR="));
		assertEquals(2, TestUtils.regexCount(p01.getContent(), "<*w:r>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "</w:p>"));
	}
	
	@Test
	public void testPiecesOneWithStyle() {
		ParagraphPiece piece01 = new ParagraphPiece("Piece01");
		Paragraph p01 = new Paragraph(piece01);
		ParagraphPieceStyle style = new ParagraphPieceStyle();
		style.setBold(true);
		style.setItalic(true);
		piece01.setStyle(style);
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>Piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:p wsp:rsidR="));
		assertEquals(2, TestUtils.regexCount(p01.getContent(), "<*w:r>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "</w:p>"));
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:b/>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:i/>"));
	}
	
	@Test
	public void testPiecesMany() {
		ParagraphPiece piece01 = new ParagraphPiece("Piece11111");
		ParagraphPiece piece02 = new ParagraphPiece("Piece22222");
		Paragraph p01 = new Paragraph(piece01, piece02);
		
		ParagraphPieceStyle style = new ParagraphPieceStyle();
		style.setBold(true);
		style.setItalic(true);
		
		piece02.setStyle(style);
		
		System.out.println(p01.getContent());
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>Piece11111</w:t>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>Piece22222</w:t>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:p wsp:rsidR="));
		assertEquals(4, TestUtils.regexCount(p01.getContent(), "<*w:r>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "</w:p>"));
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:b/>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:i/>"));
		
	}
	
	@Test
	public void testEmptyPiecesEtc() {
		ParagraphPiece piece01 = new ParagraphPiece("");
		ParagraphPiece piece02 = new ParagraphPiece("");
		Paragraph p01 = new Paragraph(piece01, piece02);
		assertEquals("", p01.getContent());		
	}
	
	@Test
	public void testEmptyPiecesEtc02() {
		ParagraphPiece piece01 = new ParagraphPiece(null);
		ParagraphPiece piece02 = new ParagraphPiece("Piece222");
		Paragraph p01 = new Paragraph(piece01, piece02);
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>Piece222</w:t>"));
	}

}
