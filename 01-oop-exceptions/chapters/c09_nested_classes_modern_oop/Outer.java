package c09_nested_classes_modern_oop;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/** The four kinds of nested class from the handbook. */
public class Outer {

    private int secret = 42;

    /** 1. Static nested class: no link to an Outer object. */
    public static class Helper {
        String hello() { return "static nested: no Outer needed"; }
    }

    /** 2. Inner (non-static) class: tied to one Outer object, can read its fields. */
    public class Inner {
        int peek() { return secret; }
    }

    public void setSecret(int secret) { this.secret = secret; }

    List<String> sortByLength(List<String> words) {
        // 3. Local class: declared inside a method, visible only here.
        class LengthLabel {
            String label(String w) { return w + "(" + w.length() + ")"; }
        }

        // 4. Anonymous class: a one-off implementation (mostly replaced by lambdas).
        Comparator<String> byLength = new Comparator<>() {
            @Override
            public int compare(String a, String b) { return a.length() - b.length(); }
        };

        List<String> sorted = new ArrayList<>(words);
        sorted.sort(byLength);
        LengthLabel labeller = new LengthLabel();
        List<String> result = new ArrayList<>();
        for (String w : sorted) {
            result.add(labeller.label(w));
        }
        return result;
    }
}
