package c16_custom_exceptions;

import java.math.BigDecimal;

/** Custom CHECKED exception: a business situation callers are expected to handle. */
public class InsufficientFundsException extends Exception {

    private static final long serialVersionUID = 1L; // exceptions are Serializable

    private final BigDecimal shortBy;

    public InsufficientFundsException(BigDecimal balance, BigDecimal requested) {
        super("Balance " + balance + " is less than " + requested);
        this.shortBy = requested.subtract(balance);
    }

    public BigDecimal getShortBy() {
        return shortBy; // extra context a caller can act on
    }
}
