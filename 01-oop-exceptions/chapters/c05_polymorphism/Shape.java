package c05_polymorphism;

/** One interface, many behaviours: each shape decides how to work out its own area. */
public abstract class Shape {
    public abstract double area();
}

class Circle extends Shape {
    private final double r;

    Circle(double r) {
        this.r = r;
    }

    @Override
    public double area() {
        return Math.PI * r * r;
    }
}

class Rectangle extends Shape {
    private final double w;
    private final double h;

    Rectangle(double w, double h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public double area() {
        return w * h;
    }
}
