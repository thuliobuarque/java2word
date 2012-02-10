package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;
import word.api.interfaces.IFluentElementStylable;
import word.w2004.elements.Paragraph;

public class TableCell implements IElement, IFluentElement<TableCell>, IFluentElementStylable<TableCellStyle>{
	
	StringBuilder txt = new StringBuilder("");
	private TableCellStyle style = new TableCellStyle();
	private boolean hasBeenCalledBefore = false; 
	
    @Override
    public String getContent() {
    	System.out.println("### TableCell.getContent");
//        if (hasBeenCalledBefore) {
//            return txt.toString();
//        } else {
//            hasBeenCalledBefore = true;
//        }
    	
    	String withStyle = style.getNewContentWithStyle(txt.toString());
    	System.out.println("@@@ withStyle: " + withStyle);
        return withStyle;
    }
    
    /* 
     * Private Contructor, so you have to use Fluent Interface to create it 
     * */
    private TableCell() {
    }
    
    private TableCell(Object cell) {		
    	
        if (cell instanceof String) { //new Par
        	txt.append("\n		<w:tc>\n		{styleCellPh}");
        	txt.append(Paragraph.with(cell.toString()).create().getContent());
        	txt.append("\n		</w:tc>");    	
        } else if (cell instanceof IElement) {

        	if(cell instanceof TableCell){ 
        		//simple do a getContent because object is already a TableCell
        		txt.append("\n		<w:tc>\n		{styleCellPh}");
        		txt.append(((TableCell) cell).txt.toString()); //it is a Paragraph at this moment
        		
        		txt.append("\n		</w:tc>");  
        	}else{
        		//Paragraph for example...
        		//apply parent styles
        		//((Paragraph) cell).withStyle().
            	txt.append( ((IElement) cell).getContent() );
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
		
		if(cell instanceof TableCell){
			//it is already TableCell so no need to create another instance
			//it is a Paragraph at this moment. It needs Column Top and Bottom    		
			((TableCell) cell).txt.insert(0, "\n		<w:tc>\n		{styleCellPh}");    		
			((TableCell) cell).txt.append("\n		</w:tc>"); 
			
			return (TableCell) cell; 
		}else{
			TableCell tableCell = new TableCell(cell);
			return tableCell;
		}
	}

	@Override
	public TableCellStyle withStyle() {
		style.setElement(this);
        return style;
	}

}
