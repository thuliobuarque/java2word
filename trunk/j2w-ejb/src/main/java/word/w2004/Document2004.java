package word.w2004;

import org.apache.log4j.Logger;

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


	static Logger log = Logger.getLogger(Document2004.class);

	private StringBuilder txt = new StringBuilder();
	private IHead head = new Head2004();
	private IBody body = new Body2004();
	
	private boolean hasBeenCalledBefore = false; // if getContent has already been called, I cached the result for future invocations
	
	public String getUri() {
		String uri = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
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
		
		//txt.append(this.getHeader().getContent());
	
		
		txt.append("\n</w:wordDocument>");
	
		
		return txt.toString();
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

}