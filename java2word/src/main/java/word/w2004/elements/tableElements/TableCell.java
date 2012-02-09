package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;
import word.api.interfaces.IFluentElementStylable;
import word.w2004.elements.Paragraph;

public class TableCell implements IElement, IFluentElement<TableCell>, IFluentElementStylable<TableCellStyle>{
	
	StringBuilder txt = new StringBuilder("");
	private TableCellStyle style = new TableCellStyle();
	
    @Override
    public String getContent() {
    	System.out.println("TableCell, getContent");
    	
    	String withStyle = style.getNewContentWithStyle(txt.toString());
        return withStyle;
        //return txt.toString();
    }
    
    /* So you have to use Fluent Interface to create it */
    private TableCell() {
    }
    
    private TableCell(Object cell) {		
    	System.out.println("TableCell, for " + cell);
    	
		
        if (cell instanceof String) { //new Par        	
        	//txt.append("\n		<w:tc>\n");
        	txt.append("\n		<w:tc>\n{styleCellPh}");
        	txt.append(Paragraph.with(cell.toString()).create().getContent());
        	txt.append("\n		</w:tc>");    	
        } else if (cell instanceof IElement) {
            
        	//cols[i].getContent();
        	if(cell instanceof TableCell){ 
        		//simple do a getContent because object is already a TableCell
        		System.out.println("((IElement) cell).getContent(): " + ((IElement) cell).getContent());
        		
        		txt.append(((IElement) cell).getContent());
        	}else{
        		//if it is NOT a TableCell instance, we wrap in one of it
        		//txt.append(new TableCell.with(cols[i].getContent()) );
        	}
        	
        } else {
            throw new IllegalArgumentException(
                    "Parameter can only be String of IElement. You gave me: " + cell.getClass().toString());
        }
        
    }

	@Override
	public TableCell create() {
		return this;
	}
	
	
	public static TableCell with(Object cell) {
		TableCell tableCell = new TableCell(cell);
		return tableCell;
	}

	@Override
	public TableCellStyle withStyle() {
		style.setElement(this);
        return style;
	}

}
