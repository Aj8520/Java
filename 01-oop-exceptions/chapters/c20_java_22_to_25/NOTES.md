# 01.20 · After 21: Java 22 to 25

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#beyond`
> Branch: `topic/01-oop-exceptions/20-java-22-to-25`

## 1. Key concepts (fill while reading)
- **Java 22 (Mar 2024):**
  - Foreign Function & Memory API final: call C libraries without JNI.
  - Unnamed variables `_` final.
  - Launch multi-file source programs.
  - Statements before `super()` (preview).
- **Java 23 (Sep 2024):**
  - Markdown in Javadoc comments (`///`).
  - Generational mode becomes ZGC's default.
  - Primitive types in patterns (preview) and module import declarations (preview).
- **Java 24 (Mar 2025):**
  - Stream Gatherers final (custom intermediate operations such as `windowFixed` and `windowSliding`).
  - Virtual threads no longer pinned by `synchronized`.
  - Class-File API.
  - Quantum-resistant ML-KEM and ML-DSA crypto.
  - Ahead-of-time class loading.
  - Security Manager permanently disabled.
- **Java 25 (Sep 2025), LTS:**
  - Compact source files and instance `main` methods final (`void main() { ... }`).
  - Module import declarations.
  - Flexible constructor bodies (statements before `super(...)`).
  - `ScopedValue` final.
  - Compact object headers.
  - Generational Shenandoah.
- Java 25 is the next LTS after 21.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Java 22–25 polish what came before. There is less boilerplate (`_`, a simple `main`, code before `super`), better stream building blocks (gatherers), safer per-request data (`ScopedValue`) and faster runtimes. Java 25 bundles them into the next LTS.

**Real-life example:** A web request id that every layer needs. In Java 17 I used a `ThreadLocal`, and I had to `remove()` it in `finally`, otherwise a pooled thread would carry the old id into the next request. In Java 25, `ScopedValue.where(ID, "req-7").run(...)` makes the value disappear automatically when `run` ends.

**Trade-off:** The new features remove whole classes of bugs, but you only get them on 22+ (or 25 for LTS). Non-LTS releases are supported for six months, so most teams wait for 25 rather than run 22–24 in production.

## 3. Code I wrote and what I observed
- `Practice.java` and `Windows.java`: this module is Java 17, so none of the 22–25 syntax compiles. Each example runs the Java 17 way, with the new syntax in a comment next to it.
- Unnamed variables: in 17 I needed a loop variable named `ignored`. In 22+ it would be `for (var _ : list)`.
- `///` Markdown comments compile fine in 17. They are just comments, and only Javadoc 23+ renders them.
- My Java 17 `windowFixed`:
  - `windowFixed(3)` on 1..7 gave `[[1, 2, 3], [4, 5, 6], [7]]` (the last window is partial).
  - `windowFixed(2)` on 1..5 gave `[[1, 2], [3, 4], [5]]`.
- My Java 17 `windowSliding`:
  - `windowSliding(3)` on 1..5 gave `[[1, 2, 3], [2, 3, 4], [3, 4, 5]]`.
  - `windowSliding(9)` on a 5-element list first gave `[]` in my version. As I understand the Java 24 Javadoc, a stream shorter than the window gives one window with everything, so I changed it to `[[1, 2, 3, 4, 5]]`. I couldn't run the real `Gatherers` here (JDK 17), so that comes from the docs, not from a test.
- Flexible constructors: in 17, `super(...)` must come first, so I validated inside its argument (`super(requireName(name))`). An empty name was rejected, and the `Person` constructor never ran.
- `ThreadLocal` request id: the controller and repository both saw `req-7` without passing it as a parameter. After `remove()` it was `null`.
- This JVM is 17, and `Class.forName` found neither `java.lang.ScopedValue` nor `java.util.stream.Gatherers`.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| Which release is the LTS after 21? | Java 25 (September 2025). |
| What are Stream Gatherers? | Java 24: custom intermediate stream operations through `stream.gather(...)`. Built-ins include `windowFixed`, `windowSliding`, `fold` and `scan`. |
| What is the Foreign Function & Memory API? | Java 22: a safe, fast way to call native (C) code and manage off-heap memory, replacing JNI. |
| `ScopedValue` vs `ThreadLocal`? | `ScopedValue` is immutable, bound for a scope and released automatically, and is cheap with virtual threads. `ThreadLocal` is mutable and must be removed by hand. |
| What are flexible constructor bodies? | Java 25: code (such as validation) can run before `super(...)`, as long as it doesn't touch `this`. |
| What changed for virtual threads in 24? | `synchronized` no longer pins a virtual thread to its carrier thread, so blocking inside `synchronized` scales. |
| What are compact source files? | Java 25: a file can be just `void main() { ... }`, with no class declaration. Good for scripts and teaching. |

## 5. Gotchas / mistakes I made
- None of this compiles on JDK 17. Switch the parent `pom.xml` release to 25 (and install JDK 25) to try the real syntax.
- With `ThreadLocal` on a thread pool, forgetting `remove()` leaks data into the next task.
- `windowSliding` with a window bigger than the input still gives one window (according to the Javadoc), not an empty result.
- Releases 22, 23 and 24 are not LTS, so production teams usually skip them.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
