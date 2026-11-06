# 01.01 · Classes & objects

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#classes`
> Branch: `topic/01-oop-exceptions/01-classes-objects`

## 1. Key concepts (fill while reading)
- Object-oriented programming models software as objects that hold state  (fields) and behaviour (methods), like real things. A class is the blueprint; an object is one thing built from it. One house plan, many houses, each with its own paint colour.
- The four pillars
  - Encapsulation: hide data behind safe methods.
  - Inheritance: reuse and specialise. 
  - Polymorphism: one call, many behaviours.
  - Abstraction: expose what, hide how. 
   Remember them as “A PIE”.
- Instance vs static
   - Instance members belong to each object (each car's speed).
   - Static members belong to the class itself, shared by all objects (how many cars were built).

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

- Class is blueprint (house plan). object is instance built from it (multiple houses with same plan but different color)
-  Four pillars of oops
   - Abstraction:  hiding details (hide how). showing essential things (expose what)
   - polymorphism: one call different behavior 
   - Inheritance: reuse parent property and specifies its own 
   - Encapsulation: hide data behind safe methods .
   
-  Static vs Instance 
   - static is present in class level and share values to its object( how many cars are build)
   - instance variable belongs to each variable(each car speed).

## 3. Code I wrote and what I observed
- `Practice.java`: 
  - no "this" in static methods 

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What is a class? | A blueprint that defines the state (fields) and behaviour (methods) of its objects. |
| What is an object? | An instance of a class, with its own copy of the instance fields. |
| State vs behaviour? | State is the data an object holds (`speed`, `colour`); behaviour is what it can do (`accelerate()`). |
| Four pillars of OOP? | Abstraction, Polymorphism, Inheritance, Encapsulation ("A PIE"). |
| Abstraction? | Expose what an object does and hide how it does it. |
| Polymorphism? | One call, different behaviour depending on the actual object. |
| Inheritance? | A child class reuses a parent's members and can add or override its own. |
| Encapsulation? | Keep fields private and allow access only through safe methods. |
| What does `new` do? | Allocates the object on the heap, runs the constructor and returns a reference to it. |
| Constructor vs method? | A constructor initialises a new object, has the class name and no return type, and runs automatically with `new`; a method has a return type and is called explicitly. |
| What is a default constructor? | The empty no-arg constructor Java adds only when a class declares no constructor; writing any constructor removes it. |
| What does `this` refer to? | The current object, the one whose constructor or instance method is running. |
| What is constructor chaining? | One constructor calling another in the same class with `this(...)`, which must be the first statement (Java 17). |
| Static vs instance field? | A static field has one copy shared by the whole class; each object gets its own copy of an instance field. |
| Why no `this` in a static method? | A static method belongs to the class and runs without an object, so there is no current object; using `this` is a compile error. |
| Why can't `main` call `checkStaticDiffrence()` directly? | `main` is static and that method is an instance method, so it needs an object: `new Practice().checkStaticDiffrence()`. |
| Why is `carsBuilt` static but `speed` not? | The count is one value shared by all cars, while each car has its own speed. |
| Does the chained constructor count a car twice? | No, `carsBuilt++` runs only in the two-arg constructor, so it runs once per object and the result is 2. |
| Value of `speed` if never assigned? | 0, because fields get default values (`int` = 0, `boolean` = false, references = null); local variables do not. |
| Does `new Car()` compile? | No, `Car` declares constructors, so Java adds no default no-arg constructor. |
| `a.getSpeed()` after `a.accelerate(250)`? | 200, because `Math.min(speed + by, 200)` caps the speed. |
| After `Car d = a; d.accelerate(10);`, does `a` change? | Yes, `d` copies the reference, not the object, so both point to the same car. |
| What prints if `Car` has no `toString()`? | `Object.toString()` runs and prints class name + `@` + hex hash code, like `c01_classes_objects.Car@1b6d3586`. |

## 5. Gotchas / mistakes I made
- State vs behaviour is not static vs instance: state is data (`speed`), behaviour is methods (`accelerate()`).
- Encapsulation is not only `private`: fields are private **and** accessed through safe methods.
- `new` does more than create an instance: it allocates heap memory, runs the constructor and returns a reference.
- A constructor initialises the object, has the class name and no return type, and runs automatically with `new`.
- `this` is the current object, not an outside variable.
- Constructor chaining means calling another constructor with `this(...)`, as the first statement.
- `this` is not just "not required" in a static method: it is not available, because there is no current object, so using it is a compile error.
- `a.accelerate(250)` gives 200, not 250: read the method body, because `Math.min(speed + by, 200)` caps the speed.
- `Car d = a;` copies the reference, not the object, so a change through `d` also shows through `a`.
- Without `toString()`, printing an object shows class name + `@` + hex hash code, like `c01_classes_objects.Car@1b6d3586`.
- Compile error: `main` is static, so it cannot call the instance method `checkStaticDiffrence()` directly; create an object first.

## Done checklist
- [x] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [x] Committed, pushed and merged into `master`
