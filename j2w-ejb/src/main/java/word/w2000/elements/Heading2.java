package word.w2000.elements;

import word.api.interfaces.IElement;

public class Heading2 implements IElement{

	private String value;
	
	//Constructor
	public Heading2(String value){
		this.value = value;
	}
	
	public String getContent() {
		return "<h2>" + this.value + "</h2>";
	}
	
}
