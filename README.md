<img src="fabric/26.3/src/main/resources/assets/squeegee/icon.png" alt="Squeegee icon" width="128" height="128" />

# Squeegee

[![Get it on Modrinth](https://img.shields.io/badge/Get%20it%20on-Modrinth-black?style=for-the-badge&logo=modrinth&logoColor=00AF5C)](https://modrinth.com/project/)
<a href="https://www.curseforge.com/minecraft/mc-mods/">
  <img
    alt="Get it on CurseForge"
    src="https://img.shields.io/badge/Get%20it%20on-CurseForge-f16436?style=for-the-badge&logo=curseforge&logoColor=white"
  />
</a>

Squeegee is a lightweight server-side chat cleanup utility for Minecraft servers.
It lets staff instantly clear chat for everyone online with a visible red warning,
while keeping the client side fully vanilla-friendly. Players do not need a mod
installed on their client; only the server needs the plugin/mod.

## Features

- Clear chat for all online players with `/clearchat`, `/cc`, `/cls`, or `/wipe`
- Optional reason support, for example `/cc spam`
- Permission control based on operator level, tagged players, and the LAN/singleplayer host setting
- Live config reload via `/squeegee reload`
- Works on both Fabric and Forge targets
- Designed for server administration and staff moderation workflows

## Commands

- `/clearchat [reason]`
- `/cc [reason]`
- `/cls [reason]`
- `/wipe [reason]`
- `/squeegee reload`

The command system supports an optional custom reason, such as `/cc spam`.

## Permissions and config

Staff access is controlled by a combination of:

- operator status
- entity tags
- singleplayer/LAN host ownership
- the configuration file at `config/squeegee.json`

Tagged players can use the clear-chat commands by default, but
`permissions.allowTaggedPlayersToReload` is disabled by default. That means only
operators, the singleplayer host, or the server console can reload config unless
that permission is explicitly enabled.

## Supported versions

This project ships as separate Gradle builds for each supported Minecraft family.

| Target | Loader | Minecraft | Java |
| --- | --- | --- | --- |
| `fabric/26.1` | Fabric | 26.1 | 25 |
| `fabric/26.1.1` | Fabric | 26.1.1 | 25 |
| `fabric/26.1.2` | Fabric | 26.1.2 | 25 |
| `fabric/26.2` | Fabric | 26.2 | 25 |
| `fabric/26.3` | Fabric | 26.3 | 25 |
| `forge/1.20.1` | Forge | 1.20.1 | 17 toolchain |
| `forge/26.1` | Forge | 26.1 | 25 |
| `forge/26.1.1` | Forge | 26.1.1 | 25 |
| `forge/26.1.2` | Forge | 26.1.2 | 25 |
| `forge/26.2` | Forge | 26.2 | 25 |
| `forge/26.3` | Forge | 26.3 | 25 |

See [docs/SUPPORT_MATRIX.md](docs/SUPPORT_MATRIX.md) for the current verification status of each target.

## Installation

1. Download the matching jar for your loader and Minecraft version.
2. Place it in your server's `mods` folder.
3. Start or restart the server.
4. Use the commands above as an operator or configured staff member.

## Development

For local builds and testing, run the relevant Gradle task from the target directory:

```bash
./gradlew build
```

Common logic is stored in `common/`, while version-specific compatibility layers live under `compat/` and the loader-specific project folders.

## Notes

- Forge 1.20.1 uses ForgeGradle 6 and a Java 17 toolchain.
- Forge 26.x uses ForgeGradle 7 and Java 25.
- Fabric targets use the standard Fabric Loom setup.
- This repository is organized around independent target folders rather than a single universal build.

## License

This project is released under the CC0-1.0 license.
