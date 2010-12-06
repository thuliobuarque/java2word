package word.api.interfaces;


public interface IDocument extends IHasElement{

	public String getUri();

	public IBody getBody();

	public IHead getHead();

	public IHeader getHeader();

	public IFooter getFooter();

	
}
