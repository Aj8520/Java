# 01.07 · final, static, and the Object class

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#keywords`
> Branch: `topic/01-oop-exceptions/07-final-static-object`

## 1. Key concepts (fill while reading)
- `final` on a variable: assign once. For a reference, the reference is fixed but the object can still change.
- `final` on a method: it can't be overridden.
- `final` on a class: it can't be extended. `String`, `Integer` and `LocalDate` are final.
- `static` belongs to the class, not to any one object: constants, shared counters, utility methods.
- `static` methods can't use `this` or instance fields, and can't be overridden (only hidden).
- Utility class: a `final` class with a `private` constructor and only static methods (like `MathUtil` or `Math`).
- Every class inherits from `java.lang.Object`. The methods you meet most:
  - `equals()`: logical equality
  - `hashCode()`: must match `equals`
  - `toString()`
  - `getClass()`
- Other `Object` methods: `clone()` (protected, avoid), `wait()`/`notify()`/`notifyAll()`, and `finalize()` (deprecated).
- A `record` generates `equals`, `hashCode` and `toString` for you.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** `final` means "this can't change any more" (a value, an override or a subclass). `static` means "one per class, shared", not one per object. `Object` is the root class, and its `equals`/`hashCode`/`toString` decide how your objects compare and print.

**Real-life example:** In a token machine, the counter inside the machine is `static`: there is one, and every token uses it. The number printed on your token is `final`: once printed, it never changes.

**Trade-off:** `final` makes code safer and easier to reason about, but less flexible (no subclass, no reassigning). `static` is handy for constants and helpers, but static mutable state is shared by the whole app: hard to test, and unsafe between threads.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using:
  - `MathUtil.java`: utility class with a shared `calls` counter
  - `Point.java`: `Point`, the `Point2` record and `BadPoint`
  - `Ticket.java`: static counter plus a final number
- `final List<String> names` still accepted `names.add("ok")`. Only reassigning `names` was blocked.
- `MathUtil.calls()` returned `2` after two `square()` calls. It is one counter for the whole class.
- `a == b` was `false` but `a.equals(b)` was `true` for two `Point(1, 2)` objects. Their hash codes were equal too.
- A record prints `Point2[x=1, y=2]` and has value equality for free.
- Tokens were numbered 1, 2, 3 across different counters, because they share one static counter. `issuedSoFar()` returned `3`.
- `BadPoint` overrides `equals` but not `hashCode`, and the two objects are equal (`true`). Yet a `HashSet` kept both of them (size `2`), and `contains(new BadPoint(1, 2))` was `false`. `Point` worked correctly (size `1`, contains `true`).
- Compile errors I saw:
  - `cannot assign a value to final variable max`
  - `cannot assign a value to final variable names`
  - `MathUtil() has private access in MathUtil`
  - `non-static variable this cannot be referenced from a static context`
  - `cannot inherit from final String`

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What does `final` mean on a variable, method and class? | Variable: assigned once. Method: can't be overridden. Class: can't be extended. |
| Is a `final` list immutable? | No. Only the reference is fixed. Use `List.of` or `List.copyOf` for an unmodifiable list. |
| `final` vs `finally` vs `finalize`? | Keyword for "can't change", a block that always runs after `try`, and a deprecated `Object` method called before garbage collection. |
| Can a static method use instance fields? | No. There is no `this`, unless an object is passed in. |
| Can you override a static method? | No, a same-signature static method in a subclass hides it. |
| `==` vs `equals()`? | `==` compares references (same object). `equals` compares meaning, if the class overrides it. |
| Why override `hashCode` with `equals`? | Hash-based collections find the bucket by `hashCode` first. Equal objects with different hashes get "lost" in a `HashSet`/`HashMap`. |
| What is the `equals`/`hashCode` contract? | Equal objects must have equal hash codes. Unequal objects may share a hash. Both must be consistent over time. |
| Why make a class final? | To protect its invariants and security (like `String`), and because immutable classes rely on it. |

## 5. Gotchas / mistakes I made
- Forgetting `hashCode()`: `equals` looks right in tests, but `HashSet`/`HashMap` misbehave.
- Using mutable fields in `equals`/`hashCode`: if you change an object after putting it in a set, the set can't find it.
- Static mutable counters (`Ticket.nextNumber`) are not thread-safe. Real code uses `AtomicInteger` (concurrency handbook).
- `equals(Object o)` must take an `Object`. `equals(Point p)` is an overload, not an override, so add `@Override`.
- Call static members through the class name (`MathUtil.square`), not through an object.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
