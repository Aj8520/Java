package c21_upgrading_from_8;

import java.util.LinkedHashMap;
import java.util.Map;

/** Real-life example: a tiny "pre-flight" report before moving a Java 8 app to 17/21. */
public final class MigrationCheck {

    private MigrationCheck() { }

    /** Class to look for -> what to do if it is missing. */
    static final Map<String, String> CHECKS = new LinkedHashMap<>();

    static {
        CHECKS.put("javax.xml.bind.JAXBContext", "removed in 11: add jakarta.xml.bind:jakarta.xml.bind-api");
        CHECKS.put("javax.annotation.PostConstruct", "removed in 11: add jakarta.annotation:jakarta.annotation-api");
        CHECKS.put("javax.xml.ws.Service", "JAX-WS removed in 11: add a JAX-WS implementation");
        CHECKS.put("org.omg.CORBA.ORB", "CORBA removed in 11: no replacement in the JDK");
        CHECKS.put("java.net.http.HttpClient", "added in 11: can replace HttpURLConnection or Apache client");
        CHECKS.put("sun.misc.Unsafe", "internal API (jdk.unsupported): libraries using it need upgrading");
    }

    public static void print() {
        for (Map.Entry<String, String> check : CHECKS.entrySet()) {
            String status = exists(check.getKey()) ? "present" : "MISSING";
            System.out.printf("  %-32s %-8s %s%n", check.getKey(), status, check.getValue());
        }
    }

    static boolean exists(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
