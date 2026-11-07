package c06_abstraction;

/** A small immutable result object (a record, Java 16+). */
public record Receipt(String method, double amount, String status) {
    public static final Receipt EMPTY = new Receipt("none", 0, "nothing to refund");
}
