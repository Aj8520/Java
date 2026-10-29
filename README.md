# Java Backend Interview Handbook — Study & Practice Repo

A hands-on study workspace for the **Java Backend Interview Handbook** (9 handbooks).
Every chapter has **its own folder** (notes + runnable code) and **its own git branch** so you can learn one
topic at a time, practise it, and merge it into `master` when you finish.

- 📘 Handbook (online, with demos and quizzes): https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A
- 📄 Index PDF: [`Java Handbook.pdf`](Java%20Handbook.pdf)
- 🌿 **201 topic branches** · 9 Maven modules · Java 17+ (21 recommended)

---

## 1. Handbooks at a glance

| # | Handbook | Folder | Chapters / branches | Branch prefix |
|---|---|---|---|---|
| 01 | [OOP, Exceptions & Modern Java](01-oop-exceptions/) | `01-oop-exceptions/` | 21 | `topic/01-oop-exceptions/*` |
| 02 | [Java Collections Handbook](02-collections/) | `02-collections/` | 24 | `topic/02-collections/*` |
| 03 | [JVM, Memory & Strings](03-jvm-memory-strings/) | `03-jvm-memory-strings/` | 14 | `topic/03-jvm-memory-strings/*` |
| 04 | [Java 8 & Streams Handbook](04-java8-streams/) | `04-java8-streams/` | 19 | `topic/04-java8-streams/*` |
| 05 | [Java Concurrency Handbook](05-concurrency/) | `05-concurrency/` | 20 | `topic/05-concurrency/*` |
| 06 | [Java Patterns Handbook](06-design-patterns/) | `06-design-patterns/` | 32 | `topic/06-design-patterns/*` |
| 07 | [SQL for Java Developers](07-sql/) | `07-sql/` | 24 | `topic/07-sql/*` |
| 08 | [Spring Boot Interview Handbook](08-spring-boot/) | `08-spring-boot/` | 22 | `topic/08-spring-boot/*` |
| 09 | [Docker & Kubernetes for Java](09-docker-kubernetes/) | `09-docker-kubernetes/` | 25 | `topic/09-docker-kubernetes/*` |

---

## 2. Setup

| Tool | Version |
|---|---|
| JDK | 17+ (**21 recommended**: needed for virtual threads and record patterns) |
| Maven | 3.9+ |
| IDE | IntelliJ IDEA (open the root `pom.xml` as a project) |
| Docker / kubectl | only for module 09 |

```bash
git clone https://github.com/Aj8520/Java.git && cd Java
mvn -q compile                       # compile every module
mvn -q -pl 02-collections compile    # compile one handbook
# run one chapter's practice class
mvn -q -pl 02-collections compile exec:java -Dexec.mainClass=c11_hashmap.Practice
```

To move to Java 21, install JDK 21 and change `<maven.compiler.release>` in the root [`pom.xml`](pom.xml) to `21`.

---

## 3. Folder structure

```
Java/
├── README.md                    ← you are here (flows, branching, chapter index)
├── CHECKLIST.md                 ← progress tracker in industry-standard study order
├── CLAUDE.md                    ← conventions for Claude Code (AI study assistant)
├── progress.sh                  ← prints progress and the next chapter to study
├── pom.xml                      ← parent POM (Java version, JUnit 5, Spring Boot BOM)
├── Java Handbook.pdf
├── 01-oop-exceptions/
├── 02-collections/
│   ├── pom.xml
│   ├── README.md                ← chapter list for this handbook
│   ├── chapters/                ← Maven source dir: notes + code live together
│   │   ├── c01_why_collections/
│   │   │   ├── NOTES.md         ← your study notes (template inside)
│   │   │   └── Practice.java    ← your practice code (package c01_why_collections)
│   │   ├── c11_hashmap/
│   │   └── ...
│   ├── tests/                   ← optional JUnit tests
│   └── review/                  ← interview Q&A, quiz, facts, cheat sheets (no branch)
├── 03-jvm-memory-strings/
├── 04-java8-streams/
├── 05-concurrency/
├── 06-design-patterns/          ← chapters/ grouped: foundations, solid, creational, structural, behavioral, applied
├── 07-sql/                      ← + practice.sql per chapter, H2 driver available
├── 08-spring-boot/              ← Spring Boot starter-web/test available
└── 09-docker-kubernetes/        ← + Dockerfile / compose.yaml / k8s/ stubs where relevant
```

| File | What you put in it |
|---|---|
| `NOTES.md` | Key concepts, explanation in your own words, interview Q&A, gotchas, done-checklist |
| `Practice.java` | Code from the chapter + your own experiments (4 TODOs to guide you) |
| `practice.sql` | (SQL only) queries for the chapter |
| `review/*.md` | Quiz answers, interview Q&A, cheat sheets — revise before interviews |

> Package names start with `cNN_` because Java packages cannot start with a digit.

---

## 4. Branching model

```
master ──●──────────────●──────────────●──────────────●──▶   (stable: scaffold + everything you finished)
          \            / \            /  \            /
           ● topic/01-oop-exceptions/01-classes-objects
                        ● topic/01-oop-exceptions/02-access-modifiers
                                        ● topic/02-collections/11-hashmap  …
```

| Branch | Purpose |
|---|---|
| `master` | Main branch. Full folder structure + this README. Only finished, working topics are merged here. |
| `topic/<NN-handbook>/<NN-chapter>` | One branch per chapter, e.g. `topic/02-collections/11-hashmap`. Study + practise only that chapter here. |

**Rules**

1. Never study directly on `master`; always use the chapter's topic branch.
2. One branch = one chapter. Change only that chapter's folder (and optionally that handbook's `review/` files).
3. Before starting a branch, merge the latest `master` into it so earlier work is available.
4. Merge back into `master` with `--no-ff` (or a GitHub Pull Request) so each topic shows up as one merge in history.
5. In the same merge, tick the chapter's box in [`CHECKLIST.md`](CHECKLIST.md). It is the single progress tracker.
6. Branch numbers follow the handbook's own chapter order. Handbook numbers follow the recommended learning order.

---

## 5. Daily study flow

