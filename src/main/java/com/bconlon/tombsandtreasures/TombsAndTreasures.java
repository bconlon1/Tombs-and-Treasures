package com.bconlon.tombsandtreasures;

import com.bconlon.tombsandtreasures.client.TombsClient;
import com.bconlon.tombsandtreasures.common.registry.TombsItems;
import com.bconlon.tombsandtreasures.core.data.TombsItemModelData;
import com.bconlon.tombsandtreasures.core.data.TombsLangData;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
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

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TombsClient::clientInitialization);

        DeferredRegister<?>[] registers = {
                TombsItems.ITEMS,
        };

        for (DeferredRegister<?> register : registers) {
            register.register(modEventBus);
        }
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }

    public void curiosSetup(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("dungeon_artifact").size(3).build());
    }

    public void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeClient()) {
            generator.addProvider(new TombsItemModelData(generator, helper));
            generator.addProvider(new TombsLangData(generator));
        }
        if (event.includeServer()) {

        }
    }
}
