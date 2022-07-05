package myproject.congestiontax.exception;

/**
 * Tax Config Data Error Exception: to indicate the city configuration file contains erroneous data
 */
public class TaxConfigDataError extends RuntimeException {
    public TaxConfigDataError(String message) {
        super(message);
    }
}