```bash
# 1. Start from an up-to-date master
git checkout master
git pull

# 2. Switch to the chapter's branch and bring in the latest master
git checkout topic/02-collections/11-hashmap
git merge master

# 3. Study  → open the handbook chapter (link at the top of NOTES.md)
#    Write → 02-collections/chapters/c11_hashmap/NOTES.md
#    Code  → 02-collections/chapters/c11_hashmap/Practice.java
mvn -q -pl 02-collections compile exec:java -Dexec.mainClass=c11_hashmap.Practice

# 4. Commit (small commits are fine)
git add 02-collections/chapters/c11_hashmap
git commit -m "study(collections/11): hashmap buckets, resize and treeify"
git push -u origin topic/02-collections/11-hashmap

# 5. Finished? Merge into master (or open a PR on GitHub)
git checkout master
git merge --no-ff topic/02-collections/11-hashmap -m "merge: collections/11 hashmap done"
#    tick the chapter in CHECKLIST.md ( - [ ]  →  - [x] ), then:
git commit -am "progress: collections/11 done"
./progress.sh                        # see overall progress
git push
```

**Commit message style:** `study(<handbook>/<NN>): <what you learned>` · `practice(<handbook>/<NN>): <exercise>` · `docs: ...`

---

## 6. Handy git commands

| Task | Command |
|---|---|
| List all topic branches of a handbook | `git branch --list 'topic/02-collections/*'` |
| List remote topic branches | `git branch -r --list 'origin/topic/05-*'` |
| Topics in progress (commits not yet merged into master) | `git branch --no-merged master --list 'topic/*'` |
| Overall progress + next step | `./progress.sh` |
| Which branch am I on? | `git branch --show-current` |
| See what I changed on this topic | `git diff master...HEAD --stat` |
| Park unfinished work and switch | `git stash` → `git checkout <other>` → later `git stash pop` |
| Fetch branches on a new machine | `git fetch --all` then `git checkout topic/...` (auto-tracks origin) |

---

## 7. Learning paths (from the handbook)

**Fresher · 0–2 years** — get the core language rock solid
1. OOP pillars → exceptions (01) 2. Collections: ArrayList, HashMap, equals/hashCode (02)
3. String, StringBuilder, String pool (03) 4. Lambdas and streams (04)
5. SQL basics → easy coding questions (07) 6. Spring Boot basics, DI, REST (08) 7. JAR → Dockerfile → Compose (09)

**Mid-level · 3–5 years** — show you understand what happens underneath
1. HashMap internals, concurrent collections (02) 2. Thread safety, thread pools, CompletableFuture (05)
3. Stack vs heap, GC (03) 4. SOLID + 10 most-used patterns (06) 5. Window functions, indexes, N+1 (07)
6. Auto-config, Actuator, circuit breaker (08) 7. JVM in containers, probes, rolling updates (09)

**Senior · 5–8 years** — design, trade-offs, production experience
1. 30 Spring Boot scenarios + readiness checklist (08) 2. Saga & outbox, large data (08)
3. Isolation levels & locking, hard SQL (07) 4. Memory model, virtual threads (05)
5. GC algorithms, leaks, tuning (03) 6. Look-alike patterns, Java 8 → 21 (06, 01) 7. 25 Docker/K8s scenarios, HPA, GitOps (09)

### 5-week plan (≈1.5–2 h/day)

| Week | Mon–Tue | Wed–Thu | Fri–Sat | Sun |
|---|---|---|---|---|
| 1 · Core Java | 01 OOP & exceptions | 02 Collections | 03 JVM, memory & strings | 01 Java 8 → 21 + quizzes |
| 2 · Modern & concurrent | 04 Lambdas & streams | 04 Stream recipes | 05 Concurrency | Concurrency review + quiz |
| 3 · Data & design | 07 SQL fundamentals | 07 27 coding questions | 06 SOLID & patterns | Patterns quiz + redo SQL |
| 4 · Spring Boot | 08 Core, DI, auto-config | 08 REST, data, Actuator | 08 Microservices, security | 08 Scenario mock interview |
| 5 · Deployment | 09 JAR, Dockerfile, layers | 09 JVM in containers, Compose | 09 Kubernetes, probes, HPA | 09 Troubleshooting + scenarios |

---

## 8. Progress tracking

| File | Purpose |
|---|---|
| [`CHECKLIST.md`](CHECKLIST.md) | **Industry-standard study sequence** (10 phases, every chapter numbered 001…201) with checkboxes and phase milestones |
| [`progress.sh`](progress.sh) | `./progress.sh` prints progress per handbook from git (merged branches) and per phase from CHECKLIST.md |
| [`CLAUDE.md`](CLAUDE.md) | Conventions for Claude Code: structure, branch rules, how to update progress, how to coach |
| `chapters/*/NOTES.md` | Per-chapter "Done checklist" |

## 9. Chapter index (handbook order)

The study order is in [`CHECKLIST.md`](CHECKLIST.md). This section lists the chapters in the handbook's own order.

### 01 · OOP, Exceptions & Modern Java — [`01-oop-exceptions/`](01-oop-exceptions/)

The four pillars, overloading vs overriding, abstract classes vs interfaces, exception handling end to end, and every major change from Java 8 to 21 (plus 22-25).

