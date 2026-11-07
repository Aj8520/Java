package c15_try_with_resources;

/** A resource that reports when it opens and closes, and can be told to fail. */
public class FakeResource implements AutoCloseable {

    private final String name;
    private final boolean failOnClose;

    public FakeResource(String name, boolean failOnOpen, boolean failOnClose) {
        if (failOnOpen) {
            throw new IllegalStateException(name + " could not open");
        }
        this.name = name;
        this.failOnClose = failOnClose;
        System.out.println("  open  " + name);
    }

    public FakeResource(String name) {
        this(name, false, false);
    }

    public void use() {
        System.out.println("  use   " + name);
    }

    @Override
    public void close() {
        System.out.println("  close " + name);
        if (failOnClose) {
            throw new IllegalStateException(name + ".close() failed");
        }
    }
}
