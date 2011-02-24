package word.w2004.style;

import word.api.interfaces.ISuperStylin;


/**
 * @author anyone
 * 
 * Use this class to apply style to the Paragraph - the whole paragraph. At the moment is only possible to apply Align.
 * If you want to apply bold, italic or underline, use a ParagraphPiece to do that.
 * 
 */
public class ParagraphStyle extends AbstractStyle implements ISuperStylin{

	private Align align = Align.LEFT;

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
	
	@Override
	public String getNewContentWithStyle(String txt) {
		String styleValue = "\n		<w:pPr>\n		" +
							"	<w:jc w:val=\"" + align.getValue()+ "\"/> \n	" +
							"		{styleText}\n 	" +
							"	</w:pPr>";
		txt = txt.replace("{styleAlign}", styleValue);
		txt = txt.replaceAll("[{]style(.*)[}]", ""); //Convention: replace unused styles after... 
		return txt;
	}
	
	
	//### Getters and setters... ###

	public ParagraphStyle setAlign(Align align) {
		this.align = align;
		return this;
	}

}
