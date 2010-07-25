package word.w2004.elements;

import word.api.interfaces.IElement;

public class Paragraph implements IElement{

	private String value = "";
	
	//Constructor
	public Paragraph(String value) {
		this.value = value;
	}
	
	public String getContent() {
		if("".equals(this.value)){
			return "";
		}
		
		String txt = "	<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">"
		+"\n	<w:r>"
		+"\n		<w:t>{value}</w:t>"
		+"\n	</w:r>"
		+"\n	</w:p>";
		
		return txt.replace("{value}", this.value);
	}

	
	// #### Getters and setters ####  
//	public String getValue() {
//		return value;
//	}
//	public void setValue(String value) {
//		this.value = value;
//	}
	
}
