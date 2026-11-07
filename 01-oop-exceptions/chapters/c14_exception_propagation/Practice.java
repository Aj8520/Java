package c14_exception_propagation;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UncheckedIOException;

/**
 * 01.14 · Exception propagation and stack traces
 * Handbook: OOP, Exceptions & Modern Java (oop.html#propagation)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c14_exception_propagation.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Propagation & stack traces ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's demo. Catch in main and watch every frame pop.
    static void coreExample() {
        System.out.println("\n-- 1. Catch in main --");
        try {
            new OrderFlow("main").place();
        } catch (IllegalStateException e) {
            System.out.println("  caught in main: " + e.getMessage());
        }
    }

    // TODO 2 (done): move the catch closer. Predict which "leaving" lines print and
    // whether "checkout continues" appears BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict: catch in checkout --");
        new OrderFlow("checkout").place();
        System.out.println("\n-- 2. Predict: catch in place --");
        new OrderFlow("place").place();
    }

    // TODO 3 (done): my own real-life example: read a stack trace and a helpful NPE message.
    static void realLifeExample() {
        System.out.println("\n-- 3. Reading a stack trace --");
        Customer newCustomer = new Customer("Asha", null);
        try {
            System.out.println(newCustomer.getAddress().getCity());
        } catch (NullPointerException e) {
            System.out.println("helpful NPE message (Java 14+):");
            System.out.println("  " + e.getMessage());
        }

        try {
            loadInvoice(42);
        } catch (RuntimeException e) {
            System.out.println("stack trace, first lines only:");
            String[] lines = stackTraceOf(e).split("\n");
            for (String line : lines) {
                if (!line.trim().startsWith("at ") || line.contains("c14_")) { // keep our frames
                    System.out.println("  " + line.trim());
                }
            }
            System.out.println("root cause: " + rootCause(e).getMessage());
        }
    }

    static void loadInvoice(int id) {
        try {
            readFile("invoice-" + id + ".pdf");
        } catch (IOException e) {
            throw new UncheckedIOException("Could not load invoice " + id, e); // chain: keep e
        }
    }

    static void readFile(String name) throws IOException {
        throw new IOException("disk not mounted: " + name);
    }

    static String stackTraceOf(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    static Throwable rootCause(Throwable t) {
        while (t.getCause() != null) {
            t = t.getCause();
        }
        return t;
    }

    // TODO 4 (done): interview question as code.
    // Q: "How does a checked exception propagate compared with an unchecked one?"
    static void level3Checked() throws IOException { throw new IOException("checked from level 3"); }
    static void level2Checked() throws IOException { level3Checked(); }     // must declare
    // static void level2NoThrows() { level3Checked(); } // compile error: every level must declare it
    static void level1Checked() throws IOException { level2Checked(); }

    static void level3Unchecked() { throw new IllegalStateException("unchecked from level 3"); }
    static void level2Unchecked() { level3Unchecked(); }                     // nothing to declare
    static void level1Unchecked() { level2Unchecked(); }

    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        try {
            level1Checked();
        } catch (IOException e) {
            System.out.println("checked:   " + e.getMessage() + ", thrown at " + e.getStackTrace()[0].getMethodName()
                    + ", " + e.getStackTrace().length + " frames deep");
        }
        try {
            level1Unchecked();
        } catch (IllegalStateException e) {
            System.out.println("unchecked: " + e.getMessage() + ", thrown at " + e.getStackTrace()[0].getMethodName());
        }
    }
}
