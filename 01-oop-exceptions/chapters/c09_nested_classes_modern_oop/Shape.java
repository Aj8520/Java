package c09_nested_classes_modern_oop;

/** Modern OOP: a sealed interface lists EXACTLY which types may implement it. */
public sealed interface Shape permits Circle, Square, Rect { }

record Circle(double r) implements Shape { }

record Square(double side) implements Shape { }

record Rect(double w, double h) implements Shape {
    Rect { // compact constructor: validation, the fields are assigned for you afterwards
        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException("positive sizes only, got " + w + " x " + h);
        }
    }
}
