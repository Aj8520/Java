package c04_inheritance;

/** The handbook's parent class: shared state and behaviour for every employee. */
public class Employee {

    protected final String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double monthlyPay() {
        return baseSalary / 12;
    }

    public String describe() {
        return name + " earns " + monthlyPay();
    }
}
