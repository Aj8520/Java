package c01_classes_objects;

/**
 * 01.01 · Classes & objects
 * Handbook: OOP, Exceptions & Modern Java (oop.html#classes)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c01_classes_objects.Practice
 */
public class Practice {
    
    public  void checkStaticDiffrence() {
        
      System.out.println(HousePlan.getHousesBuilt());   // no "this" inside static methods

    }

    public static void main(String[] args) {
        System.out.println("== Classes & objects ==");

        // TODO 1: Re-type the core example from the handbook chapter and run it.
        Car a = new Car("Nexon", "red");    // new: allocate on the heap, run the constructor
        Car b = new Car("Creta");
        a.accelerate(60);
        System.out.println(a);               // red Nexon @ 60 km/h
        System.out.println(Car.getCarsBuilt());   // 2  (call static members on the class)

         // No constructor written? Java adds an empty no-arg "default constructor".
        // Write ANY constructor and that default disappears.
        
        // TODO 2: Change one thing (input, type, edge case) and predict the output before running.
        Car c = new Car("Thar");
        System.out.println(c);
        System.out.println(Car.getCarsBuilt());
        
        // TODO 3: Write a small real-life example of your own using this concept.
        
        HousePlan h1 = new HousePlan("Ajay Jadhav", "blue");
        HousePlan h2 = new HousePlan("Anil JadhavS");
        System.out.println(h1);
        System.out.println(h2);
        System.out.println(HousePlan.getHousesBuilt());
        
        // TODO 4: Recreate one interview question from the chapter as code.
       // checkStaticDiffrence(); - cannot make a static reference to the non-static method checkStaticDiffrence() from the type Practice
        Practice p = new Practice();
        p.checkStaticDiffrence();
    }
}
