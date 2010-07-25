package word.w2000.elements;

import word.api.interfaces.IElement;

public class Heading5 implements IElement{

	private String value;
	
	//Constructor
	public Heading5(String value){
		this.value = value;
	}
	
	public String getContent() {
		return "<h5>" + this.value + "</h5>";
	}
	
}
