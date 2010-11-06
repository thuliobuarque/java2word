package word.w2004.style;

import word.api.interfaces.ISuperStylin;

/**
 * @author 
 * 
 * This is one way to do superStylin using Abstract class.
 * This is experimental pattern. Issue 8.
 *
 * Steps:
 * 
 * - Make your class extend AbstractStyle. Eg.: AbstractHeading extends AbstractStyle
 * - Call the method applyStyle(txt) just before returning the content 
 * 
 */
public class AbstractStyle {
	
//	private HeadingStyle style; //### CAN NOT  BE HERE!!!!
	ISuperStylin style;
	
	public String applyStyle(String txt) {
		if(style != null){
			txt = style.getNewContentWithStyle(txt);
		}else{ 
			//clean up {styleXXXXX} place holders...
			txt = txt.replaceAll("[{]style(.*)[}]", "");
		}			
		return txt;
	}
	
	// #### Getters and setters ####
//	public HeadingStyle getStyle() {
//		return style;
//	}
//	public void setStyle(HeadingStyle style) {
//		this.style = style;
//	}
	
}
