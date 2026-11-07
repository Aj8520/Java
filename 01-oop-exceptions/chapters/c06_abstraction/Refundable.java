package c06_abstraction;

import java.time.LocalDate;

/** Interface: a "can-do" capability with no instance state. */
public interface Refundable {

    int MAX_DAYS = 30; // implicitly public static final

    Receipt refund(String txnId); // implicitly public abstract

    /** Java 8: default method, a body every implementer gets for free. */
    default boolean canRefund(LocalDate paidOn, LocalDate today) {
        log("checking refund window for " + paidOn);
        return !paidOn.plusDays(MAX_DAYS).isBefore(today);
    }

    /** Java 8: static method, called as Refundable.none(). */
    static Refundable none() {
        return txnId -> Receipt.EMPTY; // one abstract method, so a lambda works
    }

    /** Java 9: private helper, shared by the default methods only. */
    private void log(String message) {
        System.out.println("  [refundable] " + message);
    }
}
