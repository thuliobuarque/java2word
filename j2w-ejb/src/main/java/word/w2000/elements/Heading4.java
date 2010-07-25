package word.w2000.elements;

import word.api.interfaces.IElement;

public class Heading4 implements IElement{

	private String value;
	
	//Constructor
	public Heading4(String value){
		this.value = value;
	}
	
	public String getContent() {
		return "<h4>" + this.value + "</h4>";
	}
	
}
