package word.api.interfaces;

/**
 * The main interface for documents MS Word 2004+.
 * 
 * @author leonardo
 * 
 */
public interface IDocument extends IHasElement {

	/**
	 * @return the URI ready to be added to the document
	 */
	String getUri();

	/**
	 * @return the body of the document
	 */
	IBody getBody();

	/**
	 * @return the head that contains uri
	 */
	IHead getHead();

	/**
	 * @return the header that may contain other elements
	 */
	IHeader getHeader();

	/**
	 * @return the Footer that may contain other elements
	 */
	IFooter getFooter();

}
