# 01.15 · try-with-resources

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#twr`
> Branch: `topic/01-oop-exceptions/15-try-with-resources`

## 1. Key concepts (fill while reading)
- Files, database connections, sockets and locks must be released even when something fails.
- try-with-resources (Java 7) closes every `AutoCloseable` automatically, in reverse order, even on exceptions: `try (A a = ...; B b = ...) { }`.
- Before Java 7: `finally` blocks full of null checks and nested `try/catch`, which were easy to get wrong.
- If the body throws AND `close()` throws, the body's exception wins. The close exception is attached as "suppressed" (`e.getSuppressed()`) instead of being lost.
- With the old `finally` style, the `close()` exception would replace the real one, and you'd debug the wrong problem.
- Java 9: an existing effectively-final variable can be used directly: `try (in) { ... }`.
- Your own resource: implement `AutoCloseable` (or `Closeable`, whose `close()` throws `IOException`).

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** try-with-resources is a `try` that owns the objects declared in its brackets, and it guarantees `close()` is called on each of them when the block ends, however it ends.

**Real-life example:** Writing a shopping list to a file. The writer is closed (and flushed) automatically at the closing `}`, so the text really reaches the disk, and the reader is closed after reading, even if a line had failed.

**Trade-off:** It is almost always better than `finally`: shorter, the order is right, and no exception is lost. The limits: it only works for `AutoCloseable` types, the resource's scope is the block, and resources are closed before `catch` runs, so you can't use them inside `catch`.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using `FakeResource.java`, which prints `open`/`close` and can fail on open or close.
- Opened in the order reader, connection, statement, and closed in reverse: statement, connection, reader.
- Order of events: `open file`, `close file`, then `catch sees: body failed`, then `finally`. Resources are closed BEFORE the `catch` block runs.
- When `second` failed to open, `first` was still closed, and `second` was never closed because it never existed. The body didn't run.
- The body and both `close()` calls failed: the main exception was `query failed`, with two suppressed exceptions (`statement.close() failed`, then `connection.close() failed`).
- A real temp file: wrote 3 lines and read back 3 items, with both streams closed automatically.
- Old `finally` style: the caller saw `old.close() failed` with 0 suppressed. The REAL problem was lost.
- try-with-resources: the caller saw `the REAL problem`, with the close failure kept as suppressed.
- Compile errors I saw:
  - `incompatible types: try-with-resources not applicable to variable type` (for `Object`)
  - `auto-closeable resource r may not be assigned`

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What is try-with-resources? | A `try` that declares `AutoCloseable` resources and closes them automatically, in reverse order, even if an exception occurs. |
| In what order are resources closed? | The reverse of the order they were opened, so later resources (which may depend on earlier ones) close first. |
| What are suppressed exceptions? | Exceptions from `close()` that happen while another exception is already in flight. They are attached to it (`getSuppressed()`) instead of replacing it. |
| `AutoCloseable` vs `Closeable`? | `Closeable` (java.io) extends `AutoCloseable`, and its `close()` throws `IOException` and should be idempotent. `AutoCloseable.close()` throws `Exception`. |
| Does close run before or after catch? | Before. By the time `catch` and `finally` run, the resources are already closed. |
| What if a resource fails to open? | The ones already opened are closed, and the failed one isn't (it doesn't exist). |
| What changed in Java 9? | You can put an existing effectively-final variable in the brackets: `try (in) { }`. |

## 5. Gotchas / mistakes I made
- Resources can't be used inside `catch`, because they are already closed.
- A resource variable is implicitly final: reassigning it is a compile error.
- Only what's declared in the brackets is closed. An object created inside the body is not.
- Forgetting to close a writer can lose data that was still in the buffer. With try-with-resources you can't forget.
- `close()` should be safe to call twice, and shouldn't throw for trivial reasons.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
