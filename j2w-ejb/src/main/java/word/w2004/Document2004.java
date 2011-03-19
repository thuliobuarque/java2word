package word.w2004;

import word.api.interfaces.IBody;
import word.api.interfaces.IDocument;
import word.api.interfaces.IElement;
import word.api.interfaces.IFooter;
import word.api.interfaces.IHead;
import word.api.interfaces.IHeader;

/**
 * 
 * @author leonardo_correa
 *	W2004 W2004 W2004 W2004 W2004 W2004 W2004 W2004 	
 *	W2004 W2004 W2004 W2004 W2004 W2004 W2004 W2004
 *	W2004 W2004 W2004 W2004 W2004 W2004 W2004 W2004  
 *
 */
public class Document2004 implements IDocument, IElement{

	private StringBuilder txt = new StringBuilder();
	private IHead head = new Head2004();
	private IBody body = new Body2004();
	private boolean isLandscape = false;
	
	private boolean hasBeenCalledBefore = false; // if getContent has already been called, I cached the result for future invocations
	
	public String getUri() {
		String uri = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
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
		return uri;
	}

	public String getContent() {
		if(hasBeenCalledBefore ){
			return txt.toString();	
		}else{
			hasBeenCalledBefore = true;
		}
		txt.append(this.getUri());
		txt.append(this.getHead().getContent());

		txt.append(this.getBody().getContent());
		
		txt.append("\n</w:wordDocument>");
		
		String finalString = setUpPageOrientation(txt.toString());
		
		return finalString;
	}
	
	private String setUpPageOrientation(String txt) {
        if(this.isLandscape) {
            String orientation = "    <w:sectPr wsp:rsidR=\"00F04FB2\" wsp:rsidSect=\"00146B2A\">\n"
                + "      <w:pgSz w:w=\"16834\" w:h=\"11904\" w:orient=\"landscape\"/>\n"
                + "      <w:pgMar w:top=\"1800\" w:right=\"1440\" w:bottom=\"1800\" w:left=\"1440\" w:header=\"708\" w:footer=\"708\" w:gutter=\"0\"/>\n"
                + "      <w:cols w:space=\"708\"/>\n" + "    </w:sectPr>";
            txt = txt.replace("</w:body>", orientation + "\n</w:body>");  
        }
        return txt;
	}
	
	public void setPageOrientationLandscape() {
	    this.isLandscape = true;
	}
	
	//### Getters and Setters
	public IBody getBody() {
		return this.body;
	}
	public IHead getHead() {
		return this.head;
	}	
	public IFooter getFooter() {//forward it to the body
		return this.getBody().getFooter();
	}
	public IHeader getHeader() {
		return this.getBody().getHeader(); //forward it to the body
	}

	/**
	 * This is an alias to 'getBody().addEle' 
	 */
	@Override
	public void addEle(IElement e) {
		this.getBody().addEle(e);
	}

	/**
	 * This is an alias to 'getBody().addEle' 
	 */
	@Override
	public void addEle(String str) {
		this.getBody().addEle(str);
	}

	
	@Override
	public String toString() {	 
	    return this.getContent();
	}
}
