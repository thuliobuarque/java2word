package word.w2004.elements;

import word.w2004.style.HeadingStyle;

public class Heading2 extends AbstractHeading<HeadingStyle> {

	//Constructor
	public Heading2(String value){
		super("Heading2", value);
	}
	
	
	public static Heading2 with(String string) {
		return new Heading2(string);
	}
	
	// #### Getters and setters #### 
	
}
