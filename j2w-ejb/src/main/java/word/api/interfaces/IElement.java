package word.api.interfaces;

/**
 * @author leonardo_correa
 *
 */
public interface IElement {

	/**
	 * Important: Once you call this method, the Document value is cached an no elements can be added leter. 
	 * @return this is the String value of the element ready to be appended/inserted in the Document
	 */
	public String getContent();
	
}
