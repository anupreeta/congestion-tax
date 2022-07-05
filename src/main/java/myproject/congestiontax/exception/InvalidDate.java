package myproject.congestiontax.exception;

/**
 * Invalid Date Exception: to indicate the input date is invalid
 */
public class InvalidDate extends RuntimeException {
    public InvalidDate(String message) {
        super(message);
    }
}
