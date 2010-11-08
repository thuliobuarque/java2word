package word.api.interfaces;


/**
 * @author no one
 * 
 * I invented the word "Stylable"
 *
 */
public interface IFluentElementStylable <S>{

	/**
	 * This method returns style for the element. The element knows who is his style class, but the style doesn't.
	 * This method will do this:
	 * 	1) set up the itself to the style class
	 *      this.getStyle().setElement(this); //, Heading1.class
	 *  2) Return the style class    
	 *	return this.getStyle();
	 *
	 */
	public S withStyle();
	
}
