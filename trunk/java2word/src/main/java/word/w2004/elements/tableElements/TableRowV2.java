package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElementStylable;

public class TableRowV2 implements IElement, IFluentElementStylable<TableRowStyle>{

	private StringBuilder txt = new StringBuilder("");
	private TableRowStyle style = new TableRowStyle();
	
	public TableRowV2(Object[] cols) {
        txt.append("\n	<w:tr>{styleRowPh}");

        for (int i = 0; i < cols.length; i++) {            
        	//TableCell knows how to do the rest.
        	txt.append( TableCell.with(cols[i]).create().getContent() );
        }
		
        txt.append("\n	</w:tr>");
	}

	@Override
	public TableRowStyle withStyle() {
		style.setElement(this);
        return style;
	}

	@Override
	public String getContent() {
		String withStyle = style.getNewContentWithStyle(txt.toString());
		return withStyle;
	}

	public static TableRowV2 with(Object ... colls) {		
		return new TableRowV2(colls);
	}

}
