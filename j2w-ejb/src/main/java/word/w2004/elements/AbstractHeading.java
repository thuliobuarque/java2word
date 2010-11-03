package word.w2004.elements;

import word.w2004.style.HeadingStyle;

public abstract class AbstractHeading {

	private String headingType;

	protected AbstractHeading(String headingType){
		this.headingType = headingType;
	}
	//Heading1
	private String template = 
		"\n<w:p wsp:rsidR=\"004429ED\" wsp:rsidRDefault=\"00000000\" wsp:rsidP=\"004429ED\">"		
		+"\n	<w:pPr>"
		+"\n		<w:pStyle w:val=\"{heading}\" />"
		+"\n		{styleAlign}"
		+"\n	</w:pPr>"
		+"\n	<w:r>"
		+"\n		{styleText}"
		+"\n		<w:t>{value}</w:t>"
		+"\n	</w:r>"
		+"\n</w:p>";

	private HeadingStyle style;
	
	// this method could maybe be in an abstract class for any class that needs to apply style...
	
	protected String applyStyle(String txt) {
		if(style != null){
			txt = this.style.getNewContentWithStyle(txt);
		}else{ 
			//clean up {styleXXXXX} place holders...
			txt = txt.replaceAll("[{]style(.*)[}]", "");
		}
			
		return txt;
	}

	
	// #### Getters and setters #### 
	
	public String getTemplate() {		
		return this.template.replace("{heading}", this.headingType);
	}
	public HeadingStyle getStyle() {
		return style;
	}
	public void setStyle(HeadingStyle style) {
		this.style = style;
	}
	
}
