package com.bconlon.tombsandtreasures.common.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TombsItemGroups
{
    public static final ItemGroup TOMBS_COMBAT = new ItemGroup("tombs_combat_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(TombsItems.IRON_GLAIVE.get());
        }
    };
}
