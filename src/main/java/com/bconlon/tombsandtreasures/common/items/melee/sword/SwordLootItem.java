package com.bconlon.tombsandtreasures.common.items.melee.sword;

import com.bconlon.tombsandtreasures.common.items.melee.MeleeSlashingItem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class SwordLootItem extends MeleeSlashingItem
{
    public SwordLootItem(IItemTier tier, float damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }
}
