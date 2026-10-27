# Squeegee

[![Get it on Modrinth](https://img.shields.io/badge/Get%20it%20on-Modrinth-black?style=for-the-badge&logo=modrinth&logoColor=00AF5C)](https://modrinth.com/project/)
<a href="https://www.curseforge.com/minecraft/mc-mods/">
  <img
    alt="Get it on CurseForge"
    src="https://img.shields.io/badge/Get%20it%20on-CurseForge-f16436?style=for-the-badge&logo=curseforge&logoColor=white"
  />
</a>

Squeegee is a lightweight server-side (and singleplayer) staff utility for clearing chat for everyone online with a visible red warning.

It is designed for moderation and administrative use on Minecraft servers. The mod works on the server side only, so vanilla clients do not need any client-side installation.

<img src="https://github.com/Abdo9616/squeegee/modpage/screenshot.png" alt="Squeegee screenshot" width="960" />

## Commands

- `/clearchat [reason]`
- `/cc [reason]`
- `/cls [reason]`
- `/wipe [reason]`
- `/squeegee reload`

*each command can be disabled in the config file*
```
  "commands": {
    "clearchat": true,
    "cc": false,
    "cls": false,
    "wipe": false
  },
  ```

## Permissions and config

Access is controlled through a combination of:

- operator permissions
- player tags
- singleplayer/LAN host status
- configuration in `config/squeegee.json`

Tagged players can use the clear-chat commands by default, but reloading is restricted unless explicitly allowed.

## Supported versions

Fabric:
- 26.1
- 26.1.1
- 26.1.2
- 26.2
- 26.3

Forge:
- 1.20.1
- 26.1
- 26.1.1
- 26.1.2
- 26.2
- 26.3

See the support [matrix](https://github.com/Abdo9616/squeegee/blob/main/docs/SUPPORT_MATRIX.md) in the project repository for current verification status.

## Installation

1. Download the matching jar for your loader and Minecraft version.
2. Place it in the server `mods` folder.
3. Restart the server.
4. Use the commands as a staff member or operator.

## License

CC0-1.0
