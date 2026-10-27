package com.example.squeegee;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

public class Squeegee implements ModInitializer {
    public static final String MOD_ID = "squeegee";

    @Override
    public void onInitialize() {
        SqueegeeConfig.load(FabricLoader.getInstance().getConfigDir());
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                SqueegeeCommands.register(dispatcher));
    }
}
