package word;

import junit.framework.Assert;

import org.dom4j.DocumentException;
import org.junit.Test;

import word.utils.TestUtils;
import word.utils.Utils;
import org.dom4j.DocumentException;

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
	
	@Test
	public void readFileTestException() {
		System.out.println("@@@ Expected...: java.io.FileNotFoundException"); 
		String res = Utils.readFile(Utils.getAppRoot() + "/src/test/resources/not_a_file");		
		assertEquals(1, TestUtils.regexCount(res, "FileNotFoundException"));
	}

	@Test
	public void prettyTest01() {
		String str = Utils.pretty("<leo><nada></nada></leo>");
		assertTrue(str.contains("<leo>\n    <nada/>"));
		assertEquals(2, TestUtils.regexCount(str, "<*leo>"));
		assertEquals(1, TestUtils.regexCount(str, "<nada/>"));
	}
	
	@Test 
	public void prettyTestException() {
		String crap = "<leo><nada></leo>";
		String str = Utils.pretty(crap);
		assertEquals(crap, crap); // the same crap...
	}
	
	public void prettyXmlTestException() {
		
	}
	
}