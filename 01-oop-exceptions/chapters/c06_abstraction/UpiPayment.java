package c06_abstraction;

/** Extends ONE abstract class and implements an interface. */
public class UpiPayment extends PaymentMethod implements Refundable {

    public UpiPayment(String customerId) {
        super(customerId);
    }

    @Override
    protected Receipt charge(double amount) {
        return new Receipt("UPI", amount, "paid");
    }

    @Override
    public Receipt refund(String txnId) {
        return new Receipt("UPI", 0, "refunded " + txnId);
    }
}

/** Cash is a payment method, but it is not Refundable through the app. */
class CashPayment extends PaymentMethod {

    CashPayment(String customerId) {
        super(customerId);
    }

    @Override
    protected Receipt charge(double amount) {
        return new Receipt("Cash", amount, "collected on delivery");
    }
}
