package java2word;

import java2word.actions.Testing;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

import static com.opensymphony.xwork2.ActionSupport.*;

public class ConfigTest extends StrutsTestCase{

	@Test
	public void testSanity() throws Exception{
		Testing testing = new Testing();
		String res = testing.execute();
		assertEquals(SUCCESS, res);
	}
	
}
