# 01.12 · Checked vs unchecked

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#checked`
> Branch: `topic/01-oop-exceptions/12-checked-vs-unchecked`

## 1. Key concepts (fill while reading)

| | Checked | Unchecked | Error |
|---|---|---|---|
| Extends | `Exception` (not Runtime) | `RuntimeException` | `Error` |
| Compiler forces handling? | yes: catch or declare `throws` | no | no |
| Usually means | an expected external problem you can recover from | a programming bug or bad input | the JVM itself is in trouble |
| Examples | `IOException`, `SQLException`, `InterruptedException` | `NullPointerException`, `IllegalArgumentException`, `IndexOutOfBoundsException` | `OutOfMemoryError`, `StackOverflowError` |
| Should you catch it? | yes, where you can do something useful | usually fix the bug, and catch at boundaries (API, UI) | almost never |

- Three ways to deal with a checked exception: declare it (`throws`), handle it (`catch`), or wrap it in an unchecked one (`UncheckedIOException`) and rethrow.
- Catch order: most specific first. Otherwise the later block is unreachable, which is a compile error.
- Multi-catch (Java 7): `catch (A | B e)` handles several unrelated types the same way, and `e` is implicitly final.
- `throw new X(...)` is a statement that throws one object now. `throws X, Y` is part of a method signature.
- The great debate: Java is the only mainstream language with checked exceptions. Spring and Hibernate prefer unchecked ones, because `throws` clauses leak low-level details through every layer and don't fit lambdas.
- A common team rule: checked for recoverable business conditions the caller must decide on, unchecked for everything else.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** A checked exception is one the compiler makes you deal with. You must catch it or say `throws`. An unchecked exception (`RuntimeException`) is not checked by the compiler, because it usually means a bug that should be fixed, not handled.

**Real-life example:** `Account.withdraw()` declares a checked `InsufficientFundsException`. Not having enough money is a normal business case, and the caller must decide what to show the user. A negative amount throws an unchecked `IllegalArgumentException`, because that is a bug in the caller.

**Trade-off:** Checked exceptions make failure part of the method's contract, so you can't forget it. But they add `try/catch` and `throws` noise through every layer and don't work inside lambdas. Unchecked exceptions keep code clean, but nothing reminds callers that a failure can happen.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using:
  - `ConfigReader.java`: declare, handle or wrap
  - `PluginLoader.java`: multi-catch, then the parent type
  - `Account.java`: a checked business exception and an unchecked bug
- Reading a real temp file worked, and a missing file returned the default `{}`.
- Reading a directory threw an `IOException`, which was wrapped into `UncheckedIOException` with the original as its cause.
- Plugin loader results:

  | Class name | Result | Cause |
  |---|---|---|
  | `ArrayList` | loaded | |
  | `com.example.NoSuchPlugin` | `Bad plugin` | `ClassNotFoundException` |
  | `java.util.List` (an interface, no constructor) | `Bad plugin` | `NoSuchMethodException` |
  | `java.lang.Math` (private constructor) | `Reflection failed` | `IllegalAccessException` |

  `IllegalAccessException` is not in the multi-catch, so the parent `ReflectiveOperationException` block caught it.
- `withdraw(-5)` still had to sit inside a `try` for the checked exception, even though it threw the unchecked one.
- Precise rethrow: `catch (Exception e) { throw e; }` compiled in a method declaring only `throws FileNotFoundException`, and the caller received a `FileNotFoundException`.
- Compile errors I saw:
  - `unreported exception IOException; must be caught or declared to be thrown`
  - `unreported exception InsufficientFundsException; ...`
  - `exception FileNotFoundException has already been caught` (wrong catch order)
  - `Alternatives in a multi-catch statement cannot be related by subclassing`
  - `multi-catch parameter e may not be assigned`

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| Checked vs unchecked? | Checked extends `Exception` (not Runtime), and the compiler forces catch-or-declare. Unchecked extends `RuntimeException`, with no compiler check. |
| When would you create a checked exception? | For a recoverable condition the caller must consciously handle (insufficient funds). Otherwise unchecked. |
| Why do Spring and modern libraries prefer unchecked? | Checked `throws` clauses leak through every layer, clutter code, and don't fit lambdas or streams. |
| `throw` vs `throws`? | `throw` throws an exception object now. `throws` declares in the signature what a method may throw. |
| Order of catch blocks? | Most specific first. A parent before its child makes the child's block unreachable, which is a compile error. |
| What is multi-catch? | `catch (A \| B e)` for unrelated types handled the same way. The types can't be parent and child, and `e` is final. |
| How do you turn a checked exception into an unchecked one? | Wrap it, keeping the cause: `throw new UncheckedIOException("context", e)` or your own `RuntimeException` subclass. |
| Is `InterruptedException` checked? | Yes. When you catch it, restore the flag with `Thread.currentThread().interrupt()`. |

## 5. Gotchas / mistakes I made
- If a method declares a checked exception, every call must handle it, even when you know it can't happen on that call.
- Catching `Exception` swallows all the unchecked bugs too. Catch the narrowest type you can handle.
- `catch (IOException e)` around code that can't throw it doesn't compile, but `catch (RuntimeException e)` always compiles.
- When wrapping, always pass the cause (`new X(msg, e)`), otherwise the real error is lost.
- `NoSuchFileException` (NIO) is not `FileNotFoundException` (old IO). Catch the right one for the API you call.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
