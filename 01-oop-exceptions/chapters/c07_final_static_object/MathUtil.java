package c07_final_static_object;

/** static: belongs to the CLASS, not to any one object. A utility class has no instances. */
public final class MathUtil {

    public static final double TAU = 2 * Math.PI; // constant
    private static int calls;                       // ONE counter shared by every caller

    private MathUtil() { }                           // no instances

    public static double square(double x) {
        calls++;
        return x * x;
    }

    public static int calls() {
        return calls;
    }
}
