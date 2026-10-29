# 01 · OOP, Exceptions & Modern Java

The four pillars, overloading vs overriding, abstract classes vs interfaces, exception handling end to end, and every major change from Java 8 to 21 (plus 22-25).

- Handbook: https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html`
- Chapters: **21** (one branch each) · Review files: **3**
- Study order across handbooks: [CHECKLIST.md](../CHECKLIST.md)

## Chapters

| # | Chapter | Branch | Folder | Handbook anchor |
|---|---|---|---|---|
| 01 | Classes & objects | `topic/01-oop-exceptions/01-classes-objects` | [chapters/c01_classes_objects](chapters/c01_classes_objects) | `classes` |
| 02 | Access modifiers | `topic/01-oop-exceptions/02-access-modifiers` | [chapters/c02_access_modifiers](chapters/c02_access_modifiers) | `access` |
| 03 | Encapsulation | `topic/01-oop-exceptions/03-encapsulation` | [chapters/c03_encapsulation](chapters/c03_encapsulation) | `encapsulation` |
| 04 | Inheritance | `topic/01-oop-exceptions/04-inheritance` | [chapters/c04_inheritance](chapters/c04_inheritance) | `inheritance` |
| 05 | Polymorphism | `topic/01-oop-exceptions/05-polymorphism` | [chapters/c05_polymorphism](chapters/c05_polymorphism) | `polymorphism` |
| 06 | Abstraction | `topic/01-oop-exceptions/06-abstraction` | [chapters/c06_abstraction](chapters/c06_abstraction) | `abstraction` |
| 07 | final, static, Object | `topic/01-oop-exceptions/07-final-static-object` | [chapters/c07_final_static_object](chapters/c07_final_static_object) | `keywords` |
| 08 | Composition & casting | `topic/01-oop-exceptions/08-composition-casting` | [chapters/c08_composition_casting](chapters/c08_composition_casting) | `composition` |
| 09 | Nested classes & modern OOP | `topic/01-oop-exceptions/09-nested-classes-modern-oop` | [chapters/c09_nested_classes_modern_oop](chapters/c09_nested_classes_modern_oop) | `nested` |
| 10 | Why exceptions | `topic/01-oop-exceptions/10-why-exceptions` | [chapters/c10_why_exceptions](chapters/c10_why_exceptions) | `exceptions` |
| 11 | Exception hierarchy | `topic/01-oop-exceptions/11-exception-hierarchy` | [chapters/c11_exception_hierarchy](chapters/c11_exception_hierarchy) | `hierarchy` |
| 12 | Checked vs unchecked | `topic/01-oop-exceptions/12-checked-vs-unchecked` | [chapters/c12_checked_vs_unchecked](chapters/c12_checked_vs_unchecked) | `checked` |
| 13 | try / catch / finally | `topic/01-oop-exceptions/13-try-catch-finally` | [chapters/c13_try_catch_finally](chapters/c13_try_catch_finally) | `flow` |
| 14 | Propagation | `topic/01-oop-exceptions/14-exception-propagation` | [chapters/c14_exception_propagation](chapters/c14_exception_propagation) | `propagation` |
| 15 | try-with-resources | `topic/01-oop-exceptions/15-try-with-resources` | [chapters/c15_try_with_resources](chapters/c15_try_with_resources) | `twr` |
| 16 | Custom exceptions | `topic/01-oop-exceptions/16-custom-exceptions` | [chapters/c16_custom_exceptions](chapters/c16_custom_exceptions) | `custom` |
| 17 | Best practices & Spring | `topic/01-oop-exceptions/17-exception-best-practices` | [chapters/c17_exception_best_practices](chapters/c17_exception_best_practices) | `excpractice` |
| 18 | Version explorer (Java 8 → 21) | `topic/01-oop-exceptions/18-java-version-explorer` | [chapters/c18_java_version_explorer](chapters/c18_java_version_explorer) | `versions` |
| 19 | Same code, new Java | `topic/01-oop-exceptions/19-same-code-new-java` | [chapters/c19_same_code_new_java](chapters/c19_same_code_new_java) | `evolution` |
| 20 | Java 22 → 25 | `topic/01-oop-exceptions/20-java-22-to-25` | [chapters/c20_java_22_to_25](chapters/c20_java_22_to_25) | `beyond` |
| 21 | Upgrading from 8 | `topic/01-oop-exceptions/21-upgrading-from-8` | [chapters/c21_upgrading_from_8](chapters/c21_upgrading_from_8) | `migration` |

## Review (no branch)

- [LTS cheat sheet](review/lts-cheat-sheet.md)
- [Interview Q&A](review/interview-qa.md)
- [Quiz](review/quiz.md)

## Build / run

```bash
mvn -q -pl 01-oop-exceptions compile
mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=<package>.Practice
```
