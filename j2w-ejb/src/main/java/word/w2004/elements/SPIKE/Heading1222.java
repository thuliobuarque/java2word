package word.w2004.elements.SPIKE;

import word.api.interfaces.IElement;

public class Heading1222 extends AbsHeading222<Heading1222> implements IElement{

	private String value;
	
	//Constructor
	public Heading1222(String value){
		super("Heading1");
		this.value = value;
	}
	
	public String getContent() {
		if("".equals(this.value)){
			return "";
		}
		
		//For convention, it should be the last thing before returning the xml content.
//		String txt = getStyle().getNewContentWithStyle(getTemplate());
		String txt = getNewContentWithStyle(getTemplate());
		
		return txt.replace("{value}", this.value);
	}

	
	//### TEST Fluent ###
	
//	private HeadingStyle style = new HeadingStyle<Heading1>();
	
	//this method is specific for each class. Constructor can be different...
	public static Heading1222 with(String string) {
		return new Heading1222(string);
	}
	
	//this could be in an interface IStylable
//	public HeadingStyle withStyle() {
//		this.getStyle().setElement(this); //, Heading1.class
//		return this.getStyle();
//	}

}
