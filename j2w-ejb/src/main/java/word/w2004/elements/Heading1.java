package word.w2004.elements;

import word.api.interfaces.IElement;
import word.w2004.style.HeadingStyle;

public class Heading1 extends AbstractHeading implements IElement{

	private String value;
	
	//Constructor
	public Heading1(String value){
		super("Heading1");
		this.value = value;
	}
	
	public String getContent() {
		if("".equals(this.value)){
			return "";
		}
		
//		String txt = applyStyle(getTemplate());
		
		//For convention, it should be the last thing before returning the xml content.
		String txt = getStyle().getNewContentWithStyle(getTemplate());
		
		return txt.replace("{value}", this.value);
	}

	
//	//### TEST Fluent
//	public static Heading1 with(String string) {
//		return new Heading1(string);
//	}
//	public HeadingStyle withStyle() {
//		this.getStyle().setElement(this);
//		return this.getStyle();
//	}

}
