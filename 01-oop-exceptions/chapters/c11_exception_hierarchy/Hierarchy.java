package c11_exception_hierarchy;

/** Helpers to explore the Throwable tree at runtime. */
public final class Hierarchy {

    private Hierarchy() { }

    /** "NumberFormatException -> IllegalArgumentException -> ... -> Throwable" */
    public static String chain(Class<? extends Throwable> type) {
        StringBuilder sb = new StringBuilder(type.getSimpleName());
        Class<?> c = type.getSuperclass();
        while (c != null && c != Object.class) {
            sb.append(" -> ").append(c.getSimpleName());
            c = c.getSuperclass();
        }
        return sb.toString();
    }

    /** Checked = a Throwable that is NOT a RuntimeException and NOT an Error. */
    public static String kind(Class<? extends Throwable> type) {
        if (RuntimeException.class.isAssignableFrom(type)) {
            return "unchecked";
        }
        if (Error.class.isAssignableFrom(type)) {
            return "Error (unchecked)";
        }
        return "CHECKED";
    }
}
