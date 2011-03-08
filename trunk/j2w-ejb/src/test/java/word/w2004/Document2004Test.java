package word.w2004;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import word.api.interfaces.IDocument;
import word.utils.TestUtils;
import word.utils.Utils;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.Image;
import word.w2004.elements.PageBreak;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.elements.Table;
import word.w2004.elements.tableElements.TableEle;
import word.w2004.style.Color;
import word.w2004.style.HeadingStyle;
import word.w2004.style.HeadingStyle.Align;
import word.w2004.style.ParagraphPieceStyle;
import word.w2004.style.ParagraphPieceStyle.Font;

/**
 * @author leonardo_correa
 * 
 */
public class Document2004Test extends Assert {

    //TODO: do tests with assert for document
    
    @Test
    public void sanityTest() {
        IDocument myDoc = new Document2004();
        assertEquals(myDoc.getContent(), myDoc.getContent());
        assertEquals(myDoc.getContent(), myDoc.getContent());
    }

    @Test
    public void testUri() { 
        IDocument myDoc = new Document2004();
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
            + "<?mso-application progid=\"Word.Document\"?> "
            + "<w:wordDocument xmlns:aml=\"http://schemas.microsoft.com/aml/2001/core\" "
            + " xmlns:dt=\"uuid:C2F41010-65B3-11d1-A29F-00AA00C14882\" xmlns:mo=\"http://schemas.microsoft.com/office/mac/office/2008/main\" "
            + " xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" "
            + " xmlns:mv=\"urn:schemas-microsoft-com:mac:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
            + " xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" "
            + " xmlns:w=\"http://schemas.microsoft.com/office/word/2003/wordml\" "
            + " xmlns:wx=\"http://schemas.microsoft.com/office/word/2003/auxHint\" "
            + " xmlns:wsp=\"http://schemas.microsoft.com/office/word/2003/wordml/sp2\" "
            + " xmlns:sl=\"http://schemas.microsoft.com/schemaLibrary/2003/core\" "
            + " w:macrosPresent=\"no\" w:embeddedObjPresent=\"no\" w:ocxPresent=\"no\" "
            + " xml:space=\"preserve\"> "
            + " <w:ignoreSubtree w:val=\"http://schemas.microsoft.com/office/word/2003/wordml/sp2\" /> ";

        assertEquals("Uri is not as expected: ", expected, myDoc.getUri());
    }

    @Test
    public void testHead() {
        IDocument myDoc = new Document2004();
        assertTrue(myDoc.getHead().getContent().contains("<w:docPr>"));
        assertTrue(myDoc.getHead().getContent()
                .contains("<w:view w:val=\"print\"/>"));
        assertTrue(myDoc.getHead().getContent()
                .contains("<w:zoom w:percent=\"100\"/>"));
        assertTrue(myDoc.getHead().getContent().contains("</w:docPr>"));
    }

    @Test
    public void testGetHeader() {
        IDocument myDoc = new Document2004();
        assertTrue(myDoc.getBody().getContent().contains("<w:body>"));
        assertTrue(myDoc.getBody().getContent().contains("</w:body>"));
    }
    
