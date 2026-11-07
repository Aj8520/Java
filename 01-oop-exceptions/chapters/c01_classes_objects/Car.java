package c01_classes_objects;

// A class is a blueprint; objects are the houses built from it.
public class Car {
    // fields (state)
    private final String model;
    private String colour;
    private int speed;
    private static int carsBuilt = 0;          // static: ONE copy shared by all cars

    // constructors (how to build one)
    public Car(String model, String colour) {
        this.model = model;                     // "this" = the object being built
        this.colour = colour;
        carsBuilt++;
    }
    public Car(String model) {
        this(model, "white");                   // constructor chaining: must be the first line
    }

    // methods (behaviour)
    public void accelerate(int by) { speed = Math.min(speed + by, 200); }
    public int getSpeed() { return speed; }
    public static int getCarsBuilt() { return carsBuilt; }   // no "this" inside static methods

    @Override public String toString() { return colour + " " + model + " @ " + speed + " km/h"; }
}