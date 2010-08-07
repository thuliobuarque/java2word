package word;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.ImageUtils;
import word.utils.TestUtils;
import word.utils.Utils;

public class ImageUtilsTest extends Assert{

	@Test
	public void sanityTestLocal() throws IOException{
		ImageUtils imageUtils = new ImageUtils();
		assertNotNull(imageUtils);
		
		BufferedImage bufferedImage = ImageIO.read(new File(Utils.getAppRoot() + "/src/test/resources/dtpick.gif"));
		String hexa = ImageUtils.getImageHexaBase64(bufferedImage, "gif");
		assertEquals(1, TestUtils.regexCount(hexa, "R0lGODlhEAAQAPMAAKVNSkpNpUpNSqWmpdbT1v"));
	}
	
	@Test(expected = IOException.class)
	public void exceptionTest() throws IOException{
		//System.out.println("@@@ Expected: IOException");
		URL url = new URL("http://localhost:8080/ExampleStruts/img/bullshit.gif");
		BufferedImage bufferedImage = ImageIO.read(url);
		String hexa = ImageUtils.getImageHexaBase64(bufferedImage,  "bullshit");
		
		assertEquals(1, TestUtils.regexCount(hexa, "IOException"));
	}
	
}
