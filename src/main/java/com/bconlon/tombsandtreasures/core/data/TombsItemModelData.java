package com.bconlon.tombsandtreasures.core.data;

import com.bconlon.tombsandtreasures.common.registry.TombsItems;
import com.bconlon.tombsandtreasures.core.data.provider.TombsItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TombsItemModelData extends TombsItemModelProvider
{
    public TombsItemModelData(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
    }

    @Override
    public String getName() {
        return "Tombs And Treasures Item Models";
    }

    @Override
    protected void registerModels() {
        separatePerspectiveItem(TombsItems.IRON_GLAIVE, "weapon/melee/glaive/");
    }
}
