package nl.fizzylogic.reactivepi;

/**
 * Gets thrown when the native driver does not initialize
 */
public class DriverInitializationException extends RuntimeException {
    /**
     * Initializes a new instance of DriverInitializationException
     *
     * @param message Message to display
     */
    public DriverInitializationException(String message) {
        super(message);
    }
}
