package word.w2004.elements;

import word.api.interfaces.IElement;

public class Heading3 extends AbstractHeading  implements IElement{

	private String value;
	
	//Constructor
	public Heading3(String value){
		super("Heading3");
		this.value = value;
	}
	
	public String getContent() {
		if("".equals(this.value)){
			return "";
		}
		
		String txt = applyStyle(getTemplate()); //For convention, it should be the last thing before returning the xml content. 
		
		return txt.replace("{value}", this.value);
	}
	
	
}
