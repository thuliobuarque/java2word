package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.BreakLine;

public class BreakLineTest extends Assert{

		
	@Test 
	public void breakDefaultTest(){
		BreakLine br = new BreakLine();
		assertEquals("\n<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>", br.getContent());
		int tot = TestUtils.regexCount(br.getContent(), "(<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>)");
		assertEquals(1, tot);
	}
	
	@Test 
	public void breakNumberConstructorTest(){
		BreakLine br = new BreakLine(1);
		assertEquals("\n<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>", br.getContent());
		int tot = TestUtils.regexCount(br.getContent(), "(<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>)");
		assertEquals(1, tot);
	}

	@Test 
	public void breakNumberConstructor02Test(){
		BreakLine br02 = new BreakLine(2);
		int tot = TestUtils.regexCount(br02.getContent(), "(<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>)");
		assertEquals(2, tot);
		
		BreakLine br04 = new BreakLine(4);
		assertEquals(TestUtils.regexCount(br04.getContent(), "(<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>)"), 4);
	}
	
}