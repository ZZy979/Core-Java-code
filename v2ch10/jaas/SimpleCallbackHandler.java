package jaas;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;

/**
 * This simple callback handler presents the given username and password.
 */
public class SimpleCallbackHandler implements CallbackHandler {
    private String username;
    private char[] password;

    /**
     * Constructs the callback handler.
     * @param username the username
     * @param password a character array containing the password
     */
    public SimpleCallbackHandler(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback c) {
                c.setName(username);
            }
            else if (callback instanceof PasswordCallback c) {
                c.setPassword(password);
            }
        }
    }
}
