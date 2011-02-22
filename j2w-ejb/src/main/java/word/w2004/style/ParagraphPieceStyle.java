package word.w2004.style;

import word.api.interfaces.ISuperStylin;
import word.w2004.elements.ParagraphPiece;


/**
 * @author anyone
 * 
 */
public class ParagraphPieceStyle extends AbstractStyle implements ISuperStylin{

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
		StringBuilder style = new StringBuilder("");

		doStyleBold(style);
        
        doStyleItalic(style);
        
        doStyleUnderline(style);
        
        doStyleTextColorHexa(style);
        
        doStyleColorEnum(style);
        
		doStyleFont(style);

		return doStyleReplacement(style, txt);
	}

	private void doStyleBold(StringBuilder style) {
		if(this.bold) style.append("\n            	<w:b/>");
	}

	private void doStyleItalic(StringBuilder style) {
		if(this.italic) style.append("\n            	<w:i/>");
	}

	private void doStyleUnderline(StringBuilder style) {
		if(this.underline) style.append("\n			<w:u w:val=\"single\"/>");
	}

	private void doStyleTextColorHexa(StringBuilder style) {
		if(!this.textColor.equals("")) {
        	style.append("\n			<w:color w:val=\"" + this.textColor + "\"/>");
        }
	}

	private void doStyleColorEnum(StringBuilder style) {
		if(this.color != null && !this.color.getValue().equals("")) { 
        	style.append("\n			<w:color w:val=\"" + color.getValue() + "\"/>");
        }
	}

	private void doStyleFont(StringBuilder style) {
		if(this.font != null) {
			style.append("			<w:rFonts w:ascii=\"" + font.getValue() + "\" w:h-ansi=\"" + font.getValue() + "\"/>\n"); 
			style.append("			<wx:font wx:val=\"" + font.getValue() + "\"/>");
		}
	}
	
	private String doStyleReplacement(StringBuilder style, String txt) {
		if(!"".equals(style.toString())) {
			style.insert(0, "\n	 <w:rPr>");
			style.append("\n	 </w:rPr>");
	        txt = txt.replace("{styleText}", style.toString());//Convention: apply styles
	        txt = txt.replaceAll("[{]style(.*)[}]", ""); //Convention: replace unused styles after...
		}
		return txt;
	}
	
	//### Getters and setters... ###
	
	/**
	 * 
	 * This is the ParagraphPiece! I am using Covariant Return Type!!! 
	 * to be honest, I have never thought how to use and finally here we go!!!
	 * It will give the chance to eliminate the necessity of type cast for elements.
	 * 
	 */
	@Override
	public ParagraphPiece create() {
		return (ParagraphPiece) super.create();
	}
	
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
	
	/**
	 * If you know the color code, just to straight to the point! Eg.:
	 * yellow: FFFF00, black: 000000, red: FF0000, blue: 0000FF, green: 008000, etc...
	 * 
	 * If you want, you can use the class Color.whatever_color.
	 * 
	 * @param hexadecimal color code 
	 */
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
