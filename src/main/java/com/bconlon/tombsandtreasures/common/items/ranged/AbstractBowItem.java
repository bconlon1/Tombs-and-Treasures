package com.bconlon.tombsandtreasures.common.items.ranged;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class AbstractBowItem extends BowItem
{
    //Default: 0.0F (Arrow Damage is 2.0D)
    private final float rangedDamageBonus;
    //Default: 72000
    private final int useDuration;
    //Default: 20.0F
    private static float pullbackDuration;
    //Default: 0.0F (velocity is partially increased by pullbackDuration by default)
    private final float velocityStrength;
    //Default: 1.0F
    private final float inaccuracy;

    public AbstractBowItem(float rangedDamageBonus, int useDuration, float pullbackTime, float velocityStrength, float inaccuracy, Properties properties) {
        super(properties);
        this.rangedDamageBonus = rangedDamageBonus;
        this.useDuration = useDuration;
        pullbackDuration = pullbackTime;
        this.velocityStrength = velocityStrength;
        this.inaccuracy = inaccuracy;
    }

    @Override
    public void releaseUsing(ItemStack p_77615_1_, World p_77615_2_, LivingEntity p_77615_3_, int p_77615_4_) {
        if (p_77615_3_ instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)p_77615_3_;
            boolean flag = playerentity.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, p_77615_1_) > 0;
            ItemStack itemstack = playerentity.getProjectile(p_77615_1_);

            int i = this.getUseDuration(p_77615_1_) - p_77615_4_;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(p_77615_1_, p_77615_2_, playerentity, i, !itemstack.isEmpty() || flag);
            if (i >= 0) {
                if (!itemstack.isEmpty() || flag) {
                    if (itemstack.isEmpty()) {
                        itemstack = new ItemStack(Items.ARROW);
                    }

                    float f = getPowerForTime(i);
                    if (!((double) f < 0.1D)) {
                        boolean flag1 = playerentity.abilities.instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, p_77615_1_, playerentity));
                        if (!p_77615_2_.isClientSide) {
                            ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                            AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(p_77615_2_, itemstack, playerentity);
                            abstractarrowentity = customArrow(abstractarrowentity);
                            abstractarrowentity.shootFromRotation(playerentity, playerentity.xRot, playerentity.yRot, 0.0F, f * 3.0F, this.getInaccuracy());
                            abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + this.getRangedDamageBonus());

                            if (this.getVelocityStrength() > 0.0F) {
                                Vector3d motion = abstractarrowentity.getDeltaMovement();
                                abstractarrowentity.setDeltaMovement(motion.add(playerentity.getLookAngle().multiply((1.0D + f) * this.velocityStrength, (1.0D + f) * this.velocityStrength, (1.0D + f) * this.velocityStrength)));
                            }

                            if (f == 1.0F) {
                                abstractarrowentity.setCritArrow(true);
                            }

                            int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, p_77615_1_);
                            if (j > 0) {
                                abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + (double)j * 0.5D + 0.5D);
                            }

                            int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, p_77615_1_);
                            if (k > 0) {
                                abstractarrowentity.setKnockback(k);
                            }

                            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, p_77615_1_) > 0) {
                                abstractarrowentity.setSecondsOnFire(100);
                            }

                            p_77615_1_.hurtAndBreak(1, playerentity, (p_220009_1_) -> {
                                p_220009_1_.broadcastBreakEvent(playerentity.getUsedItemHand());
                            });
                            if (flag1 || playerentity.abilities.instabuild && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                                abstractarrowentity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                            }

                            p_77615_2_.addFreshEntity(abstractarrowentity);
                        }

                        p_77615_2_.playSound(null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                        if (!flag1 && !playerentity.abilities.instabuild) {
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                playerentity.inventory.removeItem(itemstack);
                            }
                        }

                        playerentity.awardStat(Stats.ITEM_USED.get(this));
                    }
                }
            }
        }
    }

    public static float getPowerForTime(int p_185059_0_) {
        float f = (float)p_185059_0_ / getPullbackDuration();
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return this.useDuration;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 20;
    }

    public float getRangedDamageBonus()
    {
        return this.rangedDamageBonus;
    }

    public static float getPullbackDuration() {
        return pullbackDuration;
    }

    public float getVelocityStrength() {
        return this.velocityStrength;
    }

    public float getInaccuracy() {
        return this.inaccuracy;
    }
}
