package word.w2004.elements;

import word.api.interfaces.IElement;

/*
 *  It inserts a Page break at the point you add this class to the document.
 * 
 */
public class PageBreak implements IElement{

	public String getContent() {
		return "\n<w:br w:type=\"page\" />";
	}
	
}
