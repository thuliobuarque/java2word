package word.w2000.elements;

import word.api.interfaces.IElement;

public class Heading1 implements IElement{

	private String value;
	
	//Constructor
	public Heading1(String value){
		this.value = value;
	}
	
	public String getContent() {
		return "<h1>" + this.value + "</h1>";
	}
	
}