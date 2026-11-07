package c19_same_code_new_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** The way we wrote it in Java 8. */
public final class OldStyle {

    private OldStyle() { }

    /** A data class: about 40 lines for two fields. */
    public static final class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }

        public int getAge() { return age; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person p = (Person) o;
            return age == p.age && Objects.equals(name, p.name);
        }

        @Override
        public int hashCode() { return Objects.hash(name, age); }

        @Override
        public String toString() { return "Person[name=" + name + ", age=" + age + "]"; }
    }

    public static String html(String name) {
        return "<div>\n"
                + "  <p>Hello, " + name + "</p>\n"
                + "</div>";
    }

    public static int daysIn(String month) {
        int days;
        switch (month) {
            case "FEBRUARY":
                days = 28;
                break;
            case "APRIL":
            case "JUNE":
            case "SEPTEMBER":
            case "NOVEMBER":
                days = 30;
                break;
            default:
                days = 31;
        }
        return days;
    }

    public static List<String> sortedCities() {
        List<String> cities = Collections.unmodifiableList(Arrays.asList("Pune", "Mumbai", "Goa"));
        List<String> copy = new ArrayList<String>(cities);
        Collections.sort(copy, new Comparator<String>() {
            @Override
            public int compare(String a, String b) { return a.length() - b.length(); }
        });
        return copy;
    }

    public static Map<Integer, List<String>> byLength(List<String> words) {
        Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
        for (String w : words) {
            if (!result.containsKey(w.length())) {
                result.put(w.length(), new ArrayList<String>());
            }
            result.get(w.length()).add(w);
        }
        return result;
    }
}
