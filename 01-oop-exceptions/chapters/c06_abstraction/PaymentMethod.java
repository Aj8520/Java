package c06_abstraction;

/**
 * Abstract class: a partly built class. It has state, a constructor and finished
 * methods, and leaves one step (charge) for subclasses. This is the Template Method pattern.
 */
public abstract class PaymentMethod {

    protected final String customerId;

    protected PaymentMethod(String customerId) {
        this.customerId = customerId;
        System.out.println("  PaymentMethod constructor ran for " + customerId);
    }

    /** Shared, finished logic. final so subclasses can't skip the validation. */
    public final Receipt pay(double amount) {
        validate(amount);
        Receipt receipt = charge(amount); // the step each subclass fills in
        audit(receipt);
        return receipt;
    }

    protected abstract Receipt charge(double amount); // no body: subclasses MUST implement it

    private void validate(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive, was " + amount);
        }
    }

    private void audit(Receipt receipt) {
        System.out.println("  audit: " + customerId + " -> " + receipt);
    }
}
