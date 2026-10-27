package com.example.squeegee;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(SqueegeeForge.MOD_ID)
public final class SqueegeeForge {
    public static final String MOD_ID = "squeegee";

    public SqueegeeForge() {
        SqueegeeConfig.load(FMLPaths.CONFIGDIR.get());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        SqueegeeCommands.register(event.getDispatcher());
    }
}
