package c12_checked_vs_unchecked;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 01.12 · Checked vs unchecked
 * Handbook: OOP, Exceptions & Modern Java (oop.html#checked)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c12_checked_vs_unchecked.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) throws IOException {
        System.out.println("== Checked vs unchecked ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's checked exceptions: declare, handle or wrap.
    static void coreExample() throws IOException {
        System.out.println("\n-- 1. Declare, handle, wrap --");
        ConfigReader reader = new ConfigReader();
        Path real = Files.createTempFile("config", ".json");
        real.toFile().deleteOnExit();
        Files.writeString(real, "{\"port\": 8080}");

        System.out.println("declared:  " + reader.readConfig(real)); // main() declares IOException
        System.out.println("missing:   " + reader.readConfigOrDefault(Path.of("no-such-config.json")));

        Path folder = real.getParent(); // a directory, not a file
        try {
            reader.readConfigOrDefault(folder);
        } catch (UncheckedIOException e) {
            System.out.println("wrapped:   " + e.getMessage().replace(folder.toString(), "<tmp dir>")
                    + " (cause: " + e.getCause().getClass().getSimpleName() + ")");
        }
    }

    // TODO 2 (done): which catch block runs for each class name? Predict BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        PluginLoader loader = new PluginLoader();
        for (String name : new String[] {"java.util.ArrayList", "com.example.NoSuchPlugin", "java.util.List", "java.lang.Math"}) {
            try {
                Object plugin = loader.load(name);
                System.out.println(name + " -> loaded " + plugin.getClass().getSimpleName());
            } catch (PluginLoader.PluginLoadException e) {
                System.out.println(name + " -> " + e.getMessage().split(" for | java| com")[0]
                        + " (cause: " + e.getCause().getClass().getSimpleName() + ")");
            }
        }
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Checked for business rules, unchecked for bugs --");
        Account account = new Account(500);
        try {
            account.withdraw(200);
            account.withdraw(900);
        } catch (Account.InsufficientFundsException e) { // the compiler made me write this
            System.out.println("business case handled: " + e.getMessage());
        }
        try {
            account.withdraw(-5); // still has to be inside try: withdraw() declares the checked one
        } catch (Account.InsufficientFundsException e) {
            System.out.println("never printed");
        } catch (IllegalArgumentException e) {
            System.out.println("bug surfaced: " + e.getMessage());
        }
        System.out.println("balance: " + account.balance());
        // account.withdraw(10); // compile error: unreported exception InsufficientFundsException
    }

    // TODO 4 (done): interview question as code.
    // Q: "throw vs throws? And what may follow a catch of a parent type?"
    static void interviewQuestion() throws FileNotFoundException {
        System.out.println("\n-- 4. Interview question --");
        try {
            mayFail(true);
        } catch (FileNotFoundException e) {
            System.out.println("throws in the signature, throw in the body: " + e.getMessage());
        }

        // try { mayFail(true); } catch (IOException e) { } catch (FileNotFoundException e) { } // compile error: already caught
        // try { mayFail(true); } catch (FileNotFoundException | IOException e) { } // compile error: related types in multi-catch

        try {
            rethrow();
        } catch (FileNotFoundException e) {
            System.out.println("precise rethrow kept the real type: " + e.getClass().getSimpleName());
        }
    }

    static void mayFail(boolean fail) throws FileNotFoundException { // throws: a promise in the signature
        if (fail) {
            throw new FileNotFoundException("report.csv"); // throw: actually throws one object now
        }
    }

    /** Java 7 "precise rethrow": catch (Exception e) { throw e; } still only throws FileNotFoundException. */
    static void rethrow() throws FileNotFoundException {
        try {
            mayFail(true);
        } catch (Exception e) {
            System.out.println("logging " + e.getClass().getSimpleName() + ", then rethrowing");
            throw e; // compiles: the compiler knows only FileNotFoundException can get here
        }
    }
}
