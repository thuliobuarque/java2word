package word.w2000;

import word.api.interfaces.IBody;
import word.api.interfaces.IElement;
import word.api.interfaces.IFooter;
import word.api.interfaces.IHeader;

public class Body2000 implements IBody{

	StringBuilder txt = new StringBuilder();
	
	public void addEle(IElement e){
		this.txt.append("\n" + e.getContent());
	}
	
	public void addEle(String str){
		this.txt.append("\n" + str);
	}

	
	public String getContent() {
		StringBuilder res = new StringBuilder();		
		res.append("\n\n<body>");
		
		res.append(txt.toString());
		
		res.append("\n</body>");
		return res.toString();
	}

	public IFooter getFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	public IHeader getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

//	public void setFooter(IFooter footer) {
//		// TODO Auto-generated method stub
//		
//	}

//	public void setHeader(IHeader header) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