| # | Chapter | Branch |
|---|---|---|
| 01 | [Classes & objects](01-oop-exceptions/chapters/c01_classes_objects/NOTES.md) | `topic/01-oop-exceptions/01-classes-objects` |
| 02 | [Access modifiers](01-oop-exceptions/chapters/c02_access_modifiers/NOTES.md) | `topic/01-oop-exceptions/02-access-modifiers` |
| 03 | [Encapsulation](01-oop-exceptions/chapters/c03_encapsulation/NOTES.md) | `topic/01-oop-exceptions/03-encapsulation` |
| 04 | [Inheritance](01-oop-exceptions/chapters/c04_inheritance/NOTES.md) | `topic/01-oop-exceptions/04-inheritance` |
| 05 | [Polymorphism](01-oop-exceptions/chapters/c05_polymorphism/NOTES.md) | `topic/01-oop-exceptions/05-polymorphism` |
| 06 | [Abstraction](01-oop-exceptions/chapters/c06_abstraction/NOTES.md) | `topic/01-oop-exceptions/06-abstraction` |
| 07 | [final, static, Object](01-oop-exceptions/chapters/c07_final_static_object/NOTES.md) | `topic/01-oop-exceptions/07-final-static-object` |
| 08 | [Composition & casting](01-oop-exceptions/chapters/c08_composition_casting/NOTES.md) | `topic/01-oop-exceptions/08-composition-casting` |
| 09 | [Nested classes & modern OOP](01-oop-exceptions/chapters/c09_nested_classes_modern_oop/NOTES.md) | `topic/01-oop-exceptions/09-nested-classes-modern-oop` |
| 10 | [Why exceptions](01-oop-exceptions/chapters/c10_why_exceptions/NOTES.md) | `topic/01-oop-exceptions/10-why-exceptions` |
| 11 | [Exception hierarchy](01-oop-exceptions/chapters/c11_exception_hierarchy/NOTES.md) | `topic/01-oop-exceptions/11-exception-hierarchy` |
| 12 | [Checked vs unchecked](01-oop-exceptions/chapters/c12_checked_vs_unchecked/NOTES.md) | `topic/01-oop-exceptions/12-checked-vs-unchecked` |
| 13 | [try / catch / finally](01-oop-exceptions/chapters/c13_try_catch_finally/NOTES.md) | `topic/01-oop-exceptions/13-try-catch-finally` |
| 14 | [Propagation](01-oop-exceptions/chapters/c14_exception_propagation/NOTES.md) | `topic/01-oop-exceptions/14-exception-propagation` |
| 15 | [try-with-resources](01-oop-exceptions/chapters/c15_try_with_resources/NOTES.md) | `topic/01-oop-exceptions/15-try-with-resources` |
| 16 | [Custom exceptions](01-oop-exceptions/chapters/c16_custom_exceptions/NOTES.md) | `topic/01-oop-exceptions/16-custom-exceptions` |
| 17 | [Best practices & Spring](01-oop-exceptions/chapters/c17_exception_best_practices/NOTES.md) | `topic/01-oop-exceptions/17-exception-best-practices` |
| 18 | [Version explorer (Java 8 → 21)](01-oop-exceptions/chapters/c18_java_version_explorer/NOTES.md) | `topic/01-oop-exceptions/18-java-version-explorer` |
| 19 | [Same code, new Java](01-oop-exceptions/chapters/c19_same_code_new_java/NOTES.md) | `topic/01-oop-exceptions/19-same-code-new-java` |
| 20 | [Java 22 → 25](01-oop-exceptions/chapters/c20_java_22_to_25/NOTES.md) | `topic/01-oop-exceptions/20-java-22-to-25` |
| 21 | [Upgrading from 8](01-oop-exceptions/chapters/c21_upgrading_from_8/NOTES.md) | `topic/01-oop-exceptions/21-upgrading-from-8` |

Review: [LTS cheat sheet](01-oop-exceptions/review/lts-cheat-sheet.md) · [Interview Q&A](01-oop-exceptions/review/interview-qa.md) · [Quiz](01-oop-exceptions/review/quiz.md)

### 02 · Java Collections Handbook — [`02-collections/`](02-collections/)

Lists, sets, maps and queues from the inside: ArrayList growth, HashMap buckets, red-black trees, heaps, LRU caches, equals/hashCode, concurrency.

| # | Chapter | Branch |
|---|---|---|
| 01 | [Why collections?](02-collections/chapters/c01_why_collections/NOTES.md) | `topic/02-collections/01-why-collections` |
| 02 | [The family tree](02-collections/chapters/c02_family_tree/NOTES.md) | `topic/02-collections/02-family-tree` |
| 03 | [Core interfaces](02-collections/chapters/c03_core_interfaces/NOTES.md) | `topic/02-collections/03-core-interfaces` |
| 04 | [ArrayList](02-collections/chapters/c04_arraylist/NOTES.md) | `topic/02-collections/04-arraylist` |
| 05 | [LinkedList](02-collections/chapters/c05_linkedlist/NOTES.md) | `topic/02-collections/05-linkedlist` |
| 06 | [Vector & Stack](02-collections/chapters/c06_vector_stack/NOTES.md) | `topic/02-collections/06-vector-stack` |
| 07 | [HashSet](02-collections/chapters/c07_hashset/NOTES.md) | `topic/02-collections/07-hashset` |
| 08 | [LinkedHashSet](02-collections/chapters/c08_linkedhashset/NOTES.md) | `topic/02-collections/08-linkedhashset` |
| 09 | [TreeSet](02-collections/chapters/c09_treeset/NOTES.md) | `topic/02-collections/09-treeset` |
| 10 | [EnumSet](02-collections/chapters/c10_enumset/NOTES.md) | `topic/02-collections/10-enumset` |
| 11 | [HashMap (deep dive)](02-collections/chapters/c11_hashmap/NOTES.md) | `topic/02-collections/11-hashmap` |
| 12 | [LinkedHashMap & LRU](02-collections/chapters/c12_linkedhashmap_lru/NOTES.md) | `topic/02-collections/12-linkedhashmap-lru` |
| 13 | [TreeMap](02-collections/chapters/c13_treemap/NOTES.md) | `topic/02-collections/13-treemap` |
| 14 | [Special maps](02-collections/chapters/c14_special_maps/NOTES.md) | `topic/02-collections/14-special-maps` |
| 15 | [PriorityQueue](02-collections/chapters/c15_priorityqueue/NOTES.md) | `topic/02-collections/15-priorityqueue` |
| 16 | [ArrayDeque](02-collections/chapters/c16_arraydeque/NOTES.md) | `topic/02-collections/16-arraydeque` |
| 17 | [equals & hashCode](02-collections/chapters/c17_equals_hashcode/NOTES.md) | `topic/02-collections/17-equals-hashcode` |
| 18 | [Comparable & Comparator](02-collections/chapters/c18_comparable_comparator/NOTES.md) | `topic/02-collections/18-comparable-comparator` |
| 19 | [Iterators & fail-fast](02-collections/chapters/c19_iterators_fail_fast/NOTES.md) | `topic/02-collections/19-iterators-fail-fast` |
| 20 | [Immutable collections](02-collections/chapters/c20_immutable_collections/NOTES.md) | `topic/02-collections/20-immutable-collections` |
| 21 | [Collections & Arrays](02-collections/chapters/c21_collections_arrays_utils/NOTES.md) | `topic/02-collections/21-collections-arrays-utils` |
| 22 | [Concurrent collections](02-collections/chapters/c22_concurrent_collections/NOTES.md) | `topic/02-collections/22-concurrent-collections` |
| 23 | [Streams & Collectors](02-collections/chapters/c23_streams_collectors/NOTES.md) | `topic/02-collections/23-streams-collectors` |
| 24 | [Generics & wildcards](02-collections/chapters/c24_generics_wildcards/NOTES.md) | `topic/02-collections/24-generics-wildcards` |

