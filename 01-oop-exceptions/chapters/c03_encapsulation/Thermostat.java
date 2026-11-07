package c03_encapsulation;

/**
 * Real-life example: a heater only accepts temperatures from 10 to 30.
 * This one HAS a setter, because setting a value directly is genuinely
 * allowed here. The setter still protects the rule.
 */
public class Thermostat {

    private static final int MIN = 10;
    private static final int MAX = 30;

    private int temperature = 20;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        if (temperature < MIN || temperature > MAX) {
            throw new IllegalArgumentException(
                    "Temperature must be " + MIN + " to " + MAX + ": " + temperature);
        }
        this.temperature = temperature;
    }

    public void warmer() {
        setTemperature(temperature + 1); // reuses the same rule, so it lives in one place
    }
}
