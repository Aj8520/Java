package c19_same_code_new_java;

import java.util.List;

/**
 * 01.19 · Same code, newer Java
 * Handbook: OOP, Exceptions & Modern Java (oop.html#evolution)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c19_same_code_new_java.Practice
 *
 * OldStyle.java = Java 8, NewStyle.java = Java 17. Each task checks both give the same answer.
 * Java 21 versions are shown in comments (this module compiles with 17).
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Same code, newer Java ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    static void same(String task, Object oldResult, Object newResult) {
        System.out.printf("%-12s old=%s%n%-12s new=%s  same? %s%n", task, oldResult, "", newResult,
                String.valueOf(oldResult).equals(String.valueOf(newResult)));
    }

    // TODO 1 (done): run every task the old way and the new way.
    static void coreExample() {
        System.out.println("\n-- 1. Old vs new --");
        same("data class", new OldStyle.Person("Asha", 30), new NewStyle.Person("Asha", 30));
        same("switch", OldStyle.daysIn("JUNE"), NewStyle.daysIn("JUNE"));
        same("collections", OldStyle.sortedCities(), NewStyle.sortedCities());
        List<String> words = List.of("tea", "milk", "egg", "bread");
        same("grouping", OldStyle.byLength(words), NewStyle.byLength(words));
        System.out.println("text blocks give the same string? " + OldStyle.html("Ravi").equals(NewStyle.html("Ravi")));
        System.out.println(NewStyle.html("Ravi"));
    }

    // TODO 2 (done): the bug that switch expressions remove. Predict BEFORE running.
    @SuppressWarnings("fallthrough")
    static String oldSwitchForgotBreak(int level) {
        String access = "";
        switch (level) {
            case 1:
                access += "read ";
            case 2:              // forgot "break" above: level 1 falls into level 2
                access += "write ";
                break;
            default:
                access += "none";
        }
        return access.trim();
    }

    static String newSwitch(int level) {
        return switch (level) {
            case 1 -> "read";
            case 2 -> "write";
            default -> "none"; // no fall-through possible
        };
    }

    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        System.out.println("old switch, level 1: " + oldSwitchForgotBreak(1));
        System.out.println("new switch, level 1: " + newSwitch(1));

        // Does a record compare by value like the hand-written equals?
        System.out.println("old equals: " + new OldStyle.Person("A", 1).equals(new OldStyle.Person("A", 1)));
        System.out.println("record equals: " + new NewStyle.Person("A", 1).equals(new NewStyle.Person("A", 1)));
        System.out.println("old getter vs record accessor: " + new OldStyle.Person("A", 1).getName()
                + " / " + new NewStyle.Person("A", 1).name());
    }

    // TODO 3 (done): my own real-life example: modernise a type check.
    sealed interface Payment permits Upi, Card, Cash { }
    record Upi(String vpa) implements Payment { }
    record Card(String last4) implements Payment { }
    record Cash() implements Payment { }

    /** Java 8 style: instanceof + cast on every line. */
    static String describeOld(Object p) {
        if (p instanceof Upi) {
            Upi u = (Upi) p;
            return "UPI to " + u.vpa();
        } else if (p instanceof Card) {
            Card c = (Card) p;
            return "Card ending " + c.last4();
        }
        return "Cash";
    }

    /**
     * Java 16/17 style: pattern matching for instanceof. In Java 21 it becomes:
     *   return switch (p) {
     *       case Upi(var vpa)    -> "UPI to " + vpa;
     *       case Card(var last4) -> "Card ending " + last4;
     *       case Cash c          -> "Cash";
     *   };   // no default: the sealed interface makes it exhaustive
     */
    static String describeNew(Payment p) {
        if (p instanceof Upi u) return "UPI to " + u.vpa();
        if (p instanceof Card c) return "Card ending " + c.last4();
        return "Cash";
    }

    static void realLifeExample() {
        System.out.println("\n-- 3. Type-based logic --");
        for (Payment p : List.of(new Upi("asha@okbank"), new Card("4242"), new Cash())) {
            same(p.getClass().getSimpleName(), describeOld(p), describeNew(p));
        }
    }

    // TODO 4 (done): interview question as code.
    // Q: "What is a record under the hood?" A final class that extends java.lang.Record.
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        System.out.println("OldStyle.Person is a record? " + OldStyle.Person.class.isRecord());
        System.out.println("NewStyle.Person is a record? " + NewStyle.Person.class.isRecord());
        System.out.println("record parent class: " + NewStyle.Person.class.getSuperclass().getName());
        System.out.println("record is final? " + java.lang.reflect.Modifier.isFinal(NewStyle.Person.class.getModifiers()));
    }
}
