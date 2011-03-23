package word.w2004;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import word.api.interfaces.IElement;
import word.utils.TestUtils;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.elements.Paragraph.TabAlign;
import word.w2004.style.Font;
import word.w2004.style.ParagraphPieceStyle;
import word.w2004.style.ParagraphStyle;
import word.w2004.style.ParagraphStyle.Align;

public class ParagraphTest extends Assert {

    @Test
    public void sanityTest() {
        IElement par = new Paragraph("");
        assertEquals(par.getContent(), "");
    }

    @Test
    public void sanityTest02() {
        IElement par = new Paragraph(new ParagraphPiece(""));
        assertEquals(par.getContent(), "");
    }

    @Test
    public void sanityTest03() {
        IElement par = Paragraph.withPieces(new ParagraphPiece(null)).create();
        assertEquals(par.getContent(), "");
    }

    @Test
    public void sanityTestFluent() {
        IElement par = Paragraph.with("par01").withStyle().setAlign(Align.CENTER).create();

        basicParagraphCheckings(par, "par01", "center");
    }

    @Test
    public void testWithStyleBgColor() {
        IElement p01 = Paragraph.with("par01").withStyle().setBgColor("FFFF00").create();

        assertEquals(1, TestUtils.regexCount(p01.getContent(), "FFFF00")); //Background Color

        basicParagraphCheckings(p01, "par01", "left");
    }

    @Test
    public void testWithStyle() {
        IElement p01 = Paragraph.with("").withStyle().create();
        assertEquals(p01.getContent(), "");
    }


    @Test
    public void goodParagraphTest() {
        IElement p01 = new Paragraph("This is my paragraph");

        basicParagraphCheckings(p01, "This is my paragraph", null);
    }

    @Test
    public void testPiecesOne() {
        Paragraph p01 = Paragraph.with("Piece01"); //or new Paragraph("xxxx");

        basicParagraphCheckings(p01, "Piece01", null);
    }

    @Test
    public void testParagraphOneWithStyle() {
        Paragraph p01 = (Paragraph) Paragraph.with("111").withStyle().setAlign(ParagraphStyle.Align.CENTER).create();

        basicParagraphCheckings(p01, "111", "center");
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

        assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:b/>")); //bold
        assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:i/>")); //italic
        assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:u w:val=\"single\"/>")); //underline

        basicParagraphCheckings(p01, "Piece01", null);
    }

    @Test
    public void testPiecesMany() {
        ParagraphPiece piece01 = new ParagraphPiece("Piece11111");
        ParagraphPiece piece02 = new ParagraphPiece("Piece22222");

        ParagraphPieceStyle style = new ParagraphPieceStyle();
        style.setBold(true);
        style.setItalic(true);
        piece02.setStyle(style);

        Paragraph p01 = Paragraph.withPieces(piece01, piece02);


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
        //assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:t>Piece222</w:t>"));

        basicParagraphCheckings(p01, "Piece222", null);
    }

    @Test
    public void testFluent(){
        Paragraph p01 = (Paragraph) Paragraph.with("111").withStyle().setAlign(ParagraphStyle.Align.CENTER).create();

        basicParagraphCheckings(p01, "111", "center");
    }

    @Test
    public void testFluentPiece(){
        ParagraphPiece pieces = new ParagraphPiece("111");
        Paragraph p01 = Paragraph.withPieces(pieces);

        basicParagraphCheckings(p01, "111", null);
    }

    @Test
    public void testTab(){
        Paragraph p01 = Paragraph.withPieces(
                ParagraphPiece.with("Bloc 1 Price :").withStyle().setFont(Font.CALIBRI).setFontSize(Integer.toString(2*11)).create(),
                ParagraphPiece.with(" \t 3 200,00 $").withStyle().setFont(Font.CALIBRI).setFontSize(Integer.toString(2*11)).create()
        ).addTab(TabAlign.RIGHT, 8931).create();

        assertEquals(2, TestUtils.regexCount(p01.getContent(), "<w:pPr>"));
        assertEquals(1, TestUtils.regexCount(p01.getContent(), "<w:tabs>"));
        assertEquals(1, TestUtils.regexCount(p01.getContent(), "</w:tabs>"));
        assertEquals(2, TestUtils.regexCount(p01.getContent(), "</w:pPr>"));
    }

    /***
     * Verifies, for ONE paragraphPiece, basec values present
    * TODO: Add verification params for: bold, italic, bgcolor, underline
    * @param par the actual paragraph object
    * @param parValue the expected text deiplayed in the paragraph
    * @param align the expected align
    */
   private void basicParagraphCheckings(IElement par, String parValue, String align){
       if(align == null || "".equals(align)){
           align = "left"; //the default
       }

       assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:t>" + parValue + "</w:t>"));
       assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:p wsp:rsidR="));
       assertEquals(2, TestUtils.regexCount(par.getContent(), "<*w:r>"));
       assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:p>"));

       assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:jc w:val=\"" + align + "\"/>"));
       assertEquals(2, TestUtils.regexCount(par.getContent(), "<*w:pPr>"));

   }

}
