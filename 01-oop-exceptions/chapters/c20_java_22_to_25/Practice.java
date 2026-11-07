package c20_java_22_to_25;

import java.util.List;
import java.util.Map;

/**
 * 01.20 · After 21: Java 22 to 25
 * Handbook: OOP, Exceptions & Modern Java (oop.html#beyond)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c20_java_22_to_25.Practice
 *
 * This module compiles with Java 17, so NONE of the 22-25 syntax compiles here.
 * Each example runs the Java 17 way, with the 22-25 version in a comment next to it.
 */
public class Practice {

    public static void main(String[] args) throws Exception {
        System.out.println("== Java 22 -> 25 ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): one feature per release, Java 17 way + the new syntax in comments.
    static void coreExample() {
        System.out.println("\n-- 1. Features, the Java 17 way --");

        // 22: unnamed variables "_" (final). Java 22+:  catch (NumberFormatException _) { ... }
        //                                               for (var _ : orders) count++;
        int count = 0;
        for (String ignored : List.of("a", "b", "c")) { // Java 17: a name you never use
            count++;
        }
        System.out.println("22 unnamed variables: counted " + count + " without using the loop variable");

        // 23: Markdown in Javadoc comments uses "///" lines. In 17 these are just ordinary comments:
        /// **Bold** and `code` render in Javadoc 23+
        System.out.println("23 markdown javadoc: '///' lines compile everywhere, render only in 23+");

        // 24: Stream Gatherers (final): stream.gather(Gatherers.windowFixed(3))
        System.out.println("24 windowFixed(3):   " + Windows.fixed(List.of(1, 2, 3, 4, 5, 6, 7), 3));

        // 25: compact source files + instance main methods (final). A whole program can be:
        //     void main() { IO.println("Hello"); }
        System.out.println("25 compact main: in 17 you still need a class and public static void main");
    }

    // TODO 2 (done): predict BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        System.out.println("windowFixed(2):   " + Windows.fixed(nums, 2));
        System.out.println("windowSliding(3): " + Windows.sliding(nums, 3));
        System.out.println("windowSliding(9): " + Windows.sliding(nums, 9)); // bigger than the list?

        // Flexible constructor bodies (25): statements BEFORE super(...).
        try {
            new Employee("");
        } catch (IllegalArgumentException e) {
            System.out.println("validated before super(): " + e.getMessage());
        }
    }

    static class Person {
        final String name;
        Person(String name) {
            System.out.println("  Person constructor runs");
            this.name = name;
        }
    }

    static class Employee extends Person {
        /*
         * Java 25:
         *   Employee(String name) {
         *       if (name.isBlank()) throw new IllegalArgumentException("name required");
         *       super(name);
         *   }
         * Java 17: super(...) must be first, so validate inside its argument.
         */
        Employee(String name) {
            super(requireName(name));
        }

        private static String requireName(String name) {
            if (name.isBlank()) {
                throw new IllegalArgumentException("name required (Person constructor never ran)");
            }
            return name;
        }
    }

    // TODO 3 (done): my own real-life example: a request id visible to every method.
    // Java 25: ScopedValue (final). Java 17: ThreadLocal, which must be cleaned up by hand.
    //   static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();
    //   ScopedValue.where(REQUEST_ID, "req-7").run(() -> handle());   // gone after run()
    static final ThreadLocal<String> REQUEST_ID = new ThreadLocal<>();

    static void realLifeExample() {
        System.out.println("\n-- 3. Request id --");
        REQUEST_ID.set("req-7");
        try {
            handleRequest();
        } finally {
            REQUEST_ID.remove(); // forget this and a pooled thread keeps the old id
        }
        System.out.println("after the request: " + REQUEST_ID.get());
    }

    static void handleRequest() {
        System.out.println("controller sees " + REQUEST_ID.get());
        saveOrder();
    }

    static void saveOrder() {
        System.out.println("repository sees " + REQUEST_ID.get() + " (never passed as a parameter)");
    }

    // TODO 4 (done): interview question as code.
    // Q: "What changed after Java 21, and which release is the next LTS?"
    static void interviewQuestion() throws Exception {
        System.out.println("\n-- 4. Interview question --");
        Map<Integer, String> highlights = Map.of(
                22, "FFM API final (call C without JNI), unnamed variables _, multi-file source launch",
                23, "Markdown Javadoc, generational ZGC by default",
                24, "Stream Gatherers, virtual threads no longer pinned by synchronized, quantum-resistant crypto",
                25, "LTS: compact source + instance main, module imports, flexible constructors, ScopedValue, compact object headers");
        for (int v = 22; v <= 25; v++) {
            System.out.println("  " + v + ": " + highlights.get(v));
        }

        // Check what this JVM can do, without compiling any new syntax.
        int feature = Runtime.version().feature();
        boolean hasScopedValue = classExists("java.lang.ScopedValue");
        boolean hasGatherers = classExists("java.util.stream.Gatherers");
        System.out.println("this JVM is " + feature + ": ScopedValue class? " + hasScopedValue
                + ", Gatherers class? " + hasGatherers);
    }

    static boolean classExists(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
