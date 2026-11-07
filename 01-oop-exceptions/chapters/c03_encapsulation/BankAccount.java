package c03_encapsulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulated: the data is hidden, and the only way to change it is through
 * methods that protect the rules (invariants):
 *   1. an account always has an owner
 *   2. the balance is never negative
 *   3. every change appears in the history
 */
public class BankAccount {

    private final String owner;
    private double balance;
    private final List<String> history = new ArrayList<>();

    public BankAccount(String owner, double opening) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("owner required");
        }
        if (opening < 0) {
            throw new IllegalArgumentException("opening balance can't be negative");
        }
        this.owner = owner;
        this.balance = opening;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        balance += amount;
        history.add("+" + amount);
    }

    public void withdraw(double amount) {
        // Added to the handbook's version. Without it, withdraw(-100) ADDS money.
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
        history.add("-" + amount);
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    /** Returns an unmodifiable copy, so callers cannot change the real list. */
    public List<String> getHistory() {
        return List.copyOf(history);
    }

    // No setBalance(): the only way to change money is through the rules above.
}
