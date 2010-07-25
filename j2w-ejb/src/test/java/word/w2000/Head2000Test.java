package word.w2000;

import org.junit.Test;

import word.api.interfaces.IHead;
import word.utils.TestUtils;

import junit.framework.Assert;

public class Head2000Test extends Assert{

	@Test
	public void sanityTest(){
		Head2000 hd = new Head2000();
		hd.setStyle(" style=\"myStyle\"");
		hd.setWwordDocument("wwordDocument");
		System.out.println(hd.getContent());
		assertEquals(2, TestUtils.regexCount(hd.getContent(), "<*head>"));
		assertEquals(1, TestUtils.regexCount(hd.getContent(), " style=\"myStyle\""));
		assertEquals(1, TestUtils.regexCount(hd.getContent(), "wwordDocument"));
	}
	
}
