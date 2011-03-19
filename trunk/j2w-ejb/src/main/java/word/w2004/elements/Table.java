package word.w2004.elements;

import word.api.interfaces.IElement;
import word.w2004.elements.tableElements.ITableItemStrategy;
import word.w2004.elements.tableElements.TableEle;
import word.w2004.elements.tableElements.TableFactoryMethod;

/**
 * @author leonardo_correa
 *
 */
public class Table implements IElement{
	
	
	StringBuilder txt = new StringBuilder("");
	private boolean hasBeenCalledBefore = false; // if getContent has already been called, I cached the result for future invocations
    private boolean isRepeatTableHeaderOnEveryPage = false;

	
	public String getContent() {
		if("".equals(txt.toString())){
			return "";
		}
		if(hasBeenCalledBefore ){
			return txt.toString();	
		}else{
			hasBeenCalledBefore = true;
		}
		
		ITableItemStrategy tableDef = TableFactoryMethod.getInstance().getTableItem(TableEle.TABLE_DEF);
		
		txt.insert(0, tableDef.getTop());
		txt.append("\n" + tableDef.getBottom());
		
		return txt.toString();
	}

	public void addTableEle(TableEle tableEle, String ... cols){
		if(cols != null && cols.length > 0){
			StringBuilder th = new StringBuilder("");
			
			ITableItemStrategy item = TableFactoryMethod.getInstance().getTableItem(tableEle);
			
			for (int i = 0; i < cols.length; i++) {			
			    //### commented in order to render the cell regardless of null or empty string
				//if(!"".equals(cols[i])){ 
				th.append("\n" + item.getMiddle().replace("{value}", cols[i]));
				//}
			}
			
			if(!"".equals(th.toString())){
				th.insert(0, item.getTop());
				th.append(item.getBottom());
			}
			
			String finalResult = setUpRepeatTableHeaderOnEveryPage(th);
			
			txt.append(finalResult);//final result appended
		}	
	}

	/**
	 * Adds the repeat header code if this.isRepeatTableHeaderOnEveryPage is true.
	 * If not, it removes {tblHeader} placeholder.
	 * @param th 
	 * @return the final string 
	 */
    private String setUpRepeatTableHeaderOnEveryPage(StringBuilder th) {
        String res = th.toString();
        if(this.isRepeatTableHeaderOnEveryPage) {
           res = res.replace("{tblHeader}", "  \n<w:trPr>\n        <w:tblHeader/>\n    </w:trPr>\n ");  
        }
        
        //clean up placeholder  
        res = res.replace("{tblHeader}", "");
        return res;
    }

    /***
     * Pass 'true' if you want to repeat the table header when the table takes more than one page.
     * Default is false. 
     * @param value
     */
    public void setRepeatTableHeaderOnEveryPage(boolean value) {
        this.isRepeatTableHeaderOnEveryPage  = value;
    }

	
}
