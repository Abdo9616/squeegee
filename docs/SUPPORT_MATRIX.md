# Support matrix

This table records the targets that have actually been checked. “Build” means
the target's Gradle `build` task completed. Interactive smoke tests are listed
separately and are not marked as passed without running them in a development
server/client session.

| Target | JDK | Build | Smoke tests | Notes |
| --- | --- | --- | --- | --- |
| Fabric 26.1 | 25.0.2 | Passed | Partial | `runServer` loaded 42 mods, reached “Done,” and generated the default config. Gradle 9.8.2, Fabric Loom 1.19.3 resolved from the configured `1.19-SNAPSHOT` selector; unobfuscated names. Command, permission, reload, LAN, vanilla-client, and syntax-error cases remain to be exercised. |
| Forge 1.20.1 | Java 21 Gradle JVM with Java 17 toolchain | Passed | Not completed | `runServer` began its first large asset download and was stopped before Forge initialization. Forge 47.4.28, ForgeGradle 6, official mappings. A locally installed JDK 17 is still recommended for the Gradle JVM. |

The remaining 1.20.x and 26.x families are intentionally not listed until the
exact Minecraft versions and loader/toolchain combinations are selected and
verified.
