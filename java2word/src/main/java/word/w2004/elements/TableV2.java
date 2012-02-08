package word.w2004.elements;

import word.api.interfaces.IElement;
import word.w2004.elements.tableElements.TableEle;

/**
 * @author leonardo_correa
 * 
 */
public class TableV2 implements IElement {

    StringBuilder txt = new StringBuilder("");

    public String getContent() {
        if ("".equals(txt.toString())) {
            return "";
        }
        //here it goes
        
        return txt.toString();
    }

    /*
     * 
  
    - Top or TableDefinition is the same
    - just realized that TableRow is always the same. Ou seja, nao precisara dizer TableEle.TD ou TableEle.TH.
    - sera possivel applicar style a cell ou linha inteira. 
    - sera possivel fazer tudo na mesma linha com Fluent Interfaces.
    - collSpan ou gredSpan e mais relacionado a celula do que Style. Por isso nao esta no Style.
    - sera possivel passar String ou voce mandar seu Paragraph, que   
    
    # Desired    
    tbl.showHeaderonEveryPage();
    tbl.addRow("Pele", "1281", "Brazil").withStyle();
    tbl.addRow(Cell.with("Pele").withStyle().bold(), "1281", "Brazil");
    tbl.addRow(Cell.with("line has merge").collSpan(2).withStyle().bold(), "Brazil");
    tbl.addRow("Style applied to the whole line", "", "").withStyle().bold();
    tbl.addRow(Paragraph.with("Paragraph 01").create(), "", "" ).withStyle().bold();
    
    
					<w:tcPr> 
                        <w:tcW w:w="4258" w:type="dxa"/> 
                         <w:gridSpan w:val="2"/>
                    </w:tcPr> 
    
    				<w:tcPr> 
                        <w:tcW w:w="4258" w:type="dxa"/> 
                        <w:shd w:val="clear" w:color="auto" w:fill="00FFFF"/>
                    </w:tcPr> 
    
    
    */
    
    
    
}
