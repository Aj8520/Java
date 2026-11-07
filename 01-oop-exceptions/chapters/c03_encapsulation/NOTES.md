# 01.03 · Encapsulation

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#encapsulation`
> Branch: `topic/01-oop-exceptions/03-encapsulation`

## 1. Key concepts (fill while reading)
- Encapsulation: hide the data, and expose behaviour that enforces the rules. It is one of the four OOP pillars ("A PIE").
- Recipe: private fields, public methods that protect the rules. Getters for reading, and no setter unless changing that value directly is genuinely allowed.
- Invariant: a rule that must always be true for the object, such as "the balance is never negative". Encapsulation exists to protect invariants.
- Rules are checked in two places: the constructor (an object can't start invalid) and every method that changes state (it can't become invalid later).
- `final` on a field stops it being reassigned. It does not stop the object it points to from changing, so a `final List` can still be modified.
- Defensive copy: copy mutable data on the way in and on the way out, so outside code never holds a reference to your internal state.
- Real-life: an ATM never lets you open the cash drawer. You ask it to `withdraw()`, and it checks your balance first.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Encapsulation means an object keeps its data private and owns the rules for changing it. Other code asks the object to do something instead of changing its data directly.

**Real-life example:** At an ATM you can't open the drawer and take cash. You ask to withdraw, and the machine checks your balance first. In code, `BankAccount` has no `setBalance()`, so `deposit()` and `withdraw()` are the only ways to change the money.

**Trade-off:** You write more code up front, such as methods, checks and copies, and defensive copies cost a little memory and time. In return the rules live in one place, bugs are easier to find, and the internals can change without breaking callers.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using the classes below.
- `PublicFieldAccount.java`: the handbook's bad version. It accepted `balance = -5000.0` and `owner = null` with no complaint.
- `BankAccount.java`: the handbook's good version. After `deposit(500)` and `withdraw(200)` on 1000, the balance was `1300.0` and the history was `[+500.0, -200.0]`. `withdraw(5000)` was rejected.
- Edge case found: the handbook's `withdraw()` only checks `amount > balance`, so `withdraw(-100)` passes that check. With no `amount <= 0` check, the balance went from `100.0` to `200.0`. I added the check, and now it is rejected.
- `account.getHistory().add(...)` threw `UnsupportedOperationException`, because `List.copyOf` returns an unmodifiable list.
- `Thermostat.java`: a case where a setter is fine, because it validates. `warmer()` calls `setTemperature()`, so the rule lives in one place. At 30, `warmer()` was rejected and the temperature stayed `30`.
- `LeakyTeam.java`: has private final fields and no setter, with a limit of 2 members, but it ended up as `[Asha, Ravi, Sam, Neha]`.
- `SafeTeam.java`: built from the same list, it stayed `[Asha, Ravi]`.
- Compile errors I saw when uncommenting lines:
  - `balance has private access in BankAccount`
  - `cannot find symbol` for `setBalance`

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What is encapsulation? | Keeping data private and exposing methods that enforce the rules, so an object can never be put in an invalid state. |
| Why not just use public fields? | Nothing can validate the values, and every caller depends on the field, so the internals can't change later. |
| Is private fields plus getters and setters for all of them encapsulation? | Not really. A setter with no checks is almost a public field. Expose behaviour, like `withdraw()`, instead of `setBalance()`. |
| Encapsulation vs abstraction? | Encapsulation protects the data and its rules. Abstraction hides complexity and shows what an object does, not how. |
| Encapsulation vs data hiding? | Data hiding (using `private`) is the tool. Encapsulation is the bigger idea of keeping data and the rules for it together. |
| Why return a copy from a getter? | Returning the real mutable object lets callers change internal state and skip every rule. |
| Does `final` make a field safe? | Only the reference is fixed. The object it points to can still change unless it is immutable. |
| `List.copyOf` vs `Collections.unmodifiableList`? | `copyOf` makes an independent copy. `unmodifiableList` is a read-only view, so changes to the original still show through it. |
| Where should validation go? | In the constructor and in every method that changes state, ideally shared in one place. |
| What are the benefits? | Valid objects, rules in one place, easier testing and debugging, and internals that can change without breaking callers. |

## 5. Gotchas / mistakes I made
- Checking only the upper limit: `withdraw(-100)` passes `amount > balance` and adds money. Always test negative and zero inputs.
- Forgetting the constructor: validating in methods only means an object can start out invalid.
- Leaking through the constructor: storing the caller's list directly is as bad as returning it from a getter.
- `List.copyOf` is a shallow copy. The list is protected, but mutable objects inside it can still change.
- `List.copyOf` throws `NullPointerException` if the list or any element is null.
- Generating getters and setters for every field by habit removes the protection. Add each one only when it is needed.
- `double` is not exact for money (`0.1 + 0.2` gives `0.30000000000000004`). Real code uses `BigDecimal` or whole cents in a `long`.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
