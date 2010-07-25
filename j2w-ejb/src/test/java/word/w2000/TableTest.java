package word.w2000;

import junit.framework.Assert;

import org.junit.Test;

import word.w2000.elements.Table;
import word.w2000.elements.Table.TableEle;

public class TableTest extends Assert{

		
	@Test // New table has to return ""
	public void createEmptyTableTest(){
		Table tbl01 = new Table();
		assertEquals("", tbl01.getContent());		
	}


	@Test	// Null Emun has to return "" 
	public void nullEnumTest(){
		Table tbl03 = new Table();
		tbl03.addTableEle(null, "");
		assertEquals("", tbl03.getContent());		
	}
	
	//### TH - Table Header ###
	@Test 
	public void createTableWithArrayTest(){
		Table tbl01 = new Table();
		String [] cols = {"aaa", "bbb"};
		tbl01.addTableEle(TableEle.TH, cols);
		assertEquals("<table>\n<tr>\n<th>aaa</th>\n<th>bbb</th>\n</tr>\n</table>", tbl01.getContent());
		
		Table tbl02 = new Table();
		tbl02.addTableEle(TableEle.TH, "111");
		assertEquals("<table>\n<tr>\n<th>111</th>\n</tr>\n</table>", tbl02.getContent());
		
		Table tbl03 = new Table();
		String arr[] = {};
		tbl02.addTableEle(TableEle.TH, arr);
		assertEquals("", tbl03.getContent());
		
	}

	@Test 
	public void createTableEmptyTHTest(){
		Table tbl03 = new Table();
		tbl03.addTableEle(TableEle.TH, null);
		assertEquals("", tbl03.getContent());		

		tbl03.addTableEle(TableEle.TH, "");
		assertEquals("", tbl03.getContent());		
	}
	
	//### TR - Table Row ###
	@Test 
	public void createTRTest(){
		Table tbl03 = new Table();
		tbl03.addTableEle(TableEle.TD, "row1");
		assertEquals("<table>\n<tr>\n<td>row1</td>\n</tr>\n</table>", tbl03.getContent());
	}
	
	//### TR - Table Row ###
	@Test 
	public void createTFTest(){
		Table tbl03 = new Table();
		tbl03.addTableEle(TableEle.TF, "footer1");
		assertEquals("<table>\n<tfoot>\n<tr>\n<td>footer1</td>\n</tr>\n</tfoot>\n</table>", tbl03.getContent());
	}

	
	//### Full Table!!! ###
	@Test 
	public void createFullTableTest(){
		Table tbl = new Table();
		tbl.addTableEle(TableEle.TH, "Name", "Salary");

		tbl.addTableEle(TableEle.TD, "Leonardo", "100,000.00");
		tbl.addTableEle(TableEle.TD, "Romario", "1,000,000.00");

		tbl.addTableEle(TableEle.TF, "Total", "1,100,000.00");
		
		//System.out.println(tbl.getContent());
		
		String expected = "<table>" 
				+ "\n<tr>\n<th>Name</th>\n<th>Salary</th>\n</tr>" 
				+ "\n<tr>\n<td>Leonardo</td>\n<td>100,000.00</td>\n</tr>"
				+ "\n<tr>\n<td>Romario</td>\n<td>1,000,000.00</td>\n</tr>"
				+ "\n<tfoot>\n<tr>\n<td>Total</td>\n<td>1,100,000.00</td>\n</tr>\n</tfoot>"
				+ "\n</table>";
		assertEquals(expected, tbl.getContent());
		assertEquals(expected, tbl.getContent());
		
	}
	
	
	@Test 
	public void enumTest(){
		TableEle en = TableEle.TD;
		assertEquals("td", en.getValue());
	}

	@Test 
	public void tableStyleTest(){
		Table tbl = new Table();
		tbl.addTableEle(TableEle.TH, "col1");
		tbl.setTableStyle(" style=\"font:Arial;\"");
		assertEquals(true, tbl.getContent().contains("style=\"font:Arial;\""));
		
		Table tbl2 = new Table();
		tbl2.setTableStyle("");
		assertEquals(true, !tbl2.getContent().contains("style"));
	}
}