Review: [Big-O table](02-collections/review/big-o-table.md) · [Which collection?](02-collections/review/which-collection.md) · [Surprising facts](02-collections/review/facts.md) · [Interview Q&A](02-collections/review/interview-qa.md) · [Quiz](02-collections/review/quiz.md)

### 03 · JVM, Memory & Strings — [`03-jvm-memory-strings/`](03-jvm-memory-strings/)

JDK vs JRE vs JVM, class loading, JIT, stack vs heap, garbage collection and collectors, memory leaks, String pool, StringBuilder vs StringBuffer.

| # | Chapter | Branch |
|---|---|---|
| 01 | [How a Java program runs](03-jvm-memory-strings/chapters/c01_how_java_runs/NOTES.md) | `topic/03-jvm-memory-strings/01-how-java-runs` |
| 02 | [JDK vs JRE vs JVM](03-jvm-memory-strings/chapters/c02_jdk_jre_jvm/NOTES.md) | `topic/03-jvm-memory-strings/02-jdk-jre-jvm` |
| 03 | [JVM architecture](03-jvm-memory-strings/chapters/c03_jvm_architecture/NOTES.md) | `topic/03-jvm-memory-strings/03-jvm-architecture` |
| 04 | [Class loading](03-jvm-memory-strings/chapters/c04_class_loading/NOTES.md) | `topic/03-jvm-memory-strings/04-class-loading` |
| 05 | [Interpreter & JIT](03-jvm-memory-strings/chapters/c05_interpreter_jit/NOTES.md) | `topic/03-jvm-memory-strings/05-interpreter-jit` |
| 06 | [Stack vs heap](03-jvm-memory-strings/chapters/c06_stack_vs_heap/NOTES.md) | `topic/03-jvm-memory-strings/06-stack-vs-heap` |
| 07 | [Garbage collection](03-jvm-memory-strings/chapters/c07_garbage_collection/NOTES.md) | `topic/03-jvm-memory-strings/07-garbage-collection` |
| 08 | [GC algorithms](03-jvm-memory-strings/chapters/c08_gc_algorithms/NOTES.md) | `topic/03-jvm-memory-strings/08-gc-algorithms` |
| 09 | [References & leaks](03-jvm-memory-strings/chapters/c09_references_leaks/NOTES.md) | `topic/03-jvm-memory-strings/09-references-leaks` |
| 10 | [Flags & tools](03-jvm-memory-strings/chapters/c10_jvm_flags_tools/NOTES.md) | `topic/03-jvm-memory-strings/10-jvm-flags-tools` |
| 11 | [String & immutability](03-jvm-memory-strings/chapters/c11_string_immutability/NOTES.md) | `topic/03-jvm-memory-strings/11-string-immutability` |
| 12 | [The String pool](03-jvm-memory-strings/chapters/c12_string_pool/NOTES.md) | `topic/03-jvm-memory-strings/12-string-pool` |
| 13 | [StringBuilder](03-jvm-memory-strings/chapters/c13_stringbuilder/NOTES.md) | `topic/03-jvm-memory-strings/13-stringbuilder` |
| 14 | [StringBuffer & comparison](03-jvm-memory-strings/chapters/c14_stringbuffer_comparison/NOTES.md) | `topic/03-jvm-memory-strings/14-stringbuffer-comparison` |

Review: [Surprising facts](03-jvm-memory-strings/review/facts.md) · [Interview Q&A](03-jvm-memory-strings/review/interview-qa.md) · [Quiz](03-jvm-memory-strings/review/quiz.md)

### 04 · Java 8 & Streams Handbook — [`04-java8-streams/`](04-java8-streams/)

Lambdas, functional interfaces, method references, Optional, every Stream operation, collectors, parallel streams, java.time and CompletableFuture.

| # | Chapter | Branch |
|---|---|---|
| 01 | [Why Java 8 changed everything](04-java8-streams/chapters/c01_why_java8/NOTES.md) | `topic/04-java8-streams/01-why-java8` |
| 02 | [Lambda expressions](04-java8-streams/chapters/c02_lambdas/NOTES.md) | `topic/04-java8-streams/02-lambdas` |
| 03 | [Functional interfaces](04-java8-streams/chapters/c03_functional_interfaces/NOTES.md) | `topic/04-java8-streams/03-functional-interfaces` |
| 04 | [Method references](04-java8-streams/chapters/c04_method_references/NOTES.md) | `topic/04-java8-streams/04-method-references` |
| 05 | [Default & static methods](04-java8-streams/chapters/c05_default_static_methods/NOTES.md) | `topic/04-java8-streams/05-default-static-methods` |
| 06 | [Optional](04-java8-streams/chapters/c06_optional/NOTES.md) | `topic/04-java8-streams/06-optional` |
| 07 | [How streams work](04-java8-streams/chapters/c07_how_streams_work/NOTES.md) | `topic/04-java8-streams/07-how-streams-work` |
| 08 | [Creating streams](04-java8-streams/chapters/c08_creating_streams/NOTES.md) | `topic/04-java8-streams/08-creating-streams` |
| 09 | [Intermediate operations](04-java8-streams/chapters/c09_intermediate_operations/NOTES.md) | `topic/04-java8-streams/09-intermediate-operations` |
| 10 | [Terminal operations](04-java8-streams/chapters/c10_terminal_operations/NOTES.md) | `topic/04-java8-streams/10-terminal-operations` |
| 11 | [Collectors](04-java8-streams/chapters/c11_collectors/NOTES.md) | `topic/04-java8-streams/11-collectors` |
| 12 | [Primitive streams](04-java8-streams/chapters/c12_primitive_streams/NOTES.md) | `topic/04-java8-streams/12-primitive-streams` |
| 13 | [Parallel streams](04-java8-streams/chapters/c13_parallel_streams/NOTES.md) | `topic/04-java8-streams/13-parallel-streams` |
| 14 | [18 real-world recipes](04-java8-streams/chapters/c14_stream_recipes/NOTES.md) | `topic/04-java8-streams/14-stream-recipes` |
| 15 | [Date & Time API](04-java8-streams/chapters/c15_date_time_api/NOTES.md) | `topic/04-java8-streams/15-date-time-api` |
| 16 | [CompletableFuture](04-java8-streams/chapters/c16_completablefuture/NOTES.md) | `topic/04-java8-streams/16-completablefuture` |
| 17 | [Other additions](04-java8-streams/chapters/c17_other_additions/NOTES.md) | `topic/04-java8-streams/17-other-additions` |
| 18 | [What came after Java 8](04-java8-streams/chapters/c18_after_java8/NOTES.md) | `topic/04-java8-streams/18-after-java8` |
| 19 | [Pitfalls & best practices](04-java8-streams/chapters/c19_pitfalls_best_practices/NOTES.md) | `topic/04-java8-streams/19-pitfalls-best-practices` |

