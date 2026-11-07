package c13_try_catch_finally;

/** Run in its OWN JVM by Practice: System.exit() would end the practice program too. */
public class ExitDemo {
    public static void main(String[] args) {
        try {
            System.out.println("try: calling System.exit(0)");
            System.exit(0);
        } finally {
            System.out.println("finally: you will not see this");
        }
    }
}
