# 01.04 · Inheritance

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#inheritance`
> Branch: `topic/01-oop-exceptions/04-inheritance`

## 1. Key concepts (fill while reading)
- Inheritance: a subclass `extends` a superclass to reuse its fields and methods and to specialise behaviour. It is pillar 2 of "A PIE".
- Test it with "is-a": a Manager is an Employee, a Car is a Vehicle. If the sentence sounds wrong, don't inherit.
- Kinds:
  - single: `Dog extends Animal`
  - multilevel: `Puppy extends Dog extends Animal`
  - hierarchical: Dog and Cat both extend Animal
  - multiple through interfaces: `Duck implements Flyer, Swimmer`
  - hybrid: one superclass plus interfaces
- Multiple inheritance of classes is not allowed (`class A extends B, C`). It avoids the "diamond" ambiguity of inheriting two different states.
- `super(...)` calls the parent constructor and must come first. If you leave it out, the compiler adds `super()`.
- `super.method()` reuses the parent's version from inside an override.
- Every class ultimately extends `java.lang.Object`.
- Order for `new Puppy()`: parent static blocks, then child static blocks (once per class), then for each level from the top: instance block, then constructor.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Inheritance lets a new class start from an existing one. It gets the parent's fields and methods for free, and can override the parts that behave differently.

**Real-life example:** At a toll booth every vehicle has a plate and pays a toll. A `Car` just uses the normal toll. A `Truck` overrides `toll()` to charge per axle. The `receipt()` code is written once, in `Vehicle`.

**Trade-off:** Less duplicated code and one place to fix shared logic, but it is the tightest coupling in Java. A subclass depends on every detail of its parent, and a change in the parent can break all the children (the "fragile base class" problem). Use it only for a true is-a relationship. For plain code reuse, prefer composition (chapter 8).

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections. `Employee.java` and `Manager.java` are the handbook's example, and `Vehicle.java` holds `Vehicle`, `Car` and `Truck`.
- `describe()` exists only in `Employee`, yet `asha.describe()` printed `Asha earns 225000.0`, using the Manager's `monthlyPay()`. The overridden method runs even when called from parent code.
- The first `new Puppy()` printed:
  1. `Animal static block`
  2. `Dog static block`
  3. `Animal instance block`
  4. `Animal constructor`
  5. `Dog instance block`
  6. `Dog constructor`
  7. `Puppy constructor`
- The second `new Puppy()` skipped both static blocks.
- Constructor trap: the `Base` constructor calls `describe()`, which `SafeDerived` overrides. It printed `label=null`, because the child's fields are not assigned yet. In `Derived` it threw a `NullPointerException`.
- Surprise: my first version of `Derived` used `private final String name = "derived"` and did not crash. A `final` field set to a literal is a compile-time constant, which the compiler inlines. I changed it to `"derived".toUpperCase()` to show the real bug.
- Toll booth output:
  - `Car MH12-AB-1234 pays 100` (inherited)
  - `Truck MH14-XY-9876 pays 300` (overridden: 100 × 3 axles)
- `Duck` implements two interfaces with the same `default move()`. It must override `move()` and pick with `Flyer.super.move()`. It printed `duck is flying and swimming`.
- `class Amphibian extends Car, Truck` does not compile. javac only says `'{' expected`.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What is inheritance? | A subclass reuses and specialises a superclass using `extends`. It models an is-a relationship. |
| Why doesn't Java allow multiple inheritance of classes? | The diamond problem: two parents could define the same method or field differently. Java allows multiple inheritance of type through interfaces instead. |
| Two interfaces have the same default method. What happens? | The class must override it, and can call `InterfaceName.super.method()` to choose one. |
| `this` vs `super`? | `this` is the current object, and `this(...)` calls another constructor in the same class. `super` is the parent part of the object: `super.method()` and `super(...)`. |
| What is the order of initialisation? | Static blocks top-down (once), then for each level top-down: instance initialisers, then the constructor body. |
| Are constructors inherited? | No. Each class declares its own, and it must call a parent constructor first (implicitly or explicitly). |
| Are private members inherited? | They are part of the object, but the subclass can't access them directly. They are not inherited as members. |
| Why not call an overridable method from a constructor? | The subclass version runs before the subclass's fields are initialised, so it sees nulls and zeros. |
| How do you stop a class from being extended? | Make it `final` (like `String`), or `sealed` with a `permits` list (Java 17). |

## 5. Gotchas / mistakes I made
- If the parent has no no-argument constructor, every child must call `super(args)` explicitly. Otherwise it is a compile error.
- A compile-time constant (`final` + literal) is inlined, so the constructor trap doesn't show up with it. Real fields (computed, or objects like lists) do show the bug.
- Static blocks run once per class, not once per object.
- Inheriting just to reuse code, like `Stack extends Vector` in the JDK, exposes methods that break the child's rules.
- `getClass()` returns the runtime class, so `Vehicle.receipt()` printed `Truck`, not `Vehicle`.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
