# 01.14 · Exception propagation and stack traces

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#propagation`
> Branch: `topic/01-oop-exceptions/14-exception-propagation`

## 1. Key concepts (fill while reading)
- An uncaught exception unwinds the call stack, frame by frame, until a matching `catch` is found. Each frame's `finally` runs as it is left.
- Once caught, the program carries on normally from right after that `catch`, in that frame.
- If no frame catches it, the thread dies with a stack trace.
- Reading a stack trace:
  - Read top-down to see where it happened.
  - Look for the last `Caused by:` to find the root cause.
  - Find the first line that mentions your own package. That's usually where to start debugging.
- Since Java 14, `NullPointerException` messages name the exact expression that was null ("helpful NPEs").
- Checked exceptions must be declared with `throws` on every method they pass through. Unchecked ones pass through silently.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Propagation means an exception keeps going up from the method that threw it, to its caller, to that caller's caller, until some `catch` handles it. The stack trace is the record of that path.

**Real-life example:** A card payment fails deep inside `PaymentGateway.charge()`. If the service layer catches it, checkout can carry on (offer another card). If only `main` catches it, every layer in between is abandoned, and only their `finally` blocks run.

**Trade-off:** Catching high up (one place, like a controller advice) keeps code clean and the handling consistent, but the low levels can't recover locally. Catching low allows local recovery, but scatters handling code and risks swallowing errors. Rule: catch where you can actually do something useful.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using `OrderFlow.java` (four frames, with the `catch` placed by a parameter) and `Customer.java`.
- Catch in `main`: all three frames printed "leaving ..." (charge, then checkout, then place) before `caught in main: card declined`.
- Catch in `checkout`: only `charge` was abandoned. The line `checkout continues after charge()` ran, because the order was recovered there.
- Catch in `place`: `checkout continues` did not print, because checkout was abandoned too.
- Helpful NPE message: `Cannot invoke "...Customer$Address.getCity()" because the return value of "...Customer.getAddress()" is null`.
- The chained exception's stack trace showed `UncheckedIOException: Could not load invoice 42`, then our frames, then `Caused by: java.io.IOException: disk not mounted: invoice-42.pdf`, then `... 6 more` (frames shared with the outer trace). My `rootCause()` loop found the IOException.
- The checked chain needed `throws IOException` on `level1`, `level2` and `level3`. The unchecked chain needed nothing.
- `e.getStackTrace()[0]` is where the exception was thrown (`level3Checked`). It reported `9 frames deep`, which includes the frames of the Maven runner.
- Compile error I saw: `unreported exception IOException; must be caught or declared to be thrown`, for a middle level without `throws`.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What is exception propagation? | An uncaught exception moves up the call stack, frame by frame, running each `finally`, until a matching `catch` handles it or the thread dies. |
| How do you read a stack trace? | The top line is the type and message, and the first `at` line is where it was thrown. Find your own package. The last `Caused by:` is the root cause. |
| What does `... 6 more` mean? | Those frames are the same as in the enclosing trace, so Java doesn't repeat them. |
| What are helpful NullPointerExceptions? | Java 14+ messages that say what was null, for example "the return value of getAddress() is null". |
| How do checked and unchecked exceptions propagate differently? | Both propagate the same way at runtime. Only checked ones must be declared with `throws` at every level they pass through. |
| Where should exceptions be caught? | Where you can recover or translate them meaningfully, often at a boundary (controller, job runner). Not at every level. |

## 5. Gotchas / mistakes I made
- After a `catch`, code continues in the frame that caught, not in the frame that threw.
- Wrapping without passing the cause removes the `Caused by:` section, and you lose the real reason.
- `printStackTrace()` writes to stderr. In real apps, log with a logger and pass the exception object, as in `log.error("msg", e)`.
- The top of the stack trace might be inside a library. Scroll to the first frame from your own package.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
