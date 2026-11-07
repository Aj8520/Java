package c21_upgrading_from_8;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.script.ScriptEngineManager;

/**
 * 01.21 · Upgrading from Java 8
 * Handbook: OOP, Exceptions & Modern Java (oop.html#migration)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c21_upgrading_from_8.Practice
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Upgrading from Java 8 ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): check the handbook's "common surprises" on THIS JVM.
    static void coreExample() {
        System.out.println("\n-- 1. Surprises, checked live --");
        System.out.println("running Java " + Runtime.version().feature());

        // 1. Java EE modules removed in 11
        System.out.println("1. JAXB (javax.xml.bind) present? " + MigrationCheck.exists("javax.xml.bind.JAXBContext"));

        // 5. Removed: Nashorn JavaScript engine (15)
        Object nashorn = new ScriptEngineManager().getEngineByName("nashorn");
        System.out.println("5. Nashorn engine: " + nashorn);

        // 4. Default charset: the OS default up to 17, always UTF-8 from 18
        System.out.println("4. default charset: " + Charset.defaultCharset());

        // 6. Build tools: what this project uses
        System.out.println("6. this project builds with <release>17</release> (see the parent pom.xml)");
    }

    // TODO 2 (done): strong encapsulation (16/17). Predict BEFORE running.
    static class Wallet {
        private int coins = 5;
    }

    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict: setAccessible --");
        try {
            Field own = Wallet.class.getDeclaredField("coins");
            own.setAccessible(true); // our own class
            System.out.println("own private field: " + own.get(new Wallet()));
        } catch (ReflectiveOperationException e) {
            System.out.println("own private field failed: " + e);
        }

        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true); // a JDK internal: worked (with a warning) on Java 8-15
            System.out.println("String.value opened");
        } catch (RuntimeException | ReflectiveOperationException e) {
            System.out.println("JDK internal: " + e.getClass().getSimpleName());
            System.out.println("  fix: upgrade the library; last resort --add-opens java.base/java.lang=ALL-UNNAMED");
        }
    }

    // TODO 3 (done): my own real-life example: a migration pre-flight report.
    static void realLifeExample() {
        System.out.println("\n-- 3. Pre-flight report --");
        MigrationCheck.print();
        System.out.println("  next: jdeps --jdk-internals app.jar  (lists uses of internal APIs)");
    }

    // TODO 4 (done): interview question as code.
    // Q: "Why can text break after an upgrade?" Bytes decoded with the wrong charset.
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        String original = "café ₹500";
        byte[] utf8 = original.getBytes(StandardCharsets.UTF_8);

        String right = new String(utf8, StandardCharsets.UTF_8);
        String wrong = new String(utf8, StandardCharsets.ISO_8859_1); // e.g. an old Windows default
        System.out.println("decoded as UTF-8:      " + right);
        System.out.println("decoded as ISO-8859-1: " + wrong);
        System.out.println("bytes: " + utf8.length + " for " + original.length() + " characters");
        System.out.println("rule: always pass the charset, e.g. Files.readString(path, UTF_8)");
    }
}
