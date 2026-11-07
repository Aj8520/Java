package c12_checked_vs_unchecked;

/**
 * Real-life example of the common team rule:
 * checked for a business condition the caller must decide on,
 * unchecked for a bug (bad input that should never happen).
 */
public class Account {

    /** Checked: the caller MUST decide what to do (show a message, offer a loan...). */
    public static class InsufficientFundsException extends Exception {
        InsufficientFundsException(String message) {
            super(message);
        }
    }

    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive, was " + amount); // a bug
        }
        if (amount > balance) {
            throw new InsufficientFundsException("need " + amount + ", have " + balance); // business case
        }
        balance -= amount;
    }

    public int balance() {
        return balance;
    }
}