    @Test
    public void testBody() {
        IDocument myDoc = new Document2004();
        
        assertEquals(1, TestUtils.regexCount(myDoc.getBody().getContent(), "<w:body>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getBody().getContent(), "</w:body>"));
    }
    
    @Test
    public void testHeader() {
        IDocument myDoc = new Document2004();
        
        assertEquals("", myDoc.getHeader().getContent());
        
        myDoc.getHeader().addEle(Paragraph.with("paragraph01").create());
        
        assertEquals(1, TestUtils.regexCount(myDoc.getHeader().getContent(), "<w:hdr w:type=\"odd\">"));
        assertEquals(1, TestUtils.regexCount(myDoc.getHeader().getContent(), "<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">"));
        assertEquals(1, TestUtils.regexCount(myDoc.getHeader().getContent(), "<w:t>paragraph01</w:t>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getHeader().getContent(), "</w:hdr>"));
    }
    
    @Test
    public void testFooter() {
        IDocument myDoc = new Document2004();
        
        assertEquals("", myDoc.getFooter().getContent());
        
        myDoc.getFooter().addEle(Paragraph.with("paragraph01").create());       
        
        assertEquals(1, TestUtils.regexCount(myDoc.getFooter().getContent(), "<w:ftr w:type=\"odd\">"));
        assertEquals(1, TestUtils.regexCount(myDoc.getFooter().getContent(), "<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">"));
        assertEquals(1, TestUtils.regexCount(myDoc.getFooter().getContent(), "<w:t>paragraph01</w:t>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getFooter().getContent(), "</w:ftr>"));
    }

    @Test
    public void testBasicHeadingStyle() {
        IDocument myDoc = new Document2004();
        Heading1 h1 = new Heading1("Heading 111");
        HeadingStyle headingStyle = new HeadingStyle();
        headingStyle.setAlign(HeadingStyle.Align.CENTER);
        headingStyle.setItalic(true);

        h1.setStyle(headingStyle);
        myDoc.getBody().addEle(h1);

        myDoc.getBody().addEle(new Heading2("Heading 222"));
        myDoc.getBody().addEle(new Heading3("Heading 333"));
    }

    @Test //TODO: make this useful
    public void testBasicParagraphStyle() {
        IDocument myDoc = new Document2004();

        ParagraphPieceStyle style = new ParagraphPieceStyle();
        style.setBold(true);
        style.setItalic(true);

        ParagraphPiece piece01 = new ParagraphPiece("222222");
        ParagraphPiece piece02 = new ParagraphPiece("333333");

        piece01.setStyle(style);

        Paragraph p02 = new Paragraph(piece01, piece02);

        myDoc.getBody().addEle(p02);
    }

    @Test //TODO: make this useful
    public void testBasicParagraphStyleColor() {
        IDocument myDoc = new Document2004();

        ParagraphPiece piece01 = new ParagraphPiece("11111");
        piece01.getStyle().setTextColor("FF0000");

        ParagraphPiece piece02 = new ParagraphPiece("22222");
        piece02.getStyle().setTextColor(Color.RED);

        Paragraph p01 = new Paragraph(piece01, piece02);

        myDoc.getBody().addEle(p01);
    }

    //TODO: remove this type cast for Heading
    @Test //TODO: make this useful
    public void testBasicHeadingFluent() {
        IDocument doc = new Document2004();
        Heading1 h1 = (Heading1) Heading1.with("h111").withStyle()
                .setBold(true).setItalic(true)
                .setAlign(HeadingStyle.Align.CENTER).create();

        Heading2 h2 = (Heading2) Heading2.with("h222").withStyle()
                .setBold(true).setItalic(true).create();

        doc.getBody().addEle(h1);
        doc.getBody().addEle(h2);
        doc.getBody().addEle(
                Heading1.with("h3333").withStyle().setBold(true)
                        .setItalic(true).create()); // no cast...
    }

    @Test
    public void testPageOrientation() {
        IDocument doc = new Document2004();

        ParagraphPiece piece = new ParagraphPiece("Leonardo Correa Courrier");
        piece.getStyle().setFont(ParagraphPieceStyle.Font.COURIER);

        Paragraph p01 = new Paragraph(piece);

        Paragraph p02 = new Paragraph("This is default font");

        doc.addEle(p01);
        doc.addEle(p02);
        doc.addEle(Heading1.with("h3333").create());

        String orientation = "    <w:sectPr wsp:rsidR=\"00F04FB2\" wsp:rsidSect=\"00146B2A\">\n"
                + "      <w:pgSz w:w=\"16834\" w:h=\"11904\" w:orient=\"landscape\"/>\n"
                + "      <w:pgMar w:top=\"1800\" w:right=\"1440\" w:bottom=\"1800\" w:left=\"1440\" w:header=\"708\" w:footer=\"708\" w:gutter=\"0\"/>\n"
                + "      <w:cols w:space=\"708\"/>\n" + "    </w:sectPr>";
        
        //System.out.println(doc.getContent().replace("</w:body>", orientation + "\n</w:body>"));

        // String xx =Utils.readFile("/home/leonardo/Desktop/wordDoc.doc");
        // System.out.println(xx);
    }

    @Test
    public void testAddElementAliasString() {
        IDocument myDoc = new Document2004();
        myDoc.addEle(Heading1.with("heading1").create().getContent());
        
        assertTrue(myDoc.getBody().getContent().contains("<w:body>"));
        assertTrue(myDoc.getBody().getContent().contains("<w:t>heading1</w:t>"));
        assertTrue(myDoc.getBody().getContent().contains("</w:body>"));
    }
       

@Ignore //ignored by default just to not create files in your system or break the build...
@Test
public void testJava2wordAllInOne() {

    File fileObj = new File("/home/leonardo/Desktop/Java2word_allInOne.doc");

    PrintWriter writer = null;
    try {
        writer = new PrintWriter(fileObj);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    IDocument myDoc = new Document2004();
    
    myDoc.addEle(BreakLine.times(1).create()); // this is one breakline

    //Headings
    myDoc.addEle(Heading2.with("===== Headings ======").create());
    myDoc.addEle(Paragraph.with("This doc has been generated by the unit test testJava2wordAllInOne() in the class DocumentTest2004Test.java.").create());
    myDoc.addEle(BreakLine.times(1).create());
    
    myDoc.addEle(Paragraph
            .with("I will try to use a little bit of everything in the API Java2word. " +
                    "I realised that is very dificult to keep the doucmentation updated " +
                    "so this is where I will demostrate how to do some cool things with Java2word!")
            .create());
    

    myDoc.addEle(Heading1.with("Heading01 without styling").create());
    myDoc.addEle(Heading2.with("Heading02 with styling").withStyle()
            .setAlign(Align.CENTER).setItalic(true).create());
    myDoc.addEle(Heading3.with("Heading03").withStyle().setBold(true)
            .setAlign(Align.RIGHT).create());

    //Paragraph and ParagrapPiece
    myDoc.addEle(Heading2.with("===== Paragraph and ParagrapPiece ======").create());
    myDoc.addEle(Paragraph.with("I am a very simple paragraph.").create());
    
    myDoc.addEle(BreakLine.times(1).create());      
    ParagraphPiece myParPiece01 = ParagraphPiece.with("If you use the class 'Paragraph', you will have limited style. Maybe only paragraph aligment.");
    ParagraphPiece myParPiece02 = ParagraphPiece.with("In order to use more advanced style, you have to use ParagraphPiece");
    ParagraphPiece myParPiece03 = ParagraphPiece.with("One example of this is when you want to make ONLY one word BOLD or ITALIC. the way to to this is create many pieces, format them separetely and put all together in a Paragraph object. Example:");
    
    myDoc.addEle(Paragraph.withPieces(myParPiece01, myParPiece02, myParPiece03).create());
    
    ParagraphPiece myParPieceJava = ParagraphPiece.with("I like Java and ").withStyle().setFont(Font.COURIER).create();
    ParagraphPiece myParPieceRuby = ParagraphPiece.with("Ruby!!! ").withStyle().setBold(true).setItalic(true).create();
    ParagraphPiece myParPieceAgile = ParagraphPiece.with("I actually love Java, Ruby Agile, BDD, Cucumber, automation... ").withStyle().setTextColor("008000").create();
            
    
    myDoc.addEle(Paragraph.withPieces(myParPieceJava, myParPieceRuby, myParPieceAgile).create());
    
    //font size
    myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("No size").create(), ParagraphPiece.with("I am size 50.").withStyle().setFontSize("50").create() ));
    
    //Document Header and Footer
    myDoc.addEle(BreakLine.times(2).create()); 
    myDoc.addEle(Heading2.with("===== Document Header and Footer ======").create());
    myDoc.addEle(Paragraph.with("By default everything is added to the Body when you do 'myDoc.addEle(...)'." +
            " But you can add elements to the Header and/or Footer. Other cool thing is show page number or not.").create());
    
    myDoc.addEle(BreakLine.times(2).create()); 
    myDoc.addEle(Paragraph.with("Page number is displayed by default but you can disable: 'myDoc.getFooter().showPageNumber(false)' ").create());
    
    myDoc.addEle(BreakLine.times(2).create()); 
    myDoc.addEle(Paragraph.with("you can also hide Header and Footer in the first Page. This is useful for when you have a cover page.: 'myDoc.getHeader().setHideHeaderAndFooterFirstPage(true)' ").create());
    
    myDoc.getHeader().addEle(Paragraph.withPieces(ParagraphPiece.with("I am in the"), ParagraphPiece.with(" Header ").withStyle().setBold(true).create(), ParagraphPiece.with("of all pages")).create());
    
    myDoc.getFooter().addEle(Paragraph.with("I am in the Footer of all pages").create());
            
    
    //Images
    myDoc.addEle(BreakLine.times(1).create()); 
    myDoc.addEle(Heading2.with("===== Images ======").create());
    myDoc.addEle(Paragraph.with("Images can be created from diferent locations. It can be from your local machine, from web URL or classpath.").create());
    
    myDoc.addEle(Paragraph.with("This one is coming from WEB, google web site: ").create());        
    myDoc.addEle(Image.from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png"));
    
    myDoc.addEle(BreakLine.times(2).create()); 
    myDoc.addEle(Paragraph.with("You can change the image dimensions:.").create());     
    myDoc.addEle(Image.from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png").setHeight("40").setWidth("80").create());

    
    myDoc.addEle(BreakLine.times(2).create());
    myDoc.addEle(Paragraph.with("You can always be creative mixing up images inside other IElements. Eg.: Paragraphs, Tables, etc.").create());
    
    myDoc.addEle(
            new Paragraph("This document inside the paragraph, coming from '/src/test/resources/dtpick.gif': "
                    + Image.from_FULL_LOCAL_PATHL(Utils.getAppRoot()
                            + "/src/test/resources/dtpick.gif").getContent()));
            
    myDoc.addEle(BreakLine.times(1).create()); 

    
    //Table
    myDoc.addEle(Heading2.with("===== Table ======").create());
    myDoc.addEle(Paragraph.with("Table os soccer playerd and their number of gols - the best of the best of all times:").create());
    myDoc.addEle(BreakLine.times(1).create()); 
    
    Table tbl = new Table();
    tbl.addTableEle(TableEle.TH, "Name", "Number of gols", "Country");

    tbl.addTableEle(TableEle.TD, "Arthur Friedenreich", "1329", "Brazil");
    tbl.addTableEle(TableEle.TD, "Pele", "1281", "Brazil");
    tbl.addTableEle(TableEle.TD, "Romario", "1002",  "Brazil");
    tbl.addTableEle(TableEle.TD, "Tulio Maravilha", "956",  "Brazil");
    tbl.addTableEle(TableEle.TD, "Zico", "815",  "Brazil");
    tbl.addTableEle(TableEle.TD, "Roberto Dinamite", "748",  "Brazil");
    tbl.addTableEle(TableEle.TD, "Di Stéfano", "715", "Argentina");
    tbl.addTableEle(TableEle.TD, "Puskas", "689", "Hungary");
    tbl.addTableEle(TableEle.TD, "Flávio", "591", "Brazil");
    tbl.addTableEle(TableEle.TD, "James McGory", "550", "Scotland");
    tbl.addTableEle(TableEle.TD, "Leonardo Correa", "299", "Brazil/Australia");
    
    tbl.addTableEle(TableEle.TF, "Total", "1,100,000.00", " ");
    
    myDoc.addEle(tbl);
    
    myDoc.addEle(BreakLine.times(1).create()); 
    
    myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("* Zico was mid-fieldfer and managed to score all those fucking goals!").withStyle().setItalic(true).create()).create());
    myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("* Leonardo Correa's goals (me) include futsal, soccer, friendly games, training games, so on... (but not playstation)").withStyle().setItalic(true).create()).create());

    
    //PageBreaks
    myDoc.addEle(Heading2.with("===== PageBreak ======").create());
    myDoc.addEle(Paragraph.with("There is a PAGE BREAK after this line:").create());
    myDoc.addEle(PageBreak.create());
    myDoc.addEle(Paragraph.with("There is a PAGE BREAK before this line:").create());

    
    
    String myWord = myDoc.getContent();

    writer.println(myWord);
    writer.close();
}

    @Ignore
    @Test
    public void prettyfize() {
        // Use this when you want to print out the XML formatted. You could also
        // use the website: http://www.shell-tools.net/index.php

        // String txt =
        // Utils.readFile("/Users/leonardo/Desktop/wordDoc.doc2.xml");
        // String txt =
        // Utils.readFile("/Users/leonardo/Desktop/111111yellow.xml");
        // System.out.println( Utils.pretty(txt) );
    }


    @Ignore //ignored by default just to not create files in your system or break the build...
    @Test
    public void testParagraphPieceFontSize() {
        IDocument myDoc = new Document2004();
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("No size: " + Image.from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png").setHeight("50").setWidth("100").create().getContent()).create()));

        myDoc.addEle(Paragraph.with("No size: " + Image.from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png").setHeight("50").setWidth("100").create().getContent()).withStyle().setAlign(word.w2004.style.ParagraphStyle.Align.RIGHT).create());
        
        TestUtils.createLocalDoc(myDoc.getContent());
    }
    
    
}
