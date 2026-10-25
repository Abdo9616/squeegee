package com.example.squeegee;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;

public final class SqueegeeAccess {
    private SqueegeeAccess() {
    }

    public static boolean canUseStaffCommands(CommandSourceStack source) {
        ServerPlayer player = source.getPlayer();
        if (player == null) {
            return true;
        }

        SqueegeeConfig.Permissions rules = SqueegeeConfig.get().permissions;
        if (rules == null) {
            rules = new SqueegeeConfig.Permissions();
        }

        if (rules.singleplayerHostAlwaysAllowed && Compat.isSingleplayerHost(source, player)) {
            return true;
        }
        if (rules.allowOps && Compat.hasOpLevel(source, rules.minOpLevel)) {
            return true;
        }

        if (rules.allowedTags != null) {
            for (String tag : rules.allowedTags) {
                if (tag != null && Compat.hasTag(player, tag)) {
                    return true;
                }
            }
        }
        return false;
    }
}
