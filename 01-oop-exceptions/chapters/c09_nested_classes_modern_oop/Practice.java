package c09_nested_classes_modern_oop;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 01.09 · Nested classes and modern OOP
 * Handbook: OOP, Exceptions & Modern Java (oop.html#nested)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c09_nested_classes_modern_oop.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Nested classes & modern OOP ==");

        coreExample();
        new Practice().predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the four nested kinds, then records + sealed types.
    static void coreExample() {
        System.out.println("\n-- 1a. Four kinds of nested class --");
        System.out.println(new Outer.Helper().hello());

        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner(); // needs an Outer object
        System.out.println("inner reads secret: " + inner.peek());
        System.out.println("local + anonymous: " + outer.sortByLength(List.of("banana", "fig", "apple")));

        System.out.println("\n-- 1b. Records + sealed --");
        List<Shape> shapes = List.of(new Circle(1), new Square(2), new Rect(2, 3));
        for (Shape s : shapes) {
            System.out.printf("%s -> area %.2f%n", s, area(s));
        }
        try {
            new Rect(-1, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }
    }

    /**
     * Java 17 version. In Java 21 this becomes an exhaustive switch with no default:
     *   return switch (s) {
     *       case Circle c          -> Math.PI * c.r() * c.r();
     *       case Square sq         -> sq.side() * sq.side();
     *       case Rect(var w, var h) -> w * h;   // record pattern
     *   };
     */
    static double area(Shape s) {
        if (s instanceof Circle c) return Math.PI * c.r() * c.r();
        if (s instanceof Square sq) return sq.side() * sq.side();
        if (s instanceof Rect r) return r.w() * r.h();
        throw new IllegalStateException("unknown shape " + s);
    }

    // record Triangle(double b, double h) implements Shape { } // compile error: not in "permits"

    // TODO 2 (done): predict each line BEFORE running.
    private final String name = "Practice object";

    void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        // Prediction 1: the inner object was created BEFORE the change. What does it see?
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        outer.setSecret(7);
        System.out.println("inner sees: " + inner.peek());

        // Prediction 2: what does "this" mean inside an anonymous class vs a lambda?
        Runnable anonymous = new Runnable() {
            @Override
            public void run() { System.out.println("anonymous this: " + this.getClass().getName()); }
        };
        Runnable lambda = () -> System.out.println("lambda this:    " + this.name);
        anonymous.run();
        lambda.run();

        // Prediction 3: can a lambda change a local variable?
        int count = 0;
        Runnable reader = () -> System.out.println("lambda reads count: " + count);
        // Uncomment the next line: count stops being effectively final, so javac reports
        // the error on the reader lambda ABOVE, not on this line.
        // Runnable writer = () -> count++; // compile error (reported on the reader line)
        reader.run();

        // Prediction 4: are two records with the same values equal?
        System.out.println("records equal: " + new Circle(1).equals(new Circle(1)));
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Pizza builder --");
        Pizza pizza = Pizza.builder("Large").topping("onion").topping("paneer").extraCheese().build();
        System.out.println(pizza);
        try {
            Pizza.builder("Small").topping("a").topping("b").topping("c").topping("d").build();
        } catch (IllegalStateException e) {
            System.out.println("rejected: " + e.getMessage());
        }
        // new Pizza(null); // compile error: the constructor is private
    }

    // TODO 4 (done): interview question as code.
    // Q: "Static nested vs inner class? Why can inner classes leak memory?"
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        System.out.println("Inner fields:  " + fieldNames(Outer.Inner.class));
        System.out.println("Helper fields: " + fieldNames(Outer.Helper.class));
        System.out.println("Every Inner keeps its Outer alive through that hidden field.");
    }

    static String fieldNames(Class<?> type) {
        Field[] fields = type.getDeclaredFields();
        return Arrays.toString(Arrays.stream(fields).map(Field::getName).toArray());
    }
}
