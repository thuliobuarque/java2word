package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;

public class Head2004Test extends Assert{

	@Test
	public void testHead(){
		Head2004 hd = new Head2004();
		assertEquals(2, TestUtils.regexCount(hd.getContent(), "<*o:DocumentProperties>")); // open/close test
		assertEquals(2, TestUtils.regexCount(hd.getContent(), "<*w:fonts>")); // open/close test
		assertEquals(2, TestUtils.regexCount(hd.getContent(), "<*w:styles>")); // open/close test
		assertEquals(2, TestUtils.regexCount(hd.getContent(), "<*w:docPr>")); // open/close test
		assertEquals(1, TestUtils.regexCount(hd.getContent(), "<w:view w:val=\"print\"/>")); // set up as print to be able to view page breaks etc...
	}
	
}
