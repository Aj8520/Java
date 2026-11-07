# 01.18 · What's new from Java 8 to 21

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#versions`
> Branch: `topic/01-oop-exceptions/18-java-version-explorer`

## 1. Key concepts (fill while reading)
- Since Java 9 (2017), a new version ships every six months, in March and September.
- Every two years one is a Long-Term Support (LTS) release that companies standardise on: 8, 11, 17, 21 (and 25).
- Features often arrive as preview first (`--enable-preview`), then become final a release or two later.
- The big ones per version:

| Version | Main features |
|---|---|
| 8 (LTS, 2014) | lambdas and method references, Stream API, Optional, java.time, default/static interface methods, CompletableFuture, Metaspace replaces PermGen |
| 9 | modules (`module-info.java`), JShell, `List.of`/`Set.of`/`Map.of`, private interface methods, `takeWhile`/`dropWhile`/`iterate(seed, hasNext, next)`, G1 by default |
| 10 | `var`, `List.copyOf`, `Collectors.toUnmodifiableList`, `Optional.orElseThrow()`, container awareness |
| 11 (LTS) | `HttpClient`, `String.isBlank/lines/strip/repeat`, `Files.readString/writeString`, `java Hello.java`, `Predicate.not`. Java EE and CORBA removed |
| 12 | `Collectors.teeing`, `String.indent/transform`, `CompactNumberFormat` |
| 14 | switch expressions (final), helpful NPE messages, CMS GC removed |
| 15 | text blocks (final), `String.formatted()`, ZGC and Shenandoah production-ready, Nashorn removed |
| 16 | records, pattern matching for `instanceof`, `Stream.toList()`, strong encapsulation of JDK internals by default |
| 17 (LTS) | sealed classes, `RandomGenerator`, strongly encapsulated internals, Security Manager deprecated |
| 18 | UTF-8 by default, `jwebserver`, `@snippet` in Javadoc |
| 19–20 | mostly previews (virtual threads, record patterns) |
| 21 (LTS) | virtual threads, pattern matching for `switch`, record patterns, sequenced collections, generational ZGC |

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Java now moves in small six-monthly steps, and every two years one release becomes LTS with years of updates. Most teams jump from LTS to LTS (8 to 11 to 17 to 21).

**Real-life example:** This project runs on Java 17, so records, sealed types, text blocks and switch expressions all work here. Virtual threads and pattern matching for `switch` need Java 21. My program checks `Runtime.version().feature()` and reports `virtual threads available? false`.

**Trade-off:** Staying on an old LTS is stable and well known, but you miss language features, performance (GC improvements) and security fixes, and library support ends. Upgrading costs testing and dependency work (chapter 21).

## 3. Code I wrote and what I observed
- `Practice.java`: one running line per version from 8 to 17. The 18 and 21 examples are in comments.
- Output highlights:
  - 9: `Stream.iterate(1, n -> n < 100, n -> n * 2)` gave `[1, 2, 4, 8, 16, 32, 64]`.
  - 11: `lines()` + `Predicate.not(String::isBlank)` + `strip()` gave `[milk, bread]`.
  - 12: `teeing` average of 2, 4 and 9 gave `5.0`.
  - 14: `APRIL has 30 days` from a switch expression.
  - 15: a text block plus `formatted`.
- On `[1, 2, 3, 10, 4, 5]`:

  | Operation | Result |
  |---|---|
  | `takeWhile(n < 5)` | `[1, 2, 3]` (stops at the first failure) |
  | `filter(n < 5)` | `[1, 2, 3, 4]` (checks everything) |
  | `dropWhile(n < 5)` | `[10, 4, 5]` |

- `Stream.toList()` (16) is unmodifiable, while `collect(Collectors.toList())` let me `add`.
- Compact numbers: `1500` became `2K`, and `2_500_000` became `2M`, not `3M`. It rounds half-even.
- `var list = new ArrayList<String>()` inferred `ArrayList`, not `List`.
- I'm running `17.0.19+10-1-25.10.2-Ubuntu`, feature release `17`.
- My release-date loop reproduced the calendar: 9 is Sep 2017, 10 is Mar 2018, and LTS are 11, 17, 21 and 25.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What are the most important features from Java 9 to 21? | 9: modules, JShell, `List.of`. 10: `var`. 11: HttpClient and new String methods. 14: switch expressions and helpful NPEs. 15: text blocks. 16: records, `instanceof` patterns, `toList()`. 17: sealed classes. 21: virtual threads, switch patterns, record patterns, sequenced collections. |
| What is an LTS release? Which ones are LTS? | A release with years of updates and security fixes. 8, 11, 17, 21 and 25. |
| What are preview features? | Finished but not yet permanent features, released for feedback. You opt in with `--enable-preview` at compile and run time. They may change or be removed (string templates were withdrawn). |
| `takeWhile` vs `filter`? | `takeWhile` stops at the first element that fails. `filter` checks every element. |
| `Stream.toList()` vs `Collectors.toList()`? | `toList()` (16) returns an unmodifiable list. `Collectors.toList()` makes no promise, and in practice gives a modifiable `ArrayList`. |
| Is `var` dynamic typing? | No. The type is inferred at compile time and fixed. It only works for local variables with an initialiser. |

## 5. Gotchas / mistakes I made
- Swapping `collect(Collectors.toList())` for `.toList()` can break code that later adds to the list.
- `var` infers the concrete type (`ArrayList`), so `list = new LinkedList<>()` would not compile later.
- Compact number formatting rounds half-even (`2.5M` becomes `2M`).
- A text block keeps the indentation relative to the closing `"""`, so where you put it matters.
- Features that need 21 (pattern switch, virtual threads, `reversed()`) don't compile in this Java 17 module.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
