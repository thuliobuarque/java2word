package word.w2004.elements;

import word.api.interfaces.IElement;
import word.w2004.elements.tableElements.TableRowV2;

/**
 * @author leonardo_correa
 * 
 */
public class TableV2 implements IElement {

    private StringBuilder txt = new StringBuilder("");

    public String getContent() {
        if ("".equals(txt.toString())) {
            return "";
        }
        // here it goes
        txt.insert(0, tableTop);
        txt.append("\n" + tableBottom);
        
        //apply style

        return txt.toString();
    }

    public TableRowV2 addRow(TableRowV2 row) {
        //the row will have cells
    	
    	//TableRowV2 tableRow = new TableRowV2();    	
        txt.append(row.getContent());

        //replace Styles placeHolders for ROW, if necessary
        //txt.append(TableRowV2.getMiddle());

        return row;
    }

	String tableTop = 
			 "\n	<w:tbl> "
			+"\n            <w:tblPr> "
			+"\n                <w:tblW w:w=\"0\" w:type=\"auto\"/> "
			+"\n                <w:tblBorders> "
			+"\n                    <w:top w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:left w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:bottom w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:right w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:insideH w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:insideV w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                </w:tblBorders> "
			+"\n                <w:tblLook w:val=\"00BF\"/> "
			+"\n            </w:tblPr> "
			+"\n            <w:tblGrid> "
			+"\n                <w:gridCol w:w=\"4258\"/> "
			+"\n                <w:gridCol w:w=\"4258\"/> "
			+"\n            </w:tblGrid> "
			;
	
    private String tableBottom = "\n	</w:tbl>";
    
    /*
     * 
     * 
     * - Top or TableDefinition is the same - just realized that TableRow is
     * always the same. Ou seja, nao precisara dizer TableEle.TD ou TableEle.TH.
     * - sera possivel applicar style a cell ou linha inteira. - sera possivel
     * fazer tudo na mesma linha com Fluent Interfaces. - collSpan ou gredSpan e
     * mais relacionado a celula do que Style. Por isso nao esta no Style. -
     * sera possivel passar String ou voce mandar seu Paragraph, que
     * 
     * # Desired tbl.showHeaderonEveryPage(); tbl.addRow("Pele", "1281",
     * "Brazil").withStyle(); tbl.addRow(Cell.with("Pele").withStyle().bold(),
     * "1281", "Brazil");
     * tbl.addRow(Cell.with("line has merge").collSpan(2).withStyle().bold(),
     * "Brazil"); tbl.addRow("Style applied to the whole line", "",
     * "").withStyle().bold();
     * tbl.addRow(Paragraph.with("Paragraph 01").create(), "", ""
     * ).withStyle().bold();
     * 
     * 
     * <w:tcPr> <w:tcW w:w="4258" w:type="dxa"/> <w:gridSpan w:val="2"/>
     * </w:tcPr>
     * 
     * <w:tcPr> <w:tcW w:w="4258" w:type="dxa"/> <w:shd w:val="clear"
     * w:color="auto" w:fill="00FFFF"/> </w:tcPr>
     * 

	<w:tr wsp:rsidR="00505659" wsp:rsidRPr="00505659">
                <w:tc> 
                    <w:tcPr> 
                        <w:tcW w:w="4258" w:type="dxa"/> 
                         <w:gridSpan w:val="2"/>
                    </w:tcPr> 
                    <w:p wsp:rsidR="00505659" wsp:rsidRPr="00505659" wsp:rsidRDefault="00505659"> 
                        <w:r wsp:rsidRPr="00505659"> 
                            <w:t>* Arthur Friedenreich</w:t> 
                        </w:r> 
                    </w:p> 
                </w:tc> 
                <w:tc> 
                    <w:tcPr> 
                        <w:tcW w:w="4258" w:type="dxa"/> 
                    </w:tcPr> 
                    <w:p wsp:rsidR="00505659" wsp:rsidRPr="00505659" wsp:rsidRDefault="00505659"> 
                        <w:r wsp:rsidRPr="00505659"> 
                            <w:t>Brazil</w:t> 
                        </w:r> 
                    </w:p> 
                </w:tc> 
	</w:tr>

     */

}
