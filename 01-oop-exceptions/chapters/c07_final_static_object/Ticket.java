package c07_final_static_object;

/** Real-life example: a token machine. static = the machine's counter, final = your token number. */
public class Ticket {

    private static int nextNumber = 1; // shared by all tickets

    private final int number;          // fixed once printed
    private final String counter;

    public Ticket(String counter) {
        this.number = nextNumber++;
        this.counter = counter;
    }

    public static int issuedSoFar() {
        return nextNumber - 1;
        // return this.number; // compile error: static methods have no "this"
    }

    @Override
    public String toString() {
        return "Token " + number + " at " + counter;
    }
}
