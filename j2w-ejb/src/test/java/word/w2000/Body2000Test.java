package word.w2000;

import org.junit.Test;

import word.utils.TestUtils;

import junit.framework.Assert;

public class Body2000Test extends Assert{

	
	@Test
	public void sanityTest(){
		Body2000 bd = new Body2000();
		assertEquals(2, TestUtils.regexCount(bd.getContent(), "<*body>"));
	}
	
	@Test
	public void emptyHeaderAndFooterTest(){
		//for W2000, header and footer haven't been implemented yet...
		Body2000 bd = new Body2000();
//		bd.setHeader(null);
//		bd.setFooter(null);
		assertNull(bd.getHeader());
		assertNull(bd.getFooter());
		//System.out.println(bd.getHeader().getContent());
		//assertEquals(2, TestUtils.regexCount(bd.getContent(), "<*body>"));
	}
	
}