Review: [Operations cheat sheet](04-java8-streams/review/operations-cheat-sheet.md) · [Interview Q&A](04-java8-streams/review/interview-qa.md) · [Quiz](04-java8-streams/review/quiz.md)

### 05 · Java Concurrency Handbook — [`05-concurrency/`](05-concurrency/)

Threads, race conditions, synchronized, volatile and the memory model, deadlock, locks, atomics, thread pools, CompletableFuture and virtual threads.

| # | Chapter | Branch |
|---|---|---|
| 01 | [Why concurrency?](05-concurrency/chapters/c01_why_concurrency/NOTES.md) | `topic/05-concurrency/01-why-concurrency` |
| 02 | [Creating threads](05-concurrency/chapters/c02_creating_threads/NOTES.md) | `topic/05-concurrency/02-creating-threads` |
| 03 | [Thread lifecycle](05-concurrency/chapters/c03_thread_lifecycle/NOTES.md) | `topic/05-concurrency/03-thread-lifecycle` |
| 04 | [sleep, join, interrupt](05-concurrency/chapters/c04_sleep_join_interrupt/NOTES.md) | `topic/05-concurrency/04-sleep-join-interrupt` |
| 05 | [Race conditions](05-concurrency/chapters/c05_race_conditions/NOTES.md) | `topic/05-concurrency/05-race-conditions` |
| 06 | [synchronized](05-concurrency/chapters/c06_synchronized/NOTES.md) | `topic/05-concurrency/06-synchronized` |
| 07 | [volatile & memory model](05-concurrency/chapters/c07_volatile_memory_model/NOTES.md) | `topic/05-concurrency/07-volatile-memory-model` |
| 08 | [Producer–consumer](05-concurrency/chapters/c08_producer_consumer/NOTES.md) | `topic/05-concurrency/08-producer-consumer` |
| 09 | [Deadlock](05-concurrency/chapters/c09_deadlock/NOTES.md) | `topic/05-concurrency/09-deadlock` |
| 10 | [Explicit locks](05-concurrency/chapters/c10_explicit_locks/NOTES.md) | `topic/05-concurrency/10-explicit-locks` |
| 11 | [Atomics & CAS](05-concurrency/chapters/c11_atomics_cas/NOTES.md) | `topic/05-concurrency/11-atomics-cas` |
| 12 | [ExecutorService](05-concurrency/chapters/c12_executorservice/NOTES.md) | `topic/05-concurrency/12-executorservice` |
| 13 | [ThreadPoolExecutor](05-concurrency/chapters/c13_threadpoolexecutor/NOTES.md) | `topic/05-concurrency/13-threadpoolexecutor` |
| 14 | [Callable & Future](05-concurrency/chapters/c14_callable_future/NOTES.md) | `topic/05-concurrency/14-callable-future` |
| 15 | [CompletableFuture](05-concurrency/chapters/c15_completablefuture/NOTES.md) | `topic/05-concurrency/15-completablefuture` |
| 16 | [Latch, barrier, semaphore](05-concurrency/chapters/c16_latch_barrier_semaphore/NOTES.md) | `topic/05-concurrency/16-latch-barrier-semaphore` |
| 17 | [ThreadLocal](05-concurrency/chapters/c17_threadlocal/NOTES.md) | `topic/05-concurrency/17-threadlocal` |
| 18 | [Fork/Join](05-concurrency/chapters/c18_fork_join/NOTES.md) | `topic/05-concurrency/18-fork-join` |
| 19 | [Virtual threads (needs JDK 21)](05-concurrency/chapters/c19_virtual_threads/NOTES.md) | `topic/05-concurrency/19-virtual-threads` |
| 20 | [Best practices & debugging](05-concurrency/chapters/c20_best_practices_debugging/NOTES.md) | `topic/05-concurrency/20-best-practices-debugging` |

Review: [Surprising facts](05-concurrency/review/facts.md) · [Interview Q&A](05-concurrency/review/interview-qa.md) · [Quiz](05-concurrency/review/quiz.md)

### 06 · Java Patterns Handbook — [`06-design-patterns/`](06-design-patterns/)

SOLID principles and all 23 GoF design patterns with real-life analogies, before/after Java code, class diagrams and a Spring Boot case study.

