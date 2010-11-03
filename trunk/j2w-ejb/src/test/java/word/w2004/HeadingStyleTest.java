package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.w2004.style.HeadingStyle;

public class HeadingStyleTest extends Assert{

	//TODO: We right tests for this guy as soon as we define a pattern for superStylin	

	@Test
	public void sanityTest(){
		HeadingStyle style = new HeadingStyle();
		assertNotNull(style);
	}
	
}
