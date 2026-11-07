package c05_polymorphism;

/** Compile-time polymorphism: same name, different parameters. The compiler picks one. */
public class Printer {
    void print(int x) { System.out.println("int " + x); }
    void print(double x) { System.out.println("double " + x); }
    void print(String s) { System.out.println("String " + s); }
    void print(String s, int n) { System.out.println(s.repeat(n)); }
}
