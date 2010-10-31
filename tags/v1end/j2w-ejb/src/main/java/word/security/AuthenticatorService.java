package word.security;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

@Name("authenticator")
public class AuthenticatorService {

	@Logger
	public Log log;

	public boolean authenticate() {
		log.info("authenticate");
		log.info("authenticate this one: #0", "whatever");
		return true;
	}
}
