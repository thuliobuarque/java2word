package word.w2004;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.jboss.seam.util.Base64;
import org.junit.Ignore;
import org.junit.Test;

import word.utils.TestUtils;
import word.utils.Utils;
import word.w2004.elements.Image;
import word.w2004.elements.ImageLocation;

public class ImageTest extends Assert {

    @Test
    public void sanityTest() throws IOException{
        Image img = new Image(Utils.getAppRoot() + "/src/test/resources/dtpick.gif", ImageLocation.FULL_LOCAL_PATH);
        //Image img = new Image(Utils.getAppRoot() + "/src/test/resources/base2logo.png");
        // Image("/Users/leonardo_correa/Desktop/icons_corrup/quote.gif");

        //System.out.println(img.getContent());
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*w:pict>"));
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shapetype"));
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shape[ >]")); //white space or >
        assertEquals(2, TestUtils.regexCount(img.getContent(), "wordml"));
        //for dtPicker.gif
        assertEquals(1, TestUtils.regexCount(img.getContent(), "R0lGODlhEAAQAPMAAKVNSkpNpUpNSqWmpdbT1v///////wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAACH5BAEAAAYALAAAAAAQABAAQwRI0MhJqxmlkLwLyF8hYBpnluJArGzbjkEsB0NtD6PLAjyw\njqeOMANEDVGjm1IJm8WWONLxWDyGQjkdoecjVIOnrzEsKJvPaEEEADs="));

    }

    @Test
    public void testLocalImage(){
    	Image img = new Image(Utils.getAppRoot() + "/src/test/resources/dtpick.gif", ImageLocation.FULL_LOCAL_PATH);
    	assertEquals(2, TestUtils.regexCount(img.getContent(), "<*w:pict>"));
    	assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shapetype"));
    	assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shape[ >]")); //white space or >
    	assertEquals(2, TestUtils.regexCount(img.getContent(), "wordml"));
    	assertEquals(1, TestUtils.regexCount(img.getContent(), "R0lGODlhEAAQAPMAAKVNSkpNpUpNS"));//just the beginning of...
    }
    
    @Test
    public void testLocalImageFluent(){
        Image img = Image.from_FULL_LOCAL_PATHL(Utils.getAppRoot() + "/src/test/resources/dtpick.gif");
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*w:pict>"));
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shapetype"));
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shape[ >]")); //white space or >
        assertEquals(2, TestUtils.regexCount(img.getContent(), "wordml"));
        assertEquals(1, TestUtils.regexCount(img.getContent(), "R0lGODlhEAAQAPMAAKVNSkpNpUpNS"));//just the beginning of...
    }

    @Test(expected = RuntimeException.class )
    public void localImageTestWeb(){
    	@SuppressWarnings("unused") //ok... jst to hit coverage
		Image img = new Image(Utils.getAppRoot() + "/src/test/resources/dtpick.gif", ImageLocation.WEB_URL);
    }
    
    @Test(expected = RuntimeException.class )
    public void localImageTestClasspath(){
    	@SuppressWarnings("unused") //ok... jst to hit coverage
    	Image img = new Image(Utils.getAppRoot() + "/src/test/resources/dtpick.gif", ImageLocation.CLASSPATH);
    }
    
    @Test(expected = RuntimeException.class )
    public void localImageTestClasspathFluent(){
    	@SuppressWarnings("unused") //ok... jst to hit coverage
        Image img = Image.from_WEB_URL(Utils.getAppRoot() + "/src/test/resources/dtpick.gif").create();
    }
    
    /**
     * ignore because it could fail if you are under a proxy. So for a matter of demostration, uncomment and run it
     */
    @Ignore
    @Test
    public void webImageTest(){
        Image img = new Image("http://www.google.com.au/intl/en_com/images/srpr/logo1w.png", ImageLocation.WEB_URL);
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*w:pict>"));
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shapetype"));
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shape[ >]")); //white space or >
        assertEquals(2, TestUtils.regexCount(img.getContent(), "wordml"));
        assertEquals(1, TestUtils.regexCount(img.getContent(), "width:275pt;height:95pt"));
        assertEquals(1, TestUtils.regexCount(img.getContent(), "BiGQFiipCSS8DCm1Cya1FiyNKzexKTjDDSrLDSvUDi3MEyzHFSvUFC3TGi7bGi/aEi7dGzLcFzPN"));
    }

    @Test
    public void classpathImageTest(){
        Image img = new Image("/dtpick.gif", ImageLocation.CLASSPATH);

        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*w:pict>"));
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shapetype"));
        assertEquals(2, TestUtils.regexCount(img.getContent(), "<*v:shape[ >]")); //white space or >
        assertEquals(2, TestUtils.regexCount(img.getContent(), "wordml"));
        assertEquals(1, TestUtils.regexCount(img.getContent(), "width:16pt;height:16pt"));
        assertEquals(1, TestUtils.regexCount(img.getContent(), "R0lGODlhEAAQAPMAAKVNSkpNpUpNS"));
    }

    @Test
    public void defaultSizeTest() throws IOException {
        Image img = new Image(Utils.getAppRoot()
                + "/src/test/resources/base2logo.png", ImageLocation.FULL_LOCAL_PATH);
        assertEquals(1, TestUtils.regexCount(img.getContent(),
                "style=\"width:116pt;height:104pt\""));
    }

    @Test
    public void widthTest() throws IOException {
        Image img = new Image(Utils.getAppRoot()
                + "/src/test/resources/base2logo.png", ImageLocation.FULL_LOCAL_PATH);
        img.setWidth("120");
        assertEquals(0, TestUtils.regexCount(img.getContent(),
                "style=\"width:116pt;height:104pt\""));
        assertEquals(1, TestUtils.regexCount(img.getContent(),
                "style=\"width:120pt;height:104pt\""));
    }

    @Test
    public void heightTest() throws IOException {
        Image img = new Image(Utils.getAppRoot()
                + "/src/test/resources/base2logo.png", ImageLocation.FULL_LOCAL_PATH);
        img.setHeight("110");
        assertEquals(0, TestUtils.regexCount(img.getContent(),
                "style=\"width:116pt;height:104pt\""));
        assertEquals(1, TestUtils.regexCount(img.getContent(),
                "style=\"width:116pt;height:110pt\""));
    }

    @Test
    public void widthAndHeightTest() throws IOException {
        Image img = new Image(Utils.getAppRoot()
                + "/src/test/resources/base2logo.png", ImageLocation.FULL_LOCAL_PATH);
        img.setWidth("121");
        img.setHeight("111");
        assertEquals(0, TestUtils.regexCount(img.getContent(),
                "style=\"width:116pt;height:104pt\""));
        assertEquals(1, TestUtils.regexCount(img.getContent(),
                "style=\"width:121pt;height:111pt\""));
    }



    @Test(expected = java.lang.RuntimeException.class)
    public void invalidImageTest(){
        @SuppressWarnings("unused")
        Image img = new Image(Utils.getAppRoot()
                + "/src/test/resources/whatever", ImageLocation.FULL_LOCAL_PATH);
    }


}
