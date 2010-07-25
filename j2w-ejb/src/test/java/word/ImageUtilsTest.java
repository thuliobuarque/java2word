package word;

import org.junit.Test;

import word.utils.ImageUtils;
import word.utils.TestUtils;
import word.utils.Utils;
import junit.framework.Assert;

public class ImageUtilsTest extends Assert{

	@Test
	public void sanityTest(){
		ImageUtils imageUtils = new ImageUtils();
		assertNotNull(imageUtils);
		String hexa = ImageUtils.getImageHexaBase64(Utils.getAppRoot() + "/src/test/resources/dtpick.gif");		
		assertEquals(1, TestUtils.regexCount(hexa, "R0lGODlhEAAQAKIAAKVNSkpNpUpNSqWmpdbT1v"));
	}
	
	@Test
	public void exceptionTest(){
		System.out.println("@@@ Expected: IOException");
		String hexa = ImageUtils.getImageHexaBase64(Utils.getAppRoot() + "/src/test/resources/bullshit");
		assertEquals(1, TestUtils.regexCount(hexa, "IOException"));
	}
	
}
