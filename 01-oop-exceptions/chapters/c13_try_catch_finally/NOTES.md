# 01.13 · try, catch, finally: what actually runs

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#flow`
> Branch: `topic/01-oop-exceptions/13-try-catch-finally`

## 1. Key concepts (fill while reading)
- `try` runs until the first exception. The rest of the `try` is skipped and Java jumps to the first matching `catch`.
- If no `catch` matches, `finally` still runs, and then the exception carries on up to the caller.
- `finally` runs after `try`/`catch` whether or not an exception happened, even after a `return`. Use it for cleanup: release locks, close things, reset state.
- A `return` in `finally` overrides everything, even a thrown exception, which silently disappears. Never do this.
- An exception thrown in `finally` replaces the original one, which is lost.
- `finally` does NOT run on `System.exit()`, if the JVM crashes or is killed (`kill -9`), if the `try` never finishes (an infinite loop or deadlock), or in a daemon thread when the JVM exits.
- A `try` needs at least one `catch` or a `finally` (or resources, in chapter 15).

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** `try` is "attempt this", `catch` is "if this kind of problem happens, do this instead", and `finally` is "no matter how we leave, do this last".

**Real-life example:** You book a meeting room. Whether the meeting goes well or the projector catches fire, you must release the room at the end. That release goes in `finally`. In my demo the meeting crashed, but the room was still free afterwards, and Ravi could book it.

**Trade-off:** `finally` guarantees cleanup, but hand-written cleanup is verbose and easy to get wrong (an exception in `finally` hides the real one). For anything `AutoCloseable`, try-with-resources (chapter 15) is safer.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using `MeetingRoom.java` and `ExitDemo.java` (run in a child JVM).
- `process()` from the handbook:

  | Input | Lines printed | Result |
  |---|---|---|
  | `"5"` | `start`, `parsed 5`, `finally` | returned `20` |
  | `"abc"` | `start`, `not a number`, `finally` | returned `-1` |
  | `"0"` | `start`, `parsed 0`, `finally` | the `ArithmeticException` (`/ by zero`) escaped to the caller, because no `catch` matched |

- `test()`: the `finally` message printed first, then `test() = 1`.
- `bad()` returned `2`. The `"lost!"` exception vanished because of `return` in `finally`. The compiler warns about this, and I had to add `@SuppressWarnings("finally")`.
- `changePrimitive()` returned `1`: the value was already saved, so changing `x` in `finally` didn't matter.
- `changeObject()` returned `ab`: the saved value is a reference, and `finally` changed the object it points to.
- An exception in `finally` replaced the original. The caller saw only `problem in finally`.
- `System.exit(0)` in a child JVM: it printed `try: calling System.exit(0)` and exit code `0`, and the `finally` line never printed.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| Does `finally` always run? | After `try`/`catch`, yes, even after `return`. Not on `System.exit()`, a JVM crash or kill, a `try` that never ends, or a daemon thread at JVM exit. |
| What if both `try` and `finally` return? | The `finally` value wins and any exception is discarded, so avoid it. |
| Can `finally` change the returned value? | Not for a primitive or a reassigned variable, because the value is saved first. It can change the state of a returned object. |
| What if `finally` throws? | That exception replaces the original, which is lost. try-with-resources keeps both, using suppressed exceptions. |
| Is `try` without `catch` allowed? | Yes, `try`/`finally` (or try-with-resources). The exception continues to the caller after `finally`. |
| What runs if an exception is not caught by any `catch`? | `finally` runs, then the exception propagates up the stack. |

## 5. Gotchas / mistakes I made
- Code after the failing line in `try` never runs: `parsed 0` printed, but nothing after `100 / n`.
- `return` or `throw` inside `finally` hides the real outcome. The compiler only warns, it doesn't stop you.
- `finally` is not a good place for logic that can fail. Keep it to simple, safe cleanup.
- To demo `System.exit()` I had to start a separate JVM. Calling it in the practice program would have stopped everything.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
