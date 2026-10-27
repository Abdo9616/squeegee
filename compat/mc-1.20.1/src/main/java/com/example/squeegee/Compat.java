package com.example.squeegee;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;

/** Minecraft 1.20.1 compatibility calls kept out of shared code. */
final class Compat {
    private Compat() {
    }

    static boolean hasOpLevel(CommandSourceStack source, int level) {
        return source.hasPermission(level);
    }

    static boolean isSingleplayerHost(CommandSourceStack source, ServerPlayer player) {
        return source.getServer().isSingleplayerOwner(player.getGameProfile());
    }

    static boolean hasTag(ServerPlayer player, String tag) {
        return player.getTags().contains(tag);
    }

    static ChatFormatting getAnnouncementColor(String name) {
        return ChatFormatting.getByName(name);
    }
}
