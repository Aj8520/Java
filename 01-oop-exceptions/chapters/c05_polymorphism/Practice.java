package c05_polymorphism;

import java.io.IOException;
import java.util.List;

/**
 * 01.05 · Polymorphism
 * Handbook: OOP, Exceptions & Modern Java (oop.html#polymorphism)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c05_polymorphism.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Polymorphism ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's two kinds of polymorphism.
    static void coreExample() {
        System.out.println("\n-- 1. Runtime: overriding --");
        List<Shape> shapes = List.of(new Circle(1), new Rectangle(2, 3));
        for (Shape s : shapes) {
            // The loop never checks types. A new Triangle would need no change here.
            System.out.printf("%s area = %.2f%n", s.getClass().getSimpleName(), s.area());
        }

        System.out.println("\n-- 1. Compile time: overloading --");
        Printer printer = new Printer();
        printer.print(5);
        printer.print("hi", 3);
    }

    // TODO 2 (done): which overload does the compiler pick? Predict each line BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        Printer printer = new Printer();
        printer.print(5L);     // there is no print(long)
        printer.print('A');    // there is no print(char)
        printer.print(null);   // null can't be an int or a double
        printer.print(5.0f);   // there is no print(float)

        Object text = "hello";
        describe(text);        // overloads are chosen from the VARIABLE type, not the object
        describe("hello");
    }

    static void describe(Object o) { System.out.println("describe(Object)"); }
    static void describe(String s) { System.out.println("describe(String)"); }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Notifications --");
        List<Notifier> channels = List.of(new EmailNotifier(), new SmsNotifier(), new PushNotifier());
        for (Notifier channel : channels) {
            System.out.println(channel.send("Your order #42 has shipped"));
        }
    }

    // TODO 4 (done): interview question as code.
    // Q: "Parent p = new Child(); which version runs for a method, a static method and a field?"
    static class Parent {
        String name = "parent field";

        protected Number value() throws IOException { return 1; }
        static String id() { return "Parent.id()"; }
        public final void locked() { }
    }

    static class Child extends Parent {
        String name = "child field"; // hides the parent's field, does not override it

        @Override
        public Integer value() { return 2; } // covariant return, wider access, no exception

        static String id() { return "Child.id()"; } // hides, does not override

        // public void locked() { } // compile error: final methods can't be overridden
    }

    static class BrokenChild extends Parent {
        // private Number value() { return 3; } // compile error: can't reduce visibility
        // public Number value() throws Exception { return 3; } // compile error: broader checked exception
    }

    @SuppressWarnings("static-access")
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        Parent p = new Child();
        try {
            // The Parent version declares IOException, so callers using Parent must handle it.
            System.out.println("method: " + p.value());
        } catch (IOException e) {
            System.out.println("never happens here");
        }
        System.out.println("static: " + p.id());
        System.out.println("field:  " + p.name);
        System.out.println("field through a Child variable: " + ((Child) p).name);
    }
}
