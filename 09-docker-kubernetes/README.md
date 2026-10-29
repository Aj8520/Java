# 09 · Docker & Kubernetes for Java

Building the Spring Boot fat JAR, production Dockerfiles, layer caching, the JVM in containers, Compose, Kubernetes, probes, rollouts, autoscaling, Helm and CI/CD.

- Handbook: https://claude.ai/artifact/Yb5hF3dXLRm97VZ1SQ5u4A → `deploy.html`
- Chapters: **25** (one branch each) · Review files: **3**
- Study order across handbooks: [CHECKLIST.md](../CHECKLIST.md)

## Chapters

| # | Chapter | Branch | Folder | Handbook anchor |
|---|---|---|---|---|
| 01 | Code to production | `topic/09-docker-kubernetes/01-code-to-production` | [chapters/c01_code_to_production](chapters/c01_code_to_production) | `overview` |
| 02 | Building the JAR | `topic/09-docker-kubernetes/02-building-the-jar` | [chapters/c02_building_the_jar](chapters/c02_building_the_jar) | `build` |
| 03 | Inside the fat JAR | `topic/09-docker-kubernetes/03-inside-fat-jar` | [chapters/c03_inside_fat_jar](chapters/c03_inside_fat_jar) | `fatjar` |
| 04 | Running it · JAR vs WAR | `topic/09-docker-kubernetes/04-jar-vs-war` | [chapters/c04_jar_vs_war](chapters/c04_jar_vs_war) | `jarwar` |
| 05 | Layers, CDS, native | `topic/09-docker-kubernetes/05-layers-cds-native` | [chapters/c05_layers_cds_native](chapters/c05_layers_cds_native) | `layers` |
| 06 | Why containers | `topic/09-docker-kubernetes/06-why-containers` | [chapters/c06_why_containers](chapters/c06_why_containers) | `containers` |
| 07 | Writing a Dockerfile | `topic/09-docker-kubernetes/07-writing-dockerfile` | [chapters/c07_writing_dockerfile](chapters/c07_writing_dockerfile) | `dockerfile` |
| 08 | Layers & build cache | `topic/09-docker-kubernetes/08-build-cache` | [chapters/c08_build_cache](chapters/c08_build_cache) | `cache` |
| 09 | Base images & buildpacks | `topic/09-docker-kubernetes/09-base-images-buildpacks` | [chapters/c09_base_images_buildpacks](chapters/c09_base_images_buildpacks) | `baseimage` |
| 10 | The JVM in a container | `topic/09-docker-kubernetes/10-jvm-in-container` | [chapters/c10_jvm_in_container](chapters/c10_jvm_in_container) | `jvmmem` |
| 11 | CLI, networks, volumes | `topic/09-docker-kubernetes/11-cli-networks-volumes` | [chapters/c11_cli_networks_volumes](chapters/c11_cli_networks_volumes) | `cli` |
| 12 | Docker Compose | `topic/09-docker-kubernetes/12-docker-compose` | [chapters/c12_docker_compose](chapters/c12_docker_compose) | `compose` |
| 13 | Image security | `topic/09-docker-kubernetes/13-image-security` | [chapters/c13_image_security](chapters/c13_image_security) | `imgsec` |
| 14 | Why Kubernetes & architecture | `topic/09-docker-kubernetes/14-k8s-architecture` | [chapters/c14_k8s_architecture](chapters/c14_k8s_architecture) | `k8s` |
| 15 | Core objects | `topic/09-docker-kubernetes/15-k8s-core-objects` | [chapters/c15_k8s_core_objects](chapters/c15_k8s_core_objects) | `objects` |
| 16 | Deploying Spring Boot | `topic/09-docker-kubernetes/16-deploying-spring-boot` | [chapters/c16_deploying_spring_boot](chapters/c16_deploying_spring_boot) | `deploy` |
| 17 | Health probes & shutdown | `topic/09-docker-kubernetes/17-probes-shutdown` | [chapters/c17_probes_shutdown](chapters/c17_probes_shutdown) | `probes` |
| 18 | Requests, limits, QoS | `topic/09-docker-kubernetes/18-requests-limits-qos` | [chapters/c18_requests_limits_qos](chapters/c18_requests_limits_qos) | `resources` |
| 19 | Rolling updates | `topic/09-docker-kubernetes/19-rolling-updates` | [chapters/c19_rolling_updates](chapters/c19_rolling_updates) | `rollout` |
| 20 | Autoscaling | `topic/09-docker-kubernetes/20-autoscaling` | [chapters/c20_autoscaling](chapters/c20_autoscaling) | `hpa` |
| 21 | Ingress & Gateway API | `topic/09-docker-kubernetes/21-ingress-gateway-api` | [chapters/c21_ingress_gateway_api](chapters/c21_ingress_gateway_api) | `expose` |
| 22 | Troubleshooting pods | `topic/09-docker-kubernetes/22-troubleshooting-pods` | [chapters/c22_troubleshooting_pods](chapters/c22_troubleshooting_pods) | `troubleshoot` |
| 23 | Helm & Kustomize | `topic/09-docker-kubernetes/23-helm-kustomize` | [chapters/c23_helm_kustomize](chapters/c23_helm_kustomize) | `helm` |
| 24 | CI/CD & GitOps | `topic/09-docker-kubernetes/24-cicd-gitops` | [chapters/c24_cicd_gitops](chapters/c24_cicd_gitops) | `cicd` |
| 25 | 25 scenario questions | `topic/09-docker-kubernetes/25-scenario-questions` | [chapters/c25_scenario_questions](chapters/c25_scenario_questions) | `scenarios` |

## Review (no branch)

- [kubectl cheat sheet](review/kubectl-cheat-sheet.md)
- [Interview Q&A](review/interview-qa.md)
- [Quiz](review/quiz.md)

## Build / run

```bash
mvn -q -pl 09-docker-kubernetes compile
mvn -q -pl 09-docker-kubernetes compile exec:java -Dexec.mainClass=<package>.Practice
```
