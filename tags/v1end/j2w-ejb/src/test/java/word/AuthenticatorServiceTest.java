package word;

import org.junit.Test;

import word.security.AuthenticatorService;

public class AuthenticatorServiceTest extends AbstractLog{

	@Test
	public void AuthenticatorServiceTest() {
		AuthenticatorService as = new AuthenticatorService();
		as.log = super.getMockedLog(AuthenticatorService.class); //super is just to make clear method is in the above class
		assertTrue(as.authenticate());
	}

}