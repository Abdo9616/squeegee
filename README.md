# Squeegee

Squeegee is a server-side staff utility for clearing the chat of every online
player with a red announcement. Vanilla clients can join a server running it;
no client installation is required.

Commands are `/clearchat`, `/cc`, `/cls`, and `/wipe`. Each accepts an optional
reason, for example `/cc spam`. Staff access is controlled by operator level,
entity tags, and the singleplayer/LAN host setting in `config/squeegee.json`.
The `/squeegee reload` command reloads that file and refreshes command trees.

## Supported builds

The repository uses independent Gradle builds because the supported Minecraft
families require different Java and Gradle toolchains.

| Build | Loader | Minecraft | Java | Build command |
| --- | --- | --- | --- | --- |
| `fabric-26.1` | Fabric | 26.1 | 25 | `./gradlew build` |
| `forge-1.20.1` | Forge | 1.20.1 | 17 toolchain | `./gradlew build` |

See [the support matrix](docs/SUPPORT_MATRIX.md) for verification status.

## Development

Run `runServer` or `runClient` from the target directory. Do not commit the
generated `run/`, `build/`, or `.gradle/` directories. Forge development uses
the official ForgeGradle 6 MDK and official Mojang mappings; Fabric 26.1 uses
the unobfuscated `net.fabricmc.fabric-loom` plugin.

The shared implementation is under `common/`. Only version-sensitive calls
belong in the corresponding `compat/` family. The package remains
`com.example.squeegee` pending an explicit package rename decision.
