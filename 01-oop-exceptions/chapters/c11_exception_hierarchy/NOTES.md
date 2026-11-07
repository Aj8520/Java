# 01.11 · The exception hierarchy

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#hierarchy`
> Branch: `topic/01-oop-exceptions/11-exception-hierarchy`

## 1. Key concepts (fill while reading)
- Everything that can be thrown extends `Throwable`. It has `getMessage()`, `getCause()`, `getStackTrace()` and `getSuppressed()`. You almost never catch `Throwable` directly.
- `Error`: serious JVM problems your code generally can't recover from, such as `OutOfMemoryError`, `StackOverflowError`, `NoClassDefFoundError` and `AssertionError`. Unchecked. Don't catch them, except at the very top level to log and exit.
- `Exception`: conditions an application might want to catch. Every subclass except `RuntimeException` is checked:
  - `IOException` (`FileNotFoundException`, `NoSuchFileException`)
  - `SQLException`
  - `InterruptedException`
  - `ReflectiveOperationException` (`ClassNotFoundException`)
- `RuntimeException`: unchecked, usually a bug or invalid input:
  - `NullPointerException`
  - `IllegalArgumentException` (`NumberFormatException`)
  - `IllegalStateException`
  - `IndexOutOfBoundsException` (the array and String versions)
  - `ArithmeticException`, `ClassCastException`, `UnsupportedOperationException`, `ConcurrentModificationException`, `UncheckedIOException`
- A `catch (Parent e)` also catches every subclass, so the tree decides which block runs.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** The exception hierarchy is a class tree with `Throwable` at the top. It has two big branches, `Error` (the JVM is in trouble) and `Exception` (your app's problems). Inside `Exception`, the `RuntimeException` branch is unchecked and everything else is checked.

**Real-life example:** Hospital triage. An `Error` is the building on fire, so you evacuate rather than treat it. A checked exception is an expected issue the front desk must have a plan for (the file is missing, the network is down). A runtime exception is a mistake in the process itself (null where a value was promised), and you fix the process.

**Trade-off:** Catching high in the tree (`Exception`) is short to write, but hides which problem happened and can catch bugs you wanted to see. Catching low (`NoSuchFileException`) is precise, but means more `catch` blocks.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using `Hierarchy.java`, which walks `getSuperclass()` and classifies each type.
- Some printed chains:
  - `NumberFormatException -> IllegalArgumentException -> RuntimeException -> Exception -> Throwable`
  - `NoSuchFileException -> FileSystemException -> IOException -> Exception -> Throwable`
  - `StackOverflowError -> VirtualMachineError -> Error -> Throwable`
- The catch blocks were ordered `IllegalArgumentException`, `RuntimeException`, `Exception`, `Throwable`:
  - `NumberFormatException` was caught by the `IllegalArgumentException` block (its parent).
  - `IllegalStateException` and `UnsupportedOperationException` fell through to `RuntimeException`.
  - `AssertionError` passed `catch (Exception)` and was caught only by `Throwable`.
- Real exceptions I triggered:
  - `"abc".charAt(5)` gave `StringIndexOutOfBoundsException`.
  - `List.of("a").add(...)` gave `UnsupportedOperationException`.
  - `(Integer) "text"` gave `ClassCastException`.
  - Removing during a for-each gave `ConcurrentModificationException`.
  - `null.length()` gave `NullPointerException`.
- Recursion with no base case was caught as `StackOverflowError` after about 20000 calls (depends on the stack size). It is not an `Exception`.
- `catch (IOException e)` around code that can't throw it is a compile error: `exception IOException is never thrown in body of corresponding try statement`. The compiler checks checked exceptions this way, but not unchecked ones.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What is the root of the hierarchy? | `Throwable`, with two branches: `Error` and `Exception`. |
| `Error` vs `Exception`? | `Error` is a JVM-level problem you usually can't recover from. `Exception` is an application problem you may handle. |
| Which exceptions are unchecked? | `RuntimeException` and its subclasses, and all `Error`s. |
| Is `NullPointerException` checked? | No. It is a `RuntimeException`. |
| Should you catch `Throwable` or `Error`? | Almost never. Only at the very top (a framework or the main loop) to log and shut down cleanly. |
| What causes `StackOverflowError` vs `OutOfMemoryError`? | Too-deep recursion (often a missing base case, or `toString`/`equals` calling each other), vs a full heap or Metaspace (a leak, or `-Xmx` set too small). |
| `ClassNotFoundException` vs `NoClassDefFoundError`? | The first is checked, from `Class.forName` at runtime. The second is an `Error`: a class present at compile time is missing at runtime, or its static initialiser failed. |
| What does `ConcurrentModificationException` mean? | A collection was changed while being iterated. Use `removeIf` or `iterator.remove()`. |

## 5. Gotchas / mistakes I made
- `catch (Exception e)` does NOT catch `AssertionError` or `StackOverflowError`, because they are `Error`s.
- `catch (Exception e)` DOES catch every `RuntimeException`, including the bugs you wanted to see.
- `NoSuchFileException` (NIO `Files.*`) and `FileNotFoundException` (old `java.io`) are siblings under `IOException`, so catching one doesn't catch the other.
- The compiler rejects catching a checked exception that can't be thrown, but allows catching any unchecked one.
- `5.0 / 0` gives `Infinity`. Only integer division throws `ArithmeticException`.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
