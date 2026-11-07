package c14_exception_propagation;

/** Real-life example data: a customer may have no address yet. */
public class Customer {

    private final String name;
    private final Address address;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() { return name; }

    public Address getAddress() { return address; }

    public static class Address {
        private final String city;

        public Address(String city) { this.city = city; }

        public String getCity() { return city; }
    }
}
