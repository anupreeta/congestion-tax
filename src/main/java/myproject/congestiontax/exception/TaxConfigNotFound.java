package myproject.congestiontax.exception;

/**
 * Tax Config Not Found Exception: to indicate the city configuration file not found
 */
public class TaxConfigNotFound extends RuntimeException {
    public TaxConfigNotFound(String message) {
        super(message);
    }
}
