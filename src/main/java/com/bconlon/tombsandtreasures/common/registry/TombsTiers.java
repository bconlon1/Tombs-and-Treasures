package com.bconlon.tombsandtreasures.common.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public class TombsTiers
{
    public static final IItemTier BASIC = new TombsTier(2, 513, 6.0F, 2.0F, 0, () -> Ingredient.EMPTY);

    public static final IItemTier SPECIAL = new TombsTier(3, 1793, 8.0F, 3.0F, 0, () -> Ingredient.EMPTY);

    private static class TombsTier implements IItemTier
    {
        private final int harvestLevel;
        private final int durability;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMaterial;

        public TombsTier(int harvestLevelIn, int durability, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
            this.harvestLevel = harvestLevelIn;
            this.durability = durability;
            this.efficiency = efficiencyIn;
            this.attackDamage = attackDamageIn;
            this.enchantability = enchantabilityIn;
            this.repairMaterial = new LazyValue<>(repairMaterialIn);
        }

        @Override
        public int getLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getUses() {
            return this.durability;
        }

        @Override
        public float getSpeed() {
            return this.efficiency;
        }

        @Override
        public float getAttackDamageBonus() {
            return this.attackDamage;
        }

        @Override
        public int getEnchantmentValue() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return this.repairMaterial.get();
        }
    }
}
