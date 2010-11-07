package word.w2004.style;

import word.api.interfaces.ISuperStylin;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.ParagraphPieceStyle.Color;


/**
 * @author anyone
 * 
 */
public class ParagraphPieceStyle implements ISuperStylin{

	private boolean bold = false;
	private boolean italic = false;
	private boolean underline = false;
	private String textColor = "";
	private Color color;

	/**
	 * This implementation will ONLY apply superStylin to the WHOLE element. 
	 */
	@Override
	public String getNewContentWithStyle(String txt) {
		
		StringBuilder sbText = new StringBuilder("");
        if (bold || italic || underline || !textColor.equals("") || color != null) {
            sbText.append("\n	 <w:rPr>");
            if(bold) sbText.append("\n            	<w:b/>");
            if(italic) sbText.append("\n            	<w:i/>");
            if(underline) sbText.append("\n			<w:u w:val=\"single\"/>");
            
            if(!textColor.equals("")) sbText.append("\n			<w:color w:val=\"" + textColor + "\"/>");
            if(this.color != null && !this.color.getValue().equals("")) sbText.append("\n			<w:color w:val=\"" + color.getValue() + "\"/>");
            
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
	public String getTextColor() {
		return textColor;
	}
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
	public void setTextColor(Color color) {
		this.color = color;
	}
	

	//## COLORS ###
	//Yellow: 
	public enum Color { 
		YELLOW("FFFF00"), BLACK("000000"), RED("FF0000"), BLUE("0000FF"), GREEN("008000");
		private String value;
		Color(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

}
