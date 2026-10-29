# 04 · Java 8 & Streams Handbook

Lambdas, functional interfaces, method references, Optional, every Stream operation, collectors, parallel streams, java.time and CompletableFuture.

- Handbook: https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `java8.html`
- Chapters: **19** (one branch each) · Review files: **3**
- Study order across handbooks: [CHECKLIST.md](../CHECKLIST.md)

## Chapters

| # | Chapter | Branch | Folder | Handbook anchor |
|---|---|---|---|---|
| 01 | Why Java 8 changed everything | `topic/04-java8-streams/01-why-java8` | [chapters/c01_why_java8](chapters/c01_why_java8) | `why` |
| 02 | Lambda expressions | `topic/04-java8-streams/02-lambdas` | [chapters/c02_lambdas](chapters/c02_lambdas) | `lambdas` |
| 03 | Functional interfaces | `topic/04-java8-streams/03-functional-interfaces` | [chapters/c03_functional_interfaces](chapters/c03_functional_interfaces) | `functional` |
| 04 | Method references | `topic/04-java8-streams/04-method-references` | [chapters/c04_method_references](chapters/c04_method_references) | `methodrefs` |
| 05 | Default & static methods | `topic/04-java8-streams/05-default-static-methods` | [chapters/c05_default_static_methods](chapters/c05_default_static_methods) | `defaults` |
| 06 | Optional | `topic/04-java8-streams/06-optional` | [chapters/c06_optional](chapters/c06_optional) | `optional` |
| 07 | How streams work | `topic/04-java8-streams/07-how-streams-work` | [chapters/c07_how_streams_work](chapters/c07_how_streams_work) | `streams` |
| 08 | Creating streams | `topic/04-java8-streams/08-creating-streams` | [chapters/c08_creating_streams](chapters/c08_creating_streams) | `create` |
| 09 | Intermediate operations | `topic/04-java8-streams/09-intermediate-operations` | [chapters/c09_intermediate_operations](chapters/c09_intermediate_operations) | `intermediate` |
| 10 | Terminal operations | `topic/04-java8-streams/10-terminal-operations` | [chapters/c10_terminal_operations](chapters/c10_terminal_operations) | `terminal` |
| 11 | Collectors | `topic/04-java8-streams/11-collectors` | [chapters/c11_collectors](chapters/c11_collectors) | `collectors` |
| 12 | Primitive streams | `topic/04-java8-streams/12-primitive-streams` | [chapters/c12_primitive_streams](chapters/c12_primitive_streams) | `primitive` |
| 13 | Parallel streams | `topic/04-java8-streams/13-parallel-streams` | [chapters/c13_parallel_streams](chapters/c13_parallel_streams) | `parallel` |
| 14 | 18 real-world recipes | `topic/04-java8-streams/14-stream-recipes` | [chapters/c14_stream_recipes](chapters/c14_stream_recipes) | `recipes` |
| 15 | Date & Time API | `topic/04-java8-streams/15-date-time-api` | [chapters/c15_date_time_api](chapters/c15_date_time_api) | `datetime` |
| 16 | CompletableFuture | `topic/04-java8-streams/16-completablefuture` | [chapters/c16_completablefuture](chapters/c16_completablefuture) | `async` |
| 17 | Other additions | `topic/04-java8-streams/17-other-additions` | [chapters/c17_other_additions](chapters/c17_other_additions) | `more` |
| 18 | What came after Java 8 | `topic/04-java8-streams/18-after-java8` | [chapters/c18_after_java8](chapters/c18_after_java8) | `beyond` |
| 19 | Pitfalls & best practices | `topic/04-java8-streams/19-pitfalls-best-practices` | [chapters/c19_pitfalls_best_practices](chapters/c19_pitfalls_best_practices) | `pitfalls` |

## Review (no branch)

- [Operations cheat sheet](review/operations-cheat-sheet.md)
- [Interview Q&A](review/interview-qa.md)
- [Quiz](review/quiz.md)

## Build / run

```bash
mvn -q -pl 04-java8-streams compile
mvn -q -pl 04-java8-streams compile exec:java -Dexec.mainClass=<package>.Practice
```
