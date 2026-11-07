package c04_inheritance;

/** Real-life example: every vehicle pays a toll; some kinds pay differently. */
public class Vehicle {

    private final String plate;

    public Vehicle(String plate) {
        this.plate = plate;
    }

    public int toll() {
        return 100;
    }

    public String receipt() {
        // getClass().getSimpleName() gives the REAL class name, even when called from Vehicle
        return getClass().getSimpleName() + " " + plate + " pays " + toll();
    }
}

class Car extends Vehicle {
    Car(String plate) {
        super(plate);
    }
    // no toll() here: Car simply inherits Vehicle's version
}

class Truck extends Vehicle {
    private final int axles;

    Truck(String plate, int axles) {
        super(plate);
        this.axles = axles;
    }

    @Override
    public int toll() {
        return super.toll() * axles;
    }
}
