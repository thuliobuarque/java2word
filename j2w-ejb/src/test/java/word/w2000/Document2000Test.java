package word.w2000;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import word.api.interfaces.IDocument;
import word.w2000.elements.BreakLine;
import word.w2000.elements.Heading1;
import word.w2000.elements.Heading2;
import word.w2000.elements.PageBreak;
import word.w2000.elements.Paragraph;
import word.w2000.elements.Table;
import word.w2000.elements.Table.TableEle;

public class Document2000Test extends Assert {

	static Logger log = Logger.getLogger(IDocument.class);

	@Test
	public void sanityTest() {
		IDocument myDoc = new Document2000();
		assertTrue(myDoc.getContent().contains("<html"));
		assertTrue(myDoc
				.getContent()
				.contains(
						"xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" xmlns=\"http://www.w3.org/TR/REC-html40"));
		assertTrue(myDoc.getContent().contains("<head>"));
		assertTrue(myDoc.getContent().contains("<w:View>Print</w:View>"));
		assertTrue(myDoc.getContent().contains("</head>"));
		assertTrue(myDoc.getContent().contains("<body"));
		assertTrue(myDoc.getContent().contains("</body>"));
		assertTrue(myDoc.getContent().contains("</html>"));
		
		assertEquals(myDoc.getContent(), myDoc.getContent());
		assertEquals(myDoc.getContent(), myDoc.getContent());
	}

	@Test
	public void uriTest() {
		IDocument myDoc = new Document2000();
		String expected = "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" "
				+ "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
				+ "xmlns:w=\"urn:schemas-microsoft-com:office:word\" "
				+ "xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" "
				// + "xmlns:css=\"http://macVmlSchemaUri\" "
				+ "xmlns=\"http://www.w3.org/TR/REC-html40\">\n";
		assertEquals("Uri is not as expected: ", expected, myDoc.getUri());
	}

	@Test
	public void headTest() {
		IDocument myDoc = new Document2000();
		assertTrue(myDoc.getHead().getContent().contains("<head>"));
		assertTrue(myDoc.getHead().getContent().contains("</head>"));
	}

	@Test
	public void bodyTest() {
		IDocument myDoc = new Document2000();
		assertTrue(myDoc.getBody().getContent().contains("<body>"));
		assertTrue(myDoc.getBody().getContent().contains("</body>"));
	}

	@Test
	public void basicDocTest() {
		IDocument myDoc = new Document2000();

		myDoc.getBody().addEle(new Heading1("This is a H1"));

		myDoc.getBody().addEle(
				new Paragraph("this is the document desc in the firt page"));

		myDoc.getBody().addEle(new PageBreak());

		myDoc.getBody().addEle(new Heading2("This is a H2 - 1"));
		myDoc.getBody().addEle(
				new Paragraph("this is the document desc for the item 1"));
		myDoc.getBody().addEle(new BreakLine());

		myDoc.getBody().addEle(new Heading2("This is a H2 - 2"));
		myDoc.getBody().addEle(
				new Paragraph("this is the document desc for the item 2"));
		myDoc.getBody().addEle(new BreakLine());

		myDoc.getBody().addEle(new Heading2("This is a H2 - 3"));
		myDoc.getBody().addEle(
				new Paragraph("this is the document desc for the item 3"));
		myDoc.getBody().addEle(new Heading2("This is a H2 - 4"));
		myDoc.getBody().addEle(
				new Paragraph("this is the document desc for the item 4"));

		myDoc.getBody().addEle(new BreakLine());

		Table tbl = new Table();
		tbl.addTableEle(TableEle.TH, "Name", "Salary");

		tbl.addTableEle(TableEle.TD, "Leonardo", "100,000.00");
		tbl.addTableEle(TableEle.TD, "Romario", "1,000,000.00");
		tbl.addTableEle(TableEle.TD, "Ronaldo", "1,000,000.00");
		tbl.addTableEle(TableEle.TD, "Rivaldo", "1,000,000.00");
		tbl.addTableEle(TableEle.TD, "Roberto Carlos", "1,000,000.00");

		tbl.addTableEle(TableEle.TF, "Total", "1,100,000.00");

		tbl.setTableStyle(" class=\"tblGenericList\"");

		String strTbl = tbl.getContent().replace("<th>",
				"<th style=\"background:#E0E0E0;\">").replace("<tfoot>",
				"<tfoot style=\"font-weight: bold;\">");
		myDoc.getBody().addEle(strTbl); // work around to add style for some
		// elements...

		//System.out.println(myDoc.getContent());

	}
	
	@Test
	public void headerAndFooterTest() {
		IDocument myDoc = new Document2000();
		assertNull(myDoc.getHeader());
		assertNull(myDoc.getFooter());
	}

}
