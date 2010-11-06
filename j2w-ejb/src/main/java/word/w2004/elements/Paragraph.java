package word.w2004.elements;

import word.api.interfaces.IElement;
import word.w2004.style.ParagraphPieceStyle;

public class Paragraph implements IElement{

	private ParagraphPiece[] pieces;
	
	//Constructor
	public Paragraph(String value) {
		if(value == null || "".equals(value)){
			return;
		}
			ParagraphPiece piece = new ParagraphPiece(value);
			pieces = new ParagraphPiece[1];
			pieces[0] = piece;
	}
	
	public Paragraph(ParagraphPiece ... pieces) {
		this.pieces = pieces;
	}

	public String getContent() {
		if(pieces == null || pieces.length == 0){
			return "";
		}

		StringBuilder sb = new StringBuilder("");
		for (ParagraphPiece piece : pieces) {
			sb.append(piece.getContent());
		}
		
		String txt = 
			"	<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">" 
			+"\n		{value}"
			+"\n	</w:p>";
		
		if("".equals(sb.toString())){ //if there is no content in the pieces, there is no return - just empty string. 
			return "";
		}else{
			return txt.replace("{value}", sb.toString());
		}
	}


}
