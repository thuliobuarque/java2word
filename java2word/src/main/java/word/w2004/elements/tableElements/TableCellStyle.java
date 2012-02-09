package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.ISuperStylin;

public class TableCellStyle implements ISuperStylin{
	
	private IElement element;
	StringBuilder style = new StringBuilder("");
	
	private boolean bold = false;
	
	@Override
	public String getNewContentWithStyle(String txt) {
		style.append("<w:tcPr>");
		
		//do all styles
		doStyleBold(style);
		
		style.append("</w:tcPr>");
		return txt.replace("{styleCellPh}", style);
	}

	@Override
	public void setElement(IElement element) {
		this.element = element;		
	}

	@Override
	public IElement create() {
		return this.element;
	}

	//### Useful external methods ###
    /**
     * Set the text to Bold
     * @return
     */
    public TableCellStyle bold() {
        this.bold = true;
        return this;
    }	
	
	//### Chunk of code ###
    private void doStyleBold(StringBuilder style) {
        if (bold ) {
            style.append("\n            	<w:b/>");
        }
    }
    
}
