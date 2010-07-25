package word.api.interfaces;

public interface IDocument extends IElement{

	public String getUri();
	
	public IBody getBody();	
//	public void setBody(IBody body);

	public IHead getHead();
//	public void setHead(IHead head);
	
	public IHeader getHeader();
//	public void setHeader(IHeader header);
	
	public IFooter getFooter();
//	public void setFooter(IFooter footer);
	
}
