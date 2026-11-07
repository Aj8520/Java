package c07_final_static_object;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 01.07 · final, static, and the Object class
 * Handbook: OOP, Exceptions & Modern Java (oop.html#keywords)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c07_final_static_object.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== final, static, Object ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's final and static examples, and Point's Object methods.
    static void coreExample() {
        System.out.println("\n-- 1. final, static, Object --");
        final int max = 10;
        // max = 11; // compile error: a final variable is assigned once

        final List<String> names = new ArrayList<>();
        names.add("ok"); // the REFERENCE is final, the list itself can still change
        // names = new ArrayList<>(); // compile error: can't point it at a new list
        System.out.println("final list after add: " + names + ", max=" + max);

        // new MathUtil(); // compile error: the constructor is private
        MathUtil.square(3);
        System.out.println("square(4) = " + MathUtil.square(4) + ", calls = " + MathUtil.calls());
        System.out.printf("TAU = %.4f%n", MathUtil.TAU);

        Point p = new Point(1, 2);
        System.out.println("toString: " + p);
        System.out.println("record toString: " + new Point2(1, 2));
    }

    // TODO 2 (done): == vs equals. Predict each line BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        Point a = new Point(1, 2);
        Point b = new Point(1, 2);
        System.out.println("a == b:      " + (a == b));      // same object?
        System.out.println("a.equals(b): " + a.equals(b));   // same value?
        System.out.println("same hash:   " + (a.hashCode() == b.hashCode()));
        System.out.println("records equal: " + new Point2(1, 2).equals(new Point2(1, 2)));
        System.out.println("a.equals(null): " + a.equals(null));
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Token machine --");
        Ticket t1 = new Ticket("Counter A");
        Ticket t2 = new Ticket("Counter B");
        Ticket t3 = new Ticket("Counter A");
        System.out.println(t1 + " | " + t2 + " | " + t3);
        System.out.println("issued so far: " + Ticket.issuedSoFar());
    }

    // TODO 4 (done): interview question as code.
    // Q: "Why must you override hashCode() when you override equals()?"
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        Set<Point> good = new HashSet<>();
        good.add(new Point(1, 2));
        good.add(new Point(1, 2));
        System.out.println("HashSet<Point> size:    " + good.size()
                + ", contains(1,2)? " + good.contains(new Point(1, 2)));

        Set<BadPoint> bad = new HashSet<>();
        bad.add(new BadPoint(1, 2));
        bad.add(new BadPoint(1, 2));
        System.out.println("HashSet<BadPoint> size: " + bad.size()
                + ", contains(1,2)? " + bad.contains(new BadPoint(1, 2)));
        System.out.println("but the two BadPoints are equal: " + new BadPoint(1, 2).equals(new BadPoint(1, 2)));
    }

    // static class MyString extends String { } // compile error: String is final
}
