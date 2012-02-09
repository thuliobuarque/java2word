package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;
import word.api.interfaces.IFluentElementStylable;
import word.api.interfaces.ISuperStylin;

public class TableRowV2 implements IElement, IFluentElementStylable<TableRowV2>{

	private StringBuilder txt = new StringBuilder("");
	
	public TableRowV2(Object[] cols) {
		System.out.println("TableRowV2, init");
		
        txt.append("\n	<w:tr>");

        for (int i = 0; i < cols.length; i++) {            
        	//TableCell knows how to do the rest.
        	System.out.println("about to do cell: " + i);
        	
        	txt.append( TableCell.with(cols[i]).create().getContent() );
        }
		
        txt.append("\n	</w:tr>");
        
        System.out.println("TableRowV2, end");
	}

	@Override
	public TableRowV2 withStyle() {
		//TODO
		
		//style.setElement(this);
        //return style;
		return null;
	}

	@Override
	public String getContent() {
		System.out.println("TableRowV2, getContent");
		
		return txt.toString();
	}

}
