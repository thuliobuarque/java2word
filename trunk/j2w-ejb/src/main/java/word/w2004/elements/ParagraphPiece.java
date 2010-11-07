package word.w2004.elements;

import word.api.interfaces.IElement;
import word.w2004.style.ParagraphPieceStyle;

public class ParagraphPiece implements IElement{
	
	private String value = "";
	private ParagraphPieceStyle style = new ParagraphPieceStyle();
	
	String txt =
	 "\n		<w:r>"
	+"\n			{styleText}"
	+"\n			<w:t>{value}</w:t>"
	+"\n		</w:r>";

	//Constructor
	public ParagraphPiece(String value) {
		this.value = value;
	}
	
	@Override
	public String getContent() {
		if("".equals(this.value) || this.value == null){ // null is very unusual. That the reason null comparison is after empty verification. I am not sure if we use ApacheUtils we can achieve the same  
			return "";
		}
		
		//For convention, it should be the last thing before returning the xml content.
		txt = style.getNewContentWithStyle(txt);
		
		return txt.replace("{value}", this.value);
	}
	
	//### Gettets and Setters
	public ParagraphPieceStyle getStyle() {
		return style;
	}
	public void setStyle(ParagraphPieceStyle style) {
		this.style = style;
	}
	
}
