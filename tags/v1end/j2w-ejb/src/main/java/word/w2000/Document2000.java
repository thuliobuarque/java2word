package word.w2000;

import org.apache.log4j.Logger;

import word.api.interfaces.IBody;
import word.api.interfaces.IDocument;
import word.api.interfaces.IFooter;
import word.api.interfaces.IHead;
import word.api.interfaces.IHeader;

public class Document2000 implements IDocument{

	static Logger log = Logger.getLogger(Document2000.class);

	private StringBuilder txt = new StringBuilder();
	private IHead head = new Head2000();
	private IBody body = new Body2000();
	private boolean hasBeenCalledBefore = false;

	public String getUri() {
		String uri = "<html "
				+ "xmlns:v=\"urn:schemas-microsoft-com:vml\" "
				+ "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
				+ "xmlns:w=\"urn:schemas-microsoft-com:office:word\" "
				+ "xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" "
				//+ "xmlns:css=\"http://macVmlSchemaUri\" "
				+ "xmlns=\"http://www.w3.org/TR/REC-html40\">" + "\n";
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
		txt.append("\n</html>");
		return txt.toString();
	}	
	
	
	//### Getters and Setters #####
	public IBody getBody() {
		return body;
	}
	public IHead getHead() {
		return head;
	}
	public IFooter getFooter() {
		// TODO Auto-generated method stub
		return null;
	}
	public IHeader getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

}
