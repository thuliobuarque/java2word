package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.Table;
import word.w2004.elements.tableElements.TableCol;
import word.w2004.elements.tableElements.TableDefinition;
import word.w2004.elements.tableElements.TableEle;
import word.w2004.elements.tableElements.TableFooter;

public class TableTest extends Assert{

		
	@Test // New table has to return ""
	public void createEmptyTableTest(){
		Table tbl01 = new Table();
		assertEquals("", tbl01.getContent());		
	}

	//### TH - Table Header ###
	@Test 
	public void createTableWithArrayTest(){
		Table tbl01 = new Table();
		String [] cols = {"aaa", "bbb"};
		tbl01.addTableEle(TableEle.TH, cols);
		assertTrue(tbl01.getContent().contains("<w:tbl>"));
		assertTrue(tbl01.getContent().contains("<w:tblPr>"));
		assertTrue(tbl01.getContent().contains("</w:tblPr>"));
		
		assertTrue(tbl01.getContent().contains("<w:tr ")); //TH
		assertTrue(tbl01.getContent().contains("<w:t>aaa</w:t>")); //TH
		assertTrue(tbl01.getContent().contains("<w:t>bbb</w:t>")); //TH
		assertTrue(tbl01.getContent().contains("</w:tr>"));//TH
		
		assertTrue(tbl01.getContent().contains("</w:tbl>")); 
		
	}

	@Test 
	public void createTableEmptyTHTest(){
		Table tbl03 = new Table();
		tbl03.addTableEle(TableEle.TH, null);
		assertEquals("", tbl03.getContent());		

		tbl03.addTableEle(TableEle.TH, "");
		assertEquals("", tbl03.getContent());		
	}
	
	@Test 
	public void tableDefinitionTest(){
		TableDefinition tbldef = new TableDefinition();		
		assertEquals(1, TestUtils.regexCount(tbldef.getTop(), "<*w:tbl>"));
		assertEquals(2, TestUtils.regexCount(tbldef.getTop(), "<*w:tblPr>"));
		
		assertNull(tbldef.getMiddle());
		
		assertEquals(1, TestUtils.regexCount(tbldef.getBottom(), "</w:tbl>"));
	}
	
	@Test 
	public void tableColTest(){
		TableCol tblcol = new TableCol();
		assertEquals(1, TestUtils.regexCount(tblcol.getTop(), "<*w:tr"));
		assertEquals(2, TestUtils.regexCount(tblcol.getMiddle(), "<*w:tc>"));
		assertEquals(1, TestUtils.regexCount(tblcol.getMiddle(), "<w:t>[{]value[}]</w:t>")); //test placeholder
		assertEquals(1, TestUtils.regexCount(tblcol.getBottom(), "</w:tr>"));
	}
	
	@Test 
	public void tableFooterTest(){
		TableFooter tblFooter = new TableFooter();
		assertEquals(1, TestUtils.regexCount(tblFooter.getTop(), "<*w:tr"));
		assertEquals(2, TestUtils.regexCount(tblFooter.getMiddle(), "<*w:tc>"));
		assertEquals(1, TestUtils.regexCount(tblFooter.getMiddle(), "<w:b/>")); //In this framework, footer has to be bold...
		assertEquals(1, TestUtils.regexCount(tblFooter.getMiddle(), "<w:t>[{]value[}]</w:t>")); //test placeholder
		assertEquals(1, TestUtils.regexCount(tblFooter.getBottom(), "</w:tr>"));
	}
	
	@Test 
	public void nullTest(){
		Table tbl = new Table();
		tbl.addTableEle(TableEle.TABLE_DEF, null);
		assertEquals("", tbl.getContent());
	}
	
	@Test 
	public void emptyTest(){
		Table tbl = new Table();
		String [] arr = {};
		tbl.addTableEle(TableEle.TABLE_DEF, arr);
		assertEquals("", tbl.getContent());
	}
	
	//### Full Table!!! ###
	@Test 
	public void createFullTableTest(){
		Table tbl = new Table();
		tbl.addTableEle(TableEle.TH, "Name", "Salary");

		tbl.addTableEle(TableEle.TD, "Leonardo", "100,000.00");
		tbl.addTableEle(TableEle.TD, "Romario", "1,000,000.00");

		tbl.addTableEle(TableEle.TF, "Total", "1,100,000.00");
		
		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:tbl>"));
		
		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:tblPr>"));
		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "</w:tblPr>"));
		
		assertEquals(4, TestUtils.regexCount(tbl.getContent(), "<w:tr"));
		assertEquals(4, TestUtils.regexCount(tbl.getContent(), "</w:tr>"));

		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>Name</w:t>"));
		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>Salary</w:t>"));
		
		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>Leonardo</w:t>"));
		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>100,000.00</w:t>"));
		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>Romario</w:t>"));
		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>1,000,000.00</w:t>"));


		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>Total</w:t>"));
		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>1,100,000.00</w:t>"));

		assertEquals(1, TestUtils.regexCount(tbl.getContent(), "</w:tbl>"));
		
		assertEquals(tbl.getContent(), tbl.getContent());
		//System.out.println(tbl.getContent());
	}
	
	
}
