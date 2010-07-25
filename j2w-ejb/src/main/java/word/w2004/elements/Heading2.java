package word.w2004.elements;

import word.api.interfaces.IElement;

public class Heading2 implements IElement{

	private String value;
	
	//Constructor
	public Heading2(String value){
		this.value = value;
	}
	
	public String getContent() {
		if("".equals(this.value)){
			return "";
		}
		
		String txt = 
			"\n<w:p wsp:rsidR=\"004429ED\" wsp:rsidRDefault=\"00000000\" wsp:rsidP=\"004429ED\">"		
			+"\n	<w:pPr>"
			+"\n		<w:pStyle w:val=\"Heading2\" />"
			+"\n	</w:pPr>"
			+"\n	<w:r>"
			+"\n		<w:t>{value}</w:t>"
			+"\n	</w:r>"
			+"\n</w:p>";
		
		return txt.replace("{value}", this.value);
	}
	
	// #### Getters and setters #### 
	
}
