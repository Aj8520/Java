package c08_composition_casting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/** Inheritance for reuse: a stack IS-NOT-A list, but now it has every list method too. */
class InheritedStack<T> extends ArrayList<T> {
    void push(T t) { add(t); }
    T pop() { return remove(size() - 1); }
}

/** Composition: HAS-A. Exposes only stack operations; the inside can change freely. */
class ComposedStack<T> {
    private final Deque<T> items = new ArrayDeque<>();

    public void push(T t) { items.push(t); }
    public T pop() { return items.pop(); }
    public boolean isEmpty() { return items.isEmpty(); }
}
