# 01.09 · Nested classes and modern OOP

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#nested`
> Branch: `topic/01-oop-exceptions/09-nested-classes-modern-oop`

## 1. Key concepts (fill while reading)
- Four kinds of nested class:
  1. **Static nested**: no link to an outer object. This is the most common kind (Builder, Node). Create one with `new Outer.Helper()`.
  2. **Inner (non-static)**: tied to one outer instance and can read its fields. Create one with `outer.new Inner()`.
  3. **Local**: declared inside a method and visible only there.
  4. **Anonymous**: a one-off implementation with no name. Mostly replaced by lambdas.
- Every inner object holds a hidden reference to its outer object, which can cause memory leaks.
- Rule of thumb: make nested classes `static` unless they truly need the outer instance.
- Modern OOP (Java 16–21): records, sealed types and pattern matching together give "algebraic data types".
- **Record** (Java 16): an immutable data carrier. The constructor, accessors (`r()`), `equals`, `hashCode` and `toString` are generated. A compact constructor validates the input.
- **Sealed** (Java 17): `sealed interface Shape permits Circle, Square, Rect` closes the hierarchy, so no other class can implement it.
- In Java 21, `switch` over a sealed type needs no `default`, because the compiler knows every case. Record patterns like `case Rect(var w, var h)` take the fields apart. Both need Java 21+, so here they are only shown in comments.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** A nested class is a class declared inside another one, to keep a helper close to the only class that uses it. Records and sealed types are the modern way to model data: a record says "this is just these values", and sealed says "these are all the possible kinds".

**Real-life example:** `Pizza.Builder` is a static nested class. You chain `.topping("onion").extraCheese().build()`, and only the builder can call Pizza's private constructor, so every Pizza is checked (no more than 3 toppings).

**Trade-off:** Nesting keeps related code together and can hide it. Too much nesting makes files long, and inner classes keep their outer object alive. Records remove boilerplate but can't extend other classes and their fields are final. Sealed types give exhaustive checks, but adding a new kind means changing the `permits` list.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using:
  - `Outer.java`: all four nested kinds
  - `Shape.java`: a sealed interface with three records
  - `Pizza.java`: the Builder
- `outer.sortByLength([banana, fig, apple])` gave `[fig(3), apple(5), banana(6)]`, using a local class and an anonymous `Comparator`.
- Records printed themselves as `Circle[r=1.0]` and `Rect[w=2.0, h=3.0]`. `new Rect(-1, 3)` was rejected by the compact constructor.
- `area()` uses an `instanceof` chain because this project is on Java 17. The Java 21 switch version is in the comment above it.
- An inner object created before `setSecret(7)` then printed `7`. It reads the live outer object, not a copy.
- `this` in an anonymous class is the anonymous object (`Practice$1`). `this` in a lambda is the enclosing `Practice` object (`Practice object`).
- The pizza with 4 toppings was rejected with `max 3 toppings, got 4`.
- Reflection showed that `Outer.Inner` has a hidden field `[this$0]` (the outer reference), and `Outer.Helper` has none (`[]`).
- Compile errors I saw:
  - `class is not allowed to extend sealed class: Shape (as it is not listed in its permits clause)`
  - `Pizza(Builder) has private access in Pizza`
  - `local variables referenced from a lambda expression must be final or effectively final`. This one appeared on the reader lambda, not on the `count++` line I uncommented.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| Static nested vs inner class? | Static nested has no outer instance and is created on its own. Inner needs an outer object and can use its fields. |
| Why can inner classes cause memory leaks? | Each one holds a hidden reference to its outer object (`this$0`), so a long-lived inner object (a listener or a task) keeps the outer alive. |
| Anonymous class vs lambda? | A lambda only works for functional interfaces, is shorter, and its `this` means the enclosing object. An anonymous class can implement any interface or class and has its own `this` and state. |
| What is "effectively final"? | A local variable that is never reassigned after it is set. Only those can be used inside lambdas and local/anonymous classes. |
| What are records? | Concise immutable data carriers (Java 16) with a generated constructor, accessors, `equals`, `hashCode` and `toString`. |
| Can a record have extra methods or validation? | Yes: methods, static fields and a compact constructor. It can't extend a class or add instance fields. |
| What are sealed classes? | Java 17 classes or interfaces that list their only permitted subtypes. That lets the compiler check that a `switch` covers every case (Java 21). |
| Where do you use a static nested class? | Builders, map entries, linked-list nodes: helpers that belong to one class. |

## 5. Gotchas / mistakes I made
- Uncommenting `count++` reported the error on a different line (the lambda that reads `count`). Read the whole error, not just the line number.
- `getSimpleName()` of an anonymous class is an empty string. Use `getName()` to see `Outer$1`.
- Records are shallowly immutable. A `List` field can still be changed unless you copy it in the compact constructor (`List.copyOf`).
- Pattern matching for `switch` and record patterns need Java 21. This module compiles with 17.
- Record accessors are named `r()`, not `getR()`.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
