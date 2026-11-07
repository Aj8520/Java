package c06_abstraction;

import java.time.LocalDate;

/**
 * 01.06 · Abstraction: abstract classes and interfaces
 * Handbook: OOP, Exceptions & Modern Java (oop.html#abstraction)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c06_abstraction.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Abstraction ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's PaymentMethod (abstract class) and Refundable (interface).
    static void coreExample() {
        System.out.println("\n-- 1. Abstract class + interface --");
        // PaymentMethod p = new PaymentMethod("c1"); // compile error: PaymentMethod is abstract

        UpiPayment upi = new UpiPayment("cust-7");
        Receipt receipt = upi.pay(499);
        System.out.println("receipt: " + receipt);
        System.out.println("refund: " + upi.refund("TXN-1"));

        PaymentMethod cash = new CashPayment("cust-8");
        System.out.println("receipt: " + cash.pay(250));
        System.out.println("cash is Refundable? " + (cash instanceof Refundable));
    }

    // TODO 2 (done): predict each line BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        UpiPayment upi = new UpiPayment("cust-9");
        LocalDate today = LocalDate.of(2026, 11, 7);

        // Prediction 1: paid exactly 30 days ago. Still refundable?
        System.out.println("30 days ago: " + upi.canRefund(today.minusDays(30), today));
        // Prediction 2: 31 days ago?
        System.out.println("31 days ago: " + upi.canRefund(today.minusDays(31), today));

        // Prediction 3: does the template stop a bad amount BEFORE charge() runs?
        try {
            upi.pay(0);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }

        // Prediction 4: "you can't instantiate an abstract class". So what is this?
        PaymentMethod voucher = new PaymentMethod("cust-10") {
            @Override
            protected Receipt charge(double amount) {
                return new Receipt("Voucher", amount, "redeemed");
            }
        };
        System.out.println("anonymous subclass: " + voucher.pay(100));
        System.out.println("its class name: '" + voucher.getClass().getSimpleName() + "'");
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Driving --");
        System.out.println("petrol car:");
        new Car(new PetrolEngine()).drive();
        System.out.println("electric car:");
        new Car(new ElectricEngine()).drive(); // Car.drive() did not change at all
    }

    // TODO 4 (done): interview question as code.
    // Q: "Abstract class vs interface: what can each one hold?"
    abstract static class Counter {
        protected int count;                     // instance state: only abstract classes
        protected Counter(int start) { count = start; } // constructor: only abstract classes
        abstract void tick();
    }

    interface Limits {
        int MAX = 3;                             // a constant, not a field each object owns
        // Limits(); // compile error: interfaces have no constructors
    }

    static class ClickCounter extends Counter implements Limits {
        ClickCounter() { super(1); }
        @Override void tick() { if (count < MAX) count++; }
    }

    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        ClickCounter c = new ClickCounter();
        for (int i = 0; i < 5; i++) {
            c.tick();
        }
        System.out.println("count after 5 ticks (max " + Limits.MAX + "): " + c.count);
        // Limits.MAX = 10; // compile error: interface fields are final

        Refundable nothing = Refundable.none(); // static interface method returning a lambda
        System.out.println("Refundable.none(): " + nothing.refund("TXN-9"));
    }
}
