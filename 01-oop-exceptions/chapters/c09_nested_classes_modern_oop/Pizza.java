package c09_nested_classes_modern_oop;

import java.util.ArrayList;
import java.util.List;

/** Real-life example: the Builder pattern, the most common use of a static nested class. */
public class Pizza {

    private final String size;
    private final boolean extraCheese;
    private final List<String> toppings;

    private Pizza(Builder b) { // only the Builder can create a Pizza
        this.size = b.size;
        this.extraCheese = b.extraCheese;
        this.toppings = List.copyOf(b.toppings);
    }

    public static Builder builder(String size) {
        return new Builder(size);
    }

    @Override
    public String toString() {
        return size + " pizza, toppings=" + toppings + (extraCheese ? ", extra cheese" : "");
    }

    public static class Builder {
        private final String size;
        private boolean extraCheese;
        private final List<String> toppings = new ArrayList<>();

        private Builder(String size) { this.size = size; }

        public Builder topping(String t) {
            toppings.add(t);
            return this; // returning this allows chaining
        }

        public Builder extraCheese() {
            extraCheese = true;
            return this;
        }

        public Pizza build() {
            if (toppings.size() > 3) {
                throw new IllegalStateException("max 3 toppings, got " + toppings.size());
            }
            return new Pizza(this); // a nested class can call Pizza's private constructor
        }
    }
}
