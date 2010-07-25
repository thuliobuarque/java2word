package word.w2004;

import word.api.interfaces.IHead;
import word.utils.Utils;

public class Head2004 implements IHead {

	private StringBuilder content = new StringBuilder("");

	public String getContent() {
		if("".equals(this.content.toString())){ 
			content.append("\n" + Utils.HEAD2004 + "\n");
		}

		return content.toString();
	}

}
