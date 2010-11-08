package word.w2004.elements;

import word.w2004.style.HeadingStyle;

public class Heading1 extends AbstractHeading<HeadingStyle> { // implements IFluentElementStylable<HeadingStyle>

	//Constructor
	public Heading1(String value){
		super("Heading1", value);
	}
	
	//this method is specific for each class. Constructor can be different...Don't know if we can make it generic
	public static Heading1 with(String string) {
		return new Heading1(string);
	}
	
}
