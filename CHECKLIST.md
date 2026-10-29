# ✅ Study Checklist — Industry-Standard Sequence

This is the **single source of truth for progress**. It orders all 201 chapter branches the way Java backend
developers are usually trained in industry:
language fundamentals → error handling → data structures → modern/functional Java → JVM internals →
concurrency → design → databases → Spring Boot & microservices → deployment.

**How to use**
1. Work top to bottom. Use `./progress.sh` to see the next unchecked step.
2. Check out the branch shown on the line, study, practise, then merge into `master` (see [README](README.md#5-daily-study-flow)).
3. In the same merge, change `- [ ]` to `- [x]` on that line.
4. At the end of each phase, build the **milestone project**. It is what turns reading into experience you can talk about in interviews.

**A chapter counts as done when:** its NOTES.md sections 1–5 are filled in, all TODOs in Practice.java run, and
you can explain it out loud in 2 minutes (definition → example → trade-off).

| Phase | Topic | Chapters |
|---|---|---|
| 1 | Java fundamentals & OOP | 13 |
| 2 | Exception handling | 8 |
| 3 | Collections & generics | 23 |
| 4 | Functional Java (8 → 21) | 24 |
| 5 | JVM internals & memory | 10 |
| 6 | Concurrency & multithreading | 20 |
| 7 | Design principles & patterns | 32 |
| 8 | Databases: SQL → JDBC → JPA | 24 |
| 9 | Spring Boot & microservices | 22 |
| 10 | Containers, Kubernetes & CI/CD | 25 |
| | **Total** | **201** |

---

## Phase 1 · Java fundamentals & OOP

**Goal:** Write clean object-oriented Java and understand how String really works.

- [ ] **001** · 01.01 [Classes & objects](01-oop-exceptions/chapters/c01_classes_objects/NOTES.md) — `topic/01-oop-exceptions/01-classes-objects`
- [ ] **002** · 01.02 [Access modifiers](01-oop-exceptions/chapters/c02_access_modifiers/NOTES.md) — `topic/01-oop-exceptions/02-access-modifiers`
- [ ] **003** · 01.03 [Encapsulation](01-oop-exceptions/chapters/c03_encapsulation/NOTES.md) — `topic/01-oop-exceptions/03-encapsulation`
- [ ] **004** · 01.04 [Inheritance](01-oop-exceptions/chapters/c04_inheritance/NOTES.md) — `topic/01-oop-exceptions/04-inheritance`
- [ ] **005** · 01.05 [Polymorphism](01-oop-exceptions/chapters/c05_polymorphism/NOTES.md) — `topic/01-oop-exceptions/05-polymorphism`
- [ ] **006** · 01.06 [Abstraction](01-oop-exceptions/chapters/c06_abstraction/NOTES.md) — `topic/01-oop-exceptions/06-abstraction`
- [ ] **007** · 01.07 [final, static, Object](01-oop-exceptions/chapters/c07_final_static_object/NOTES.md) — `topic/01-oop-exceptions/07-final-static-object`
- [ ] **008** · 01.08 [Composition & casting](01-oop-exceptions/chapters/c08_composition_casting/NOTES.md) — `topic/01-oop-exceptions/08-composition-casting`
- [ ] **009** · 01.09 [Nested classes & modern OOP](01-oop-exceptions/chapters/c09_nested_classes_modern_oop/NOTES.md) — `topic/01-oop-exceptions/09-nested-classes-modern-oop`
- [ ] **010** · 03.11 [String & immutability](03-jvm-memory-strings/chapters/c11_string_immutability/NOTES.md) — `topic/03-jvm-memory-strings/11-string-immutability`
- [ ] **011** · 03.12 [The String pool](03-jvm-memory-strings/chapters/c12_string_pool/NOTES.md) — `topic/03-jvm-memory-strings/12-string-pool`
- [ ] **012** · 03.13 [StringBuilder](03-jvm-memory-strings/chapters/c13_stringbuilder/NOTES.md) — `topic/03-jvm-memory-strings/13-stringbuilder`
- [ ] **013** · 03.14 [StringBuffer & comparison](03-jvm-memory-strings/chapters/c14_stringbuffer_comparison/NOTES.md) — `topic/03-jvm-memory-strings/14-stringbuffer-comparison`

**🏁 Milestone project:** Library management console app: classes, encapsulation, inheritance and interfaces, plus immutable value objects.

- [ ] Milestone project done (in `projects/phase-01/` on branch `project/phase-01`)

## Phase 2 · Exception handling

**Goal:** Fail safely and predictably, the way production code is expected to.

- [ ] **014** · 01.10 [Why exceptions](01-oop-exceptions/chapters/c10_why_exceptions/NOTES.md) — `topic/01-oop-exceptions/10-why-exceptions`
- [ ] **015** · 01.11 [Exception hierarchy](01-oop-exceptions/chapters/c11_exception_hierarchy/NOTES.md) — `topic/01-oop-exceptions/11-exception-hierarchy`
- [ ] **016** · 01.12 [Checked vs unchecked](01-oop-exceptions/chapters/c12_checked_vs_unchecked/NOTES.md) — `topic/01-oop-exceptions/12-checked-vs-unchecked`
- [ ] **017** · 01.13 [try / catch / finally](01-oop-exceptions/chapters/c13_try_catch_finally/NOTES.md) — `topic/01-oop-exceptions/13-try-catch-finally`
- [ ] **018** · 01.14 [Propagation](01-oop-exceptions/chapters/c14_exception_propagation/NOTES.md) — `topic/01-oop-exceptions/14-exception-propagation`
- [ ] **019** · 01.15 [try-with-resources](01-oop-exceptions/chapters/c15_try_with_resources/NOTES.md) — `topic/01-oop-exceptions/15-try-with-resources`
- [ ] **020** · 01.16 [Custom exceptions](01-oop-exceptions/chapters/c16_custom_exceptions/NOTES.md) — `topic/01-oop-exceptions/16-custom-exceptions`
- [ ] **021** · 01.17 [Best practices & Spring](01-oop-exceptions/chapters/c17_exception_best_practices/NOTES.md) — `topic/01-oop-exceptions/17-exception-best-practices`

**🏁 Milestone project:** Add custom exceptions, validation and a try-with-resources CSV import to the library app.

- [ ] Milestone project done (in `projects/phase-02/` on branch `project/phase-02`)

## Phase 3 · Collections & generics

**Goal:** Pick the right data structure and explain its internals and Big-O.

- [ ] **022** · 02.24 [Generics & wildcards](02-collections/chapters/c24_generics_wildcards/NOTES.md) — `topic/02-collections/24-generics-wildcards`
- [ ] **023** · 02.01 [Why collections?](02-collections/chapters/c01_why_collections/NOTES.md) — `topic/02-collections/01-why-collections`
- [ ] **024** · 02.02 [The family tree](02-collections/chapters/c02_family_tree/NOTES.md) — `topic/02-collections/02-family-tree`
- [ ] **025** · 02.03 [Core interfaces](02-collections/chapters/c03_core_interfaces/NOTES.md) — `topic/02-collections/03-core-interfaces`
- [ ] **026** · 02.04 [ArrayList](02-collections/chapters/c04_arraylist/NOTES.md) — `topic/02-collections/04-arraylist`
- [ ] **027** · 02.05 [LinkedList](02-collections/chapters/c05_linkedlist/NOTES.md) — `topic/02-collections/05-linkedlist`
- [ ] **028** · 02.06 [Vector & Stack](02-collections/chapters/c06_vector_stack/NOTES.md) — `topic/02-collections/06-vector-stack`
- [ ] **029** · 02.07 [HashSet](02-collections/chapters/c07_hashset/NOTES.md) — `topic/02-collections/07-hashset`
- [ ] **030** · 02.08 [LinkedHashSet](02-collections/chapters/c08_linkedhashset/NOTES.md) — `topic/02-collections/08-linkedhashset`
- [ ] **031** · 02.09 [TreeSet](02-collections/chapters/c09_treeset/NOTES.md) — `topic/02-collections/09-treeset`
- [ ] **032** · 02.10 [EnumSet](02-collections/chapters/c10_enumset/NOTES.md) — `topic/02-collections/10-enumset`
- [ ] **033** · 02.11 [HashMap (deep dive)](02-collections/chapters/c11_hashmap/NOTES.md) — `topic/02-collections/11-hashmap`
- [ ] **034** · 02.12 [LinkedHashMap & LRU](02-collections/chapters/c12_linkedhashmap_lru/NOTES.md) — `topic/02-collections/12-linkedhashmap-lru`
- [ ] **035** · 02.13 [TreeMap](02-collections/chapters/c13_treemap/NOTES.md) — `topic/02-collections/13-treemap`
- [ ] **036** · 02.14 [Special maps](02-collections/chapters/c14_special_maps/NOTES.md) — `topic/02-collections/14-special-maps`
- [ ] **037** · 02.15 [PriorityQueue](02-collections/chapters/c15_priorityqueue/NOTES.md) — `topic/02-collections/15-priorityqueue`
- [ ] **038** · 02.16 [ArrayDeque](02-collections/chapters/c16_arraydeque/NOTES.md) — `topic/02-collections/16-arraydeque`
- [ ] **039** · 02.17 [equals & hashCode](02-collections/chapters/c17_equals_hashcode/NOTES.md) — `topic/02-collections/17-equals-hashcode`
- [ ] **040** · 02.18 [Comparable & Comparator](02-collections/chapters/c18_comparable_comparator/NOTES.md) — `topic/02-collections/18-comparable-comparator`
- [ ] **041** · 02.19 [Iterators & fail-fast](02-collections/chapters/c19_iterators_fail_fast/NOTES.md) — `topic/02-collections/19-iterators-fail-fast`
- [ ] **042** · 02.20 [Immutable collections](02-collections/chapters/c20_immutable_collections/NOTES.md) — `topic/02-collections/20-immutable-collections`
- [ ] **043** · 02.21 [Collections & Arrays](02-collections/chapters/c21_collections_arrays_utils/NOTES.md) — `topic/02-collections/21-collections-arrays-utils`
- [ ] **044** · 02.22 [Concurrent collections](02-collections/chapters/c22_concurrent_collections/NOTES.md) — `topic/02-collections/22-concurrent-collections`

**🏁 Milestone project:** In-memory inventory with Map/Set/PriorityQueue, a hand-written LRU cache, and JUnit tests.

- [ ] Milestone project done (in `projects/phase-03/` on branch `project/phase-03`)

## Phase 4 · Functional Java (8 → 21)

**Goal:** Write idiomatic modern Java with lambdas, streams, Optional and java.time.

- [ ] **045** · 04.01 [Why Java 8 changed everything](04-java8-streams/chapters/c01_why_java8/NOTES.md) — `topic/04-java8-streams/01-why-java8`
- [ ] **046** · 04.02 [Lambda expressions](04-java8-streams/chapters/c02_lambdas/NOTES.md) — `topic/04-java8-streams/02-lambdas`
- [ ] **047** · 04.03 [Functional interfaces](04-java8-streams/chapters/c03_functional_interfaces/NOTES.md) — `topic/04-java8-streams/03-functional-interfaces`
- [ ] **048** · 04.04 [Method references](04-java8-streams/chapters/c04_method_references/NOTES.md) — `topic/04-java8-streams/04-method-references`
- [ ] **049** · 04.05 [Default & static methods](04-java8-streams/chapters/c05_default_static_methods/NOTES.md) — `topic/04-java8-streams/05-default-static-methods`
- [ ] **050** · 04.06 [Optional](04-java8-streams/chapters/c06_optional/NOTES.md) — `topic/04-java8-streams/06-optional`
- [ ] **051** · 04.07 [How streams work](04-java8-streams/chapters/c07_how_streams_work/NOTES.md) — `topic/04-java8-streams/07-how-streams-work`
- [ ] **052** · 04.08 [Creating streams](04-java8-streams/chapters/c08_creating_streams/NOTES.md) — `topic/04-java8-streams/08-creating-streams`
- [ ] **053** · 04.09 [Intermediate operations](04-java8-streams/chapters/c09_intermediate_operations/NOTES.md) — `topic/04-java8-streams/09-intermediate-operations`
- [ ] **054** · 04.10 [Terminal operations](04-java8-streams/chapters/c10_terminal_operations/NOTES.md) — `topic/04-java8-streams/10-terminal-operations`
- [ ] **055** · 04.11 [Collectors](04-java8-streams/chapters/c11_collectors/NOTES.md) — `topic/04-java8-streams/11-collectors`
- [ ] **056** · 04.12 [Primitive streams](04-java8-streams/chapters/c12_primitive_streams/NOTES.md) — `topic/04-java8-streams/12-primitive-streams`
- [ ] **057** · 04.13 [Parallel streams](04-java8-streams/chapters/c13_parallel_streams/NOTES.md) — `topic/04-java8-streams/13-parallel-streams`
- [ ] **058** · 04.14 [18 real-world recipes](04-java8-streams/chapters/c14_stream_recipes/NOTES.md) — `topic/04-java8-streams/14-stream-recipes`
- [ ] **059** · 04.15 [Date & Time API](04-java8-streams/chapters/c15_date_time_api/NOTES.md) — `topic/04-java8-streams/15-date-time-api`
- [ ] **060** · 04.16 [CompletableFuture](04-java8-streams/chapters/c16_completablefuture/NOTES.md) — `topic/04-java8-streams/16-completablefuture`
- [ ] **061** · 04.17 [Other additions](04-java8-streams/chapters/c17_other_additions/NOTES.md) — `topic/04-java8-streams/17-other-additions`
- [ ] **062** · 04.18 [What came after Java 8](04-java8-streams/chapters/c18_after_java8/NOTES.md) — `topic/04-java8-streams/18-after-java8`
- [ ] **063** · 04.19 [Pitfalls & best practices](04-java8-streams/chapters/c19_pitfalls_best_practices/NOTES.md) — `topic/04-java8-streams/19-pitfalls-best-practices`
- [ ] **064** · 02.23 [Streams & Collectors](02-collections/chapters/c23_streams_collectors/NOTES.md) — `topic/02-collections/23-streams-collectors`
- [ ] **065** · 01.18 [Version explorer (Java 8 → 21)](01-oop-exceptions/chapters/c18_java_version_explorer/NOTES.md) — `topic/01-oop-exceptions/18-java-version-explorer`
- [ ] **066** · 01.19 [Same code, new Java](01-oop-exceptions/chapters/c19_same_code_new_java/NOTES.md) — `topic/01-oop-exceptions/19-same-code-new-java`
- [ ] **067** · 01.20 [Java 22 → 25](01-oop-exceptions/chapters/c20_java_22_to_25/NOTES.md) — `topic/01-oop-exceptions/20-java-22-to-25`
- [ ] **068** · 01.21 [Upgrading from 8](01-oop-exceptions/chapters/c21_upgrading_from_8/NOTES.md) — `topic/01-oop-exceptions/21-upgrading-from-8`

**🏁 Milestone project:** Employee-analytics CLI over a CSV file: all 18 stream recipes, with records and java.time.

- [ ] Milestone project done (in `projects/phase-04/` on branch `project/phase-04`)

## Phase 5 · JVM internals & memory

**Goal:** Explain class loading, JIT, heap/stack and GC, and diagnose leaks.

- [ ] **069** · 03.01 [How a Java program runs](03-jvm-memory-strings/chapters/c01_how_java_runs/NOTES.md) — `topic/03-jvm-memory-strings/01-how-java-runs`
- [ ] **070** · 03.02 [JDK vs JRE vs JVM](03-jvm-memory-strings/chapters/c02_jdk_jre_jvm/NOTES.md) — `topic/03-jvm-memory-strings/02-jdk-jre-jvm`
- [ ] **071** · 03.03 [JVM architecture](03-jvm-memory-strings/chapters/c03_jvm_architecture/NOTES.md) — `topic/03-jvm-memory-strings/03-jvm-architecture`
- [ ] **072** · 03.04 [Class loading](03-jvm-memory-strings/chapters/c04_class_loading/NOTES.md) — `topic/03-jvm-memory-strings/04-class-loading`
- [ ] **073** · 03.05 [Interpreter & JIT](03-jvm-memory-strings/chapters/c05_interpreter_jit/NOTES.md) — `topic/03-jvm-memory-strings/05-interpreter-jit`
- [ ] **074** · 03.06 [Stack vs heap](03-jvm-memory-strings/chapters/c06_stack_vs_heap/NOTES.md) — `topic/03-jvm-memory-strings/06-stack-vs-heap`
- [ ] **075** · 03.07 [Garbage collection](03-jvm-memory-strings/chapters/c07_garbage_collection/NOTES.md) — `topic/03-jvm-memory-strings/07-garbage-collection`
- [ ] **076** · 03.08 [GC algorithms](03-jvm-memory-strings/chapters/c08_gc_algorithms/NOTES.md) — `topic/03-jvm-memory-strings/08-gc-algorithms`
- [ ] **077** · 03.09 [References & leaks](03-jvm-memory-strings/chapters/c09_references_leaks/NOTES.md) — `topic/03-jvm-memory-strings/09-references-leaks`
- [ ] **078** · 03.10 [Flags & tools](03-jvm-memory-strings/chapters/c10_jvm_flags_tools/NOTES.md) — `topic/03-jvm-memory-strings/10-jvm-flags-tools`

**🏁 Milestone project:** Reproduce a memory leak, capture a heap dump with jcmd, analyse it, fix it, and compare GC logs.

- [ ] Milestone project done (in `projects/phase-05/` on branch `project/phase-05`)

## Phase 6 · Concurrency & multithreading

**Goal:** Write thread-safe code and use executors and async pipelines correctly.

- [ ] **079** · 05.01 [Why concurrency?](05-concurrency/chapters/c01_why_concurrency/NOTES.md) — `topic/05-concurrency/01-why-concurrency`
- [ ] **080** · 05.02 [Creating threads](05-concurrency/chapters/c02_creating_threads/NOTES.md) — `topic/05-concurrency/02-creating-threads`
- [ ] **081** · 05.03 [Thread lifecycle](05-concurrency/chapters/c03_thread_lifecycle/NOTES.md) — `topic/05-concurrency/03-thread-lifecycle`
- [ ] **082** · 05.04 [sleep, join, interrupt](05-concurrency/chapters/c04_sleep_join_interrupt/NOTES.md) — `topic/05-concurrency/04-sleep-join-interrupt`
- [ ] **083** · 05.05 [Race conditions](05-concurrency/chapters/c05_race_conditions/NOTES.md) — `topic/05-concurrency/05-race-conditions`
- [ ] **084** · 05.06 [synchronized](05-concurrency/chapters/c06_synchronized/NOTES.md) — `topic/05-concurrency/06-synchronized`
- [ ] **085** · 05.07 [volatile & memory model](05-concurrency/chapters/c07_volatile_memory_model/NOTES.md) — `topic/05-concurrency/07-volatile-memory-model`
- [ ] **086** · 05.08 [Producer–consumer](05-concurrency/chapters/c08_producer_consumer/NOTES.md) — `topic/05-concurrency/08-producer-consumer`
- [ ] **087** · 05.09 [Deadlock](05-concurrency/chapters/c09_deadlock/NOTES.md) — `topic/05-concurrency/09-deadlock`
- [ ] **088** · 05.10 [Explicit locks](05-concurrency/chapters/c10_explicit_locks/NOTES.md) — `topic/05-concurrency/10-explicit-locks`
- [ ] **089** · 05.11 [Atomics & CAS](05-concurrency/chapters/c11_atomics_cas/NOTES.md) — `topic/05-concurrency/11-atomics-cas`
- [ ] **090** · 05.12 [ExecutorService](05-concurrency/chapters/c12_executorservice/NOTES.md) — `topic/05-concurrency/12-executorservice`
- [ ] **091** · 05.13 [ThreadPoolExecutor](05-concurrency/chapters/c13_threadpoolexecutor/NOTES.md) — `topic/05-concurrency/13-threadpoolexecutor`
- [ ] **092** · 05.14 [Callable & Future](05-concurrency/chapters/c14_callable_future/NOTES.md) — `topic/05-concurrency/14-callable-future`
- [ ] **093** · 05.15 [CompletableFuture](05-concurrency/chapters/c15_completablefuture/NOTES.md) — `topic/05-concurrency/15-completablefuture`
- [ ] **094** · 05.16 [Latch, barrier, semaphore](05-concurrency/chapters/c16_latch_barrier_semaphore/NOTES.md) — `topic/05-concurrency/16-latch-barrier-semaphore`
- [ ] **095** · 05.17 [ThreadLocal](05-concurrency/chapters/c17_threadlocal/NOTES.md) — `topic/05-concurrency/17-threadlocal`
- [ ] **096** · 05.18 [Fork/Join](05-concurrency/chapters/c18_fork_join/NOTES.md) — `topic/05-concurrency/18-fork-join`
- [ ] **097** · 05.19 [Virtual threads (needs JDK 21)](05-concurrency/chapters/c19_virtual_threads/NOTES.md) — `topic/05-concurrency/19-virtual-threads`
- [ ] **098** · 05.20 [Best practices & debugging](05-concurrency/chapters/c20_best_practices_debugging/NOTES.md) — `topic/05-concurrency/20-best-practices-debugging`

**🏁 Milestone project:** Parallel order processor using ExecutorService + CompletableFuture. Reproduce and fix a race and a deadlock.

- [ ] Milestone project done (in `projects/phase-06/` on branch `project/phase-06`)

## Phase 7 · Design principles & patterns

**Goal:** Apply SOLID and GoF patterns where they help, and recognise them in Spring.

- [ ] **099** · 06.01 [Foundations: the four OOP ideas](06-design-patterns/chapters/foundations/c01_oop_foundations/NOTES.md) — `topic/06-design-patterns/01-oop-foundations`
- [ ] **100** · 06.02 [Single Responsibility Principle](06-design-patterns/chapters/solid/c02_srp/NOTES.md) — `topic/06-design-patterns/02-srp`
- [ ] **101** · 06.03 [Open / Closed Principle](06-design-patterns/chapters/solid/c03_ocp/NOTES.md) — `topic/06-design-patterns/03-ocp`
- [ ] **102** · 06.04 [Liskov Substitution Principle](06-design-patterns/chapters/solid/c04_lsp/NOTES.md) — `topic/06-design-patterns/04-lsp`
- [ ] **103** · 06.05 [Interface Segregation Principle](06-design-patterns/chapters/solid/c05_isp/NOTES.md) — `topic/06-design-patterns/05-isp`
- [ ] **104** · 06.06 [Dependency Inversion Principle](06-design-patterns/chapters/solid/c06_dip/NOTES.md) — `topic/06-design-patterns/06-dip`
- [ ] **105** · 06.07 [Singleton](06-design-patterns/chapters/creational/c07_singleton/NOTES.md) — `topic/06-design-patterns/07-singleton`
- [ ] **106** · 06.08 [Factory Method](06-design-patterns/chapters/creational/c08_factory_method/NOTES.md) — `topic/06-design-patterns/08-factory-method`
- [ ] **107** · 06.09 [Abstract Factory](06-design-patterns/chapters/creational/c09_abstract_factory/NOTES.md) — `topic/06-design-patterns/09-abstract-factory`
- [ ] **108** · 06.10 [Builder](06-design-patterns/chapters/creational/c10_builder/NOTES.md) — `topic/06-design-patterns/10-builder`
- [ ] **109** · 06.11 [Prototype](06-design-patterns/chapters/creational/c11_prototype/NOTES.md) — `topic/06-design-patterns/11-prototype`
- [ ] **110** · 06.12 [Adapter](06-design-patterns/chapters/structural/c12_adapter/NOTES.md) — `topic/06-design-patterns/12-adapter`
- [ ] **111** · 06.13 [Bridge](06-design-patterns/chapters/structural/c13_bridge/NOTES.md) — `topic/06-design-patterns/13-bridge`
- [ ] **112** · 06.14 [Composite](06-design-patterns/chapters/structural/c14_composite/NOTES.md) — `topic/06-design-patterns/14-composite`
- [ ] **113** · 06.15 [Decorator](06-design-patterns/chapters/structural/c15_decorator/NOTES.md) — `topic/06-design-patterns/15-decorator`
- [ ] **114** · 06.16 [Facade](06-design-patterns/chapters/structural/c16_facade/NOTES.md) — `topic/06-design-patterns/16-facade`
- [ ] **115** · 06.17 [Flyweight](06-design-patterns/chapters/structural/c17_flyweight/NOTES.md) — `topic/06-design-patterns/17-flyweight`
- [ ] **116** · 06.18 [Proxy](06-design-patterns/chapters/structural/c18_proxy/NOTES.md) — `topic/06-design-patterns/18-proxy`
- [ ] **117** · 06.19 [Chain of Responsibility](06-design-patterns/chapters/behavioral/c19_chain_of_responsibility/NOTES.md) — `topic/06-design-patterns/19-chain-of-responsibility`
- [ ] **118** · 06.20 [Command](06-design-patterns/chapters/behavioral/c20_command/NOTES.md) — `topic/06-design-patterns/20-command`
- [ ] **119** · 06.21 [Interpreter](06-design-patterns/chapters/behavioral/c21_interpreter/NOTES.md) — `topic/06-design-patterns/21-interpreter`
- [ ] **120** · 06.22 [Iterator](06-design-patterns/chapters/behavioral/c22_iterator/NOTES.md) — `topic/06-design-patterns/22-iterator`
- [ ] **121** · 06.23 [Mediator](06-design-patterns/chapters/behavioral/c23_mediator/NOTES.md) — `topic/06-design-patterns/23-mediator`
- [ ] **122** · 06.24 [Memento](06-design-patterns/chapters/behavioral/c24_memento/NOTES.md) — `topic/06-design-patterns/24-memento`
- [ ] **123** · 06.25 [Observer](06-design-patterns/chapters/behavioral/c25_observer/NOTES.md) — `topic/06-design-patterns/25-observer`
- [ ] **124** · 06.26 [State](06-design-patterns/chapters/behavioral/c26_state/NOTES.md) — `topic/06-design-patterns/26-state`
- [ ] **125** · 06.27 [Strategy](06-design-patterns/chapters/behavioral/c27_strategy/NOTES.md) — `topic/06-design-patterns/27-strategy`
- [ ] **126** · 06.28 [Template Method](06-design-patterns/chapters/behavioral/c28_template_method/NOTES.md) — `topic/06-design-patterns/28-template-method`
- [ ] **127** · 06.29 [Visitor](06-design-patterns/chapters/behavioral/c29_visitor/NOTES.md) — `topic/06-design-patterns/29-visitor`
- [ ] **128** · 06.30 [Look-alike patterns, told apart](06-design-patterns/chapters/applied/c30_look_alike_patterns/NOTES.md) — `topic/06-design-patterns/30-look-alike-patterns`
- [ ] **129** · 06.31 [One order, a dozen patterns: Spring Boot walkthrough](06-design-patterns/chapters/applied/c31_spring_boot_case_study/NOTES.md) — `topic/06-design-patterns/31-spring-boot-case-study`
- [ ] **130** · 06.32 [Anti-patterns and common mistakes](06-design-patterns/chapters/applied/c32_anti_patterns/NOTES.md) — `topic/06-design-patterns/32-anti-patterns`

**🏁 Milestone project:** Refactor earlier projects with SOLID, then build a payment flow using Strategy, Factory, Observer and Decorator.

- [ ] Milestone project done (in `projects/phase-07/` on branch `project/phase-07`)

## Phase 8 · Databases: SQL → JDBC → JPA

**Goal:** Write efficient SQL, understand transactions and indexes, and use JPA without N+1.

- [ ] **131** · 07.01 [Why SQL matters & the sample database](07-sql/chapters/c01_why_sql_sample_db/NOTES.md) — `topic/07-sql/01-why-sql-sample-db`
- [ ] **132** · 07.02 [SELECT basics](07-sql/chapters/c02_select_basics/NOTES.md) — `topic/07-sql/02-select-basics`
- [ ] **133** · 07.03 [Execution order](07-sql/chapters/c03_execution_order/NOTES.md) — `topic/07-sql/03-execution-order`
- [ ] **134** · 07.04 [NULL](07-sql/chapters/c04_null_handling/NOTES.md) — `topic/07-sql/04-null-handling`
- [ ] **135** · 07.05 [GROUP BY & HAVING](07-sql/chapters/c05_group_by_having/NOTES.md) — `topic/07-sql/05-group-by-having`
- [ ] **136** · 07.06 [JOINs](07-sql/chapters/c06_joins/NOTES.md) — `topic/07-sql/06-joins`
- [ ] **137** · 07.07 [Subqueries & CTEs](07-sql/chapters/c07_subqueries_ctes/NOTES.md) — `topic/07-sql/07-subqueries-ctes`
- [ ] **138** · 07.08 [Window functions](07-sql/chapters/c08_window_functions/NOTES.md) — `topic/07-sql/08-window-functions`
- [ ] **139** · 07.09 [CASE, functions, UNION](07-sql/chapters/c09_case_functions_union/NOTES.md) — `topic/07-sql/09-case-functions-union`
- [ ] **140** · 07.10 [DDL, constraints, DML](07-sql/chapters/c10_ddl_constraints_dml/NOTES.md) — `topic/07-sql/10-ddl-constraints-dml`
- [ ] **141** · 07.11 [Keys & normalization](07-sql/chapters/c11_keys_normalization/NOTES.md) — `topic/07-sql/11-keys-normalization`
- [ ] **142** · 07.12 [Views & triggers](07-sql/chapters/c12_views_triggers/NOTES.md) — `topic/07-sql/12-views-triggers`
- [ ] **143** · 07.13 [Indexes](07-sql/chapters/c13_indexes/NOTES.md) — `topic/07-sql/13-indexes`
- [ ] **144** · 07.14 [Transactions & ACID](07-sql/chapters/c14_transactions_acid/NOTES.md) — `topic/07-sql/14-transactions-acid`
- [ ] **145** · 07.15 [Isolation & locking](07-sql/chapters/c15_isolation_locking/NOTES.md) — `topic/07-sql/15-isolation-locking`
- [ ] **146** · 07.16 [Query performance](07-sql/chapters/c16_query_performance/NOTES.md) — `topic/07-sql/16-query-performance`
- [ ] **147** · 07.17 [JDBC](07-sql/chapters/c17_jdbc/NOTES.md) — `topic/07-sql/17-jdbc`
- [ ] **148** · 07.18 [SQL injection](07-sql/chapters/c18_sql_injection/NOTES.md) — `topic/07-sql/18-sql-injection`
- [ ] **149** · 07.19 [Transactions, batching, pools](07-sql/chapters/c19_jdbc_tx_batching_pools/NOTES.md) — `topic/07-sql/19-jdbc-tx-batching-pools`
- [ ] **150** · 07.20 [JPA & Hibernate](07-sql/chapters/c20_jpa_hibernate/NOTES.md) — `topic/07-sql/20-jpa-hibernate`
- [ ] **151** · 07.21 [The N+1 problem](07-sql/chapters/c21_n_plus_1/NOTES.md) — `topic/07-sql/21-n-plus-1`
- [ ] **152** · 07.22 [Spring Data JPA](07-sql/chapters/c22_spring_data_jpa/NOTES.md) — `topic/07-sql/22-spring-data-jpa`
- [ ] **153** · 07.23 [@Transactional & locking](07-sql/chapters/c23_transactional_locking/NOTES.md) — `topic/07-sql/23-transactional-locking`
- [ ] **154** · 07.24 [27 coding questions](07-sql/chapters/c24_coding_questions/NOTES.md) — `topic/07-sql/24-coding-questions`

**🏁 Milestone project:** Orders schema: SQL design, then a JDBC DAO, then a Spring Data JPA repository. Solve all 27 coding questions.

- [ ] Milestone project done (in `projects/phase-08/` on branch `project/phase-08`)

## Phase 9 · Spring Boot & microservices

**Goal:** Build, secure, test and operate production-grade Spring Boot services.

- [ ] **155** · 08.01 [Spring vs Spring Boot](08-spring-boot/chapters/c01_spring_vs_boot/NOTES.md) — `topic/08-spring-boot/01-spring-vs-boot`
- [ ] **156** · 08.02 [How Boot starts](08-spring-boot/chapters/c02_how_boot_starts/NOTES.md) — `topic/08-spring-boot/02-how-boot-starts`
- [ ] **157** · 08.03 [@Component, @Service…](08-spring-boot/chapters/c03_stereotypes/NOTES.md) — `topic/08-spring-boot/03-stereotypes`
- [ ] **158** · 08.04 [Dependency injection](08-spring-boot/chapters/c04_dependency_injection/NOTES.md) — `topic/08-spring-boot/04-dependency-injection`
- [ ] **159** · 08.05 [Scopes & lifecycle](08-spring-boot/chapters/c05_scopes_lifecycle/NOTES.md) — `topic/08-spring-boot/05-scopes-lifecycle`
- [ ] **160** · 08.06 [@Conditional & auto-config](08-spring-boot/chapters/c06_conditional_autoconfig/NOTES.md) — `topic/08-spring-boot/06-conditional-autoconfig`
- [ ] **161** · 08.07 [Configuration & profiles](08-spring-boot/chapters/c07_configuration_profiles/NOTES.md) — `topic/08-spring-boot/07-configuration-profiles`
- [ ] **162** · 08.08 [REST APIs](08-spring-boot/chapters/c08_rest_apis/NOTES.md) — `topic/08-spring-boot/08-rest-apis`
- [ ] **163** · 08.09 [Validation & errors](08-spring-boot/chapters/c09_validation_errors/NOTES.md) — `topic/08-spring-boot/09-validation-errors`
- [ ] **164** · 08.10 [Actuator](08-spring-boot/chapters/c10_actuator/NOTES.md) — `topic/08-spring-boot/10-actuator`
- [ ] **165** · 08.11 [Large data](08-spring-boot/chapters/c11_large_data/NOTES.md) — `topic/08-spring-boot/11-large-data`
- [ ] **166** · 08.12 [Architecture & patterns](08-spring-boot/chapters/c12_microservice_architecture/NOTES.md) — `topic/08-spring-boot/12-microservice-architecture`
- [ ] **167** · 08.13 [Circuit breaker](08-spring-boot/chapters/c13_circuit_breaker/NOTES.md) — `topic/08-spring-boot/13-circuit-breaker`
- [ ] **168** · 08.14 [Gateway, discovery, config](08-spring-boot/chapters/c14_gateway_discovery_config/NOTES.md) — `topic/08-spring-boot/14-gateway-discovery-config`
- [ ] **169** · 08.15 [Saga & outbox](08-spring-boot/chapters/c15_saga_outbox/NOTES.md) — `topic/08-spring-boot/15-saga-outbox`
- [ ] **170** · 08.16 [Logging & tracing](08-spring-boot/chapters/c16_logging_tracing/NOTES.md) — `topic/08-spring-boot/16-logging-tracing`
- [ ] **171** · 08.17 [Security](08-spring-boot/chapters/c17_security/NOTES.md) — `topic/08-spring-boot/17-security`
- [ ] **172** · 08.18 [Caching](08-spring-boot/chapters/c18_caching/NOTES.md) — `topic/08-spring-boot/18-caching`
- [ ] **173** · 08.19 [@Async & scheduling](08-spring-boot/chapters/c19_async_scheduling/NOTES.md) — `topic/08-spring-boot/19-async-scheduling`
- [ ] **174** · 08.20 [Testing](08-spring-boot/chapters/c20_testing/NOTES.md) — `topic/08-spring-boot/20-testing`
- [ ] **175** · 08.21 [Deployment & Boot 3/4](08-spring-boot/chapters/c21_deployment_boot3_4/NOTES.md) — `topic/08-spring-boot/21-deployment-boot3-4`
- [ ] **176** · 08.22 [30 scenario questions](08-spring-boot/chapters/c22_scenario_questions/NOTES.md) — `topic/08-spring-boot/22-scenario-questions`

**🏁 Milestone project:** Order-service REST API (validation, errors, security, caching, Actuator, tests) plus a 2nd service behind a circuit breaker.

- [ ] Milestone project done (in `projects/phase-09/` on branch `project/phase-09`)

## Phase 10 · Containers, Kubernetes & CI/CD

**Goal:** Ship the service: image, cluster, probes, scaling and a pipeline.

- [ ] **177** · 09.01 [Code to production](09-docker-kubernetes/chapters/c01_code_to_production/NOTES.md) — `topic/09-docker-kubernetes/01-code-to-production`
- [ ] **178** · 09.02 [Building the JAR](09-docker-kubernetes/chapters/c02_building_the_jar/NOTES.md) — `topic/09-docker-kubernetes/02-building-the-jar`
- [ ] **179** · 09.03 [Inside the fat JAR](09-docker-kubernetes/chapters/c03_inside_fat_jar/NOTES.md) — `topic/09-docker-kubernetes/03-inside-fat-jar`
- [ ] **180** · 09.04 [Running it · JAR vs WAR](09-docker-kubernetes/chapters/c04_jar_vs_war/NOTES.md) — `topic/09-docker-kubernetes/04-jar-vs-war`
- [ ] **181** · 09.05 [Layers, CDS, native](09-docker-kubernetes/chapters/c05_layers_cds_native/NOTES.md) — `topic/09-docker-kubernetes/05-layers-cds-native`
- [ ] **182** · 09.06 [Why containers](09-docker-kubernetes/chapters/c06_why_containers/NOTES.md) — `topic/09-docker-kubernetes/06-why-containers`
- [ ] **183** · 09.07 [Writing a Dockerfile](09-docker-kubernetes/chapters/c07_writing_dockerfile/NOTES.md) — `topic/09-docker-kubernetes/07-writing-dockerfile`
- [ ] **184** · 09.08 [Layers & build cache](09-docker-kubernetes/chapters/c08_build_cache/NOTES.md) — `topic/09-docker-kubernetes/08-build-cache`
- [ ] **185** · 09.09 [Base images & buildpacks](09-docker-kubernetes/chapters/c09_base_images_buildpacks/NOTES.md) — `topic/09-docker-kubernetes/09-base-images-buildpacks`
- [ ] **186** · 09.10 [The JVM in a container](09-docker-kubernetes/chapters/c10_jvm_in_container/NOTES.md) — `topic/09-docker-kubernetes/10-jvm-in-container`
- [ ] **187** · 09.11 [CLI, networks, volumes](09-docker-kubernetes/chapters/c11_cli_networks_volumes/NOTES.md) — `topic/09-docker-kubernetes/11-cli-networks-volumes`
- [ ] **188** · 09.12 [Docker Compose](09-docker-kubernetes/chapters/c12_docker_compose/NOTES.md) — `topic/09-docker-kubernetes/12-docker-compose`
- [ ] **189** · 09.13 [Image security](09-docker-kubernetes/chapters/c13_image_security/NOTES.md) — `topic/09-docker-kubernetes/13-image-security`
- [ ] **190** · 09.14 [Why Kubernetes & architecture](09-docker-kubernetes/chapters/c14_k8s_architecture/NOTES.md) — `topic/09-docker-kubernetes/14-k8s-architecture`
- [ ] **191** · 09.15 [Core objects](09-docker-kubernetes/chapters/c15_k8s_core_objects/NOTES.md) — `topic/09-docker-kubernetes/15-k8s-core-objects`
- [ ] **192** · 09.16 [Deploying Spring Boot](09-docker-kubernetes/chapters/c16_deploying_spring_boot/NOTES.md) — `topic/09-docker-kubernetes/16-deploying-spring-boot`
- [ ] **193** · 09.17 [Health probes & shutdown](09-docker-kubernetes/chapters/c17_probes_shutdown/NOTES.md) — `topic/09-docker-kubernetes/17-probes-shutdown`
- [ ] **194** · 09.18 [Requests, limits, QoS](09-docker-kubernetes/chapters/c18_requests_limits_qos/NOTES.md) — `topic/09-docker-kubernetes/18-requests-limits-qos`
- [ ] **195** · 09.19 [Rolling updates](09-docker-kubernetes/chapters/c19_rolling_updates/NOTES.md) — `topic/09-docker-kubernetes/19-rolling-updates`
- [ ] **196** · 09.20 [Autoscaling](09-docker-kubernetes/chapters/c20_autoscaling/NOTES.md) — `topic/09-docker-kubernetes/20-autoscaling`
- [ ] **197** · 09.21 [Ingress & Gateway API](09-docker-kubernetes/chapters/c21_ingress_gateway_api/NOTES.md) — `topic/09-docker-kubernetes/21-ingress-gateway-api`
- [ ] **198** · 09.22 [Troubleshooting pods](09-docker-kubernetes/chapters/c22_troubleshooting_pods/NOTES.md) — `topic/09-docker-kubernetes/22-troubleshooting-pods`
- [ ] **199** · 09.23 [Helm & Kustomize](09-docker-kubernetes/chapters/c23_helm_kustomize/NOTES.md) — `topic/09-docker-kubernetes/23-helm-kustomize`
- [ ] **200** · 09.24 [CI/CD & GitOps](09-docker-kubernetes/chapters/c24_cicd_gitops/NOTES.md) — `topic/09-docker-kubernetes/24-cicd-gitops`
- [ ] **201** · 09.25 [25 scenario questions](09-docker-kubernetes/chapters/c25_scenario_questions/NOTES.md) — `topic/09-docker-kubernetes/25-scenario-questions`

**🏁 Milestone project:** Containerise the order service, run it with Postgres in Compose, deploy to kind/minikube with probes, HPA and rolling updates, add GitHub Actions CI.

- [ ] Milestone project done (in `projects/phase-10/` on branch `project/phase-10`)

---

## 🔁 Revision checklist (before interviews)

- **OOP, Exceptions & Modern Java:** [ ] [LTS cheat sheet](01-oop-exceptions/review/lts-cheat-sheet.md) · [ ] [Interview Q&A](01-oop-exceptions/review/interview-qa.md) · [ ] [Quiz](01-oop-exceptions/review/quiz.md)
- **Java Collections Handbook:** [ ] [Big-O table](02-collections/review/big-o-table.md) · [ ] [Which collection?](02-collections/review/which-collection.md) · [ ] [Surprising facts](02-collections/review/facts.md) · [ ] [Interview Q&A](02-collections/review/interview-qa.md) · [ ] [Quiz](02-collections/review/quiz.md)
- **JVM, Memory & Strings:** [ ] [Surprising facts](03-jvm-memory-strings/review/facts.md) · [ ] [Interview Q&A](03-jvm-memory-strings/review/interview-qa.md) · [ ] [Quiz](03-jvm-memory-strings/review/quiz.md)
- **Java 8 & Streams Handbook:** [ ] [Operations cheat sheet](04-java8-streams/review/operations-cheat-sheet.md) · [ ] [Interview Q&A](04-java8-streams/review/interview-qa.md) · [ ] [Quiz](04-java8-streams/review/quiz.md)
- **Java Concurrency Handbook:** [ ] [Surprising facts](05-concurrency/review/facts.md) · [ ] [Interview Q&A](05-concurrency/review/interview-qa.md) · [ ] [Quiz](05-concurrency/review/quiz.md)
- **Java Patterns Handbook:** [ ] [Which pattern do I need?](06-design-patterns/review/which-pattern.md) · [ ] [Cheat sheet](06-design-patterns/review/cheat-sheet.md) · [ ] [Spot the pattern (quiz)](06-design-patterns/review/quiz.md)
- **SQL for Java Developers:** [ ] [Dialect cheat sheet](07-sql/review/dialect-cheat-sheet.md) · [ ] [Theory Q&A](07-sql/review/theory-qa.md) · [ ] [Quiz](07-sql/review/quiz.md)
- **Spring Boot Interview Handbook:** [ ] [Rapid-fire Q&A](08-spring-boot/review/rapid-fire-qa.md) · [ ] [Readiness checklist](08-spring-boot/review/readiness-checklist.md) · [ ] [Quiz](08-spring-boot/review/quiz.md)
- **Docker & Kubernetes for Java:** [ ] [kubectl cheat sheet](09-docker-kubernetes/review/kubectl-cheat-sheet.md) · [ ] [Interview Q&A](09-docker-kubernetes/review/interview-qa.md) · [ ] [Quiz](09-docker-kubernetes/review/quiz.md)
