package word.security;

import org.jboss.seam.annotations.Name;

@Name("authenticator")
public class AuthenticatorService {

	public boolean authenticate() {
		return true;
	}
}
