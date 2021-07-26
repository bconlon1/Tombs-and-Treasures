package com.bconlon.tombsandtreasures.common.items.melee.sword;

import com.bconlon.tombsandtreasures.common.items.melee.AbstractSlashingItem;
import net.minecraft.item.IItemTier;

public class SwordLootItem extends AbstractSlashingItem
{
    public SwordLootItem(IItemTier tier, float damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }
}
