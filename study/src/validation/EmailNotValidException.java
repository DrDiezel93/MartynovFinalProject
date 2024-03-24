package validation;

@SuppressWarnings("serial")
public class EmailNotValidException extends Throwable {
    private static final long serialVersionUID = 1L;

    public EmailNotValidException(final String message) {
        super(message);
    }

}