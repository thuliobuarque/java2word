package word.w2004.elements;

import word.api.interfaces.IElement;

/**
 * 
 * Breaks lines like when you press enter in your MS Word.
 * 
 * You can insert many Breaklines at once. Eg.:
 * 
 * <code>
 * new BreakLine(3)
 * </code>
 *
 *This will insert 3 Breaklines
 *
 */
public class BreakLine implements IElement{

	private int times = 1;

	public String getContent() {
		StringBuilder res = new StringBuilder(""); 
		for (int i = 0; i < this.times; i++) {
			res.append("\n<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>");
		}
		return res.toString();
	}
	
	
	/**
	 * @param times
	 * constructor: Number of break lines you want to add
	 */
	public BreakLine(int times){
		this.times  = times;
	}
	
	/**
	 * @param times
	 * constructor: By default, 1 Number of break line when no number is provided
	 */
	public BreakLine(){
	}
	
}
