package com.example.squeegee;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SqueegeeCommands {
    private static final Logger LOGGER = LoggerFactory.getLogger("squeegee");

    private SqueegeeCommands() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        // Keep every literal registered so /squeegee reload can toggle aliases live.
        for (String alias : SqueegeeConfig.ALIASES) {
            dispatcher.register(Commands.literal(alias)
                    .requires(source -> SqueegeeConfig.get().isEnabled(alias)
                            && SqueegeeAccess.canUseStaffCommands(source))
                    .executes(context -> clearAll(context.getSource(), ""))
                    .then(Commands.argument("reason", StringArgumentType.greedyString())
                            .executes(context -> clearAll(context.getSource(),
                                    StringArgumentType.getString(context, "reason")))));
        }

        dispatcher.register(Commands.literal("squeegee")
                .requires(SqueegeeAccess::canReloadConfig)
                .then(Commands.literal("reload")
                        .executes(context -> reload(context.getSource()))));
    }

    private static void clear(ServerPlayer player) {
        int lines = Math.max(1, Math.min(500, SqueegeeConfig.get().blankLines));
        for (int i = 0; i < lines; i++) {
            player.sendSystemMessage(Component.empty());
        }
    }

    private static int clearAll(CommandSourceStack source, String reason) {
        SqueegeeConfig config = SqueegeeConfig.get();
        String normalizedReason = reason.trim();
        Component announcement = config.announce
                ? buildAnnouncement(config, source.getTextName(), normalizedReason)
                : null;

        for (ServerPlayer player : source.getServer().getPlayerList().getPlayers()) {
            clear(player);
            if (announcement != null) {
                player.sendSystemMessage(announcement);
            }
        }

        if (announcement != null) {
            LOGGER.info("{}", announcement.getString());
        }
        return 1;
    }

    private static Component buildAnnouncement(SqueegeeConfig config, String name, String reason) {
        String template = reason.isEmpty() ? config.announceFormat : config.announceReasonFormat;
        String text = template.replace("{player}", name).replace("{reason}", reason);
        ChatFormatting color = Compat.getAnnouncementColor(config.announceColor);
        return Component.literal(text).withStyle(color == null ? ChatFormatting.RED : color);
    }

    private static int reload(CommandSourceStack source) {
        SqueegeeConfig.reload();
        MinecraftServer server = source.getServer();
        server.getPlayerList().getPlayers().forEach(player -> server.getCommands().sendCommands(player));
        source.sendSuccess(() -> Component.literal("Squeegee config reloaded"), false);
        return 1;
    }
}
