package word.w2000.elements;

import word.api.interfaces.IElement;

public class Paragraph implements IElement{

	private String value;
	
	public Paragraph(String value) {
		this.value = value;
	}

	public String getContent() {
		//return "<p><o:p>" + value + "</o:p></p>";
		return "<p>" + value + "</p>";
	}

	
	// #### Getters and setters ####  
//	public String getValue() {
//		return value;
//	}
//	public void setValue(String value) {
//		this.value = value;
//	}

}
