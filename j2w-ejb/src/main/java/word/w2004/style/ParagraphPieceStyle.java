package word.w2004.style;

import word.api.interfaces.ISuperStylin;


/**
 * @author anyone
 * 
 */
public class ParagraphPieceStyle extends AbrstractStyle implements ISuperStylin{

	private boolean bold = false;
	private boolean italic = false;
	private boolean underline = false;
	private String textColor = "";
	private Color color;
	private Font font;
	
	/**
	 * This implementation will ONLY apply superStylin to the WHOLE element. 
	 */
	@Override
	public String getNewContentWithStyle(String txt) {
		
		StringBuilder sbText = new StringBuilder("");
        if (bold || italic || underline || !textColor.equals("") || color != null || this.font != null) {
            sbText.append("\n	 <w:rPr>");
            if(bold) sbText.append("\n            	<w:b/>");
            if(italic) sbText.append("\n            	<w:i/>");
            if(underline) sbText.append("\n			<w:u w:val=\"single\"/>");
            
            if(!textColor.equals("")) sbText.append("\n			<w:color w:val=\"" + textColor + "\"/>");
            if(this.color != null && !this.color.getValue().equals("")) sbText.append("\n			<w:color w:val=\"" + color.getValue() + "\"/>");
            
    		if(this.font != null) {
//    			"		{styleFont}\n	" +
//    			String fontTxt = 
    			sbText.append("			<w:rFonts w:ascii=\"" + font.getValue() + "\" w:h-ansi=\"" + font.getValue() + "\"/>\n"); 
    			sbText.append("			<wx:font wx:val=\"" + font.getValue() + "\"/>");
    		}
            
            sbText.append("\n	 </w:rPr>");
        }
        
        txt = txt.replace("{styleText}", sbText.toString());//Convention: apply styles
        txt = txt.replaceAll("[{]style(.*)[}]", ""); //Convention: replace unused styles after... 
        
		return txt;
	}
	
	//### Getters and setters... ###
//	public boolean isBold() {
//		return bold;
//	}
	public ParagraphPieceStyle setBold(boolean bold) {
		this.bold = bold;
		return this;
	}
//	public boolean isItalic() {
//		return italic;
//	}
	public ParagraphPieceStyle setItalic(boolean italic) {
		this.italic = italic;
		return this;
	}
//	public boolean isUnderline() {
//		return underline;
//	}
	public ParagraphPieceStyle setUnderline(boolean underline) {
		this.underline = underline;
		return this;
	}
//	public String getTextColor() {
//		return textColor;
//	}
	public ParagraphPieceStyle setTextColor(String textColor) {
		this.textColor = textColor;
		return this;
	}
	public ParagraphPieceStyle setTextColor(Color color) {
		this.color = color;
		return this;
	}
	
	public ParagraphPieceStyle setFont(Font font) {
		this.font = font;
		return this;
	}

	//### Enums ###
	public enum Font { 
		COURIER("Courier");
		private String value;

		Font(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
}