# 01.10 · Why exceptions?

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#exceptions`
> Branch: `topic/01-oop-exceptions/10-why-exceptions`

## 1. Key concepts (fill while reading)
- Things go wrong: files go missing, networks drop, users type "abc" where a number belongs.
- An exception is an object that says "something unusual happened here". The JVM throws it up the call stack until some code catches it.
- Error-code style (`if (result == -1)`) mixes error checks into every line, and a forgotten check silently passes bad values on.
- Exceptions keep the normal path clean and make errors impossible to silently ignore.
- `try` holds the risky code. `catch` handles a specific problem. `finally` always runs, for cleanup.
- If no one catches an exception, the thread dies with a stack trace. Other threads keep running.
- Real-life: a fire alarm. Normal work stops, the alarm travels up floor by floor, and whoever is trained for it (a `catch` block) responds. If nobody does, the building is evacuated (the thread dies).

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** An exception is Java's way of saying "I can't continue normally". It stops the current code and jumps to the nearest matching `catch`. That keeps error handling separate from the main logic.

**Real-life example:** For a cinema booking, the error-code version returns `-1` for a bad seat. My code forgot to check and printed "Booked seat -1". The exception version throws `seat must be 1-10, was 42`, which can't be missed.

**Trade-off:** Exceptions make failures clear and carry a message and a stack trace. But they cost more than a normal return, and the jump in control flow is invisible when reading code. Use them for unusual situations, not for normal branching.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using `SeatBooking.java` (error codes vs exceptions).
- `marks[5]` threw `ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 2`. I caught it and the program carried on.
- `divide()` results, with `finally` printing for every input:

  | Input | Result |
  |---|---|
  | `"5"` | `20` |
  | `"abc"` | `Please type a number` |
  | `"0"` | `Can't divide by zero` |
  | `"-25"` | `-4` |
  | `" 7"` (leading space) | `Please type a number` |
  | `"2147483648"` (too big for `int`) | `Please type a number` |

- `100.0 / 0` gave `Infinity` with no exception. Only integer division by zero throws.
- An uncaught `NullPointerException` killed the `worker-1` thread, and then `main is still running` printed.
- An exception can be created without being thrown. It is just an object with a message.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What is an exception? | An object describing an unusual event. When thrown, it interrupts normal flow and travels up the call stack to a matching `catch`. |
| Why use exceptions instead of error codes? | Codes can be ignored and mix checks into every line. Exceptions keep the normal path clean, carry details, and can't be silently dropped. |
| What happens if an exception is never caught? | The thread ends and the default handler prints the stack trace. If it was the main thread and no other non-daemon thread is running, the program exits. |
| Does an exception in one thread stop the others? | No. Only that thread dies. |
| Is `5 / 0` the same as `5.0 / 0`? | No. Integers throw `ArithmeticException`, while doubles give `Infinity` (or `NaN` for `0.0 / 0`). |
| Are exceptions slow? | Creating one fills in a stack trace, which costs time. That's fine for unusual cases, but bad as everyday control flow. |

## 5. Gotchas / mistakes I made
- `Integer.parseInt(" 7")` fails. Trim user input first.
- Numbers outside the `int` range also throw `NumberFormatException`, not an overflow.
- A forgotten error-code check doesn't fail loudly: the bad value just continues (`seat -1`).
- `finally` runs even when the `try` or `catch` returns. It printed before the result line.
- Don't use exceptions for normal decisions (chapter 17).

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
