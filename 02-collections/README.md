# 02 · Java Collections Handbook

Lists, sets, maps and queues from the inside: ArrayList growth, HashMap buckets, red-black trees, heaps, LRU caches, equals/hashCode, concurrency.

- Handbook: https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `collections.html`
- Chapters: **24** (one branch each) · Review files: **5**
- Study order across handbooks: [CHECKLIST.md](../CHECKLIST.md)

## Chapters

| # | Chapter | Branch | Folder | Handbook anchor |
|---|---|---|---|---|
| 01 | Why collections? | `topic/02-collections/01-why-collections` | [chapters/c01_why_collections](chapters/c01_why_collections) | `why` |
| 02 | The family tree | `topic/02-collections/02-family-tree` | [chapters/c02_family_tree](chapters/c02_family_tree) | `hierarchy` |
| 03 | Core interfaces | `topic/02-collections/03-core-interfaces` | [chapters/c03_core_interfaces](chapters/c03_core_interfaces) | `interfaces` |
| 04 | ArrayList | `topic/02-collections/04-arraylist` | [chapters/c04_arraylist](chapters/c04_arraylist) | `arraylist` |
| 05 | LinkedList | `topic/02-collections/05-linkedlist` | [chapters/c05_linkedlist](chapters/c05_linkedlist) | `linkedlist` |
| 06 | Vector & Stack | `topic/02-collections/06-vector-stack` | [chapters/c06_vector_stack](chapters/c06_vector_stack) | `legacy` |
| 07 | HashSet | `topic/02-collections/07-hashset` | [chapters/c07_hashset](chapters/c07_hashset) | `hashset` |
| 08 | LinkedHashSet | `topic/02-collections/08-linkedhashset` | [chapters/c08_linkedhashset](chapters/c08_linkedhashset) | `linkedhashset` |
| 09 | TreeSet | `topic/02-collections/09-treeset` | [chapters/c09_treeset](chapters/c09_treeset) | `treeset` |
| 10 | EnumSet | `topic/02-collections/10-enumset` | [chapters/c10_enumset](chapters/c10_enumset) | `enumset` |
| 11 | HashMap (deep dive) | `topic/02-collections/11-hashmap` | [chapters/c11_hashmap](chapters/c11_hashmap) | `hashmap` |
| 12 | LinkedHashMap & LRU | `topic/02-collections/12-linkedhashmap-lru` | [chapters/c12_linkedhashmap_lru](chapters/c12_linkedhashmap_lru) | `linkedhashmap` |
| 13 | TreeMap | `topic/02-collections/13-treemap` | [chapters/c13_treemap](chapters/c13_treemap) | `treemap` |
| 14 | Special maps | `topic/02-collections/14-special-maps` | [chapters/c14_special_maps](chapters/c14_special_maps) | `specialmaps` |
| 15 | PriorityQueue | `topic/02-collections/15-priorityqueue` | [chapters/c15_priorityqueue](chapters/c15_priorityqueue) | `priorityqueue` |
| 16 | ArrayDeque | `topic/02-collections/16-arraydeque` | [chapters/c16_arraydeque](chapters/c16_arraydeque) | `arraydeque` |
| 17 | equals & hashCode | `topic/02-collections/17-equals-hashcode` | [chapters/c17_equals_hashcode](chapters/c17_equals_hashcode) | `equality` |
| 18 | Comparable & Comparator | `topic/02-collections/18-comparable-comparator` | [chapters/c18_comparable_comparator](chapters/c18_comparable_comparator) | `ordering` |
| 19 | Iterators & fail-fast | `topic/02-collections/19-iterators-fail-fast` | [chapters/c19_iterators_fail_fast](chapters/c19_iterators_fail_fast) | `iteration` |
| 20 | Immutable collections | `topic/02-collections/20-immutable-collections` | [chapters/c20_immutable_collections](chapters/c20_immutable_collections) | `immutable` |
| 21 | Collections & Arrays | `topic/02-collections/21-collections-arrays-utils` | [chapters/c21_collections_arrays_utils](chapters/c21_collections_arrays_utils) | `utilities` |
| 22 | Concurrent collections | `topic/02-collections/22-concurrent-collections` | [chapters/c22_concurrent_collections](chapters/c22_concurrent_collections) | `concurrent` |
| 23 | Streams & Collectors | `topic/02-collections/23-streams-collectors` | [chapters/c23_streams_collectors](chapters/c23_streams_collectors) | `streams` |
| 24 | Generics & wildcards | `topic/02-collections/24-generics-wildcards` | [chapters/c24_generics_wildcards](chapters/c24_generics_wildcards) | `generics` |

## Review (no branch)

- [Big-O table](review/big-o-table.md)
- [Which collection?](review/which-collection.md)
- [Surprising facts](review/facts.md)
- [Interview Q&A](review/interview-qa.md)
- [Quiz](review/quiz.md)

## Build / run

```bash
mvn -q -pl 02-collections compile
mvn -q -pl 02-collections compile exec:java -Dexec.mainClass=<package>.Practice
```
