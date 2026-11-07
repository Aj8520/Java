package c07_final_static_object;

import java.util.Objects;

/** Overrides the three Object methods you meet most: equals, hashCode, toString. */
public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point p)) { // pattern matching for instanceof (Java 16)
            return false;
        }
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y); // must use the same fields as equals
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}

/** A record writes the constructor, accessors, equals, hashCode and toString for you. */
record Point2(int x, int y) { }

/** The classic bug: equals overridden, hashCode forgotten. */
class BadPoint {
    private final int x;
    private final int y;

    BadPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BadPoint p && x == p.x && y == p.y;
    }
    // no hashCode(): each object keeps Object's identity-based hash
}
