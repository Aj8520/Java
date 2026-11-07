# 01.19 · Same code, newer Java

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#evolution`
> Branch: `topic/01-oop-exceptions/19-same-code-new-java`

## 1. Key concepts (fill while reading)
- The handbook's slider rewrites the same task with the best features of each version:

| Task | Old way | New way |
|---|---|---|
| Data class | a POJO with getters, `equals`, `hashCode`, `toString` (8) | a `record` (16) |
| Type-based logic | `instanceof` + cast (8) | `instanceof` patterns (16), then a sealed type + switch patterns + record patterns (21) |
| Text | string concatenation with `\n` (8) | text blocks + `formatted` (15) |
| `switch` | statements with `break` (8) | switch expressions with `->` (14) |
| Collections | `Arrays.asList` + `unmodifiableList` (8) | `List.of` + `var` (9/10), `toList()` (16), `reversed()`/`getFirst()` (21) |
| HTTP | `HttpURLConnection` (8) | `HttpClient` (11) |
| Threads | thread pools (8) | virtual threads (21) |
| Hello world | full class + `public static void main(String[] args)` | `java Hello.java` single-file launch (11), simpler main methods (preview in 21, final in 25) |

- Newer syntax does the same work with less code and fewer places for bugs, such as a forgotten `break` or a hand-written `equals`.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Modern Java doesn't change what a program can do. It gives shorter, safer ways to write the same logic: records instead of boilerplate classes, switch expressions instead of `break`, patterns instead of casts.

**Real-life example:** A payments screen describes UPI, card and cash payments. The Java 8 version uses `instanceof` and then a cast in each branch. The Java 17 version uses `instanceof` patterns. In Java 21 it becomes an exhaustive `switch` over a sealed interface, where the compiler complains if a new payment type is forgotten.

**Trade-off:** The new style is shorter and safer, but the team (and the build) must be on the newer Java, and mixed styles in one codebase can confuse. Modernise gradually, when you touch the code anyway, and keep behaviour identical, checked by tests.

## 3. Code I wrote and what I observed
- `OldStyle.java` is Java 8 style, `NewStyle.java` is Java 17 style, and `Practice.java` runs both and prints `same?` for each task.
- Every task gave identical results:
  - The `Person` POJO and the record printed the same `Person[name=Asha, age=30]`.
  - `daysIn("JUNE")` gave `30` both ways.
  - The cities sorted by length gave `[Goa, Pune, Mumbai]`.
  - Grouping gave `{3=[tea, egg], 4=[milk], 5=[bread]}`.
- The concatenated HTML and the text block were exactly the same string (`true`).
- A forgotten `break`: the old switch for level 1 returned `read write` (it fell into case 2). The switch expression returned `read`.
- The hand-written `equals` and the record's generated `equals` both gave `true`. The accessor is `name()`, not `getName()`.
- The payment descriptions matched for UPI, Card and Cash. The Java 21 switch version is in a comment.
- Under the hood, a record is a `final` class that extends `java.lang.Record` (`isRecord()` was `true`).

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| How would you modernise Java 8 code? | Records for data classes, switch expressions, `instanceof` patterns, text blocks, `List.of`/`var`/`toList()`, and sealed types for closed hierarchies. Do it gradually, with tests. |
| What does a record generate? | A canonical constructor, accessors named after the fields, and `equals`, `hashCode` and `toString`. The class is final and its fields are final. |
| Why are switch expressions safer? | No fall-through, they return a value, and switches over enums or sealed types can be checked for exhaustiveness. |
| Can a record replace every POJO? | No. Not for mutable objects, JPA entities (which need a no-arg constructor and setters), or classes that must extend another class. |
| What does a sealed interface add to pattern matching? | The compiler knows every subtype, so a Java 21 `switch` needs no `default` and fails to compile if a case is missing. |

## 5. Gotchas / mistakes I made
- `Stream.toList()` is unmodifiable, unlike the old `ArrayList` copy. Check callers before swapping.
- Text blocks: indentation is measured from the closing `"""`. Putting it on the last content line avoids a trailing newline.
- Record accessors are `name()`, not `getName()`, so frameworks or code expecting getters may need changes.
- `HashMap` printing order looked the same here, but neither version promises an order.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
