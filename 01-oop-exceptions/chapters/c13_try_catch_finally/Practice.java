package c13_try_catch_finally;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * 01.13 · try, catch, finally: what actually runs
 * Handbook: OOP, Exceptions & Modern Java (oop.html#flow)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c13_try_catch_finally.Practice
 */
public class Practice {

    public static void main(String[] args) throws Exception {
        System.out.println("== try / catch / finally ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's step-through demo, process(input).
    static int process(String input) {
        System.out.println("  start");
        try {
            int n = Integer.parseInt(input);
            System.out.println("  parsed " + n);
            return 100 / n;
        } catch (NumberFormatException e) {
            System.out.println("  not a number");
            return -1;
        } finally {
            System.out.println("  finally");
        }
    }

    static void coreExample() {
        System.out.println("\n-- 1. process(input) --");
        for (String input : new String[] {"5", "abc", "0"}) {
            System.out.println("process(\"" + input + "\"):");
            try {
                System.out.println("  returned " + process(input));
            } catch (ArithmeticException e) {
                System.out.println("  escaped to the caller: " + e.getMessage());
            }
        }
    }

    // TODO 2 (done): predict BEFORE running. finally vs return.
    static int test() {
        try {
            return 1;
        } finally {
            System.out.println("  finally runs even after return");
        }
    }

    @SuppressWarnings("finally")
    static int bad() {
        try {
            throw new RuntimeException("lost!");
        } finally {
            return 2; // overrides the exception: never do this
        }
    }

    static int changePrimitive() {
        int x = 1;
        try {
            return x;   // the value 1 is saved here
        } finally {
            x = 2;      // too late to change what is returned
        }
    }

    static StringBuilder changeObject() {
        StringBuilder sb = new StringBuilder("a");
        try {
            return sb;  // the REFERENCE is saved
        } finally {
            sb.append("b"); // but the object it points to can still change
        }
    }

    static void exceptionInFinally() {
        try {
            throw new IllegalStateException("original problem");
        } finally {
            throw new IllegalArgumentException("problem in finally"); // replaces the original
        }
    }

    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        System.out.println("test() = " + test());
        System.out.println("bad() = " + bad() + "  (where did \"lost!\" go?)");
        System.out.println("changePrimitive() = " + changePrimitive());
        System.out.println("changeObject() = " + changeObject());
        try {
            exceptionInFinally();
        } catch (RuntimeException e) {
            System.out.println("caller sees: " + e.getMessage());
        }
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Meeting room --");
        MeetingRoom room = new MeetingRoom();
        try {
            room.book("Asha");
            System.out.println("  meeting crashes...");
            throw new IllegalStateException("projector on fire");
        } catch (IllegalStateException e) {
            System.out.println("  handled: " + e.getMessage());
        } finally {
            room.release(); // runs on success AND on failure
        }
        System.out.println("occupied after the crash? " + room.isOccupied());
        room.book("Ravi"); // works, because the room was released
        room.release();
    }

    // TODO 4 (done): interview question as code.
    // Q: "Does finally always run?" Not after System.exit(). Shown in a separate JVM.
    static void interviewQuestion() throws Exception {
        System.out.println("\n-- 4. Interview question --");
        String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String classes = new File(ExitDemo.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();

        Process child = new ProcessBuilder(java, "-cp", classes, ExitDemo.class.getName())
                .redirectErrorStream(true).start();
        try (BufferedReader out = new BufferedReader(new InputStreamReader(child.getInputStream()))) {
            String line;
            while ((line = out.readLine()) != null) {
                System.out.println("  child JVM: " + line);
            }
        }
        System.out.println("child exit code: " + child.waitFor() + " (the finally line never printed)");
    }
}
