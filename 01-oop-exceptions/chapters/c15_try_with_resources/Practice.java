package c15_try_with_resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 01.15 · try-with-resources
 * Handbook: OOP, Exceptions & Modern Java (oop.html#twr)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c15_try_with_resources.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) throws IOException {
        System.out.println("== try-with-resources ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's three resources, closed in reverse order.
    static void coreExample() {
        System.out.println("\n-- 1. Reverse close order --");
        try (FakeResource reader = new FakeResource("reader");
             FakeResource con = new FakeResource("connection");
             FakeResource ps = new FakeResource("statement")) {
            reader.use();
            ps.use();
        }

        System.out.println("Java 9: an existing effectively-final variable");
        FakeResource lease = new FakeResource("lease");
        try (lease) {
            lease.use();
        }
        // try (Object o = new Object()) { } // compile error: Object is not AutoCloseable
    }

    // TODO 2 (done): predict the order of every line BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2a. Predict: does close() run before or after catch? --");
        try (FakeResource r = new FakeResource("file")) {
            throw new IllegalStateException("body failed");
        } catch (IllegalStateException e) {
            System.out.println("  catch sees: " + e.getMessage());
        } finally {
            System.out.println("  finally");
        }

        System.out.println("\n-- 2b. Predict: the second resource fails to open --");
        try (FakeResource a = new FakeResource("first");
             FakeResource b = new FakeResource("second", true, false)) {
            System.out.println("  body never runs");
        } catch (IllegalStateException e) {
            System.out.println("  catch sees: " + e.getMessage());
        }

        System.out.println("\n-- 2c. Predict: the body AND both close() calls fail --");
        try (FakeResource con = new FakeResource("connection", false, true);
             FakeResource ps = new FakeResource("statement", false, true)) {
            throw new IllegalStateException("query failed");
        } catch (IllegalStateException e) {
            System.out.println("  main exception: " + e.getMessage());
            for (Throwable s : e.getSuppressed()) {
                System.out.println("  suppressed:     " + s.getMessage());
            }
        }
    }

    // TODO 3 (done): my own real-life example: write and read a real file.
    static void realLifeExample() throws IOException {
        System.out.println("\n-- 3. A real file --");
        Path file = Files.createTempFile("shopping", ".txt");
        file.toFile().deleteOnExit();

        try (BufferedWriter out = Files.newBufferedWriter(file)) {
            out.write("milk\nbread\neggs\n");
        } // flushed and closed here; without close() the text might never reach the disk

        int count = 0;
        try (BufferedReader in = Files.newBufferedReader(file)) {
            String line;
            while ((line = in.readLine()) != null) {
                count++;
                System.out.println("  item " + count + ": " + line);
            }
        }
        System.out.println("  read " + count + " items; both streams already closed");
    }

    // TODO 4 (done): interview question as code.
    // Q: "Why is try-with-resources better than closing in finally?" The old way loses the real error.
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        try {
            oldStyle();
        } catch (IllegalStateException e) {
            System.out.println("old finally style, caller sees: " + e.getMessage()
                    + " (suppressed: " + e.getSuppressed().length + ")");
        }
        try {
            newStyle();
        } catch (IllegalStateException e) {
            System.out.println("try-with-resources, caller sees: " + e.getMessage()
                    + " (suppressed: " + e.getSuppressed()[0].getMessage() + ")");
        }
    }

    static void oldStyle() {
        FakeResource r = null;
        try {
            r = new FakeResource("old", false, true);
            throw new IllegalStateException("the REAL problem");
        } finally {
            if (r != null) {
                r.close(); // its exception replaces the real one
            }
        }
    }

    static void newStyle() {
        try (FakeResource r = new FakeResource("new", false, true)) {
            // r = null; // compile error: a resource variable is implicitly final
            throw new IllegalStateException("the REAL problem");
        }
    }
}
