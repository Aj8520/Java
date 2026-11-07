package c12_checked_vs_unchecked;

/** Multi-catch (Java 7): one handler for several unrelated checked exceptions. */
public class PluginLoader {

    public static class PluginLoadException extends RuntimeException {
        PluginLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public Object load(String className) {
        try {
            Class<?> c = Class.forName(className);
            return c.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException e) {
            // e = null; // compile error: a multi-catch parameter is implicitly final
            throw new PluginLoadException("Bad plugin " + className, e);
        } catch (ReflectiveOperationException e) { // the parent type, so it must come AFTER
            throw new PluginLoadException("Reflection failed for " + className, e);
        }
    }
}
