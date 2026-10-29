# 05 · Java Concurrency Handbook

Threads, race conditions, synchronized, volatile and the memory model, deadlock, locks, atomics, thread pools, CompletableFuture and virtual threads.

- Handbook: https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `concurrency.html`
- Chapters: **20** (one branch each) · Review files: **3**
- Study order across handbooks: [CHECKLIST.md](../CHECKLIST.md)

## Chapters

| # | Chapter | Branch | Folder | Handbook anchor |
|---|---|---|---|---|
| 01 | Why concurrency? | `topic/05-concurrency/01-why-concurrency` | [chapters/c01_why_concurrency](chapters/c01_why_concurrency) | `why` |
| 02 | Creating threads | `topic/05-concurrency/02-creating-threads` | [chapters/c02_creating_threads](chapters/c02_creating_threads) | `create` |
| 03 | Thread lifecycle | `topic/05-concurrency/03-thread-lifecycle` | [chapters/c03_thread_lifecycle](chapters/c03_thread_lifecycle) | `lifecycle` |
| 04 | sleep, join, interrupt | `topic/05-concurrency/04-sleep-join-interrupt` | [chapters/c04_sleep_join_interrupt](chapters/c04_sleep_join_interrupt) | `methods` |
| 05 | Race conditions | `topic/05-concurrency/05-race-conditions` | [chapters/c05_race_conditions](chapters/c05_race_conditions) | `race` |
| 06 | synchronized | `topic/05-concurrency/06-synchronized` | [chapters/c06_synchronized](chapters/c06_synchronized) | `sync` |
| 07 | volatile & memory model | `topic/05-concurrency/07-volatile-memory-model` | [chapters/c07_volatile_memory_model](chapters/c07_volatile_memory_model) | `volatile` |
| 08 | Producer–consumer | `topic/05-concurrency/08-producer-consumer` | [chapters/c08_producer_consumer](chapters/c08_producer_consumer) | `waitnotify` |
| 09 | Deadlock | `topic/05-concurrency/09-deadlock` | [chapters/c09_deadlock](chapters/c09_deadlock) | `deadlock` |
| 10 | Explicit locks | `topic/05-concurrency/10-explicit-locks` | [chapters/c10_explicit_locks](chapters/c10_explicit_locks) | `locks` |
| 11 | Atomics & CAS | `topic/05-concurrency/11-atomics-cas` | [chapters/c11_atomics_cas](chapters/c11_atomics_cas) | `atomic` |
| 12 | ExecutorService | `topic/05-concurrency/12-executorservice` | [chapters/c12_executorservice](chapters/c12_executorservice) | `executors` |
| 13 | ThreadPoolExecutor | `topic/05-concurrency/13-threadpoolexecutor` | [chapters/c13_threadpoolexecutor](chapters/c13_threadpoolexecutor) | `pool` |
| 14 | Callable & Future | `topic/05-concurrency/14-callable-future` | [chapters/c14_callable_future](chapters/c14_callable_future) | `future` |
| 15 | CompletableFuture | `topic/05-concurrency/15-completablefuture` | [chapters/c15_completablefuture](chapters/c15_completablefuture) | `cf` |
| 16 | Latch, barrier, semaphore | `topic/05-concurrency/16-latch-barrier-semaphore` | [chapters/c16_latch_barrier_semaphore](chapters/c16_latch_barrier_semaphore) | `synchronizers` |
| 17 | ThreadLocal | `topic/05-concurrency/17-threadlocal` | [chapters/c17_threadlocal](chapters/c17_threadlocal) | `threadlocal` |
| 18 | Fork/Join | `topic/05-concurrency/18-fork-join` | [chapters/c18_fork_join](chapters/c18_fork_join) | `forkjoin` |
| 19 | Virtual threads (needs JDK 21) | `topic/05-concurrency/19-virtual-threads` | [chapters/c19_virtual_threads](chapters/c19_virtual_threads) | `virtual` |
| 20 | Best practices & debugging | `topic/05-concurrency/20-best-practices-debugging` | [chapters/c20_best_practices_debugging](chapters/c20_best_practices_debugging) | `practices` |

## Review (no branch)

- [Surprising facts](review/facts.md)
- [Interview Q&A](review/interview-qa.md)
- [Quiz](review/quiz.md)

## Build / run

```bash
mvn -q -pl 05-concurrency compile
mvn -q -pl 05-concurrency compile exec:java -Dexec.mainClass=<package>.Practice
```
