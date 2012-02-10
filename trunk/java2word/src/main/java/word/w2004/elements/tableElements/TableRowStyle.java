package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.ISuperStylin;

public class TableRowStyle implements ISuperStylin{
	
	private IElement element;
	StringBuilder style = new StringBuilder("");
	
	private boolean bold = false;
	private String bgColor = "";
	
	@Override
	public String getNewContentWithStyle(String txt) {
		
		txt = doStyleBold(style, txt);
		doStyleBgColor(style);
		
		if(!"".equals(style.toString())){
			style.insert(0, "\n<w:trPr>");
			style.append("\n</w:trPr>\n");			
		}
		
		return txt.replace("{styleRowPh}", style);
	}

	@Override
	public void setElement(IElement element) {
		this.element = element;	
	}

	
	@Override
	public TableRowV2 create() {
		/**
		 *  This is Covariant Return if you wanna know. No many people use it because the need is pretty rare. 
		 *  I am returning a subtype of the IElement when overriding the method.  
		 */
		return (TableRowV2) this.element;
	}

	
	//### Useful external methods ############################
    /**
     * Set the text to Bold
     * @return
     */
    public TableRowStyle bold() {
    	//TODO: This doesn't work so trying to apply style to all paragraphs
    	
        this.bold = true;
        return this;
    }
    
    public TableRowStyle bgColor(String bgColor) {
		this.bgColor = bgColor;
		return this;
	}
    
	
	//### Chunk of code ######################################
    private String doStyleBold(StringBuilder style, String txt) { //bold can only be applied to "run|rPr" not "pPr" 
        if (bold ) {
            //style.append("\n            	<w:b/>");
            txt = txt.replace("<w:r>", "<w:r>\n	<w:rPr>\n            	<w:b/>\n	</w:rPr>\n"); //hardcode aplying style to the Paragraph
        }
        return txt;
    }

    private void doStyleBgColor(StringBuilder style) {
    	if (!"".equals(bgColor)) {
    		style.append("\n            	<w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"" + this.bgColor + "\"/>\n");
    	}
    }
	
}
