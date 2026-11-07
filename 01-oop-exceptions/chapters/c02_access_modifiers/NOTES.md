# 01.02 · Access modifiers

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#access`
> Branch: `topic/01-oop-exceptions/02-access-modifiers`

## 1. Key concepts (fill while reading)
- Access modifiers: who is allowed to see a field, method or class?
- Start with the most restrictive (private) and open up only when needed. Top-level classes can only be public or package-private. Real-life: private is your diary, package-private is your family, protected is family plus your children who moved away, public is a notice board.
- The four levels, from most to least restrictive:

| Modifier | Same class | Same package | Subclass in another package | Everyone else |
|---|---|---|---|---|
| `private` | yes | no | no | no |
| (none) package-private | yes | yes | no | no |
| `protected` | yes | yes | yes (inherited access only) | no |
| `public` | yes | yes | yes | yes |

- Package-private has no keyword. You get it by writing no modifier.
- `protected` is wider than package-private, not narrower: it gives everything package-private gives, plus subclasses.
- Nested classes can use all four modifiers. Only top-level classes are limited to `public` or package-private.
- Access is checked at compile time, using the declared type of the reference and the class that declares the member.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Access modifiers decide which code can use a class or member. They protect a class's internals so that other code depends only on what the class chooses to expose.

**Real-life example:** A bank account hides its balance. You cannot change the number directly. You go through `deposit()` and `withdraw()`, and those methods enforce the rules, such as "the balance is never negative".

**Trade-off:** Tighter access means more freedom to change code later, because fewer places depend on it, but you write more methods to expose what is needed. Wider access is quicker to write, but everything public becomes something other code can rely on, so it is hard to change or remove. That is why we start with `private`.

## 3. Code I wrote and what I observed
- `Practice.java`: runs four sections, using `Household`, `BankAccount` and the `outside` package.
- `Household.java`: one member per access level, based on the handbook's analogy.
- `outside/MovedAwayChild.java`: subclass in another package. It sees public and protected members only.
- `outside/Stranger.java`: not a subclass, in another package. It sees public members only.
- `BankAccount.java`: `private` balance. `withdraw(800)` on a balance of 500 returned `false`, and `deposit(-50)` was rejected.
- Observed: `a.hasSameDiaryAs(b)` read the private field of a different object, and it compiled. Private belongs to the class, not to the object.
- Observed: `child.familyRecipe` compiles in `Practice` even though `MovedAwayChild` is in another package, because `familyRecipe` is declared in `Household` and `Practice` shares that package.
- Observed: `p.revealSecret()` on a `Child` printed `parent secret`, while `p.role()` printed `child role`. The private method was not overridden.
- Compile errors I saw when uncommenting lines:
  - `diary has private access in Household`
  - `fridgeCode is not public in Household; cannot be accessed from outside package`
  - `familyRecipe has protected access in Household`
  - `attempting to assign weaker access privileges; was protected`

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What are the access levels in Java? | `private`, package-private (no keyword), `protected`, `public`, from most to least restrictive. |
| What is the default access level? | Package-private: visible only inside the same package. Interface methods are the exception, as they are implicitly `public`. |
| What is the difference between `protected` and package-private? | Both allow the same package. `protected` also allows subclasses in other packages. |
| Can a top-level class be `private` or `protected`? | No, only `public` or package-private. Nested classes can use all four. |
| Can an override change the access level? | It can widen access but not narrow it. Otherwise code using the parent type could call a method the child had hidden. |
| Can a private method be overridden? | No. Subclasses do not inherit it, so a method with the same name is a new, unrelated method. |
| Can one object read another object's private field? | Yes, if both are the same class. Private is per class, not per object. `equals()` relies on this. |
| Can a constructor be private? | Yes. It stops outside code from calling `new`, which is used in singletons, factory methods and utility classes. |
| Does `private` give real security? | No. It is a design tool, not a security feature. Reflection can get around it in many cases. |

## 5. Gotchas / mistakes I made
- Package-private is not the `default` keyword. That keyword is for `switch` and interface default methods.
- In a subclass in another package, `protected` works through `this` or the subclass type, but not through a parent-type reference: `parent.familyRecipe` is a compile error.
- A sub-package is a separate package. `c02_access_modifiers.outside` gets no special access to `c02_access_modifiers`.
- Adding `@Override` to a method that "overrides" a private method is a compile error, which is a useful way to catch this mistake.
- A `public` field of a mutable type is still risky, and even a `private` field can leak if a getter returns the mutable object itself.

## Done checklist
- [x] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [x] Committed, pushed and merged into `master`
