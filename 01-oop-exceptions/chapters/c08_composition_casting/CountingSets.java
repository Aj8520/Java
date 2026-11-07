package c08_composition_casting;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Interview classic (Effective Java): count how many elements were ever added.
 * The inherited version double counts, because HashSet.addAll() calls add() internally.
 */
class InheritedCountingSet<E> extends HashSet<E> {
    int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c); // secretly calls add() for each element
    }
}

/** Composition: wrap a Set and forward calls. The parent's internals can't surprise us. */
class ComposedCountingSet<E> {
    private final Set<E> inner = new HashSet<>();
    int addCount = 0;

    public boolean add(E e) {
        addCount++;
        return inner.add(e);
    }

    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return inner.addAll(c); // inner's add() is not OUR add(), so no double count
    }
}
