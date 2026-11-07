package c08_composition_casting;

import java.util.List;

/**
 * 01.08 · Composition over inheritance, and casting
 * Handbook: OOP, Exceptions & Modern Java (oop.html#composition)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c08_composition_casting.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Composition & casting ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's stacks, and upcasting / downcasting.
    static void coreExample() {
        System.out.println("\n-- 1a. Inheritance vs composition --");
        InheritedStack<String> s1 = new InheritedStack<>();
        s1.push("a");
        s1.push("b");
        s1.add(0, "oops"); // allowed: every ArrayList method leaked in
        System.out.println("inherited stack: " + s1 + " -> pop gives " + s1.pop());

        ComposedStack<String> s2 = new ComposedStack<>();
        s2.push("a");
        s2.push("b");
        // s2.add(0, "oops"); // compile error: only push/pop/isEmpty exist
        System.out.println("composed stack pop gives " + s2.pop());

        System.out.println("\n-- 1b. Casting --");
        Animal a = new Dog("Rex", true); // UPCAST: automatic, always safe
        System.out.println("a.sound(): " + a.sound()); // Dog's version
        // a.fetch(); // compile error: the VARIABLE type Animal has no fetch()

        Dog d = (Dog) a; // DOWNCAST: explicit, checked at runtime
        System.out.println(d.fetch());

        Animal c = new Cat();
        if (c instanceof Dog) { // classic check first
            System.out.println("never printed");
        }
        if (a instanceof Dog dog && dog.isTrained()) { // Java 16: test + cast + variable
            System.out.println("pattern match: " + dog.fetch());
        }
        // Java 21 only (this project uses 17): switch (c) { case Dog dog -> ...; case Cat cat -> ...; }
        System.out.println("describe(c): " + describe(c));
    }

    /** Java 17 version of "switch on type": instanceof patterns in order. */
    static String describe(Animal animal) {
        if (animal instanceof Dog dog) return "Woof, I'm " + dog.name();
        if (animal instanceof Cat) return "Meow";
        return "...";
    }

    // TODO 2 (done): predict each line BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        Animal c = new Cat();
        try {
            Dog wrong = (Dog) c; // compiles: an Animal MIGHT be a Dog
            System.out.println(wrong.fetch());
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage().split(" \\(")[0]);
        }

        Animal nothing = null;
        System.out.println("null instanceof Dog: " + (nothing instanceof Dog));
        Dog stillNull = (Dog) nothing; // casting null never fails
        System.out.println("(Dog) null is " + stillNull);

        // Cat cat = (Cat) new Dog("Rex", true); // compile error: Dog can never be a Cat
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Composition vs aggregation --");
        House house = new House("kitchen", "bedroom");
        System.out.println(house + " has " + house.roomCount() + " rooms (rooms die with the house)");

        Player asha = new Player("Asha");
        Player ravi = new Player("Ravi");
        Team team = new Team(List.of(asha, ravi));
        team = null; // the team is disbanded
        System.out.println("team is " + team + ", players still exist: " + asha + ", " + ravi);
    }

    // TODO 4 (done): interview question as code.
    // Q: "Why prefer composition over inheritance?" The fragile base class problem.
    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        InheritedCountingSet<String> inherited = new InheritedCountingSet<>();
        inherited.addAll(List.of("a", "b", "c"));
        System.out.println("inherited set counted: " + inherited.addCount);

        ComposedCountingSet<String> composed = new ComposedCountingSet<>();
        composed.addAll(List.of("a", "b", "c"));
        System.out.println("composed set counted:  " + composed.addCount);
    }
}
