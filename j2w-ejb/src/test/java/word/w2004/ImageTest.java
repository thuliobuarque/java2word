package word.w2004;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import word.utils.TestUtils;
import word.utils.Utils;
import word.w2004.elements.Image;
import org.jboss.seam.util.Base64;

public class ImageTest extends Assert {

	@Test
	public void sanityTest(){
		Image img = new Image(Utils.getAppRoot() + "/src/test/resources/dtpick.gif");
		//Image img = new Image(Utils.getAppRoot() + "/src/test/resources/base2logo.png");
		// Image("/Users/leonardo_correa/Desktop/icons_corrup/quote.gif");

		//System.out.println(img.getContent());
		assertEquals(2, TestUtils.regexCount(img.getContent(), "<*w:pict>"));
		assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shapetype"));
		assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shape[ >]")); //white space or >
		assertEquals(2, TestUtils.regexCount(img.getContent(), "wordml"));
		//for dtPicker.gif
		assertEquals(1, TestUtils.regexCount(img.getContent(), "R0lGODlhEAAQAKIAAKVNSkpNpUpNSqWmpdbT1v///////wAAACH5BAEAAAYALAAAAAAQABAAAANE\naLrcNjDKKUa4OExYM95DVRTEWJLmKKLseVZELMdADcSrOwK7OqQsXkEIm8lsN0IOqCssW8Cicar8\nQa/P5kvA7Xq/ggQAOw=="));

	}

	@Test
	public void defaultSizeTest() {
		Image img = new Image(Utils.getAppRoot()
				+ "/src/test/resources/base2logo.png");
		// System.out.println(img.getContent());
		assertEquals(1, TestUtils.regexCount(img.getContent(),
				"style=\"width:116pt;height:104pt\""));
	}

	@Test
	public void widthTest() {
		Image img = new Image(Utils.getAppRoot()
				+ "/src/test/resources/base2logo.png");
		img.setWidth("120");
		assertEquals(0, TestUtils.regexCount(img.getContent(),
				"style=\"width:116pt;height:104pt\""));
		assertEquals(1, TestUtils.regexCount(img.getContent(),
				"style=\"width:120pt;height:104pt\""));
	}

	@Test
	public void heightTest() {
		Image img = new Image(Utils.getAppRoot()
				+ "/src/test/resources/base2logo.png");
		img.setHeight("110");
		assertEquals(0, TestUtils.regexCount(img.getContent(),
				"style=\"width:116pt;height:104pt\""));
		assertEquals(1, TestUtils.regexCount(img.getContent(),
				"style=\"width:116pt;height:110pt\""));
	}

	@Test
	public void widthAndHeightTest() {
		Image img = new Image(Utils.getAppRoot()
				+ "/src/test/resources/base2logo.png");
		img.setWidth("121");
		img.setHeight("111");
		assertEquals(0, TestUtils.regexCount(img.getContent(),
				"style=\"width:116pt;height:104pt\""));
		assertEquals(1, TestUtils.regexCount(img.getContent(),
				"style=\"width:121pt;height:111pt\""));
	}

	@Test
	public void getOriginalWidthHeightTest() {
		Image img = new Image(Utils.getAppRoot()
				+ "/src/test/resources/whatever");
		System.out.println("IIOException is expected ok...");
		String res = img.getOriginalWidthHeight();
		assertEquals("error", res);
	}

	@Ignore
	@Test
	public void xxxxx() throws IOException{


		URL url = new URL("http://localhost:8080/j2w/img/dtpick2.gif");
		BufferedImage img = ImageIO.read(url);// throws IIOException
		System.out.println("H: " + img.getHeight());
		System.out.println("W: " + img.getWidth());


		ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
		//RenderedImage aBufferedImage = null;
		ImageIO.write(img, "gif" /* "png" "jpeg" ... format desired */,
		           baos );
		baos.flush();
		byte[] resultImageAsRawBytes = baos.toByteArray();


		String encodedString = Base64.encodeBytes(resultImageAsRawBytes);

		System.out.println(encodedString);
	}

}
