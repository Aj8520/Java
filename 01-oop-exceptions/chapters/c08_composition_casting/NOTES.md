# 01.08 · Composition over inheritance, and casting

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#composition`
> Branch: `topic/01-oop-exceptions/08-composition-casting`

## 1. Key concepts (fill while reading)
- Inheritance is the strongest coupling in Java: a subclass depends on every detail of its parent.
- Composition ("has-a"): hold another object and delegate to it. It is usually more flexible, because you expose only what you choose.
- UML relationship words:
  - Association: a Teacher knows Students (independent lifetimes).
  - Aggregation (◇): a Team has Players, and the players exist without the team.
  - Composition (◆): a House has Rooms, and the rooms die with the house.
  - Inheritance (▷): a Manager is an Employee.
- Upcast (`Animal a = new Dog()`) is automatic and always safe. You can only call `Animal` methods, but the overridden version runs.
- Downcast (`(Dog) a`) is explicit and checked at runtime. If wrong, it throws `ClassCastException`.
- Check before downcasting: `instanceof`, or the Java 16 pattern `if (a instanceof Dog dog && dog.isTrained())`.
- Pattern matching for `switch` (`case Dog dog ->`) is Java 21. In Java 17, use a chain of `instanceof` patterns.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Composition builds a class out of other objects it holds and calls. Inheritance builds a class by extending another one. Casting moves a reference up the hierarchy (always fine) or down it (must be checked).

**Real-life example:** A house owns its rooms: they are created inside it and go away with it (composition). A cricket team just lists players who existed before the team and still exist after it is disbanded (aggregation).

**Trade-off:** Composition means a few more forwarding methods, but you control the public API and can swap the inside (even at runtime). Inheritance is less typing, but you inherit the parent's whole API and its hidden behaviour, as the counting-set bug shows.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using:
  - `Stacks.java`: `InheritedStack` and `ComposedStack`
  - `Animals.java`: `Animal`, `Dog` and `Cat`
  - `House.java`: `House`/`Room` (composition) and `Team`/`Player` (aggregation)
  - `CountingSets.java`
- `InheritedStack` accepted `add(0, "oops")` and became `[oops, a, b]`. The stack's rules are broken. `ComposedStack` has no `add`, so that line is a compile error.
- `Animal a = new Dog(...)`: `a.sound()` printed `Woof`, but `a.fetch()` is a compile error (`cannot find symbol`). After `(Dog) a`, `fetch()` worked.
- Casting a Cat to a Dog compiled, but at runtime threw `ClassCastException: class ...Cat cannot be cast to class ...Dog`.
- `null instanceof Dog` is `false`, and `(Dog) null` does not throw: it is just `null`.
- `(Cat) new Dog(...)` is a compile error (`incompatible types`). The compiler knows a Dog can never be a Cat, since they are sibling classes.
- Counting set: adding 3 elements with `addAll` gave `6` in the version that extends `HashSet`, because `HashSet.addAll()` calls our overridden `add()`. The composed version counted `3`.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What is composition, and why prefer it over inheritance? | Holding and delegating to other objects (has-a). It is looser coupling: you expose only what you choose, internals can change, and behaviour can be swapped at runtime. |
| What is the fragile base class problem? | A subclass depends on how the parent is implemented, so a parent change (or a hidden self-call like `addAll` → `add`) breaks the child. |
| When is inheritance still right? | For a true is-a relationship, when the parent is designed for extension (documented, abstract or template methods). |
| Aggregation vs composition? | Both are has-a. In composition the part's lifetime is owned by the whole (rooms). In aggregation the parts live on their own (players). |
| Upcasting vs downcasting? | Upcast to a parent type: implicit and safe. Downcast to a child type: explicit, and can throw `ClassCastException`. |
| How do you avoid `ClassCastException`? | Check with `instanceof` (preferably the pattern form), or design with polymorphism so no cast is needed. |
| What is pattern matching for `instanceof`? | Java 16: `if (o instanceof Dog d)` tests, casts and declares `d` in one step. |
| Why does `java.util.Stack` get criticised? | It extends `Vector`, so a "stack" lets you insert anywhere. Use `ArrayDeque` instead. |

## 5. Gotchas / mistakes I made
- Overriding methods of a JDK collection is risky, because you don't know which methods call which internally.
- Lots of downcasts in code usually means polymorphism is missing. Add a method to the parent type instead.
- A cast between sibling classes is caught at compile time. A cast from a parent type is only checked at runtime.
- The pattern variable in `a instanceof Dog dog && dog.isTrained()` is only in scope where the test is known to be true. That's why `||` doesn't work there.
- `describe()` with an `instanceof` chain must put the more specific types first.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
