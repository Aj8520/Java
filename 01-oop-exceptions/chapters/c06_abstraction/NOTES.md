# 01.06 · Abstraction

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#abstraction`
> Branch: `topic/01-oop-exceptions/06-abstraction`

## 1. Key concepts (fill while reading)
- Abstraction: show **what** something does, hide **how**. It is pillar 4 of "A PIE".
- Real-life: you drive any car with a steering wheel and pedals, without knowing whether it's petrol, diesel or electric underneath.
- Two tools: abstract classes and interfaces.

| | Abstract class | Interface |
|---|---|---|
| Keyword | `abstract class` / `extends` | `interface` / `implements` |
| How many per class | one | many |
| Instance fields (state) | yes | no (only `public static final` constants) |
| Constructors | yes | no |
| Method bodies | yes, any | `default` and `static` (8), `private` (9) |
| Access of members | any | public (private helpers since 9) |
| Relationship | "is-a" with shared code | "can-do" capability |
| Use when | related classes share state and a template | unrelated classes share a contract (`Comparable`, `Runnable`) |

- An abstract method has no body, and every concrete subclass must implement it.
- An abstract class can't be instantiated with `new`, but it still has a constructor that subclasses call through `super(...)`.
- An interface with exactly one abstract method is a functional interface, so it can be implemented with a lambda.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Abstraction means callers depend on a simple contract (method names and what they promise), not on the details of how the work is done.

**Real-life example:** `Car` only knows the `Engine` interface: `start()` and `accelerate()`. I swapped `PetrolEngine` for `ElectricEngine` and `Car.drive()` didn't change at all. `PaymentMethod` is the abstract-class version: the shared steps (validate, charge, audit) are written once, and each payment type fills in only `charge()`.

**Trade-off:** Code is easier to change and test, because you can swap or fake the implementation, but there are more types and one more level to jump through when reading. Don't add an interface that only ever has one implementation "just in case".

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using these files:
  - `PaymentMethod.java` (abstract, with a `final pay()` template)
  - `Refundable.java` (interface with a constant, an abstract method, and default, static and private methods)
  - `UpiPayment.java` (`UpiPayment` and `CashPayment`)
  - `Receipt.java` (record)
  - `Engine.java` (`Engine`, the two engines and `Car`)
- Even though `PaymentMethod` is abstract, its constructor ran for every payment: `PaymentMethod constructor ran for cust-7`.
- `pay()` always ran validate, then charge, then audit. `pay(0)` was rejected with `amount must be positive, was 0.0` before `charge()` was called.
- `cash instanceof Refundable` was `false`. A class picks which capabilities it has.
- Refund window: 30 days ago gave `true` and 31 days ago gave `false`. The private interface helper printed `[refundable] checking ...`.
- `new PaymentMethod("cust-10") { ... }` worked. That is an anonymous subclass, not an instance of the abstract class. Its `getSimpleName()` was an empty string `''`.
- `ClickCounter` (extends an abstract class and implements an interface) stopped at `3`, the interface constant `MAX`.
- `Refundable.none()` returned a lambda, because `Refundable` has a single abstract method.
- Compile errors I saw:
  - `PaymentMethod is abstract; cannot be instantiated`
  - `cannot assign a value to final variable MAX`
  - `<identifier> expected` for a constructor in an interface

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What is abstraction? | Exposing what an object does through a simple contract and hiding how it does it. |
| Abstract class vs interface? | Abstract class: state, constructors, any methods, one per class, is-a. Interface: no instance state, many per class, a can-do contract. |
| When to choose which? | An abstract class for closely related types sharing code and state. An interface for a capability that unrelated classes can have. |
| Can an abstract class have a constructor? | Yes. It runs when a subclass is created, through `super(...)`. |
| Can an abstract class have no abstract methods? | Yes. It just can't be instantiated (for example a base class meant only for extending). |
| Why were default methods added in Java 8? | To add methods to existing interfaces (like `Collection.stream()`) without breaking every implementing class. |
| What are interface fields? | Always `public static final` constants. |
| Can an interface extend another interface? | Yes, and it can extend several: `interface A extends B, C`. |
| Can you instantiate an abstract class? | No. `new Abstract() { ... }` creates an anonymous subclass. |

## 5. Gotchas / mistakes I made
- Interface methods are `public`, so an implementation must also be `public`. Leaving the modifier off is a compile error, because that would reduce visibility.
- A class implementing an interface but not all its methods must itself be declared `abstract`.
- Making the template method `final` stops subclasses skipping the validation.
- An abstract method can't be `private`, `static` or `final`, because each of those stops it being overridden.
- Don't use `LocalDate.now()` inside logic you want to test. I passed `today` in, so the result is predictable.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
