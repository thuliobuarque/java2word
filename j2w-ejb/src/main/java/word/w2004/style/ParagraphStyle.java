package word.w2004.style;

import word.api.interfaces.ISuperStylin;


/**
 * @author anyone
 * 
 */
public class ParagraphStyle implements ISuperStylin{

	private boolean bold = false;
	private boolean italic = false;

	/**
	 * This implementation will ONLY apply superStylin to the WHOLE element. 
	 */
	@Override
	public String getNewContentWithStyle(String txt) {
		
		StringBuilder sbText = new StringBuilder("");
        if (bold || italic) {
            sbText.append("\n	 <w:rPr>");
            if(bold) sbText.append("\n            	<w:b/>");
            if(italic) sbText.append("\n            	<w:i/>");
            sbText.append("\n	 </w:rPr>");
        }
		
		return txt.replace("{styleText}", sbText.toString());
	}
	
	//### Getters and setters... ###
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	public boolean isItalic() {
		return italic;
	}
	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	
}
