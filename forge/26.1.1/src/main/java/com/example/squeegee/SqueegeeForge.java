package com.example.squeegee;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(SqueegeeForge.MOD_ID)
public final class SqueegeeForge {
    public static final String MOD_ID = "squeegee";

    public SqueegeeForge() {
        SqueegeeConfig.load(FMLPaths.CONFIGDIR.get());
        RegisterCommandsEvent.BUS.addListener(event -> SqueegeeCommands.register(event.getDispatcher()));
    }
}
