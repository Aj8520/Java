package c02_access_modifiers;

/**
 * Real-life use of private: the field is hidden so that every change must
 * go through a method that can check the rule "balance is never negative".
 */
public class BankAccount {

    private int balance;

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive: " + amount);
        }
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public int getBalance() {
        return balance;
    }
}
