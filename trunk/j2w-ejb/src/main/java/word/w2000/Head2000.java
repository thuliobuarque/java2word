package word.w2000;

import word.api.interfaces.IHead;

public class Head2000 implements IHead {

	StringBuilder txt = new StringBuilder("");

	private String style = "\n<style>"
			+ "\nbody{ font-family: Arial,Helvetica,sans-serif; }" 
			//+ Utils.readFile("/tmpCorrupto/app02/j2w-ejb/src/main/java/word/utils/basicStyle.txt")
			+ "\n</style>";

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	private String wwordDocument = "\n<!--[if gte mso 9]>" + "<xml>"
			+ "\n<w:WordDocument>" + "\n<w:View>Print</w:View>"
			+ "\n<w:Zoom>125</w:Zoom>" + "\n</w:WordDocument>" + "\n</xml>"
			+ "\n<![endif]-->";


	public String getContent() {
		StringBuilder res = new StringBuilder();

		res.append("\n<head>");
		res.append(this.getWwordDocument());
		
		res.append(this.getStyle());

		res.append("\n</head>");
		return res.toString();
	}

	
	
	// ##3 Getters and setters ####
	public String getWwordDocument() {
		return wwordDocument;
	}

	public void setWwordDocument(String wwordDocument) {
		this.wwordDocument = wwordDocument;
	}

}
