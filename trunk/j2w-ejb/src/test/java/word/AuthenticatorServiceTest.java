package word;

import junit.framework.Assert;

import org.junit.Test;

import word.security.AuthenticatorService;

public class AuthenticatorServiceTest extends Assert{ 

	@Test
	public void AuthenticatorServiceTestTest() {
		AuthenticatorService as = new AuthenticatorService();
		assertTrue(as.authenticate());
	}

}
