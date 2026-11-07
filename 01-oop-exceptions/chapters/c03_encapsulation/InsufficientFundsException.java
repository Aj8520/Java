package c03_encapsulation;

/** Thrown when a withdrawal is larger than the balance. */
public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(double balance, double amount) {
        super("Tried to withdraw " + amount + " but balance is " + balance);
    }
}
