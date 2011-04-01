package word;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.utils.Utils;

public class Utils_Test extends Assert {

    @Test
    public void sanityTest() {
        /*
        File dir1 = new File(".");
        File dir2 = new File("..");
        try {
            System.out.println("Current dir : " + dir1.getCanonicalPath());
            System.out.println("Parent  dir : " + dir2.getCanonicalPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

    }

    @Test
    public void getAppRootTest() {
        Utils utils = new Utils();
        assertNotNull(utils);
        assertTrue(Utils.getAppRoot().contains("/j2w-ejb"));
    }

    @Test
    public void readFileTest() {
        String res = Utils.readFile(Utils.getAppRoot() + "/src/test/resources/resources.properties");
        assertEquals(1, TestUtils.regexCount(res, "reports.servlet.datasource.lookup"));
    }

    @Test(expected = RuntimeException.class)
    public void readFileTestException() {
        String res = Utils.readFile(Utils.getAppRoot() + "/src/test/resources/not_a_file");
        assertEquals(1, TestUtils.regexCount(res, "FileNotFoundException"));
    }

//    @Test
//    public void prettyTest01() {
//        String str = Utils.pretty("<leo><nada></nada></leo>");
//        assertTrue(str.contains("<leo>\n    <nada/>"));
//        assertEquals(2, TestUtils.regexCount(str, "<*leo>"));
//        assertEquals(1, TestUtils.regexCount(str, "<nada/>"));
//    }

//    @Test(expected = RuntimeException.class)
//    public void prettyTestException() {
//        String crap = "<leo><nada></leo>";
//
//        @SuppressWarnings("unused")
//        String str = Utils.pretty(crap);
//        assertEquals(crap, crap); // the same crap...
//    }

}
