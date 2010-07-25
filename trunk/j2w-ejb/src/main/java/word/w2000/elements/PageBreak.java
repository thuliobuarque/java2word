package word.w2000.elements;

import word.api.interfaces.IElement;

public class PageBreak implements IElement{

	public String getContent() {
		return "<br clear=ALL style='page-break-before:always'>";
	}
	
}
