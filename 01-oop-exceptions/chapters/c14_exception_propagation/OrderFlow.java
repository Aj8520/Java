package c14_exception_propagation;

/**
 * The handbook's call stack: main -> OrderController.place -> OrderService.checkout -> PaymentGateway.charge.
 * charge() fails. "catchAt" chooses which frame has the catch block.
 * Every frame prints when it is left, so you can watch the frames pop.
 */
public class OrderFlow {

    private final String catchAt;

    public OrderFlow(String catchAt) {
        this.catchAt = catchAt;
    }

    public void place() {
        try {
            if (catchAt.equals("place")) {
                try {
                    checkout();
                } catch (IllegalStateException e) {
                    System.out.println("  caught in OrderController.place: " + e.getMessage());
                }
            } else {
                checkout();
            }
        } finally {
            System.out.println("  leaving OrderController.place");
        }
    }

    void checkout() {
        try {
            if (catchAt.equals("checkout")) {
                try {
                    charge();
                } catch (IllegalStateException e) {
                    System.out.println("  caught in OrderService.checkout: " + e.getMessage());
                }
            } else {
                charge();
            }
            System.out.println("  checkout continues after charge()");
        } finally {
            System.out.println("  leaving OrderService.checkout");
        }
    }

    void charge() {
        try {
            System.out.println("  PaymentGateway.charge: calling the bank...");
            throw new IllegalStateException("card declined");
        } finally {
            System.out.println("  leaving PaymentGateway.charge");
        }
    }
}
