package c19_same_code_new_java;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** The same behaviour with Java 10-17 features. */
public final class NewStyle {

    private NewStyle() { }

    /** Java 16: one line gives constructor, accessors, equals, hashCode, toString. */
    public record Person(String name, int age) { }

    /** Java 15: text block. */
    public static String html(String name) {
        return """
                <div>
                  <p>Hello, %s</p>
                </div>""".formatted(name);
    }

    /** Java 14: switch expression, no break, no fall-through. */
    public static int daysIn(String month) {
        return switch (month) {
            case "FEBRUARY" -> 28;
            case "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER" -> 30;
            default -> 31;
        };
    }

    /** Java 9/10/16: List.of, var, Comparator factories, Stream.toList(). */
    public static List<String> sortedCities() {
        var cities = List.of("Pune", "Mumbai", "Goa");
        return cities.stream().sorted(Comparator.comparingInt(String::length)).toList();
    }

    /** Java 8 streams: groupingBy replaces the containsKey / put / get loop. */
    public static Map<Integer, List<String>> byLength(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(String::length));
    }
}
