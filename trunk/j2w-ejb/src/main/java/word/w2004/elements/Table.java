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
				if(!"".equals(cols[i])){
						th.append("\n" + item.getMiddle().replace("{value}", cols[i]));
				}
			}
			if(!"".equals(th.toString())){
				th.insert(0, item.getTop());
				th.append(item.getBottom());
			}
			
			txt.append(th);//final result appended
		}	
	}

	
}
