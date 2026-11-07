# 01.17 · Best practices, lambdas, and Spring Boot

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#excpractice`
> Branch: `topic/01-oop-exceptions/17-exception-best-practices`

## 1. Key concepts (fill while reading)
- The handbook's 10 rules:
  1. Never swallow (an empty `catch`).
  2. Log OR rethrow, not both, or the same error is logged at every layer.
  3. Don't catch `Throwable`/`Error`.
  4. Don't use exceptions for normal control flow (use `Optional` or a check).
  5. Never lose the cause.
  6. Write specific messages with values.
  7. Validate early ("fail fast", `Objects.requireNonNull`).
  8. Use standard exceptions when they fit (`IllegalArgumentException`, `IllegalStateException`, `UnsupportedOperationException`, `NoSuchElementException`).
  9. Clean up with try-with-resources.
  10. Document thrown exceptions with `@throws`.
- Checked exceptions and lambdas don't mix: `Function.apply()` can't throw `IOException`. Either wrap inside the lambda, or extract a helper method that wraps (cleaner).
- Java 14+ helpful NPEs say exactly what was null.
- Spring Boot: translate exceptions into HTTP responses in ONE `@RestControllerAdvice` class, with `@ExceptionHandler` methods returning `ProblemDetail` (RFC 9457). Controllers just throw.
- In the last-resort handler, log the error once and hide the details from the client.
- `@Transactional` rolls back on unchecked exceptions by default, NOT on checked ones. Use `rollbackFor = Exception.class` if needed.
- `InterruptedException`: if you can't rethrow it, restore the flag with `Thread.currentThread().interrupt()`.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Good exception handling means every error is either handled usefully or passed up with its cause, it is logged exactly once with a clear message, and users see a safe, consistent response.

**Real-life example:** A web API. Controllers just throw `OrderNotFoundException` or `IllegalArgumentException`, and one global handler turns them into 404 or 400 responses. Anything unexpected becomes a 500 with "Something went wrong". The real error (which here even contained a password) goes only to the log.

**Trade-off:** Central handling keeps controllers clean and responses consistent, but the handler must be maintained as new exception types appear. Wrapping checked exceptions for lambdas makes streams usable, but callers can no longer see the checked type in the signature.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using `GlobalExceptionHandler.java` (a plain-Java imitation of `@RestControllerAdvice`, since Spring isn't in this module) and `ProblemDetail.java` (a record).
- Rule 1: the swallowed `NumberFormatException` left no trace at all.
- Rule 2: log-and-rethrow logged one failure 2 times (repository and controller). In a real app with 5 layers, that's 5 log entries.
- Rule 4: `Optional.ofNullable(prices.get("coffee")).orElse("not on menu")`, with no try/catch needed.
- Rules 6 and 7: `discount must be 0-100, was 140`, and `Objects.requireNonNull` failed at the door with `order must not be null`.
- `paths.stream().map(p -> Files.readString(p))` is a compile error: `unreported exception IOException`.
- With the `read()` helper wrapping into `UncheckedIOException`, the stream read `a.txt`, failed on `missing-b.txt` (`NoSuchFileException`), and never reached `c.txt`. Streams stop at the first exception.
- Global handler responses:
  - 200 for success
  - 404 `Order 99 not found`
  - 400 `discount must be 0-100, was -5`
  - 500 `Something went wrong`. The `secret123` message appeared only in the log line.
- `InterruptedException`: when swallowed, the thread was no longer interrupted (`false`). When restored, it was still interrupted (`true`).

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What are common exception-handling mistakes? | Swallowing, logging and rethrowing, catching `Throwable`, using exceptions for flow control, losing the cause, and vague messages. |
| How do you use a method that throws a checked exception inside a stream? | Wrap it in an unchecked exception (`UncheckedIOException`), ideally in a small helper method. Then unwrap or handle it outside. |
| How should a Spring Boot app handle exceptions? | Services throw meaningful (usually unchecked) exceptions, and one `@RestControllerAdvice` maps them to `ProblemDetail` responses. Log unexpected ones once and hide internals. |
| Does `@Transactional` roll back on a checked exception? | Not by default, only on unchecked ones and `Error`s. Use `rollbackFor`. |
| What do you do with `InterruptedException`? | Rethrow it, or restore the interrupt flag with `Thread.currentThread().interrupt()` so code higher up knows a stop was requested. |
| Why not use exceptions for control flow? | They're slow (stack trace capture), hide intent, and can catch unrelated bugs. Use checks, `Optional` or return values. |
| What should a 500 response contain? | A generic message and maybe a correlation id. Never stack traces, SQL or secrets. Those go to the log. |

## 5. Gotchas / mistakes I made
- My unexpected exception message contained `password=secret123`. Even logs shouldn't hold secrets, so don't put them in messages.
- `Thread.sleep()` on an already-interrupted thread throws immediately and clears the flag.
- A stream that throws midway has already done part of the work (`a.txt` was read). Side effects aren't undone.
- `Optional` is for return values, not for fields or parameters.
- Catching `Exception` in the global handler must be the LAST handler, after the specific ones.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