| # | Chapter | Branch |
|---|---|---|
| 01 | [Foundations: the four OOP ideas](06-design-patterns/chapters/foundations/c01_oop_foundations/NOTES.md) | `topic/06-design-patterns/01-oop-foundations` |
| 02 | [Single Responsibility Principle](06-design-patterns/chapters/solid/c02_srp/NOTES.md) | `topic/06-design-patterns/02-srp` |
| 03 | [Open / Closed Principle](06-design-patterns/chapters/solid/c03_ocp/NOTES.md) | `topic/06-design-patterns/03-ocp` |
| 04 | [Liskov Substitution Principle](06-design-patterns/chapters/solid/c04_lsp/NOTES.md) | `topic/06-design-patterns/04-lsp` |
| 05 | [Interface Segregation Principle](06-design-patterns/chapters/solid/c05_isp/NOTES.md) | `topic/06-design-patterns/05-isp` |
| 06 | [Dependency Inversion Principle](06-design-patterns/chapters/solid/c06_dip/NOTES.md) | `topic/06-design-patterns/06-dip` |
| 07 | [Singleton](06-design-patterns/chapters/creational/c07_singleton/NOTES.md) | `topic/06-design-patterns/07-singleton` |
| 08 | [Factory Method](06-design-patterns/chapters/creational/c08_factory_method/NOTES.md) | `topic/06-design-patterns/08-factory-method` |
| 09 | [Abstract Factory](06-design-patterns/chapters/creational/c09_abstract_factory/NOTES.md) | `topic/06-design-patterns/09-abstract-factory` |
| 10 | [Builder](06-design-patterns/chapters/creational/c10_builder/NOTES.md) | `topic/06-design-patterns/10-builder` |
| 11 | [Prototype](06-design-patterns/chapters/creational/c11_prototype/NOTES.md) | `topic/06-design-patterns/11-prototype` |
| 12 | [Adapter](06-design-patterns/chapters/structural/c12_adapter/NOTES.md) | `topic/06-design-patterns/12-adapter` |
| 13 | [Bridge](06-design-patterns/chapters/structural/c13_bridge/NOTES.md) | `topic/06-design-patterns/13-bridge` |
| 14 | [Composite](06-design-patterns/chapters/structural/c14_composite/NOTES.md) | `topic/06-design-patterns/14-composite` |
| 15 | [Decorator](06-design-patterns/chapters/structural/c15_decorator/NOTES.md) | `topic/06-design-patterns/15-decorator` |
| 16 | [Facade](06-design-patterns/chapters/structural/c16_facade/NOTES.md) | `topic/06-design-patterns/16-facade` |
| 17 | [Flyweight](06-design-patterns/chapters/structural/c17_flyweight/NOTES.md) | `topic/06-design-patterns/17-flyweight` |
| 18 | [Proxy](06-design-patterns/chapters/structural/c18_proxy/NOTES.md) | `topic/06-design-patterns/18-proxy` |
| 19 | [Chain of Responsibility](06-design-patterns/chapters/behavioral/c19_chain_of_responsibility/NOTES.md) | `topic/06-design-patterns/19-chain-of-responsibility` |
| 20 | [Command](06-design-patterns/chapters/behavioral/c20_command/NOTES.md) | `topic/06-design-patterns/20-command` |
| 21 | [Interpreter](06-design-patterns/chapters/behavioral/c21_interpreter/NOTES.md) | `topic/06-design-patterns/21-interpreter` |
| 22 | [Iterator](06-design-patterns/chapters/behavioral/c22_iterator/NOTES.md) | `topic/06-design-patterns/22-iterator` |
| 23 | [Mediator](06-design-patterns/chapters/behavioral/c23_mediator/NOTES.md) | `topic/06-design-patterns/23-mediator` |
| 24 | [Memento](06-design-patterns/chapters/behavioral/c24_memento/NOTES.md) | `topic/06-design-patterns/24-memento` |
| 25 | [Observer](06-design-patterns/chapters/behavioral/c25_observer/NOTES.md) | `topic/06-design-patterns/25-observer` |
| 26 | [State](06-design-patterns/chapters/behavioral/c26_state/NOTES.md) | `topic/06-design-patterns/26-state` |
| 27 | [Strategy](06-design-patterns/chapters/behavioral/c27_strategy/NOTES.md) | `topic/06-design-patterns/27-strategy` |
| 28 | [Template Method](06-design-patterns/chapters/behavioral/c28_template_method/NOTES.md) | `topic/06-design-patterns/28-template-method` |
| 29 | [Visitor](06-design-patterns/chapters/behavioral/c29_visitor/NOTES.md) | `topic/06-design-patterns/29-visitor` |
| 30 | [Look-alike patterns, told apart](06-design-patterns/chapters/applied/c30_look_alike_patterns/NOTES.md) | `topic/06-design-patterns/30-look-alike-patterns` |
| 31 | [One order, a dozen patterns: Spring Boot walkthrough](06-design-patterns/chapters/applied/c31_spring_boot_case_study/NOTES.md) | `topic/06-design-patterns/31-spring-boot-case-study` |
| 32 | [Anti-patterns and common mistakes](06-design-patterns/chapters/applied/c32_anti_patterns/NOTES.md) | `topic/06-design-patterns/32-anti-patterns` |

Review: [Which pattern do I need?](06-design-patterns/review/which-pattern.md) · [Cheat sheet](06-design-patterns/review/cheat-sheet.md) · [Spot the pattern (quiz)](06-design-patterns/review/quiz.md)

### 07 · SQL for Java Developers — [`07-sql/`](07-sql/)

Joins, window functions, indexes, transactions, isolation, JDBC, injection, JPA, N+1, and 27 most-asked coding questions. Practice DB: H2 in-memory.

