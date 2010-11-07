package word.w2004;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import word.api.interfaces.IDocument;
import word.utils.Utils;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.Image;
import word.w2004.elements.ImageLocation;
import word.w2004.elements.PageBreak;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.HeadingStyle;
import word.w2004.style.ParagraphPieceStyle;

/**
 * @author leonardo_correa
 *
 */
public class Document2004Test extends Assert {

	static Logger log = Logger.getLogger(Document2004.class);

	@Test
	public void sanityTest() {
		IDocument myDoc = new Document2004();
		assertEquals(myDoc.getContent(), myDoc.getContent());
		assertEquals(myDoc.getContent(), myDoc.getContent());
	}

	@Test
	public void uriTest() {
		IDocument myDoc = new Document2004();
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
		+ "<?mso-application progid=\"Word.Document\"?> "
		+ "<w:wordDocument xmlns:aml=\"http://schemas.microsoft.com/aml/2001/core\" "
		+ "	xmlns:dt=\"uuid:C2F41010-65B3-11d1-A29F-00AA00C14882\" xmlns:mo=\"http://schemas.microsoft.com/office/mac/office/2008/main\" "
		+ "	xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" "
		+ "	xmlns:mv=\"urn:schemas-microsoft-com:mac:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
		+ "	xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" "
		+ "	xmlns:w=\"http://schemas.microsoft.com/office/word/2003/wordml\" "
		+ "	xmlns:wx=\"http://schemas.microsoft.com/office/word/2003/auxHint\" "
		+ "	xmlns:wsp=\"http://schemas.microsoft.com/office/word/2003/wordml/sp2\" "
		+ "	xmlns:sl=\"http://schemas.microsoft.com/schemaLibrary/2003/core\" "
		+ "	w:macrosPresent=\"no\" w:embeddedObjPresent=\"no\" w:ocxPresent=\"no\" "
		+ "	xml:space=\"preserve\"> "
		+ "	<w:ignoreSubtree w:val=\"http://schemas.microsoft.com/office/word/2003/wordml/sp2\" /> ";

		assertEquals("Uri is not as expected: ", expected, myDoc.getUri());
	}


	 @Test
	 public void headTest() {
		 IDocument myDoc = new Document2004();
		 assertTrue(myDoc.getHead().getContent().contains("<w:docPr>"));
		 assertTrue(myDoc.getHead().getContent().contains("<w:view w:val=\"print\"/>"));
		 assertTrue(myDoc.getHead().getContent().contains("<w:zoom w:percent=\"100\"/>"));
		 assertTrue(myDoc.getHead().getContent().contains("</w:docPr>"));
	 }

	 @Test
	 public void bodyTest() {
		 IDocument myDoc = new Document2004();
		 assertTrue(myDoc.getBody().getContent().contains("<w:body>"));
		 assertTrue(myDoc.getBody().getContent().contains("</w:body>"));
	 }


	// This is a basic test which contains a little bit of everything and is a good reference to start writing your own Word Document Builder
	@Test
	public void basicDocTest() {
		IDocument myDoc = new Document2004();
		myDoc.getBody().addEle(new Heading1("h1h1h1h1h1h1h"));
		myDoc.getBody().addEle(new Paragraph("This document is an example of paragraph"));

		myDoc.getBody().addEle(new Image(Utils.getAppRoot() + "/src/test/resources/base2logo.png", ImageLocation.FULL_LOCAL_PATH));

		myDoc.getBody().addEle(new BreakLine(2));

		myDoc.getBody().addEle(new Heading2("h2h2h2h2h2h2h2h"));
		myDoc.getBody().addEle(new Paragraph("This document is an example of paragraph: " + new Image(Utils.getAppRoot() + "/src/test/resources/dtpick.gif", ImageLocation.FULL_LOCAL_PATH).getContent()));

		myDoc.getBody().addEle(new PageBreak());

		myDoc.getBody().addEle(new Heading3("h3h3h3h3h3h3h3h3h3h3h3h3h3h"));
		myDoc.getBody().addEle(new Paragraph("This document is an example of paragraph"));

		myDoc.getHeader().setHideHeaderAndFooterFirstPage(true); //conf for header...
		myDoc.getHeader().addEle(new Paragraph("Header par11111"));
		myDoc.getFooter().addEle(new Paragraph("Footer par11111"));

		//System.out.println(myDoc.getContent());

	}
	
	@Test
	public void basicHeadingStyleTest() {
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

	@Test
	public void basicParagraphStyleTest() {
		IDocument myDoc = new Document2004();
		//Paragraph p01 = new Paragraph("I am a paragraph");

		ParagraphPieceStyle style = new ParagraphPieceStyle();
		style.setBold(true);
		style.setItalic(true);
		
		ParagraphPiece piece01 = new ParagraphPiece("222222");
		ParagraphPiece piece02 = new ParagraphPiece("333333");
		
		piece01.setStyle(style);
		
		Paragraph p02 = new Paragraph(piece01, piece02);
		
//		myDoc.getBody().addEle(p01);
		myDoc.getBody().addEle(p02);
		
//		System.out.println(myDoc.getContent());
	}
	
	@Test
	public void basicParagraphStyleColorTest() {
		IDocument myDoc = new Document2004();
		
		ParagraphPiece piece01 = new ParagraphPiece("11111");
		piece01.getStyle().setTextColor("FF0000");
		
		ParagraphPiece piece02 = new ParagraphPiece("22222");
		piece02.getStyle().setTextColor(ParagraphPieceStyle.Color.RED);
		
		Paragraph p01 = new Paragraph(piece01, piece02);
		
		myDoc.getBody().addEle(p01);
//		System.out.println(myDoc.getContent());
	}
	
	@Test
	public void basicFluentTest() {
		IDocument doc = new Document2004();
//		Heading1 h1 = new Heading1("h111");
//		h1.getStyle().setBold(true).setItalic(true);
		
//		Heading1 h2 = Heading1.with("h111").withStyle().setBold(true).setItalic(true).create();
//		
//		doc.getBody().addEle(h2);
//		System.out.println(doc.getContent());
	}
	
	@Ignore
	@Test
	public void prettyfize() {
		//Use this when you want to print out the XML formatted. You could also use the website: http://www.shell-tools.net/index.php 

//		String txt = Utils.readFile("/Users/leonardo/Desktop/wordDoc.doc2.xml");
//		String txt = Utils.readFile("/Users/leonardo/Desktop/111111yellow.xml");
//		System.out.println( Utils.pretty(txt) );
	}

}
