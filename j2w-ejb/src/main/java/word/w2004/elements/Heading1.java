package word.w2004.elements;

import word.api.interfaces.IElement;
import word.w2004.style.HeadingStyle;

//TODO: Refactoring in all Heading classes. The only difference in the variable "txt" is this line: 
// <w:pStyle w:val=\"Heading1\" />"
// if you replace Heading1 to Heading2 you are done.
public class Heading1 implements IElement{

	private String value;
	private HeadingStyle style; //= new Heading
	
	//Constructor
	public Heading1(String value){
		this.value = value;
	}
	
	public String getContent() {
		if("".equals(this.value)){
			return "";
		}
		
		String txt = 
			"\n<w:p wsp:rsidR=\"004429ED\" wsp:rsidRDefault=\"00000000\" wsp:rsidP=\"004429ED\">"		
			+"\n	<w:pPr>"
			+"\n		<w:pStyle w:val=\"Heading1\" />"
			+"\n		{styleAlign}"
			+"\n	</w:pPr>"
			+"\n	<w:r>"
			+"\n		{styleText}"
			+"\n		<w:t>{value}</w:t>"
			+"\n	</w:r>"
			+"\n</w:p>";
		
		txt = applyStyle(txt); //For convention, it should be the last thing before returning the xml content. 
		
		return txt.replace("{value}", this.value);
	}

	// this method could maybe be in an abstract class for any class that needs to apply style...
	private String applyStyle(String txt) {
		if(this.style != null){
			txt = this.style.getNewContentWithStyle(txt);
		}else{ 
			//clean up {styleXXXXX} place holders...
			txt = txt.replaceAll("[{]style(.*)[}]", "");
		}
			
		return txt;
	}

	// #### Getters and setters #### 
	public HeadingStyle getStyle() {
		return style;
	}
	public void setStyle(HeadingStyle style) {
		this.style = style;
	}
	
}