| # | Chapter | Branch |
|---|---|---|
| 01 | [Why SQL matters & the sample database](07-sql/chapters/c01_why_sql_sample_db/NOTES.md) | `topic/07-sql/01-why-sql-sample-db` |
| 02 | [SELECT basics](07-sql/chapters/c02_select_basics/NOTES.md) | `topic/07-sql/02-select-basics` |
| 03 | [Execution order](07-sql/chapters/c03_execution_order/NOTES.md) | `topic/07-sql/03-execution-order` |
| 04 | [NULL](07-sql/chapters/c04_null_handling/NOTES.md) | `topic/07-sql/04-null-handling` |
| 05 | [GROUP BY & HAVING](07-sql/chapters/c05_group_by_having/NOTES.md) | `topic/07-sql/05-group-by-having` |
| 06 | [JOINs](07-sql/chapters/c06_joins/NOTES.md) | `topic/07-sql/06-joins` |
| 07 | [Subqueries & CTEs](07-sql/chapters/c07_subqueries_ctes/NOTES.md) | `topic/07-sql/07-subqueries-ctes` |
| 08 | [Window functions](07-sql/chapters/c08_window_functions/NOTES.md) | `topic/07-sql/08-window-functions` |
| 09 | [CASE, functions, UNION](07-sql/chapters/c09_case_functions_union/NOTES.md) | `topic/07-sql/09-case-functions-union` |
| 10 | [DDL, constraints, DML](07-sql/chapters/c10_ddl_constraints_dml/NOTES.md) | `topic/07-sql/10-ddl-constraints-dml` |
| 11 | [Keys & normalization](07-sql/chapters/c11_keys_normalization/NOTES.md) | `topic/07-sql/11-keys-normalization` |
| 12 | [Views & triggers](07-sql/chapters/c12_views_triggers/NOTES.md) | `topic/07-sql/12-views-triggers` |
| 13 | [Indexes](07-sql/chapters/c13_indexes/NOTES.md) | `topic/07-sql/13-indexes` |
| 14 | [Transactions & ACID](07-sql/chapters/c14_transactions_acid/NOTES.md) | `topic/07-sql/14-transactions-acid` |
| 15 | [Isolation & locking](07-sql/chapters/c15_isolation_locking/NOTES.md) | `topic/07-sql/15-isolation-locking` |
| 16 | [Query performance](07-sql/chapters/c16_query_performance/NOTES.md) | `topic/07-sql/16-query-performance` |
| 17 | [JDBC](07-sql/chapters/c17_jdbc/NOTES.md) | `topic/07-sql/17-jdbc` |
| 18 | [SQL injection](07-sql/chapters/c18_sql_injection/NOTES.md) | `topic/07-sql/18-sql-injection` |
| 19 | [Transactions, batching, pools](07-sql/chapters/c19_jdbc_tx_batching_pools/NOTES.md) | `topic/07-sql/19-jdbc-tx-batching-pools` |
| 20 | [JPA & Hibernate](07-sql/chapters/c20_jpa_hibernate/NOTES.md) | `topic/07-sql/20-jpa-hibernate` |
| 21 | [The N+1 problem](07-sql/chapters/c21_n_plus_1/NOTES.md) | `topic/07-sql/21-n-plus-1` |
| 22 | [Spring Data JPA](07-sql/chapters/c22_spring_data_jpa/NOTES.md) | `topic/07-sql/22-spring-data-jpa` |
| 23 | [@Transactional & locking](07-sql/chapters/c23_transactional_locking/NOTES.md) | `topic/07-sql/23-transactional-locking` |
| 24 | [27 coding questions](07-sql/chapters/c24_coding_questions/NOTES.md) | `topic/07-sql/24-coding-questions` |

Review: [Dialect cheat sheet](07-sql/review/dialect-cheat-sheet.md) · [Theory Q&A](07-sql/review/theory-qa.md) · [Quiz](07-sql/review/quiz.md)

### 08 · Spring Boot Interview Handbook — [`08-spring-boot/`](08-spring-boot/)

Auto-configuration and @Conditional, stereotypes and DI, REST APIs, Actuator, large data, circuit breakers, sagas, security, testing, and 30 scenario questions.

| # | Chapter | Branch |
|---|---|---|
| 01 | [Spring vs Spring Boot](08-spring-boot/chapters/c01_spring_vs_boot/NOTES.md) | `topic/08-spring-boot/01-spring-vs-boot` |
| 02 | [How Boot starts](08-spring-boot/chapters/c02_how_boot_starts/NOTES.md) | `topic/08-spring-boot/02-how-boot-starts` |
| 03 | [@Component, @Service…](08-spring-boot/chapters/c03_stereotypes/NOTES.md) | `topic/08-spring-boot/03-stereotypes` |
| 04 | [Dependency injection](08-spring-boot/chapters/c04_dependency_injection/NOTES.md) | `topic/08-spring-boot/04-dependency-injection` |
| 05 | [Scopes & lifecycle](08-spring-boot/chapters/c05_scopes_lifecycle/NOTES.md) | `topic/08-spring-boot/05-scopes-lifecycle` |
| 06 | [@Conditional & auto-config](08-spring-boot/chapters/c06_conditional_autoconfig/NOTES.md) | `topic/08-spring-boot/06-conditional-autoconfig` |
| 07 | [Configuration & profiles](08-spring-boot/chapters/c07_configuration_profiles/NOTES.md) | `topic/08-spring-boot/07-configuration-profiles` |
| 08 | [REST APIs](08-spring-boot/chapters/c08_rest_apis/NOTES.md) | `topic/08-spring-boot/08-rest-apis` |
| 09 | [Validation & errors](08-spring-boot/chapters/c09_validation_errors/NOTES.md) | `topic/08-spring-boot/09-validation-errors` |
| 10 | [Actuator](08-spring-boot/chapters/c10_actuator/NOTES.md) | `topic/08-spring-boot/10-actuator` |
| 11 | [Large data](08-spring-boot/chapters/c11_large_data/NOTES.md) | `topic/08-spring-boot/11-large-data` |
| 12 | [Architecture & patterns](08-spring-boot/chapters/c12_microservice_architecture/NOTES.md) | `topic/08-spring-boot/12-microservice-architecture` |
| 13 | [Circuit breaker](08-spring-boot/chapters/c13_circuit_breaker/NOTES.md) | `topic/08-spring-boot/13-circuit-breaker` |
| 14 | [Gateway, discovery, config](08-spring-boot/chapters/c14_gateway_discovery_config/NOTES.md) | `topic/08-spring-boot/14-gateway-discovery-config` |
| 15 | [Saga & outbox](08-spring-boot/chapters/c15_saga_outbox/NOTES.md) | `topic/08-spring-boot/15-saga-outbox` |
| 16 | [Logging & tracing](08-spring-boot/chapters/c16_logging_tracing/NOTES.md) | `topic/08-spring-boot/16-logging-tracing` |
| 17 | [Security](08-spring-boot/chapters/c17_security/NOTES.md) | `topic/08-spring-boot/17-security` |
| 18 | [Caching](08-spring-boot/chapters/c18_caching/NOTES.md) | `topic/08-spring-boot/18-caching` |
| 19 | [@Async & scheduling](08-spring-boot/chapters/c19_async_scheduling/NOTES.md) | `topic/08-spring-boot/19-async-scheduling` |
| 20 | [Testing](08-spring-boot/chapters/c20_testing/NOTES.md) | `topic/08-spring-boot/20-testing` |
| 21 | [Deployment & Boot 3/4](08-spring-boot/chapters/c21_deployment_boot3_4/NOTES.md) | `topic/08-spring-boot/21-deployment-boot3-4` |
| 22 | [30 scenario questions](08-spring-boot/chapters/c22_scenario_questions/NOTES.md) | `topic/08-spring-boot/22-scenario-questions` |

