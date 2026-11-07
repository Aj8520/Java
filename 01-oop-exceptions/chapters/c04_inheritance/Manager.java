package c04_inheritance;

/** A Manager IS-A Employee: it reuses everything and changes only how pay is worked out. */
public class Manager extends Employee {

    private final double bonus;

    public Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary); // the parent constructor must run first
        this.bonus = bonus;
    }

    @Override
    public double monthlyPay() {
        return super.monthlyPay() + bonus / 12; // reuse the parent's version, then add to it
    }
}
