package word.utils;

import java.io.BufferedReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import word.w2000.Document2000;


public class Utils {

	static Logger log = Logger.getLogger(Document2000.class);
	
	public static String getAppRoot(){
		File file = new File(".");
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public static String readFile(String file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "FileNotFoundException";
		}
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	
	public static String pretty(String xml) {
		//log.info("pretty...");
		try {
			org.dom4j.Document document = DocumentHelper.parseText(xml)
					.getDocument();

			String res = formatXml(document, false);
			return res;
		} catch (DocumentException e) {
			System.out.println("@@@@@ " + e);
			return xml;
		}
	}

	public static String formatXml(Node node, boolean oneLine) {
		//log.info("prettyfizing...");
//		if (node == null) {
//			log.info("prettyfizing ERROR - Null node");
//			return "<null/>";
//		}

		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();

			transformer.setOutputProperty("omit-xml-declaration", "yes");
			transformer.setOutputProperty("method", "xml");
			transformer.setOutputProperty("encoding", "ISO-8859-1");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.setOutputProperty("indent", "yes");
			java.io.StringWriter sw = new java.io.StringWriter();
			StreamResult sr = new StreamResult(sw);
			// Must strip out new lines and whitespace, because formatter thinks
			// this is content that should be preserved in pretty
			String xml = node.asXML().replaceAll(">\\s*(\\r|\\n)\\s*", ">")
					.replaceAll("\\s*(\\r|\\n)\\s*<", "<");

			// String xml = node.asXML();
			ByteArrayInputStream inputStream = new ByteArrayInputStream(xml
					.getBytes("UTF-8"));
			Source domSource = new StreamSource(inputStream);
			transformer.transform(domSource, sr);

			String prettyXml = sw.toString();

			// Although this looks like the same thing as above, it actually
			// isn't
//			if (oneLine) {
//				prettyXml = prettyXml.replaceAll(">\\s*(\\r|\\n)\\s*<", "><");
//			}

			return prettyXml;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static String HEAD2004 = 
		  "    <o:DocumentProperties> "
		+ "        <o:Author>Leonardo Correa</o:Author> "
		+ "        <o:LastAuthor>Leonardo Correa</o:LastAuthor> "
		+ "        <o:Revision>1</o:Revision> "
		+ "        <o:TotalTime>1</o:TotalTime> "
		+ "        <o:Created>2010-07-16T07:18:00Z</o:Created> "
		+ "        <o:LastSaved>2010-07-16T07:22:00Z</o:LastSaved> "
		+ "        <o:Pages>1</o:Pages> "
		+ "        <o:Words>0</o:Words> "
		+ "        <o:Characters>0</o:Characters> "
		+ "        <o:Lines>1</o:Lines> "
		+ "        <o:Paragraphs>1</o:Paragraphs> "
		+ "        <o:CharactersWithSpaces>0</o:CharactersWithSpaces> "
		+ "        <o:Version>12</o:Version> "
		+ "    </o:DocumentProperties> "
		+ "    <w:fonts> "
		+ "        <w:defaultFonts w:ascii=\"Cambria\" w:fareast=\"Cambria\" w:h-ansi=\"Cambria\" w:cs=\"Times New Roman\"/> "
		+ "        <w:font w:name=\"Times New Roman\"> "
		+ "            <w:panose-1 w:val=\"02020603050405020304\"/> "
		+ "            <w:charset w:val=\"00\"/> "
		+ "            <w:family w:val=\"auto\"/> "
		+ "            <w:pitch w:val=\"variable\"/> "
		+ "            <w:sig w:usb-0=\"00000003\" w:usb-1=\"00000000\" w:usb-2=\"00000000\" w:usb-3=\"00000000\" w:csb-0=\"00000001\" w:csb-1=\"00000000\"/> "
		+ "        </w:font> "
		+ "        <w:font w:name=\"Calibri\"> "
		+ "            <w:panose-1 w:val=\"020F0502020204030204\"/> "
		+ "            <w:charset w:val=\"00\"/> "
		+ "            <w:family w:val=\"auto\"/> "
		+ "            <w:pitch w:val=\"variable\"/> "
		+ "            <w:sig w:usb-0=\"00000003\" w:usb-1=\"00000000\" w:usb-2=\"00000000\" w:usb-3=\"00000000\" w:csb-0=\"00000001\" w:csb-1=\"00000000\"/> "
		+ "        </w:font> "
		+ "        <w:font w:name=\"Cambria\"> "
		+ "            <w:panose-1 w:val=\"02040503050406030204\"/> "
		+ "            <w:charset w:val=\"00\"/> "
		+ "            <w:family w:val=\"auto\"/> "
		+ "            <w:pitch w:val=\"variable\"/> "
		+ "            <w:sig w:usb-0=\"00000003\" w:usb-1=\"00000000\" w:usb-2=\"00000000\" w:usb-3=\"00000000\" w:csb-0=\"00000001\" w:csb-1=\"00000000\"/> "
		+ "        </w:font> "
		+ "    </w:fonts> "
		+ "    <w:styles> "
		+ "        <w:versionOfBuiltInStylenames w:val=\"2\"/> "
		+ "        <w:latentStyles w:defLockedState=\"off\" w:latentStyleCount=\"276\"> "
		+ "            <w:lsdException w:name=\"Normal\"/> "
		+ "            <w:lsdException w:name=\"heading 1\"/> "
		+ "            <w:lsdException w:name=\"heading 2\"/> "
		+ "            <w:lsdException w:name=\"heading 3\"/> "
		+ "            <w:lsdException w:name=\"heading 4\"/> "
		+ "            <w:lsdException w:name=\"heading 5\"/> "
		+ "            <w:lsdException w:name=\"heading 6\"/> "
		+ "            <w:lsdException w:name=\"heading 7\"/> "
		+ "            <w:lsdException w:name=\"heading 8\"/> "
		+ "            <w:lsdException w:name=\"heading 9\"/> "
		+ "            <w:lsdException w:name=\"toc 1\"/> "
		+ "            <w:lsdException w:name=\"toc 2\"/> "
		+ "            <w:lsdException w:name=\"toc 3\"/> "
		+ "            <w:lsdException w:name=\"toc 4\"/> "
		+ "            <w:lsdException w:name=\"toc 5\"/> "
		+ "            <w:lsdException w:name=\"toc 6\"/> "
		+ "            <w:lsdException w:name=\"toc 7\"/> "
		+ "            <w:lsdException w:name=\"toc 8\"/> "
		+ "            <w:lsdException w:name=\"toc 9\"/> "
		+ "            <w:lsdException w:name=\"caption\"/> "
		+ "            <w:lsdException w:name=\"Title\"/> "
		+ "            <w:lsdException w:name=\"Default Paragraph Font\"/> "
		+ "            <w:lsdException w:name=\"Subtitle\"/> "
		+ "            <w:lsdException w:name=\"Strong\"/> "
		+ "            <w:lsdException w:name=\"Emphasis\"/> "
		+ "            <w:lsdException w:name=\"Table Grid\"/> "
		+ "            <w:lsdException w:name=\"Placeholder Text\"/> "
		+ "            <w:lsdException w:name=\"No Spacing\"/> "
		+ "            <w:lsdException w:name=\"Light Shading\"/> "
		+ "            <w:lsdException w:name=\"Light List\"/> "
		+ "            <w:lsdException w:name=\"Light Grid\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 1\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 2\"/> "
		+ "            <w:lsdException w:name=\"Medium List 1\"/> "
		+ "            <w:lsdException w:name=\"Medium List 2\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 1\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 2\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 3\"/> "
		+ "            <w:lsdException w:name=\"Dark List\"/> "
		+ "            <w:lsdException w:name=\"Colorful Shading\"/> "
		+ "            <w:lsdException w:name=\"Colorful List\"/> "
		+ "            <w:lsdException w:name=\"Colorful Grid\"/> "
		+ "            <w:lsdException w:name=\"Light Shading Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Light List Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Light Grid Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 1 Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 2 Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Medium List 1 Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Revision\"/> "
		+ "            <w:lsdException w:name=\"List Paragraph\"/> "
		+ "            <w:lsdException w:name=\"Quote\"/> "
		+ "            <w:lsdException w:name=\"Intense Quote\"/> "
		+ "            <w:lsdException w:name=\"Medium List 2 Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 1 Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 2 Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 3 Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Dark List Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Colorful Shading Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Colorful List Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Colorful Grid Accent 1\"/> "
		+ "            <w:lsdException w:name=\"Light Shading Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Light List Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Light Grid Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 1 Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 2 Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Medium List 1 Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Medium List 2 Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 1 Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 2 Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 3 Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Dark List Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Colorful Shading Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Colorful List Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Colorful Grid Accent 2\"/> "
		+ "            <w:lsdException w:name=\"Light Shading Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Light List Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Light Grid Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 1 Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 2 Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Medium List 1 Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Medium List 2 Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 1 Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 2 Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 3 Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Dark List Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Colorful Shading Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Colorful List Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Colorful Grid Accent 3\"/> "
		+ "            <w:lsdException w:name=\"Light Shading Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Light List Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Light Grid Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 1 Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 2 Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Medium List 1 Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Medium List 2 Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 1 Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 2 Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 3 Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Dark List Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Colorful Shading Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Colorful List Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Colorful Grid Accent 4\"/> "
		+ "            <w:lsdException w:name=\"Light Shading Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Light List Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Light Grid Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 1 Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 2 Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Medium List 1 Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Medium List 2 Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 1 Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 2 Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 3 Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Dark List Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Colorful Shading Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Colorful List Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Colorful Grid Accent 5\"/> "
		+ "            <w:lsdException w:name=\"Light Shading Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Light List Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Light Grid Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 1 Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Medium Shading 2 Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Medium List 1 Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Medium List 2 Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 1 Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 2 Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Medium Grid 3 Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Dark List Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Colorful Shading Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Colorful List Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Colorful Grid Accent 6\"/> "
		+ "            <w:lsdException w:name=\"Subtle Emphasis\"/> "
		+ "            <w:lsdException w:name=\"Intense Emphasis\"/> "
		+ "            <w:lsdException w:name=\"Subtle Reference\"/> "
		+ "            <w:lsdException w:name=\"Intense Reference\"/> "
		+ "            <w:lsdException w:name=\"Book Title\"/> "
		+ "            <w:lsdException w:name=\"Bibliography\"/> "
		+ "            <w:lsdException w:name=\"TOC Heading\"/> "
		+ "        </w:latentStyles> "
		+ "        <w:style w:type=\"paragraph\" w:default=\"on\" w:styleId=\"Normal\"> "
		+ "            <w:name w:val=\"Normal\"/> "
		+ "            <w:rsid w:val=\"00D711DA\"/> "
		+ "            <w:rPr> "
		+ "                <wx:font wx:val=\"Cambria\"/> "
		+ "                <w:sz w:val=\"24\"/> "
		+ "                <w:sz-cs w:val=\"24\"/> "
		+ "                <w:lang w:val=\"EN-AU\" w:fareast=\"EN-US\" w:bidi=\"AR-SA\"/> "
		+ "            </w:rPr> "
		+ "        </w:style> "
		+ "        <w:style w:type=\"paragraph\" w:styleId=\"Heading1\"> "
		+ "            <w:name w:val=\"heading 1\"/> "
		+ "            <wx:uiName wx:val=\"Heading 1\"/> "
		+ "            <w:basedOn w:val=\"Normal\"/> "
		+ "            <w:next w:val=\"Normal\"/> "
		+ "            <w:link w:val=\"Heading1Char\"/> "
		+ "            <w:rsid w:val=\"00401F80\"/> "
		+ "            <w:pPr> "
		+ "                <w:keepNext/> "
		+ "                <w:keepLines/> "
		+ "                <w:spacing w:before=\"480\"/> "
		+ "                <w:outlineLvl w:val=\"0\"/> "
		+ "            </w:pPr> "
		+ "            <w:rPr> "
		+ "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\"/> "
		+ "                <wx:font wx:val=\"Calibri\"/> "
		+ "                <w:b/> "
		+ "                <w:b-cs/> "
		+ "                <w:color w:val=\"345A8A\"/> "
		+ "                <w:sz w:val=\"32\"/> "
		+ "                <w:sz-cs w:val=\"32\"/> "
		+ "            </w:rPr> "
		+ "        </w:style> "
		+ "        <w:style w:type=\"paragraph\" w:styleId=\"Heading2\"> "
		+ "            <w:name w:val=\"heading 2\"/> "
		+ "            <wx:uiName wx:val=\"Heading 2\"/> "
		+ "            <w:basedOn w:val=\"Normal\"/> "
		+ "            <w:next w:val=\"Normal\"/> "
		+ "            <w:link w:val=\"Heading2Char\"/> "
		+ "            <w:rsid w:val=\"00401F80\"/> "
		+ "            <w:pPr> "
		+ "                <w:keepNext/> "
		+ "                <w:keepLines/> "
		+ "                <w:spacing w:before=\"200\"/> "
		+ "                <w:outlineLvl w:val=\"1\"/> "
		+ "            </w:pPr> "
		+ "            <w:rPr> "
		+ "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\"/> "
		+ "                <wx:font wx:val=\"Calibri\"/> "
		+ "                <w:b/> "
		+ "                <w:b-cs/> "
		+ "                <w:color w:val=\"4F81BD\"/> "
		+ "                <w:sz w:val=\"26\"/> "
		+ "                <w:sz-cs w:val=\"26\"/> "
		+ "            </w:rPr> "
		+ "        </w:style> "
		+ "        <w:style w:type=\"paragraph\" w:styleId=\"Heading3\"> "
		+ "            <w:name w:val=\"heading 3\"/> "
		+ "            <wx:uiName wx:val=\"Heading 3\"/> "
		+ "            <w:basedOn w:val=\"Normal\"/> "
		+ "            <w:next w:val=\"Normal\"/> "
		+ "            <w:link w:val=\"Heading3Char\"/> "
		+ "            <w:rsid w:val=\"00401F80\"/> "
		+ "            <w:pPr> "
		+ "                <w:keepNext/> "
		+ "                <w:keepLines/> "
		+ "                <w:spacing w:before=\"200\"/> "
		+ "                <w:outlineLvl w:val=\"2\"/> "
		+ "            </w:pPr> "
		+ "            <w:rPr> "
		+ "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\"/> "
		+ "                <wx:font wx:val=\"Calibri\"/> "
		+ "                <w:b/> "
		+ "                <w:b-cs/> "
		+ "                <w:color w:val=\"4F81BD\"/> "
		+ "            </w:rPr> "
		+ "        </w:style> "
		+ "        <w:style w:type=\"character\" w:default=\"on\" w:styleId=\"DefaultParagraphFont\"> "
		+ "            <w:name w:val=\"Default Paragraph Font\"/> "
		+ "        </w:style> "
		+ "        <w:style w:type=\"table\" w:default=\"on\" w:styleId=\"TableNormal\"> "
		+ "            <w:name w:val=\"Normal Table\"/> "
		+ "            <wx:uiName wx:val=\"Table Normal\"/> "
		+ "            <w:rPr> "
		+ "                <wx:font wx:val=\"Cambria\"/> "
		+ "                <w:lang w:val=\"EN-AU\" w:fareast=\"EN-US\" w:bidi=\"AR-SA\"/> "
		+ "            </w:rPr> "
		+ "            <w:tblPr> "
		+ "                <w:tblInd w:w=\"0\" w:type=\"dxa\"/> "
		+ "                <w:tblCellMar> "
		+ "                    <w:top w:w=\"0\" w:type=\"dxa\"/> "
		+ "                    <w:left w:w=\"108\" w:type=\"dxa\"/> "
		+ "                    <w:bottom w:w=\"0\" w:type=\"dxa\"/> "
		+ "                    <w:right w:w=\"108\" w:type=\"dxa\"/> "
		+ "                </w:tblCellMar> "
		+ "            </w:tblPr> "
		+ "        </w:style> "
		+ "        <w:style w:type=\"list\" w:default=\"on\" w:styleId=\"NoList\"> "
		+ "            <w:name w:val=\"No List\"/> "
		+ "        </w:style> "
		+ "        <w:style w:type=\"character\" w:styleId=\"Heading1Char\"> "
		+ "            <w:name w:val=\"Heading 1 Char\"/> "
		+ "            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
		+ "            <w:link w:val=\"Heading1\"/> "
		+ "            <w:rsid w:val=\"00401F80\"/> "
		+ "            <w:rPr> "
		+ "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\" w:cs=\"Times New Roman\"/> "
		+ "                <w:b/> "
		+ "                <w:b-cs/> "
		+ "                <w:color w:val=\"345A8A\"/> "
		+ "                <w:sz w:val=\"32\"/> "
		+ "                <w:sz-cs w:val=\"32\"/> "
		+ "                <w:lang w:val=\"EN-AU\"/> "
		+ "            </w:rPr> "
		+ "        </w:style> "
		+ "        <w:style w:type=\"character\" w:styleId=\"Heading2Char\"> "
		+ "            <w:name w:val=\"Heading 2 Char\"/> "
		+ "            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
		+ "            <w:link w:val=\"Heading2\"/> "
		+ "            <w:rsid w:val=\"00401F80\"/> "
		+ "            <w:rPr> "
		+ "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\" w:cs=\"Times New Roman\"/> "
		+ "                <w:b/> "
		+ "                <w:b-cs/> "
		+ "                <w:color w:val=\"4F81BD\"/> "
		+ "                <w:sz w:val=\"26\"/> "
		+ "                <w:sz-cs w:val=\"26\"/> "
		+ "                <w:lang w:val=\"EN-AU\"/> "
		+ "            </w:rPr> "
		+ "        </w:style> "
		+ "        <w:style w:type=\"character\" w:styleId=\"Heading3Char\"> "
		+ "            <w:name w:val=\"Heading 3 Char\"/> "
		+ "            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
		+ "            <w:link w:val=\"Heading3\"/> "
		+ "            <w:rsid w:val=\"00401F80\"/> "
		+ "            <w:rPr> "
		+ "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\" w:cs=\"Times New Roman\"/> "
		+ "                <w:b/> "
		+ "                <w:b-cs/> "
		+ "                <w:color w:val=\"4F81BD\"/> "
		+ "                <w:lang w:val=\"EN-AU\"/> "
		+ "            </w:rPr> "
		+ "        </w:style> "
		
		//HEADER_N_FOOTER_STYLE
		+"        <w:style w:type=\"list\" w:default=\"on\" w:styleId=\"NoList\"> "
		+"            <w:name w:val=\"No List\"/> "
		+"        </w:style> "		
		
		+ "        <w:style w:type=\"paragraph\" w:styleId=\"Header\"> "
		+"            <w:name w:val=\"header\"/> "
		+"            <wx:uiName wx:val=\"Header\"/> "
		+"            <w:basedOn w:val=\"Normal\"/> "
		+"            <w:link w:val=\"HeaderChar\"/> "
		+"            <w:rsid w:val=\"00B5709B\"/> "
		+"            <w:pPr> "
		+"                <w:tabs> "
		+"                    <w:tab w:val=\"center\" w:pos=\"4320\"/> "
		+"                    <w:tab w:val=\"right\" w:pos=\"8640\"/> "
		+"                </w:tabs> "
		+"            </w:pPr> "
		+"            <w:rPr> "
		+"                <wx:font wx:val=\"Cambria\"/> "
		+"            </w:rPr> "
		+"        </w:style> "
		+"        <w:style w:type=\"character\" w:styleId=\"HeaderChar\"> "
		+"            <w:name w:val=\"Header Char\"/> "
		+"            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
		+"            <w:link w:val=\"Header\"/> "
		+"            <w:rsid w:val=\"00B5709B\"/> "
		+"            <w:rPr> "
		+"                <w:sz w:val=\"24\"/> "
		+"                <w:sz-cs w:val=\"24\"/> "
		+"            </w:rPr> "
		+"        </w:style> "
		+"        <w:style w:type=\"paragraph\" w:styleId=\"Footer\"> "
		+"            <w:name w:val=\"footer\"/> "
		+"            <wx:uiName wx:val=\"Footer\"/> "
		+"            <w:basedOn w:val=\"Normal\"/> "
		+"            <w:link w:val=\"FooterChar\"/> "
		+"            <w:rsid w:val=\"00B5709B\"/> "
		+"            <w:pPr> "
		+"                <w:tabs> "
		+"                    <w:tab w:val=\"center\" w:pos=\"4320\"/> "
		+"                    <w:tab w:val=\"right\" w:pos=\"8640\"/> "
		+"                </w:tabs> "
		+"            </w:pPr> "
		+"            <w:rPr> "
		+"                <wx:font wx:val=\"Cambria\"/> "
		+"            </w:rPr> "
		+"        </w:style> "
		+"        <w:style w:type=\"character\" w:styleId=\"FooterChar\"> "
		+"            <w:name w:val=\"Footer Char\"/> "
		+"            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
		+"            <w:link w:val=\"Footer\"/> "
		+"            <w:rsid w:val=\"00B5709B\"/> "
		+"            <w:rPr> "
		+"                <w:sz w:val=\"24\"/> "
		+"                <w:sz-cs w:val=\"24\"/> "
		+"            </w:rPr> "
		+"        </w:style> "
		+"        <w:style w:type=\"character\" w:styleId=\"PageNumber\"> "
		+"            <w:name w:val=\"page number\"/> "
		+"            <wx:uiName wx:val=\"Page Number\"/> "
		+"            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
		+"            <w:rsid w:val=\"009F65CC\"/> "
		+"        </w:style> "		
		
		+ "    </w:styles> "
		+ "    <w:docPr> "
		+ "        <w:view w:val=\"print\"/> "
		+ "        <w:zoom w:percent=\"100\"/> "
		+ "        <w:proofState w:spelling=\"clean\" w:grammar=\"clean\"/> "
		+ "        <w:defaultTabStop w:val=\"720\"/> "
		+ "        <w:drawingGridHorizontalSpacing w:val=\"360\"/> "
		+ "        <w:drawingGridVerticalSpacing w:val=\"360\"/> "
		+ "        <w:displayHorizontalDrawingGridEvery w:val=\"0\"/> "
		+ "        <w:displayVerticalDrawingGridEvery w:val=\"0\"/> "
		+ "        <w:punctuationKerning/> "
		+ "        <w:characterSpacingControl w:val=\"DontCompress\"/> "
		+ "        <w:allowPNG/> "
		+ "        <w:doNotSaveWebPagesAsSingleFile/> "
		+ "        <w:savePreviewPicture/> "
		+ "        <w:validateAgainstSchema/> "
		+ "        <w:saveInvalidXML w:val=\"off\"/> "
		+ "        <w:ignoreMixedContent w:val=\"off\"/> "
		+ "        <w:alwaysShowPlaceholderText w:val=\"off\"/> "
		+ "        <w:compat> "
		+ "            <w:breakWrappedTables/> "
		+ "            <w:snapToGridInCell/> "
		+ "            <w:wrapTextWithPunct/> "
		+ "            <w:useAsianBreakRules/> "
		+ "            <w:dontGrowAutofit/> "
		+ "        </w:compat> "
		+ "        <wsp:rsids> "
		+ "            <wsp:rsidRoot wsp:val=\"00401F80\"/> "
		+ "        </wsp:rsids> "
		+ "    </w:docPr> ";

	
}
