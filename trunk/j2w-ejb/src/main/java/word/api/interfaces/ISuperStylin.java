package word.api.interfaces;

/**
 * @author no one
 *
 */
public interface ISuperStylin {

	/**
	 *	This method will called by IElement.getContent();
	 */
	public String getNewContentWithStyle(String txt);
	
	/**
	 * 
	 *
	 */
	public void setElement(IElement element);
	
	/**
	 * This method returns the element. There should be a cast for the return
	 *
	 */
	public IElement create();
	
}
