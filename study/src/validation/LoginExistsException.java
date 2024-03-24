package validation;

@SuppressWarnings("serial")
public class LoginExistsException extends Throwable {
    private static final long serialVersionUID = 1L;

    public LoginExistsException() {
        super();
    }

    public LoginExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LoginExistsException(final String message) {
        super(message);
    }

    public LoginExistsException(final Throwable cause) {
        super(cause);
    }

}