package com.bconlon.tombsandtreasures.client;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class TombsClient
{
    public static void clientInitialization() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(TombsClient::clientSetup);
        modEventBus.addListener(TombsClient::clientComplete);
    }

    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            PropertiesCreator.setupItemProperties();
        });
    }

    public static void clientComplete(FMLLoadCompleteEvent event) {

    }
}
