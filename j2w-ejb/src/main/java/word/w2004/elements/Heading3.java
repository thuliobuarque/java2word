package word.w2004.elements;

import word.api.interfaces.IFluentElement;
import word.w2004.style.HeadingStyle;


public class Heading3 extends AbstractHeading <HeadingStyle> implements IFluentElement<Heading3>{

	//Constructor
	public Heading3(String value){
		super("Heading3", value);
	}
	
	
	public static Heading3 with(String string) {
		return new Heading3(string);
	}

	@Override
	public Heading3 create() {
		return this;
	}
	
}
