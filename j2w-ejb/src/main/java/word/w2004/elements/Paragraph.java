package word.w2004.elements;

import word.api.interfaces.IElement;
import word.w2004.style.ParagraphStyle;

public class Paragraph implements IElement{

//	private String value = "";
//	private ParagraphStyle style;
	private ParagraphPiece[] pieces;
	
	//Constructor
	public Paragraph(String value) {
		ParagraphPiece piece = new ParagraphPiece(value);
		pieces = new ParagraphPiece[1];
		pieces[0] = piece;
		//this.value = value;
	}
	
	public Paragraph(ParagraphPiece ... pieces) {
		this.pieces = pieces;
	}

	public String getContent() {
//		if("".equals(this.value)){
//			return "";
//		}
		if(pieces.length == 0){
			return "";
		}

		StringBuilder sb = new StringBuilder("");
		for (ParagraphPiece piece : pieces) {
			sb.append(piece.getContent());
		}
		
//		String txt = 
//			"	<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">" 
//		+"\n		<w:r>"
//		+"\n			{styleText}"
//		+"\n			<w:t>{value}</w:t>"
//		+"\n		</w:r>"
//		+"\n	</w:p>";
		
		String txt = 
			"	<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">" 
			+"\n		{value}"
			+"\n	</w:p>";
		
//		txt = applyStyle(txt); //For convention, it should be the last thing before returning the xml content.
		
		return txt.replace("{value}", sb.toString());
	}

	
	//This is gonna be refactored soon...
/*	
	protected String applyStyle(String txt) {
		if(style != null){
			txt = this.style.getNewContentWithStyle(txt);
		}else{ 
			//clean up {styleXXXXX} place holders...
			txt = txt.replaceAll("[{]style(.*)[}]", "");
		}
			
		return txt;
	}
*/	
	
	//### Gettets and Setters
//	public ParagraphStyle getStyle() {
//		return style;
//	}
//	public void setStyle(ParagraphStyle style) {
//		this.style = style;
//	}

	
	//this is the final result:
	
	String txt222 = 
		"	<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">" 
	+"\n		<w:r>"
	+"\n			{styleText}"
	+"\n			<w:t>{value}</w:t>"
	+"\n		</w:r>"
	+"\n		<w:r>"
	+"\n			<w:rPr>\n"  
	+"\n	          <w:b/>\n"  
	+"\n	       </w:rPr>"
	+"\n			<w:t>I am bold!!!</w:t>"
	+"\n		</w:r>"
	+"\n	</w:p>";
	
}
