package word.w2000.elements;

import word.api.interfaces.IElement;

public class Heading6 implements IElement{

	private String value;
	
	//Constructor
	public Heading6(String value){
		this.value = value;
	}
	
	public String getContent() {
		return "<h6>" + this.value + "</h6>";
	}
	
}
