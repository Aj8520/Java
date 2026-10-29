# 03 · JVM, Memory & Strings

JDK vs JRE vs JVM, class loading, JIT, stack vs heap, garbage collection and collectors, memory leaks, String pool, StringBuilder vs StringBuffer.

- Handbook: https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `jvm.html`
- Chapters: **14** (one branch each) · Review files: **3**
- Study order across handbooks: [CHECKLIST.md](../CHECKLIST.md)

## Chapters

| # | Chapter | Branch | Folder | Handbook anchor |
|---|---|---|---|---|
| 01 | How a Java program runs | `topic/03-jvm-memory-strings/01-how-java-runs` | [chapters/c01_how_java_runs](chapters/c01_how_java_runs) | `run` |
| 02 | JDK vs JRE vs JVM | `topic/03-jvm-memory-strings/02-jdk-jre-jvm` | [chapters/c02_jdk_jre_jvm](chapters/c02_jdk_jre_jvm) | `jdk` |
| 03 | JVM architecture | `topic/03-jvm-memory-strings/03-jvm-architecture` | [chapters/c03_jvm_architecture](chapters/c03_jvm_architecture) | `arch` |
| 04 | Class loading | `topic/03-jvm-memory-strings/04-class-loading` | [chapters/c04_class_loading](chapters/c04_class_loading) | `classloading` |
| 05 | Interpreter & JIT | `topic/03-jvm-memory-strings/05-interpreter-jit` | [chapters/c05_interpreter_jit](chapters/c05_interpreter_jit) | `jit` |
| 06 | Stack vs heap | `topic/03-jvm-memory-strings/06-stack-vs-heap` | [chapters/c06_stack_vs_heap](chapters/c06_stack_vs_heap) | `memory` |
| 07 | Garbage collection | `topic/03-jvm-memory-strings/07-garbage-collection` | [chapters/c07_garbage_collection](chapters/c07_garbage_collection) | `gc` |
| 08 | GC algorithms | `topic/03-jvm-memory-strings/08-gc-algorithms` | [chapters/c08_gc_algorithms](chapters/c08_gc_algorithms) | `collectors` |
| 09 | References & leaks | `topic/03-jvm-memory-strings/09-references-leaks` | [chapters/c09_references_leaks](chapters/c09_references_leaks) | `refs` |
| 10 | Flags & tools | `topic/03-jvm-memory-strings/10-jvm-flags-tools` | [chapters/c10_jvm_flags_tools](chapters/c10_jvm_flags_tools) | `tuning` |
| 11 | String & immutability | `topic/03-jvm-memory-strings/11-string-immutability` | [chapters/c11_string_immutability](chapters/c11_string_immutability) | `strings` |
| 12 | The String pool | `topic/03-jvm-memory-strings/12-string-pool` | [chapters/c12_string_pool](chapters/c12_string_pool) | `pool` |
| 13 | StringBuilder | `topic/03-jvm-memory-strings/13-stringbuilder` | [chapters/c13_stringbuilder](chapters/c13_stringbuilder) | `builder` |
| 14 | StringBuffer & comparison | `topic/03-jvm-memory-strings/14-stringbuffer-comparison` | [chapters/c14_stringbuffer_comparison](chapters/c14_stringbuffer_comparison) | `buffer` |

## Review (no branch)

- [Surprising facts](review/facts.md)
- [Interview Q&A](review/interview-qa.md)
- [Quiz](review/quiz.md)

## Build / run

```bash
mvn -q -pl 03-jvm-memory-strings compile
mvn -q -pl 03-jvm-memory-strings compile exec:java -Dexec.mainClass=<package>.Practice
```
