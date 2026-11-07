package c20_java_22_to_25;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 17 versions of what Stream Gatherers (Java 24) give you built in:
 *   stream.gather(Gatherers.windowFixed(3)).toList()
 *   stream.gather(Gatherers.windowSliding(3)).toList()
 */
public final class Windows {

    private Windows() { }

    public static <T> List<List<T>> fixed(List<T> items, int size) {
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i += size) {
            result.add(List.copyOf(items.subList(i, Math.min(i + size, items.size()))));
        }
        return result;
    }

    public static <T> List<List<T>> sliding(List<T> items, int size) {
        List<List<T>> result = new ArrayList<>();
        if (!items.isEmpty() && items.size() < size) {
            result.add(List.copyOf(items)); // like Gatherers: a short stream gives ONE window of everything
            return result;
        }
        for (int i = 0; i + size <= items.size(); i++) {
            result.add(List.copyOf(items.subList(i, i + size)));
        }
        return result;
    }
}
