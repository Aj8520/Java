# 01.05 · Polymorphism

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#polymorphism`
> Branch: `topic/01-oop-exceptions/05-polymorphism`

## 1. Key concepts (fill while reading)
- Polymorphism means "many forms": the same call behaves differently depending on the object. It is pillar 3 of "A PIE".
- Real-life: a TV remote's power button works on Sony, LG and Samsung TVs. Each TV decides what "power on" means.
- The variable type decides what you're allowed to call (compile time). The object type decides which overridden method actually runs (runtime).
- Fields, static methods and private methods follow the variable type.
- Overloading (compile-time / static polymorphism): same name, different parameters. The compiler picks the method from the argument types.
- Overriding (runtime / dynamic polymorphism): a subclass redefines a method with the same signature. The JVM picks the method from the actual object.

| | Overloading | Overriding |
|---|---|---|
| Return type | can differ freely | same or a subtype (covariant) |
| Access | any | same or wider, never narrower |
| Checked exceptions | any | same, narrower or none, never broader |
| static / private / final | can be overloaded | cannot be overridden (static is hidden) |

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Polymorphism lets you write code against a general type and have each object do its own version of the work. With overloading, the compiler picks a method from the argument types. With overriding, the JVM picks the method from the actual object at runtime.

**Real-life example:** A shop sends "your order shipped" through email, SMS and push. The loop calls `channel.send(...)` and never asks which kind it is. SMS cuts the text short, and push shouts it in capitals.

**Trade-off:** New behaviour means adding a class, not editing existing `if/else` chains (the Open/Closed principle), and code becomes easy to test with fakes. The cost is that you can't see which method runs just by reading the call, and lots of tiny classes can be harder to follow.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using `Shape.java` (`Circle`, `Rectangle`), `Printer.java` and `Notifier.java` (email, SMS, push).
- The shapes loop printed `Circle area = 3.14` and `Rectangle area = 6.00` without any type checks.
- Overload picks:
  - `print(5L)` printed `double 5.0`: a long widens to a double.
  - `print('A')` printed `int 65`: a char widens to an int.
  - `print(null)` printed `String null`: only the `String` version can take null.
  - `print(5.0f)` printed `double 5.0`.
- `describe(text)`, where `Object text = "hello"`, printed `describe(Object)`. The overload is chosen from the variable type, even though the object is a String.
- `Parent p = new Child()` gave:
  - `p.value()` returned `2` (overridden, so the object decides).
  - `p.id()` returned `Parent.id()` (static, so the variable decides).
  - `p.name` returned `parent field` (fields are never polymorphic).
- `p.value()` had to sit in a `try/catch (IOException)`, because the Parent version declares it, even though the Child version throws nothing.
- Compile errors I saw: `locked() in Child cannot override locked() in Parent` (final), and `value() in BrokenChild cannot override value() in Parent`, both for narrowing access and for a broader `throws Exception`.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| Overloading vs overriding? | Overloading: same name, different parameters, chosen at compile time. Overriding: same signature in a subclass, chosen at runtime from the object. |
| Can you override a static method? | No, it is hidden. The call is bound to the declared variable type at compile time. |
| Can you override a private or final method? | No. Private isn't inherited, so a same-named method is a new one. Final is blocked by the compiler. |
| Can the return type change when overriding? | Only to a subtype (covariant return), for example `Number` to `Integer`. |
| Can an override throw more exceptions? | It can't throw new or broader checked exceptions. It may throw fewer, narrower or none, and any unchecked ones. |
| Are fields polymorphic? | No. Field access uses the variable type. The child's same-named field hides the parent's. |
| Can overloading differ only in return type? | No. The parameter lists must differ, otherwise it is a compile error. |
| Why use `@Override`? | It turns a typo or wrong signature into a compile error, instead of a silent new method. |

## 5. Gotchas / mistakes I made
- Overloads are chosen at compile time from the declared types, so passing an `Object` variable picks `describe(Object)`.
- Widening beats boxing: `print(5L)` goes to `print(double)`, not to some `print(Long)`.
- `print(null)` would be ambiguous (a compile error) if there were two reference-type overloads, such as `print(String)` and `print(Integer)`.
- Calling a static method through an instance (`p.id()`) compiles but misleads. Always write `Parent.id()`.
- The checked exceptions you must handle are decided by the variable's type, not the object's.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
