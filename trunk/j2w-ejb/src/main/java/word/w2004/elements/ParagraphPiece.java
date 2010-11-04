package word.w2004.elements;

import word.api.interfaces.IElement;
import word.w2004.style.ParagraphStyle;

public class ParagraphPiece implements IElement{
	
	private String value = "";
	private ParagraphStyle style;
	
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
		if("".equals(this.value)){
			return "";
		}
		
		txt = applyStyle(txt); //For convention, it should be the last thing before returning the xml content.
		
		return txt.replace("{value}", this.value);
	}
	
	
	//This is gonna be refactored soon...
	protected String applyStyle(String txt) {
		if(style != null){
			txt = this.style.getNewContentWithStyle(txt);
		}else{ 
			//clean up {styleXXXXX} place holders...
			txt = txt.replaceAll("[{]style(.*)[}]", "");
		}
			
		return txt;
	}
	
	
	//### Gettets and Setters
	public ParagraphStyle getStyle() {
		return style;
	}
	public void setStyle(ParagraphStyle style) {
		this.style = style;
	}
	
}
