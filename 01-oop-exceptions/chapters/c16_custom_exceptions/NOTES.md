# 01.16 · Custom exceptions and chaining

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#custom`
> Branch: `topic/01-oop-exceptions/16-custom-exceptions`

## 1. Key concepts (fill while reading)
- Custom checked exception: `extends Exception`. Use it for a business situation callers are expected to handle (`InsufficientFundsException`).
- Custom unchecked exception: `extends RuntimeException`. This is the common choice in Spring apps (`OrderNotFoundException`).
- Add fields for useful context (`shortBy`, `dueBack`, an error code) so callers can act, not just print.
- Exception chaining: wrap a low-level exception in a higher-level one but keep the original as its cause: `new OrderPersistenceException("Could not save order " + id, e)`. The stack trace then shows the root cause as `Caused by:`.
- Provide constructors `(String message)` and `(String message, Throwable cause)`.
- Naming: end with `Exception`. The message should say what happened AND the relevant values.
- Exceptions are `Serializable`, so declare a `serialVersionUID`.
- A small hierarchy (a base `LibraryException` with specific subclasses) lets callers catch broadly or precisely.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** A custom exception is your own subclass of `Exception` or `RuntimeException`, named after a problem in your domain, that carries the details needed to handle it. Chaining means that when you translate a low-level error into yours, you keep the original inside it.

**Real-life example:** A library app has `LibraryException` with an error code, plus `BookNotFoundException` and `BookOnLoanException` (which knows the `dueBack` date, so the app can offer a reservation). One handler maps the codes to HTTP statuses: 404, 409 and 403.

**Trade-off:** Domain exceptions make code readable and handling precise. But too many custom types become clutter. Reuse the standard ones (`IllegalArgumentException`, `IllegalStateException`) when they already say the right thing.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using:
  - `InsufficientFundsException.java`: checked, with a `BigDecimal shortBy`
  - `OrderNotFoundException.java`: unchecked
  - `OrderPersistenceException.java`: chaining
  - `LibraryException.java`: a base type plus two subclasses
- The checked exception gave `Balance 500.00 is less than 750.50, short by 250.50`.
- The chained exception gave `Could not save order 7`, `caused by SQLException: duplicate key value violates unique constraint "orders_pkey"`.
- Wrapping the same `SQLException` three ways:

  | How | `getMessage()` | `getCause()` |
  |---|---|---|
  | message only | `save failed` | `null`, the original is gone |
  | message + cause | `save failed` | `connection refused` |
  | cause only | `java.sql.SQLException: connection refused` (`cause.toString()`) | the `SQLException` |

- `initCause()` adds a cause after creation, for exceptions without a `(message, cause)` constructor.
- Library: `catch (BookOnLoanException)` came before `catch (LibraryException)`, so the loan case got its own handling (`reserve it for 2026-11-20`). The base type caught `BOOK_NOT_FOUND`.
- Status mapping: `404 <- BookNotFoundException`, `409 <- BookOnLoanException`, `500 <- IllegalStateException`. I used a switch expression over the enum: all three values are covered, so no `default` is needed.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| How do you create a custom exception? | Extend `Exception` (checked) or `RuntimeException` (unchecked), add `(message)` and `(message, cause)` constructors, and add fields for context. |
| Checked or unchecked for a custom exception? | Checked if callers can reasonably recover and must decide. Otherwise unchecked, which is more common in modern frameworks. |
| What is exception chaining? | Wrapping a low-level exception in a higher-level one, with the original as its `cause`, so the root cause is still in the stack trace. |
| What happens if you forget the cause? | `getCause()` is `null` and the stack trace has no `Caused by:`. You lose where and why it really failed. |
| Why create a hierarchy of custom exceptions? | Callers can catch the base type for general handling or a subclass for specific handling, and a code field can map to HTTP status. |
| What makes a good exception message? | What failed plus the relevant values: `"Order 42 not found"`, not `"error"`. |
| Should exception classes be immutable? | Yes. Make the context fields final, set them in the constructor, and only add getters. |

## 5. Gotchas / mistakes I made
- `new RuntimeException(cause)` sets the message to `cause.toString()`, including the class name, which may not be what users should see.
- Catch blocks for subclasses must come before the base class, or they're unreachable (a compile error).
- Don't put secrets (passwords, card numbers) in exception messages. They end up in logs.
- Use `BigDecimal` for money in exceptions too. `compareTo`, not `equals`, compares values (`2.0` vs `2.00`).
- Don't make a new exception class for every `if`. Reuse the standard ones when they fit.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
