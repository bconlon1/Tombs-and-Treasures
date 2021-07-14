package com.bconlon.tombsandtreasures.common.items.melee.axe;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class AxeLootItem extends AxeItem
{
    public AxeLootItem(IItemTier tier, float damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }
}
