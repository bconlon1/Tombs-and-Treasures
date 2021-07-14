package com.bconlon.tombsandtreasures.common.items.melee.glaive;

import com.bconlon.tombsandtreasures.common.items.melee.MeleeSlashingItem;
import com.bconlon.tombsandtreasures.common.items.util.IReach;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraftforge.common.ForgeMod;

public class GlaiveItem extends MeleeSlashingItem implements IReach
{
    private final float reach;

    private Multimap<Attribute, AttributeModifier> glaiveAttributes;

    public GlaiveItem(IItemTier tier, float damage, float speed, float reach, Properties properties) {
        super(tier, damage, speed, properties);
        this.reach = reach;
    }

    @Override
    public double getAttackReach() {
        return this.reach;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType slot) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(slot));
        builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(REACH_MODIFIER_UUID, "Reach modifier", this.getAttackReach(), AttributeModifier.Operation.ADDITION));
        this.glaiveAttributes = builder.build();
        return slot == EquipmentSlotType.MAINHAND ? this.glaiveAttributes : super.getDefaultAttributeModifiers(slot);
    }
}
