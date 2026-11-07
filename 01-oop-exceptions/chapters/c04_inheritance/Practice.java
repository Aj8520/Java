package c04_inheritance;

import java.util.List;

/**
 * 01.04 · Inheritance
 * Handbook: OOP, Exceptions & Modern Java (oop.html#inheritance)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c04_inheritance.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Inheritance ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's Employee and Manager.
    static void coreExample() {
        System.out.println("\n-- 1. Employee and Manager --");
        Employee asha = new Manager("Asha", 2_400_000, 300_000);
        Employee ravi = new Employee("Ravi", 1_200_000);

        // describe() lives only in Employee, but it calls the Manager's monthlyPay().
        System.out.println(asha.describe());
        System.out.println(ravi.describe());
        System.out.println("Asha is an Employee? " + (asha instanceof Employee));
        System.out.println("Ravi is a Manager?   " + (ravi instanceof Manager));
    }

    // TODO 2 (done): what runs first? Predict the order of every line BEFORE running.
    static class Animal {
        static { System.out.println("Animal static block"); }
        { System.out.println("Animal instance block"); }
        Animal() { System.out.println("Animal constructor"); }
    }

    static class Dog extends Animal {
        static { System.out.println("Dog static block"); }
        { System.out.println("Dog instance block"); }
        Dog() { System.out.println("Dog constructor"); } // super() is added for you
    }

    static class Puppy extends Dog { // multilevel: Puppy -> Dog -> Animal
        Puppy() { System.out.println("Puppy constructor"); }
    }

    // The constructor trap: the parent's constructor calls a method the child overrides.
    static class Base {
        Base() { System.out.println("Base constructor sees: " + describe()); }
        String describe() { return "base"; }
    }

    static class Derived extends Base {
        // Not a constant (computed at runtime). A plain "derived" literal would be inlined
        // by the compiler and hide the bug.
        private final String name = "derived".toUpperCase();
        @Override String describe() { return "name=" + name.length(); }
    }

    static class SafeDerived extends Base {
        private String label = "set";
        @Override String describe() { return "label=" + label; } // runs before label is assigned
    }

    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        System.out.println("first new Puppy():");
        new Puppy();
        System.out.println("second new Puppy():");
        new Puppy(); // static blocks do not run again

        System.out.println("constructor trap:");
        new SafeDerived(); // label is still null here
        try {
            new Derived();
        } catch (NullPointerException e) {
            System.out.println("Derived crashed: NullPointerException");
        }
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Toll booth --");
        List<Vehicle> queue = List.of(new Car("MH12-AB-1234"), new Truck("MH14-XY-9876", 3));
        for (Vehicle v : queue) {
            System.out.println(v.receipt());
        }
    }

    // TODO 4 (done): interview question as code.
    // Q: "Why no multiple inheritance of classes? What if two interfaces have the same default method?"
    // class Amphibian extends Car, Truck { } // compile error: a class can extend only ONE class

    interface Flyer {
        default String move() { return "flying"; }
    }

    interface Swimmer {
        default String move() { return "swimming"; }
    }

    // Without the move() below, Duck does not compile: the compiler can't pick one.
    static class Duck implements Flyer, Swimmer {
        @Override
        public String move() {
            return Flyer.super.move() + " and " + Swimmer.super.move(); // choose explicitly
        }
    }

    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        System.out.println("duck is " + new Duck().move());
        System.out.println("every class extends Object: " + new Duck().getClass().getSuperclass().getSimpleName());
    }
}
