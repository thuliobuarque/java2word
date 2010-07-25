package word.w2000.elements;

import word.api.interfaces.IElement;

public class Heading3 implements IElement{

	private String value;
	
	//Constructor
	public Heading3(String value){
		this.value = value;
	}
	
	public String getContent() {
		return "<h3>" + this.value + "</h3>";
	}
	
}
