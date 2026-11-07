# 01.21 · Upgrading from Java 8

> Handbook: **OOP, Exceptions & Modern Java** — https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `oop.html#migration`
> Branch: `topic/01-oop-exceptions/21-upgrading-from-8`

## 1. Key concepts (fill while reading)
- Most code compiles unchanged. The pain is in libraries and removed modules.
- Go 8 → 11 → 17 → 21 (or straight to 21), upgrading dependencies first.
- Common surprises:
  1. Java EE modules were removed in 11 (JAXB, JAX-WS, `javax.annotation`, CORBA). Add explicit dependencies, such as `jakarta.xml.bind:jakarta.xml.bind-api`.
  2. Internal APIs are locked down (16/17): `sun.misc.*`, and `setAccessible` on JDK internals throws `InaccessibleObjectException`. Upgrade the libraries (Lombok, Mockito, ByteBuddy, Spring). The last resort is `--add-opens java.base/java.lang=ALL-UNNAMED`.
  3. Spring Boot 3 / Jakarta EE 9+ renamed packages from `javax.*` to `jakarta.*`, and needs Java 17+.
  4. The default charset is UTF-8 from Java 18. Before that it was the OS default, such as windows-1252.
  5. Removed: Nashorn (15), the CMS GC (14), applets, and the Security Manager (deprecated in 17, disabled in 24).
  6. Build tools: the Maven compiler plugin `<release>21</release>`, or Gradle toolchains.
  7. Run `jdeps --jdk-internals app.jar` to find uses of internal APIs before upgrading.

## 2. In my own words
_Explain it as if teaching a junior: definition → real-life example → trade-off._

**Definition:** Upgrading from Java 8 is mostly about the ecosystem, not your own code: replace the Java EE pieces the JDK dropped, update libraries that poked at JDK internals, and make implicit settings (like the charset) explicit.

**Real-life example:** An old app reads a CSV of prices with `new String(bytes)`. On a Windows server with the old default charset, `café ₹500` turns into `cafÃ© â¹500`, and nobody changed a line of code. Passing `StandardCharsets.UTF_8` everywhere makes it behave the same on every machine and every Java version.

**Trade-off:** Upgrading gives years of security fixes, faster GCs, modern language features and current Spring versions. It costs dependency upgrades and a full test run. Staying on 8 avoids that work today but piles up risk (no support, libraries dropping 8).

## 3. Code I wrote and what I observed
- `Practice.java` and `MigrationCheck.java`: checks run live on this JVM (Java 17, `<maven.compiler.release>17</maven.compiler.release>` in the parent `pom.xml`).
- JAXB was not present, and `new ScriptEngineManager().getEngineByName("nashorn")` returned `null` (removed in 15).
- The default charset printed `UTF-8` here. On Linux that was already the OS default. On Java 17 it still depends on the OS, while 18+ always uses UTF-8.
- `setAccessible(true)` worked on my own `Wallet.coins` field (`5`), but on `String.value` it threw `InaccessibleObjectException`. That is strong encapsulation of JDK internals.
- Pre-flight report:
  - `MISSING`: `javax.xml.bind.JAXBContext`, `javax.annotation.PostConstruct`, `javax.xml.ws.Service`, `org.omg.CORBA.ORB`
  - `present`: `java.net.http.HttpClient` (new in 11) and `sun.misc.Unsafe` (still reachable through `jdk.unsupported`)
- The same 12 bytes (for 9 characters) decoded as UTF-8 gave `café ₹500`, and as ISO-8859-1 gave `cafÃ© â¹500`.

## 4. Interview questions on this topic
| Question | My short answer |
|---|---|
| What usually breaks when upgrading from 8 to 17/21? | Removed Java EE modules (JAXB, JAX-WS), libraries using JDK internals (strong encapsulation), the `javax` → `jakarta` rename with Spring Boot 3, old build plugins, and UTF-8 becoming the default in 18. |
| How do you plan the upgrade? | Upgrade the build tools and dependencies first, run `jdeps --jdk-internals`, then move LTS to LTS (or straight to 21), with tests and CI on the new JDK. |
| What is `InaccessibleObjectException`? | Thrown when reflection tries to open a JDK-internal member that the module system doesn't export (16/17+). |
| Is `--add-opens` a fix? | Only a temporary workaround. The real fix is a library version that doesn't need it. |
| Why does Spring Boot 3 force Java 17? | It is built on Jakarta EE 9+ (`jakarta.*` packages) and uses Java 17 features and APIs. |
| How do you avoid charset bugs? | Never rely on the default. Pass `StandardCharsets.UTF_8` to `getBytes`, `new String`, readers and writers. |

## 5. Gotchas / mistakes I made
- My machine already used UTF-8, so the charset problem didn't show by default. It appears on servers with a different OS locale, which makes it easy to miss in testing.
- `sun.misc.Unsafe` is still present, but that doesn't mean it is safe to depend on. It is being phased out.
- `javax.*` to `jakarta.*` is a package rename, so every import changes, not only dependencies.
- Upgrade one LTS step at a time if the app is big, so failures are easier to trace.

## Done checklist
- [ ] Read the handbook chapter and tried its demo
- [x] Filled sections 1–5 above
- [x] All TODOs in `Practice.java` done and it runs
- [ ] Committed, pushed and merged into `master`
