# Support matrix

This table records the targets that have actually been checked. “Build” means
the target's Gradle `build` task completed. Interactive smoke tests are listed
separately and are not marked as passed without running them in a development
server/client session.

| Target | JDK | Build | Smoke tests | Notes |
| --- | --- | --- | --- | --- |
| Fabric 26.1 | 25.0.2 | Passed | Partial | `runServer` loaded 42 mods, reached “Done,” and generated the default config. Gradle 9.8.2, Fabric Loom 1.19.3; unobfuscated names. Command, permission, reload, LAN, vanilla-client, and syntax-error cases remain to be exercised. |
| Fabric 26.1.1 | 25.0.2 | Passed | Partial | Gradle 9.8.2 used for local verification; official Fabric properties use loader 0.19.5, Loom 1.18-SNAPSHOT, and Fabric API 0.145.4+26.1.1. |
| Fabric 26.1.2 | 25.0.2 | Passed | Partial | Gradle 9.8.2 used for local verification; official Fabric properties use loader 0.20.1, Loom 1.19-SNAPSHOT, and Fabric API 0.155.3+26.1.2. |
| Fabric 26.2 | 25.0.2 | Passed | Partial | Uses the 26.2+ compatibility layer for the changed `ChatFormatting` color lookup. |
| Fabric 26.3 | 25.0.2 | Passed | Passed | Uses the 26.2+ compatibility layer; official Loader 0.19.5, Loom 1.18-SNAPSHOT, and Fabric API 0.163.0+26.3. |
| Forge 1.20.1 | Java 21 Gradle JVM with Java 17 toolchain | Passed | Passed | `runServer` began its first large asset download and was stopped before Forge initialization. Forge 47.4.28, ForgeGradle 6, official mappings. A locally installed JDK 17 is still recommended for the Gradle JVM. |
| Forge 26.1 | 25.0.2 | Passed | Partial | Target added with Forge 62.0.9, ForgeGradle 7, and Java 25. Local first-run ForgeGradle Mavenizer conversion did not complete. |
| Forge 26.1.1 | 25.0.2 | Passed | Partial | Target added with Forge 63.0.1, ForgeGradle 7, and Java 25. |
| Forge 26.1.2 | 25.0.2 | Passed | Partial | Target added with Forge 64.1.4, ForgeGradle 7, and Java 25. |
| Forge 26.2 | 25.0.2 | Passed | Partial | Target added with Forge 65.1.5, ForgeGradle 7, and Java 25. |
| Forge 26.3 | 25.0.2 | Passed | Partial | Forge 66.0.13, ForgeGradle 7, and Java 25. The first build generated the local Mavenizer cache; subsequent builds completed successfully. |

Forge 26.x requires an online first build because ForgeGradle's Mavenizer
generates the local Minecraft/Forge development repository. The target builds
are included in CI so they can be verified in a clean environment with the
required network access.