Review: [Rapid-fire Q&A](08-spring-boot/review/rapid-fire-qa.md) · [Readiness checklist](08-spring-boot/review/readiness-checklist.md) · [Quiz](08-spring-boot/review/quiz.md)

### 09 · Docker & Kubernetes for Java — [`09-docker-kubernetes/`](09-docker-kubernetes/)

Building the Spring Boot fat JAR, production Dockerfiles, layer caching, the JVM in containers, Compose, Kubernetes, probes, rollouts, autoscaling, Helm and CI/CD.

| # | Chapter | Branch |
|---|---|---|
| 01 | [Code to production](09-docker-kubernetes/chapters/c01_code_to_production/NOTES.md) | `topic/09-docker-kubernetes/01-code-to-production` |
| 02 | [Building the JAR](09-docker-kubernetes/chapters/c02_building_the_jar/NOTES.md) | `topic/09-docker-kubernetes/02-building-the-jar` |
| 03 | [Inside the fat JAR](09-docker-kubernetes/chapters/c03_inside_fat_jar/NOTES.md) | `topic/09-docker-kubernetes/03-inside-fat-jar` |
| 04 | [Running it · JAR vs WAR](09-docker-kubernetes/chapters/c04_jar_vs_war/NOTES.md) | `topic/09-docker-kubernetes/04-jar-vs-war` |
| 05 | [Layers, CDS, native](09-docker-kubernetes/chapters/c05_layers_cds_native/NOTES.md) | `topic/09-docker-kubernetes/05-layers-cds-native` |
| 06 | [Why containers](09-docker-kubernetes/chapters/c06_why_containers/NOTES.md) | `topic/09-docker-kubernetes/06-why-containers` |
| 07 | [Writing a Dockerfile](09-docker-kubernetes/chapters/c07_writing_dockerfile/NOTES.md) | `topic/09-docker-kubernetes/07-writing-dockerfile` |
| 08 | [Layers & build cache](09-docker-kubernetes/chapters/c08_build_cache/NOTES.md) | `topic/09-docker-kubernetes/08-build-cache` |
| 09 | [Base images & buildpacks](09-docker-kubernetes/chapters/c09_base_images_buildpacks/NOTES.md) | `topic/09-docker-kubernetes/09-base-images-buildpacks` |
| 10 | [The JVM in a container](09-docker-kubernetes/chapters/c10_jvm_in_container/NOTES.md) | `topic/09-docker-kubernetes/10-jvm-in-container` |
| 11 | [CLI, networks, volumes](09-docker-kubernetes/chapters/c11_cli_networks_volumes/NOTES.md) | `topic/09-docker-kubernetes/11-cli-networks-volumes` |
| 12 | [Docker Compose](09-docker-kubernetes/chapters/c12_docker_compose/NOTES.md) | `topic/09-docker-kubernetes/12-docker-compose` |
| 13 | [Image security](09-docker-kubernetes/chapters/c13_image_security/NOTES.md) | `topic/09-docker-kubernetes/13-image-security` |
| 14 | [Why Kubernetes & architecture](09-docker-kubernetes/chapters/c14_k8s_architecture/NOTES.md) | `topic/09-docker-kubernetes/14-k8s-architecture` |
| 15 | [Core objects](09-docker-kubernetes/chapters/c15_k8s_core_objects/NOTES.md) | `topic/09-docker-kubernetes/15-k8s-core-objects` |
| 16 | [Deploying Spring Boot](09-docker-kubernetes/chapters/c16_deploying_spring_boot/NOTES.md) | `topic/09-docker-kubernetes/16-deploying-spring-boot` |
| 17 | [Health probes & shutdown](09-docker-kubernetes/chapters/c17_probes_shutdown/NOTES.md) | `topic/09-docker-kubernetes/17-probes-shutdown` |
| 18 | [Requests, limits, QoS](09-docker-kubernetes/chapters/c18_requests_limits_qos/NOTES.md) | `topic/09-docker-kubernetes/18-requests-limits-qos` |
| 19 | [Rolling updates](09-docker-kubernetes/chapters/c19_rolling_updates/NOTES.md) | `topic/09-docker-kubernetes/19-rolling-updates` |
| 20 | [Autoscaling](09-docker-kubernetes/chapters/c20_autoscaling/NOTES.md) | `topic/09-docker-kubernetes/20-autoscaling` |
| 21 | [Ingress & Gateway API](09-docker-kubernetes/chapters/c21_ingress_gateway_api/NOTES.md) | `topic/09-docker-kubernetes/21-ingress-gateway-api` |
| 22 | [Troubleshooting pods](09-docker-kubernetes/chapters/c22_troubleshooting_pods/NOTES.md) | `topic/09-docker-kubernetes/22-troubleshooting-pods` |
| 23 | [Helm & Kustomize](09-docker-kubernetes/chapters/c23_helm_kustomize/NOTES.md) | `topic/09-docker-kubernetes/23-helm-kustomize` |
| 24 | [CI/CD & GitOps](09-docker-kubernetes/chapters/c24_cicd_gitops/NOTES.md) | `topic/09-docker-kubernetes/24-cicd-gitops` |
| 25 | [25 scenario questions](09-docker-kubernetes/chapters/c25_scenario_questions/NOTES.md) | `topic/09-docker-kubernetes/25-scenario-questions` |

Review: [kubectl cheat sheet](09-docker-kubernetes/review/kubectl-cheat-sheet.md) · [Interview Q&A](09-docker-kubernetes/review/interview-qa.md) · [Quiz](09-docker-kubernetes/review/quiz.md)

