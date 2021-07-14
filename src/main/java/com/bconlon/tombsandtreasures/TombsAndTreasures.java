package com.bconlon.tombsandtreasures;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod(TombsAndTreasures.MODID)
public class TombsAndTreasures
{
    public static final String MODID = "tombsandtreasures";
    private static final Logger LOGGER = LogManager.getLogger();

    public TombsAndTreasures() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::curiosSetup);
        modEventBus.addListener(this::dataSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("dungeons_artifact").size(3).build());

    }

    public void curiosSetup(InterModEnqueueEvent event) {

    }

    public void dataSetup(GatherDataEvent event) {

    }
}
