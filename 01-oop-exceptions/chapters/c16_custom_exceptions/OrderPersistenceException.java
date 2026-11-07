package c16_custom_exceptions;

/** A higher-level exception that WRAPS a low-level one (exception chaining). */
public class OrderPersistenceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderPersistenceException(String message, Throwable cause) {
        super(message, cause); // keep the original as the cause
    }
}
