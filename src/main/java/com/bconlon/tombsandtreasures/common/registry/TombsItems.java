package com.bconlon.tombsandtreasures.common.registry;

import com.bconlon.tombsandtreasures.TombsAndTreasures;
import com.bconlon.tombsandtreasures.common.items.melee.axe.AxeLootItem;
import com.bconlon.tombsandtreasures.common.items.melee.glaive.GlaiveItem;
import com.bconlon.tombsandtreasures.common.items.melee.sword.SwordLootItem;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TombsItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TombsAndTreasures.MODID);

    //GLAIVES
    public static final RegistryObject<GlaiveItem> IRON_GLAIVE = ITEMS.register("iron_glaive",
            () -> new GlaiveItem(ItemTier.IRON, 3.5F, -2.6F, 1.0F, (new Item.Properties()).tab(TombsItemGroups.TOMBS_COMBAT)));
}
