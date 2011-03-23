package word.w2004;

import junit.framework.Assert;

import org.ejb3unit.hibernate.validator.AssertTrue;
import org.junit.Ignore;
import org.junit.Test;

import word.api.interfaces.IElement;
import word.utils.TestUtils;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.Font;

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

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));

        // if there is no style, shouldn't have this
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<*w:rPr>"));
    }

    @Test
    public void testGetContentWithStyleALL() {
        IElement par = ParagraphPiece.with("piece01").withStyle().setBold(true)
                .setItalic(true).setUnderline(true).setFontSize("24")
                .setFont(Font.COURIER).setTextColor("008000").create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier Bold Italic\" w:h-ansi=\"Courier Bold Italic\"/>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier Bold Italic\"/>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"48\" />"));
    }

    @Test
    public void testGetContentWithStyleBold() {
        IElement par = ParagraphPiece.with("piece01").withStyle().setBold(true)
                .setItalic(false).setUnderline(false).create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));

    }

    @Test
    public void testGetContentWithStyleItalic() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
                .setItalic(true).create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic

        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));

    }

    @Test
    public void testGetContentWithStyleUnderline() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
                .setUnderline(true).create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));


        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic

        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));

    }

    @Test
    public void testGetContentWithStyleFont() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
                .setFont(Font.COURIER).create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));


        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));

    }

    @Test
    public void testGetContentWithStyleTextColor() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
                .setItalic(false).setUnderline(false)
                .setTextColor("008000").create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));


        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));
    }

    @Test
    public void testGetContentWithStyleFontSize() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
                .setItalic(false)
                .setUnderline(false)
                .setFontSize("50").create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));


        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(), "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));
    }

    @Test
    public void testGetContentWithStyleBGcolor() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
        .setBgColor("FFFF00")
        .create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));

        assertEquals(1, TestUtils.regexCount(par.getContent(), "FFFF00")); //Background Color
    }

    @Test
    public void testNOsmartFont() {
        /***
         * the font is "ARIAL_NARROW", so there should not be any bold tag in it.
         */

        IElement par = ParagraphPiece.with("piece01").withStyle().setFont(Font.ARIAL_NARROW).create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
    }

    @Test
    public void testSmartFontBold() {
        /***
         * the font is "ARIAL_NARROW_BOLD", so there has to be a 'smart' bold tag in it.
         * There should not be any 'italic' this time
         */
        IElement par = ParagraphPiece.with("piece01").withStyle().setFont(Font.ARIAL_NARROW_BOLD).create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));


        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
    }

    @Test
    public void testSmartFontItalic() {
        /***
         * the font is "ARIAL_NARROW_ITALIC", so there has to be a 'smart' Italic tag in it.
         * There should not be any 'bold' this time
         */
        IElement par = ParagraphPiece.with("piece01").withStyle().setFont(Font.ARIAL_NARROW_ITALIC).create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));


        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
    }

    @Test
    public void testSmartFontItalicAndBold() {
        /***
         * the font is "ARIAL_NARROW_ITALIC", so there has to be both 'smart' Italic and 'bold' tags in it.
         */
        IElement par = ParagraphPiece.with("piece01").withStyle().setFont(Font.ARIAL_NARROW_BOLD_ITALIC).create();

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));


        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
    }

    @Test
    public void testEquivalentSmartFont() {
        Paragraph p1 = Paragraph.withPieces(ParagraphPiece.with("same").withStyle().setFont(Font.COURIER).setBold(true).setItalic(true).create());
        Paragraph p2 = Paragraph.withPieces(ParagraphPiece.with("same").withStyle().setFont(Font.COURIER_BOLD_ITALIC).create());

        assertTrue(p1.getContent().equals(p2.getContent()));
    }

}
