# CLAUDE.md

Guidance for Claude Code when working in this repository.

## What this repo is
A personal **study and practice workspace** for the Java Backend Interview Handbook
(https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A; the index is in `Java Handbook.pdf`). The owner is learning, so the goal is understanding,
not just finished code.

## Layout
Maven multi-module project (parent `pom.xml`, Java release 17, switch to 21 when JDK 21 is installed).
- `01-oop-exceptions/` — OOP, Exceptions & Modern Java (21 chapters, handbook page `oop.html`)
- `02-collections/` — Java Collections Handbook (24 chapters, handbook page `collections.html`)
- `03-jvm-memory-strings/` — JVM, Memory & Strings (14 chapters, handbook page `jvm.html`)
- `04-java8-streams/` — Java 8 & Streams Handbook (19 chapters, handbook page `java8.html`)
- `05-concurrency/` — Java Concurrency Handbook (20 chapters, handbook page `concurrency.html`)
- `06-design-patterns/` — Java Patterns Handbook (32 chapters, handbook page `patterns.html`)
- `07-sql/` — SQL for Java Developers (24 chapters, handbook page `sql.html`)
- `08-spring-boot/` — Spring Boot Interview Handbook (22 chapters, handbook page `spring-boot.html`)
- `09-docker-kubernetes/` — Docker & Kubernetes for Java (25 chapters, handbook page `deploy.html`)

Inside each module:
- `chapters/cNN_<slug>/` — one folder per chapter. It is also the Java package (`sourceDirectory` = `chapters`).
  Patterns chapters are nested under `foundations|solid|creational|structural|behavioral|applied/`, so their package is
  `<group>.cNN_<slug>`.
  - `NOTES.md` — the learner's notes (fixed template: key concepts, own words, code observations, interview Qs, gotchas, done checklist).
  - `Practice.java` — runnable practice class with TODOs. Extra classes for the chapter go in the same package.
  - `practice.sql` (07-sql), `Dockerfile` / `compose.yaml` / `k8s/` (09-docker-kubernetes) where relevant.
- `review/` — quiz, interview Q&A, facts and cheat sheets. These have no branch.
- `tests/` — JUnit 5 tests (`testSourceDirectory`).

## Branching rules
- `master` = stable base. Never commit study work directly on master, except for progress ticks and review files.
- One branch per chapter: `topic/<NN-handbook>/<NN-chapter-slug>` (list: `git branch --list 'topic/*'`).
  On a topic branch, change only that chapter's folder (and optionally that handbook's `review/`).
- Milestone projects: branch `project/phase-NN`, code in `projects/phase-NN/`.
- Merge into master with `git merge --no-ff <branch>` (or a PR). Commit style: `study(<handbook>/<NN>): …`,
  `practice(<handbook>/<NN>): …`, `progress: …`, `docs: …`.

## Progress tracking
- `CHECKLIST.md` is the single source of truth. Its order is the industry-standard study sequence (10 phases).
- When a chapter is merged into master, change its line from `- [ ]` to `- [x]`. Keep the step numbers and order unchanged.
- `./progress.sh` prints per-phase and per-handbook progress, branches with unmerged work, and the next step.
- Asked "what next?" or "my progress" → run `./progress.sh` and answer from its output.

## How to help the learner
- Default to **coaching**. Explain the concept, give hints, review their code and NOTES.md, and ask them to predict output.
  Write a full solution only when they ask for one.
- Explain in this order: definition → real-life example → code → trade-off → typical interview question.
- Keep examples small and runnable from `Practice.java`, with Java 17 syntax (say so when a feature needs 21+).
- When reviewing a chapter, check it against the NOTES.md "Done checklist".
- Do not create, delete or rename topic branches, and do not rewrite CHECKLIST.md order, unless asked.

## Commands
```bash
mvn -q compile                                                            # build everything
mvn -q -pl <module> compile exec:java -Dexec.mainClass=<package>.Practice   # run one chapter
mvn -q -pl <module> test                                                  # run that module's tests
./progress.sh                                                             # progress + next step
```
