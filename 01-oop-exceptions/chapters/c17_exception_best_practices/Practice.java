package c17_exception_best_practices;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 01.17 · Best practices, lambdas, and Spring Boot
 * Handbook: OOP, Exceptions & Modern Java (oop.html#excpractice)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c17_exception_best_practices.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) throws IOException {
        System.out.println("== Exception best practices ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): a few of the handbook's 10 rules, wrong way vs right way.
    static int logLines = 0;

    static void log(String message) {
        logLines++;
        System.out.println("  [log] " + message);
    }

    static void coreExample() {
        System.out.println("\n-- 1. Rules in code --");

        // Rule 1: never swallow.
        try {
            Integer.parseInt("x");
        } catch (NumberFormatException e) {
            // empty: the error vanishes (this is the WRONG way, kept to show it)
        }
        System.out.println("rule 1: swallowed exception -> nothing printed, nobody knows");

        // Rule 2: log OR rethrow, not both. Count how often one error is logged.
        logLines = 0;
        try {
            layer3LogsAndRethrows();
        } catch (IllegalStateException e) {
            log("controller: " + e.getMessage());
        }
        System.out.println("rule 2: one failure was logged " + logLines + " times");

        // Rule 4: no exceptions for normal control flow.
        Map<String, String> prices = Map.of("tea", "20");
        System.out.println("rule 4: price of coffee = " + Optional.ofNullable(prices.get("coffee")).orElse("not on menu"));

        // Rule 6: specific messages with values.  Rule 7: fail fast.
        try {
            applyDiscount(140);
        } catch (IllegalArgumentException e) {
            System.out.println("rule 6: " + e.getMessage());
        }
        try {
            ship(null);
        } catch (NullPointerException e) {
            System.out.println("rule 7: " + e.getMessage());
        }
    }

    static void layer3LogsAndRethrows() {
        try {
            throw new IllegalStateException("payment timeout");
        } catch (IllegalStateException e) {
            log("repository: " + e.getMessage());
            throw e; // the controller will log it AGAIN
        }
    }

    static void applyDiscount(int pct) {
        if (pct < 0 || pct > 100) {
            throw new IllegalArgumentException("discount must be 0-100, was " + pct);
        }
    }

    static void ship(Object order) {
        Objects.requireNonNull(order, "order must not be null"); // fail at the door, not deep inside
    }

    // TODO 2 (done): checked exceptions inside lambdas. Predict what the stream does BEFORE running.
    static void predictTheOutput() throws IOException {
        System.out.println("\n-- 2. Predict: lambdas and checked exceptions --");
        Path a = Files.createTempFile("a", ".txt");
        Path c = Files.createTempFile("c", ".txt");
        a.toFile().deleteOnExit();
        c.toFile().deleteOnExit();
        Files.writeString(a, "alpha");
        Files.writeString(c, "gamma");
        List<Path> paths = List.of(a, Path.of("missing-b.txt"), c);

        // List<String> broken = paths.stream().map(p -> Files.readString(p)).toList(); // compile error: unreported IOException

        try {
            List<String> contents = paths.stream()
                    .peek(p -> System.out.println("  reading " + p.getFileName().toString().replaceAll("\\d+", "")))
                    .map(Practice::read) // option 2: a helper method that wraps
                    .collect(Collectors.toList());
            System.out.println("  contents: " + contents);
        } catch (UncheckedIOException e) {
            System.out.println("  stream stopped: " + e.getCause().getClass().getSimpleName());
        }
    }

    static String read(Path p) {
        try {
            return Files.readString(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    // TODO 3 (done): my own real-life example: a global handler, Spring style.
    static void realLifeExample() {
        System.out.println("\n-- 3. One global handler --");
        List<ProblemDetail> responses = List.of(
                GlobalExceptionHandler.call(() -> "order 1: 2 items"),
                GlobalExceptionHandler.call(() -> { throw new GlobalExceptionHandler.OrderNotFoundException(99); }),
                GlobalExceptionHandler.call(() -> { applyDiscount(-5); return "never"; }),
                GlobalExceptionHandler.call(() -> { throw new IllegalStateException("db password=secret123 rejected"); }));
        responses.forEach(r -> System.out.println("  " + r));
    }

    // TODO 4 (done): interview question as code.
    // Q: "You catch InterruptedException. What should you do with it?"
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        System.out.println("swallowed: still interrupted? " + sleepAndSwallow());
        System.out.println("restored:  still interrupted? " + sleepAndRestore());
        Thread.interrupted(); // clear the flag again for the rest of the program
    }

    static boolean sleepAndSwallow() {
        Thread.currentThread().interrupt(); // someone asks this thread to stop
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // WRONG: the request to stop is forgotten
        }
        return Thread.currentThread().isInterrupted();
    }

    static boolean sleepAndRestore() {
        Thread.currentThread().interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // RIGHT: keep the request for code further up
        }
        return Thread.currentThread().isInterrupted();
    }
}
