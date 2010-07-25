package word.w2004.elements;

import word.api.interfaces.IElement;

public class PageBreak implements IElement{

	public String getContent() {
		return "\n<w:br w:type=\"page\" />";
	}
	
}
