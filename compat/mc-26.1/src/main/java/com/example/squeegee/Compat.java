package com.example.squeegee;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.Permissions;

/** Minecraft 26.1 compatibility calls kept out of shared code. */
final class Compat {
    private Compat() {
    }

    static boolean hasOpLevel(CommandSourceStack source, int level) {
        if (level <= 0) {
            return true;
        }
        Permission required = switch (level) {
            case 1 -> Permissions.COMMANDS_MODERATOR;
            case 2 -> Permissions.COMMANDS_GAMEMASTER;
            case 3 -> Permissions.COMMANDS_ADMIN;
            default -> Permissions.COMMANDS_OWNER;
        };
        return source.permissions().hasPermission(required);
    }

    static boolean isSingleplayerHost(CommandSourceStack source, ServerPlayer player) {
        return source.getServer().isSingleplayerOwner(player.nameAndId());
    }

    static boolean hasTag(ServerPlayer player, String tag) {
        return player.entityTags().contains(tag);
    }
}
