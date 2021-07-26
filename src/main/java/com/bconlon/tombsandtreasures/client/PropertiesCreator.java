package com.bconlon.tombsandtreasures.client;

import com.bconlon.tombsandtreasures.common.registry.TombsItems;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class PropertiesCreator
{
    public static void setupItemProperties() {
        makeBowProperties(TombsItems.LONGBOW.get());
    }

    private static void makeBowProperties(Item item) {
        ItemModelsProperties.register(item, new ResourceLocation("pull"), (p_239429_0_, p_239429_1_, p_239429_2_) -> {
            if (p_239429_2_ == null) {
                return 0.0F;
            } else {
                return p_239429_2_.getUseItem() != p_239429_0_ ? 0.0F : (float) (p_239429_0_.getUseDuration() - p_239429_2_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemModelsProperties.register(item, new ResourceLocation("pulling"), (p_239428_0_, p_239428_1_, p_239428_2_) ->
                p_239428_2_ != null && p_239428_2_.isUsingItem() && p_239428_2_.getUseItem() == p_239428_0_ ? 1.0F : 0.0F);
    }

    private static void makeCrossbowProperties(Item item) {
        ItemModelsProperties.register(item, new ResourceLocation("pull"), (p_239427_0_, p_239427_1_, p_239427_2_) -> {
            if (p_239427_2_ == null) {
                return 0.0F;
            } else {
                return CrossbowItem.isCharged(p_239427_0_) ? 0.0F : (float)(p_239427_0_.getUseDuration() - p_239427_2_.getUseItemRemainingTicks()) / (float) CrossbowItem.getChargeDuration(p_239427_0_);
            }
        });
        ItemModelsProperties.register(item, new ResourceLocation("pulling"), (p_239426_0_, p_239426_1_, p_239426_2_) ->
                p_239426_2_ != null && p_239426_2_.isUsingItem() && p_239426_2_.getUseItem() == p_239426_0_ && !CrossbowItem.isCharged(p_239426_0_) ? 1.0F : 0.0F);
        ItemModelsProperties.register(item, new ResourceLocation("charged"), (p_239425_0_, p_239425_1_, p_239425_2_) ->
                p_239425_2_ != null && CrossbowItem.isCharged(p_239425_0_) ? 1.0F : 0.0F);
        ItemModelsProperties.register(item, new ResourceLocation("firework"), (p_239424_0_, p_239424_1_, p_239424_2_) ->
                p_239424_2_ != null && CrossbowItem.isCharged(p_239424_0_) && CrossbowItem.containsChargedProjectile(p_239424_0_, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);
    }
}
