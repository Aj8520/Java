# 08 · Spring Boot Interview Handbook

Auto-configuration and @Conditional, stereotypes and DI, REST APIs, Actuator, large data, circuit breakers, sagas, security, testing, and 30 scenario questions.

- Handbook: https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `spring-boot.html`
- Chapters: **22** (one branch each) · Review files: **3**
- Study order across handbooks: [CHECKLIST.md](../CHECKLIST.md)

## Chapters

| # | Chapter | Branch | Folder | Handbook anchor |
|---|---|---|---|---|
| 01 | Spring vs Spring Boot | `topic/08-spring-boot/01-spring-vs-boot` | [chapters/c01_spring_vs_boot](chapters/c01_spring_vs_boot) | `why` |
| 02 | How Boot starts | `topic/08-spring-boot/02-how-boot-starts` | [chapters/c02_how_boot_starts](chapters/c02_how_boot_starts) | `startup` |
| 03 | @Component, @Service… | `topic/08-spring-boot/03-stereotypes` | [chapters/c03_stereotypes](chapters/c03_stereotypes) | `stereotypes` |
| 04 | Dependency injection | `topic/08-spring-boot/04-dependency-injection` | [chapters/c04_dependency_injection](chapters/c04_dependency_injection) | `di` |
| 05 | Scopes & lifecycle | `topic/08-spring-boot/05-scopes-lifecycle` | [chapters/c05_scopes_lifecycle](chapters/c05_scopes_lifecycle) | `scopes` |
| 06 | @Conditional & auto-config | `topic/08-spring-boot/06-conditional-autoconfig` | [chapters/c06_conditional_autoconfig](chapters/c06_conditional_autoconfig) | `conditional` |
| 07 | Configuration & profiles | `topic/08-spring-boot/07-configuration-profiles` | [chapters/c07_configuration_profiles](chapters/c07_configuration_profiles) | `config` |
| 08 | REST APIs | `topic/08-spring-boot/08-rest-apis` | [chapters/c08_rest_apis](chapters/c08_rest_apis) | `rest` |
| 09 | Validation & errors | `topic/08-spring-boot/09-validation-errors` | [chapters/c09_validation_errors](chapters/c09_validation_errors) | `errors` |
| 10 | Actuator | `topic/08-spring-boot/10-actuator` | [chapters/c10_actuator](chapters/c10_actuator) | `actuator` |
| 11 | Large data | `topic/08-spring-boot/11-large-data` | [chapters/c11_large_data](chapters/c11_large_data) | `largedata` |
| 12 | Architecture & patterns | `topic/08-spring-boot/12-microservice-architecture` | [chapters/c12_microservice_architecture](chapters/c12_microservice_architecture) | `micro` |
| 13 | Circuit breaker | `topic/08-spring-boot/13-circuit-breaker` | [chapters/c13_circuit_breaker](chapters/c13_circuit_breaker) | `circuit` |
| 14 | Gateway, discovery, config | `topic/08-spring-boot/14-gateway-discovery-config` | [chapters/c14_gateway_discovery_config](chapters/c14_gateway_discovery_config) | `gateway` |
| 15 | Saga & outbox | `topic/08-spring-boot/15-saga-outbox` | [chapters/c15_saga_outbox](chapters/c15_saga_outbox) | `saga` |
| 16 | Logging & tracing | `topic/08-spring-boot/16-logging-tracing` | [chapters/c16_logging_tracing](chapters/c16_logging_tracing) | `observability` |
| 17 | Security | `topic/08-spring-boot/17-security` | [chapters/c17_security](chapters/c17_security) | `security` |
| 18 | Caching | `topic/08-spring-boot/18-caching` | [chapters/c18_caching](chapters/c18_caching) | `caching` |
| 19 | @Async & scheduling | `topic/08-spring-boot/19-async-scheduling` | [chapters/c19_async_scheduling](chapters/c19_async_scheduling) | `async` |
| 20 | Testing | `topic/08-spring-boot/20-testing` | [chapters/c20_testing](chapters/c20_testing) | `testing` |
| 21 | Deployment & Boot 3/4 | `topic/08-spring-boot/21-deployment-boot3-4` | [chapters/c21_deployment_boot3_4](chapters/c21_deployment_boot3_4) | `deploy` |
| 22 | 30 scenario questions | `topic/08-spring-boot/22-scenario-questions` | [chapters/c22_scenario_questions](chapters/c22_scenario_questions) | `scenarios` |

## Review (no branch)

- [Rapid-fire Q&A](review/rapid-fire-qa.md)
- [Readiness checklist](review/readiness-checklist.md)
- [Quiz](review/quiz.md)

## Build / run

```bash
mvn -q -pl 08-spring-boot compile
mvn -q -pl 08-spring-boot compile exec:java -Dexec.mainClass=<package>.Practice
```
