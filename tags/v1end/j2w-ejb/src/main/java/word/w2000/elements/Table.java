package word.w2000.elements;

import word.api.interfaces.IElement;

public class Table implements IElement{
	
	StringBuilder txt = new StringBuilder("");
	private boolean hasBeenCalledBefore = false; // if getContent has already been called, I cached the result for future invocations
	private String style = "";
	
	public enum TableEle{	
		TH("th"), TD("td"), TF("td"); //TR("tr"),

		private String value;
		
		TableEle(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
		
	};
	
	
	/**
	 * This is just for  while... I will create another framework for Styling
	 * @param style 
	 * send the whole style tag including an initial space eg.: " style=\"border=1; color=blue\""
	 * Optionally you can do this: "class=\"tblGenericList\"" 
	 */
	public void setTableStyle(String style){
		if(!"".equals(style)){			
			this.style  = style;
		}
	}
	
	
	public String getContent() {
		if("".equals(txt.toString())){
			return "";
		}
		if(hasBeenCalledBefore ){
			return txt.toString();	
		}else{
			hasBeenCalledBefore = true;
		}
		
		txt.insert(0, "<table {style}>");
		txt.append("\n</table>");
		
		String res = txt.toString().replace(" {style}", this.style);
		txt = new StringBuilder(res);
		
		return txt.toString();
	}

	public void addTableEle(TableEle tableEle, String ... cols){
		if(cols != null && cols.length > 0){
			StringBuilder th = new StringBuilder("");
			
			for (int i = 0; i < cols.length; i++) {			
				if(!"".equals(cols[i])){
					th.append("\n<" + tableEle.value + ">");
					th.append(cols[i]);
					th.append("</" + tableEle.value + ">");
				}
			}
			
			if(!"".equals(th.toString())){ //if there was at least one valid TH
				th.insert(0, "\n<tr>");
				th.append("\n</tr>");
				if(tableEle.equals(TableEle.TF)){
					th.insert(0, "\n<tfoot>");
					th.append("\n</tfoot>");
				}
				
				txt.append(th);//final result appended
			}
		}	
	}
	
//	<tf>
//	  <tr>
//	   <td>Total</tf>
//	   <td>1,100,000.00</tf>
//	  </tr>
//	</tf>
	
	
}
