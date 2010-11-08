package word.w2004;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import word.api.interfaces.IElement;
import word.utils.TestUtils;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.elements.SPIKE.Create;
import word.w2004.style.ParagraphPieceStyle;
import word.w2004.style.ParagraphStyle;

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
//		ParagraphPiece piece01 = new ParagraphPiece("Piece01");
//		Paragraph p01 = new Paragraph(piece01);
		Paragraph p01 = Paragraph.with("Piece01"); //or new Paragraph("xxxx");
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>Piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:p wsp:rsidR="));
		assertEquals(2, TestUtils.regexCount(p01.getContent(), "<*w:r>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "</w:p>"));
	}
	
	@Test
	public void testParagraphOneWithStyle() {
//		Paragraph p01 = new Paragraph("111");
//		p01.getStyle().setAlign(ParagraphStyle.Align.CENTER);
		Paragraph p01 = (Paragraph) Paragraph.with("111").withStyle().setAlign(ParagraphStyle.Align.CENTER).create();

		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:p wsp:rsidR="));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>111</w:t>"));
		assertEquals(2, TestUtils.regexCount(p01.getContent(), "<*w:r>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "</w:p>"));
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:jc w:val=\"center\"/>"));
		assertEquals(2, TestUtils.regexCount(p01.getContent(), "<*w:pPr>"));
	}
	
	@Test
	public void testPiecesOneWithStyle() {
		ParagraphPiece piece01 = new ParagraphPiece("Piece01");
		Paragraph p01 = new Paragraph(piece01);
		ParagraphPieceStyle style = new ParagraphPieceStyle();
		style.setBold(true);
		style.setItalic(true);
		style.setUnderline(true);
		piece01.setStyle(style);
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>Piece01</w:t>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:p wsp:rsidR="));
		assertEquals(2, TestUtils.regexCount(p01.getContent(), "<*w:r>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "</w:p>"));
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:b/>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:i/>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:u w:val=\"single\"/>"));
	}
	
	@Test
	public void testPiecesMany() {
		ParagraphPiece piece01 = new ParagraphPiece("Piece11111");
		ParagraphPiece piece02 = new ParagraphPiece("Piece22222");
//		Paragraph p01 = new Paragraph(piece01, piece02);
		
		ParagraphPieceStyle style = new ParagraphPieceStyle();
		style.setBold(true);
		style.setItalic(true);
		piece02.setStyle(style);
		
		Paragraph p01 = (Paragraph) Paragraph.withPieces(piece01, piece02);
		
		
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

	@Test
	public void testFluent(){
		Paragraph p01 = (Paragraph) Paragraph.with("111").withStyle().setAlign(ParagraphStyle.Align.CENTER).create();
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:p wsp:rsidR="));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>111</w:t>"));
		assertEquals(2, TestUtils.regexCount(p01.getContent(), "<*w:r>"));
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "</w:p>"));
		
		assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:jc w:val=\"center\"/>"));
		assertEquals(2, TestUtils.regexCount(p01.getContent(), "<*w:pPr>"));
	}
	
}
