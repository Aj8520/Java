package c18_java_version_explorer;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 01.18 · What's new from Java 8 to 21
 * Handbook: OOP, Exceptions & Modern Java (oop.html#versions)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c18_java_version_explorer.Practice
 *
 * This module compiles with Java 17, so every feature up to 17 really runs here.
 * Java 18–21 features are shown in comments (they need JDK 21+).
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Java 8 -> 21 ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // Used by the Java 16/17 examples.
    record Point(int x, int y) { }

    sealed interface Vehicle permits Car, Truck { }
    record Car(String plate) implements Vehicle { }
    record Truck(String plate, int axles) implements Vehicle { }

    // TODO 1 (done): one line of real code per version, oldest first.
    static void coreExample() {
        System.out.println("\n-- 1. One feature per version --");
        List<String> names = List.of("Asha", "Ravi", "Anil", "Sam");

        // 8: lambdas, method references, streams, Optional
        System.out.println("8:  " + names.stream().filter(n -> n.startsWith("A")).map(String::toUpperCase)
                .collect(Collectors.toList()));

        // 9: List.of (above), Stream.iterate with a stop condition, takeWhile
        System.out.println("9:  " + Stream.iterate(1, n -> n < 100, n -> n * 2).collect(Collectors.toList()));

        // 10: var, List.copyOf, Optional.orElseThrow()
        var copy = List.copyOf(names);
        System.out.println("10: var copy = " + copy + ", first = " + Optional.of(copy.get(0)).orElseThrow());

        // 11: String isBlank, lines, strip, repeat; Predicate.not
        String text = "  milk \n\n bread  \n";
        System.out.println("11: " + text.lines().filter(Predicate.not(String::isBlank)).map(String::strip)
                .collect(Collectors.toList()) + " " + "-".repeat(5));

        // 12: Collectors.teeing, String.transform
        double avg = Stream.of(2.0, 4.0, 9.0).collect(Collectors.teeing(
                Collectors.summingDouble(x -> x), Collectors.counting(), (sum, n) -> sum / n));
        System.out.println("12: average via teeing = " + avg + ", " + "java".transform(String::toUpperCase));

        // 14: switch expressions
        String month = "APRIL";
        int days = switch (month) {
            case "FEBRUARY" -> 28;
            case "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER" -> 30;
            default -> 31;
        };
        System.out.println("14: " + month + " has " + days + " days");

        // 15: text blocks, String.formatted
        String json = """
                { "name": "%s" }""".formatted("Asha");
        System.out.println("15: " + json);

        // 16: records, pattern matching for instanceof, Stream.toList()
        Object obj = new Point(3, 4);
        if (obj instanceof Point p && p.x() > 0) {
            System.out.println("16: " + p + ", y = " + p.y() + ", toList = " + Stream.of(1, 2).toList());
        }

        // 17: sealed classes, RandomGenerator
        Vehicle v = new Truck("MH14", 3);
        System.out.println("17: sealed Vehicle -> " + v + ", random in [1,6]: "
                + (RandomGenerator.getDefault().nextInt(1, 7) > 0));

        // 18: UTF-8 is the default charset (Files.readString etc. no longer depend on the OS)
        // 21: (needs JDK 21)
        //   switch (o) { case Integer i when i > 100 -> "big"; case String s -> "text"; default -> "?"; }
        //   List.reversed(), getFirst(), getLast()      // sequenced collections
        //   Thread.startVirtualThread(() -> ...)          // virtual threads
    }

    // TODO 2 (done): predict each line BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        List<Integer> nums = List.of(1, 2, 3, 10, 4, 5);
        System.out.println("takeWhile(n < 5): " + nums.stream().takeWhile(n -> n < 5).toList());
        System.out.println("filter(n < 5):    " + nums.stream().filter(n -> n < 5).toList());
        System.out.println("dropWhile(n < 5): " + nums.stream().dropWhile(n -> n < 5).toList());

        try {
            Stream.of(1, 2).toList().add(3); // is Stream.toList() modifiable?
        } catch (UnsupportedOperationException e) {
            System.out.println("Stream.toList() is unmodifiable");
        }
        List<Integer> old = Stream.of(1, 2).collect(Collectors.toList());
        old.add(3);
        System.out.println("collect(toList()) allowed add: " + old);

        NumberFormat compact = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        System.out.println("compact 1500 = " + compact.format(1500) + ", 2_500_000 = " + compact.format(2_500_000));

        var list = new ArrayList<String>(); // what type does var infer here?
        list.add("x");
        System.out.println("var inferred: " + list.getClass().getSimpleName());
    }

    // TODO 3 (done): my own real-life example: which Java am I running?
    static void realLifeExample() {
        System.out.println("\n-- 3. Which Java is this? --");
        Runtime.Version version = Runtime.version(); // Java 9+
        int feature = version.feature();
        System.out.println("running Java " + version + " (feature release " + feature + ")");
        System.out.println("records available?           " + (feature >= 16));
        System.out.println("virtual threads available?   " + (feature >= 21));
        System.out.println("java.vendor = " + System.getProperty("java.vendor"));
    }

    // TODO 4 (done): interview question as code.
    // Q: "What is an LTS release and which ones are LTS?" Since 9: a release every March and September.
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        StringBuilder sb = new StringBuilder();
        for (int v = 9; v <= 25; v++) {
            int monthsAfterJava10 = (v - 10) * 6;          // Java 10 = March 2018
            int year = 2018 + Math.floorDiv(monthsAfterJava10 + 2, 12);
            String month = Math.floorMod(v, 2) == 0 ? "Mar" : "Sep";
            boolean lts = v == 11 || (v >= 17 && (v - 17) % 4 == 0); // every 2 years since 17
            sb.append(String.format("  %2d %s %d%s%n", v, month, year, lts ? "  LTS" : ""));
        }
        System.out.print("Java 8 Mar 2014 LTS, then:\n" + sb);
    }
}
