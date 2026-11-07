package c11_exception_hierarchy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 01.11 · The exception hierarchy
 * Handbook: OOP, Exceptions & Modern Java (oop.html#hierarchy)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c11_exception_hierarchy.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Exception hierarchy ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): walk the handbook's tree in code.
    static void coreExample() {
        System.out.println("\n-- 1. The tree --");
        List<Class<? extends Throwable>> types = List.of(
                NumberFormatException.class, ArrayIndexOutOfBoundsException.class,
                FileNotFoundException.class, NoSuchFileException.class, InterruptedException.class,
                ClassNotFoundException.class, StackOverflowError.class, OutOfMemoryError.class);
        for (Class<? extends Throwable> t : types) {
            System.out.printf("%-18s %s%n", Hierarchy.kind(t), Hierarchy.chain(t));
        }
    }

    // TODO 2 (done): which catch block wins? Predict BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        whoCatches(() -> { throw new NumberFormatException("abc"); });
        whoCatches(() -> { throw new IllegalStateException("closed"); });
        whoCatches(() -> { throw new UnsupportedOperationException("read-only"); });
        whoCatches(() -> { throw new AssertionError("failed check"); });
    }

    static void whoCatches(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException block caught " + e.getClass().getSimpleName());
        } catch (RuntimeException e) {
            System.out.println("RuntimeException block caught " + e.getClass().getSimpleName());
        } catch (Exception e) {
            System.out.println("Exception block caught " + e.getClass().getSimpleName());
        } catch (Throwable t) {
            System.out.println("only the Throwable block caught " + t.getClass().getSimpleName());
        }
    }

    // TODO 3 (done): my own real-life example: trigger the common unchecked exceptions for real.
    static void realLifeExample() {
        System.out.println("\n-- 3. Everyday exceptions --");
        trigger("charAt(5)", () -> "abc".charAt(5));
        trigger("List.of().add", () -> List.of("a").add("b"));
        trigger("bad cast", () -> {
            Object o = "text";
            return (Integer) o;
        });
        trigger("remove in for-each", () -> {
            List<String> names = new ArrayList<>(List.of("Asha", "Ravi", "Sam"));
            for (String n : names) {
                if (n.startsWith("A")) {
                    names.remove(n);
                }
            }
            return names;
        });
        trigger("null.length()", () -> {
            String s = null;
            return s.length();
        });
    }

    static void trigger(String label, Supplier<Object> action) {
        try {
            action.get();
            System.out.printf("%-20s no exception%n", label);
        } catch (RuntimeException e) {
            System.out.printf("%-20s %s (parent: %s)%n", label, e.getClass().getSimpleName(),
                    e.getClass().getSuperclass().getSimpleName());
        }
    }

    // TODO 4 (done): interview question as code.
    // Q: "Can you catch an Error? Should you?"
    static int depth = 0;

    static void recurse() {
        depth++;
        recurse(); // no base case
    }

    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        try {
            recurse();
        } catch (StackOverflowError e) {
            System.out.println("caught StackOverflowError after about " + (depth / 1000) * 1000 + " calls");
            System.out.println("is it an Exception? " + (((Throwable) e) instanceof Exception));
        }

        // try { } catch (IOException e) { } // compile error: nothing in the try can throw IOException
        try {
            throw new IOException("disk", new IllegalStateException("root cause"));
        } catch (IOException e) {
            System.out.println("Throwable methods: message=" + e.getMessage()
                    + ", cause=" + e.getCause().getMessage()
                    + ", suppressed=" + e.getSuppressed().length
                    + ", top frame=" + e.getStackTrace()[0].getMethodName());
        }
    }
}
