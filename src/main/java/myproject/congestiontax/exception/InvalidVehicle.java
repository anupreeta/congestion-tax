package myproject.congestiontax.exception;

/**
 * Invalid Vehicle Exception: to indicate the input vehicle is invalid
 */
public class InvalidVehicle extends RuntimeException {
    public InvalidVehicle(String message) {
        super(message);
    }
}
