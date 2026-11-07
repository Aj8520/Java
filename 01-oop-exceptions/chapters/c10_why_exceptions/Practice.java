package c10_why_exceptions;

/**
 * 01.10 · Why exceptions?
 * Handbook: OOP, Exceptions & Modern Java (oop.html#exceptions)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c10_why_exceptions.Practice
 */
public class Practice {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("== Why exceptions? ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's example, without and with handling.
    static void coreExample() {
        System.out.println("\n-- 1. Without and with handling --");
        int[] marks = {70, 85};
        try {
            System.out.println(marks[5]); // without the try, main would die here
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("caught " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        System.out.println("100 / \"4\" = " + divide("4"));
    }

    /** The handbook's try/catch/finally: parse, divide, always clean up. */
    static String divide(String userInput) {
        try {
            int value = Integer.parseInt(userInput); // may throw NumberFormatException
            return String.valueOf(100 / value);      // may throw ArithmeticException
        } catch (NumberFormatException e) {
            return "Please type a number";
        } catch (ArithmeticException e) {
            return "Can't divide by zero";
        } finally {
            System.out.println("  finally ran for input '" + userInput + "'");
        }
    }

    // TODO 2 (done): predict the result for each input BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        for (String input : new String[] {"5", "abc", "0", " 7", "-25", "2147483648"}) {
            System.out.println("divide(\"" + input + "\") -> " + divide(input));
        }
        // And one without any catch: what do you get for 100.0 / 0?
        System.out.println("100.0 / 0 = " + (100.0 / 0));
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Error codes vs exceptions --");
        SeatBooking cinema = new SeatBooking();

        int seat = cinema.bookWithCode(42);  // the caller forgets to check the code
        System.out.println("error-code style: \"Booked seat " + seat + "\" (bug: -1 slipped through)");

        try {
            cinema.book(42);
            System.out.println("never printed");
        } catch (IllegalArgumentException e) {
            System.out.println("exception style: " + e.getMessage());
        }

        cinema.book(3);
        try {
            cinema.book(3);
        } catch (IllegalStateException e) {
            System.out.println("exception style: " + e.getMessage());
        }
    }

    // TODO 4 (done): interview question as code.
    // Q: "What happens to an exception nobody catches?" The thread dies; other threads go on.
    static void interviewQuestion() throws InterruptedException {
        System.out.println("\n-- 4. Interview question --");
        Thread worker = new Thread(() -> {
            System.out.println("worker: starting");
            Object nothing = null;
            nothing.toString(); // nobody catches this
            System.out.println("worker: never printed");
        }, "worker-1");

        // Replace the default "print the stack trace" so the output stays short.
        worker.setUncaughtExceptionHandler((t, e) ->
                System.out.println("uncaught in " + t.getName() + ": " + e.getClass().getSimpleName()
                        + " at " + e.getStackTrace()[0].getMethodName()));
        worker.start();
        worker.join();

        System.out.println("main is still running after the worker died");
        Exception asObject = new IllegalStateException("an exception is just an object");
        System.out.println("not thrown, just created: " + asObject.getMessage());
    }
}
