package c03_encapsulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 01.03 · Encapsulation
 * Handbook: OOP, Exceptions & Modern Java (oop.html#encapsulation)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c03_encapsulation.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Encapsulation ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's two versions, side by side.
    static void coreExample() {
        System.out.println("\n-- 1. Public fields vs encapsulated --");

        PublicFieldAccount bad = new PublicFieldAccount();
        bad.balance = -5000; // nothing stops this
        bad.owner = null;    // or this
        System.out.println("bad account: owner=" + bad.owner + ", balance=" + bad.balance);

        BankAccount good = new BankAccount("Ajay", 1000);
        // good.balance = -5000;   // compile error: balance is private
        // good.setBalance(-5000); // compile error: no such method, on purpose
        good.deposit(500);
        good.withdraw(200);
        System.out.println("good account: owner=" + good.getOwner() + ", balance=" + good.getBalance());
        System.out.println("history: " + good.getHistory());

        try {
            good.withdraw(5000);
        } catch (InsufficientFundsException e) {
            System.out.println("rejected: " + e.getMessage());
        }
    }

    // TODO 2 (done): edge cases. Predict each line BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        BankAccount account = new BankAccount("Ajay", 100);

        // Prediction 1: a negative withdrawal. Does the balance go up?
        // Then delete the "amount <= 0" check in withdraw() and predict again.
        try {
            account.withdraw(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }
        System.out.println("balance: " + account.getBalance());

        // Prediction 2: can the rules be broken while creating the object?
        try {
            new BankAccount(" ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }

        // Prediction 3: can the history be changed from outside?
        try {
            account.getHistory().add("+1000000");
        } catch (UnsupportedOperationException e) {
            System.out.println("history is read-only: " + e.getClass().getSimpleName());
        }
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Thermostat --");
        Thermostat thermostat = new Thermostat();
        thermostat.setTemperature(29);
        thermostat.warmer();
        System.out.println("temperature: " + thermostat.getTemperature());

        try {
            thermostat.warmer(); // 31 is out of range
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }
        System.out.println("temperature is unchanged: " + thermostat.getTemperature());
    }

    // TODO 4 (done): interview question as code.
    // Q: "All my fields are private final. Is my class encapsulated?" Not always.
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");

        List<String> names = new ArrayList<>(List.of("Asha", "Ravi"));
        LeakyTeam leaky = new LeakyTeam(names);
        SafeTeam safe = new SafeTeam(names);

        names.add("Sam");              // change the caller's list
        leaky.getMembers().add("Neha"); // change through the getter

        System.out.println("leaky team: " + leaky.getMembers()); // limit was 2
        System.out.println("safe team:  " + safe.getMembers());

        try {
            safe.getMembers().add("Neha");
        } catch (UnsupportedOperationException e) {
            System.out.println("safe team rejected the change: " + e.getClass().getSimpleName());
        }
    }
}
