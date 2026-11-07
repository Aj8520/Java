package c06_abstraction;

/** Real-life example: the driver only knows start() and accelerate(), not how the engine works. */
public interface Engine {
    String start();

    String accelerate(int kmh);
}

class PetrolEngine implements Engine {
    @Override
    public String start() {
        return "spark plugs fire, engine roars";
    }

    @Override
    public String accelerate(int kmh) {
        return "burns more fuel to reach " + kmh + " km/h";
    }
}

class ElectricEngine implements Engine {
    @Override
    public String start() {
        return "battery connects, silent";
    }

    @Override
    public String accelerate(int kmh) {
        return "motor draws more current to reach " + kmh + " km/h";
    }
}

/** The car depends on the Engine abstraction, never on a concrete engine. */
class Car {
    private final Engine engine;

    Car(Engine engine) {
        this.engine = engine;
    }

    void drive() {
        System.out.println("  " + engine.start());
        System.out.println("  " + engine.accelerate(60));
    }
}
