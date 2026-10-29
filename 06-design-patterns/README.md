# 06 · Java Patterns Handbook

SOLID principles and all 23 GoF design patterns with real-life analogies, before/after Java code, class diagrams and a Spring Boot case study.

- Handbook: https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `patterns.html`
- Chapters: **32** (one branch each) · Review files: **3**
- Study order across handbooks: [CHECKLIST.md](../CHECKLIST.md)

## Chapters

| # | Chapter | Branch | Folder | Handbook anchor |
|---|---|---|---|---|
| 01 | Foundations: the four OOP ideas | `topic/06-design-patterns/01-oop-foundations` | [chapters/foundations/c01_oop_foundations](chapters/foundations/c01_oop_foundations) | `foundations` |
| 02 | Single Responsibility Principle | `topic/06-design-patterns/02-srp` | [chapters/solid/c02_srp](chapters/solid/c02_srp) | `srp` |
| 03 | Open / Closed Principle | `topic/06-design-patterns/03-ocp` | [chapters/solid/c03_ocp](chapters/solid/c03_ocp) | `ocp` |
| 04 | Liskov Substitution Principle | `topic/06-design-patterns/04-lsp` | [chapters/solid/c04_lsp](chapters/solid/c04_lsp) | `lsp` |
| 05 | Interface Segregation Principle | `topic/06-design-patterns/05-isp` | [chapters/solid/c05_isp](chapters/solid/c05_isp) | `isp` |
| 06 | Dependency Inversion Principle | `topic/06-design-patterns/06-dip` | [chapters/solid/c06_dip](chapters/solid/c06_dip) | `dip` |
| 07 | Singleton | `topic/06-design-patterns/07-singleton` | [chapters/creational/c07_singleton](chapters/creational/c07_singleton) | `singleton` |
| 08 | Factory Method | `topic/06-design-patterns/08-factory-method` | [chapters/creational/c08_factory_method](chapters/creational/c08_factory_method) | `factory-method` |
| 09 | Abstract Factory | `topic/06-design-patterns/09-abstract-factory` | [chapters/creational/c09_abstract_factory](chapters/creational/c09_abstract_factory) | `abstract-factory` |
| 10 | Builder | `topic/06-design-patterns/10-builder` | [chapters/creational/c10_builder](chapters/creational/c10_builder) | `builder` |
| 11 | Prototype | `topic/06-design-patterns/11-prototype` | [chapters/creational/c11_prototype](chapters/creational/c11_prototype) | `prototype` |
| 12 | Adapter | `topic/06-design-patterns/12-adapter` | [chapters/structural/c12_adapter](chapters/structural/c12_adapter) | `adapter` |
| 13 | Bridge | `topic/06-design-patterns/13-bridge` | [chapters/structural/c13_bridge](chapters/structural/c13_bridge) | `bridge` |
| 14 | Composite | `topic/06-design-patterns/14-composite` | [chapters/structural/c14_composite](chapters/structural/c14_composite) | `composite` |
| 15 | Decorator | `topic/06-design-patterns/15-decorator` | [chapters/structural/c15_decorator](chapters/structural/c15_decorator) | `decorator` |
| 16 | Facade | `topic/06-design-patterns/16-facade` | [chapters/structural/c16_facade](chapters/structural/c16_facade) | `facade` |
| 17 | Flyweight | `topic/06-design-patterns/17-flyweight` | [chapters/structural/c17_flyweight](chapters/structural/c17_flyweight) | `flyweight` |
| 18 | Proxy | `topic/06-design-patterns/18-proxy` | [chapters/structural/c18_proxy](chapters/structural/c18_proxy) | `proxy` |
| 19 | Chain of Responsibility | `topic/06-design-patterns/19-chain-of-responsibility` | [chapters/behavioral/c19_chain_of_responsibility](chapters/behavioral/c19_chain_of_responsibility) | `chain-of-responsibility` |
| 20 | Command | `topic/06-design-patterns/20-command` | [chapters/behavioral/c20_command](chapters/behavioral/c20_command) | `command` |
| 21 | Interpreter | `topic/06-design-patterns/21-interpreter` | [chapters/behavioral/c21_interpreter](chapters/behavioral/c21_interpreter) | `interpreter` |
| 22 | Iterator | `topic/06-design-patterns/22-iterator` | [chapters/behavioral/c22_iterator](chapters/behavioral/c22_iterator) | `iterator` |
| 23 | Mediator | `topic/06-design-patterns/23-mediator` | [chapters/behavioral/c23_mediator](chapters/behavioral/c23_mediator) | `mediator` |
| 24 | Memento | `topic/06-design-patterns/24-memento` | [chapters/behavioral/c24_memento](chapters/behavioral/c24_memento) | `memento` |
| 25 | Observer | `topic/06-design-patterns/25-observer` | [chapters/behavioral/c25_observer](chapters/behavioral/c25_observer) | `observer` |
| 26 | State | `topic/06-design-patterns/26-state` | [chapters/behavioral/c26_state](chapters/behavioral/c26_state) | `state` |
| 27 | Strategy | `topic/06-design-patterns/27-strategy` | [chapters/behavioral/c27_strategy](chapters/behavioral/c27_strategy) | `strategy` |
| 28 | Template Method | `topic/06-design-patterns/28-template-method` | [chapters/behavioral/c28_template_method](chapters/behavioral/c28_template_method) | `template-method` |
| 29 | Visitor | `topic/06-design-patterns/29-visitor` | [chapters/behavioral/c29_visitor](chapters/behavioral/c29_visitor) | `visitor` |
| 30 | Look-alike patterns, told apart | `topic/06-design-patterns/30-look-alike-patterns` | [chapters/applied/c30_look_alike_patterns](chapters/applied/c30_look_alike_patterns) | `compare` |
| 31 | One order, a dozen patterns: Spring Boot walkthrough | `topic/06-design-patterns/31-spring-boot-case-study` | [chapters/applied/c31_spring_boot_case_study](chapters/applied/c31_spring_boot_case_study) | `case` |
| 32 | Anti-patterns and common mistakes | `topic/06-design-patterns/32-anti-patterns` | [chapters/applied/c32_anti_patterns](chapters/applied/c32_anti_patterns) | `pitfalls` |

## Review (no branch)

- [Which pattern do I need?](review/which-pattern.md)
- [Cheat sheet](review/cheat-sheet.md)
- [Spot the pattern (quiz)](review/quiz.md)

## Build / run

```bash
mvn -q -pl 06-design-patterns compile
mvn -q -pl 06-design-patterns compile exec:java -Dexec.mainClass=<package>.Practice
```
