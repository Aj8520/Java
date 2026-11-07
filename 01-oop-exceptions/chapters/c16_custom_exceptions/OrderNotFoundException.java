package c16_custom_exceptions;

/** Custom UNCHECKED exception: the common choice in Spring apps. */
public class OrderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(long id) {
        super("Order " + id + " not found");
    }
}
