package word.w2004.style;

import word.api.interfaces.ISuperStylin;

/**
 * @author everybody - there is no ownership! 
 * 
 * Use this class to apply style to the whole Heading1 element. 
 * 
 * 
 */
public class HeadingStyle extends AbstractStyle implements ISuperStylin{

	private Align align = Align.LEFT;
	private boolean bold = false;
	private boolean italic = false;
	
	//we could abstract this or not... if we want to apply style to one word inside a the Heading, you can NOT apply align JUSTIFIED, for example.
	//For this reason I will leave this here for instance...
	public enum Align { 
		CENTER("center"), LEFT("left"), RIGHT("right"), JUSTIFIED("both");

		private String value;

		Align(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}


	//This method holds the logic to replace all place holders for styling. 
	//I also noticed if you don't replace the place holder, it doesn't cause any error!
	//But we should try to replace in order to keep the result xml clean.
	@Override
	public String getNewContentWithStyle(String txt) {
		String alignValue = "\n            	<w:jc w:val=\"" + align.getValue()+ "\" />";
		String newStr = txt.replace("{styleAlign}", alignValue);
		
		StringBuilder sbText = new StringBuilder("");
        if (bold || italic) {
            sbText.append("\n	 <w:rPr>");
            if(bold) sbText.append("\n            	<w:b/><w:b-cs/>");
            if(italic) sbText.append("\n            	<w:i/>");
            sbText.append("\n	 </w:rPr>");
        }
        newStr = newStr.replace("{styleText}", sbText.toString());//Convention: apply styles
        newStr = newStr.replaceAll("[{]style(.*)[}]", ""); //Convention: remove unused styles after...
        
		return newStr;
	}
	

	//### Getters  setters... ###
	public HeadingStyle setAlign(Align align) {
		this.align = align;
		return this;
	}
	public HeadingStyle setBold(boolean bold) {
		this.bold = bold;
		return this;
	}
	public HeadingStyle setItalic(boolean italic) {
		this.italic = italic;
		return this;
	}


}
