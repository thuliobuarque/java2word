package word.w2004.style;

import word.api.interfaces.ISuperStylin;


/**
 * @author anyone
 * 
 */
public class ParagraphPieceStyle implements ISuperStylin{

	private boolean bold = false;
	private boolean italic = false;
	private boolean underline = false;

	/**
	 * This implementation will ONLY apply superStylin to the WHOLE element. 
	 */
	@Override
	public String getNewContentWithStyle(String txt) {
		
		StringBuilder sbText = new StringBuilder("");
        if (bold || italic || underline) {
            sbText.append("\n	 <w:rPr>");
            if(bold) sbText.append("\n            	<w:b/>");
            if(italic) sbText.append("\n            	<w:i/>");
            if(underline) sbText.append("\n			<w:u w:val=\"single\"/>");
            sbText.append("\n	 </w:rPr>");
        }
        
        txt = txt.replace("{styleText}", sbText.toString());//Convention: apply styles
        txt = txt.replaceAll("[{]style(.*)[}]", ""); //Convention: replace unused styles after... 
        
		return txt;
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
	public boolean isUnderline() {
		return underline;
	}
	public void setUnderline(boolean underline) {
		this.underline = underline;
	}
	
}
