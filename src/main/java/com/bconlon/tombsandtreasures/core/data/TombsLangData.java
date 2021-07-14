package com.bconlon.tombsandtreasures.core.data;

import com.bconlon.tombsandtreasures.TombsAndTreasures;
import com.bconlon.tombsandtreasures.common.registry.TombsItemGroups;
import com.bconlon.tombsandtreasures.common.registry.TombsItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.data.LanguageProvider;

public class TombsLangData extends LanguageProvider
{
    public TombsLangData(DataGenerator gen) {
        super(gen, TombsAndTreasures.MODID, "en_us");
    }

    private void addItemGroup(ItemGroup group, String name) {
        add(group.getDisplayName().getString(), name);
    }

    public void addCuriosIdentifier(String key, String name) {
        add("curios.identifier." + key, name);
    }

    @Override
    protected void addTranslations() {
        addItem(TombsItems.IRON_GLAIVE, "Iron Glaive");

        addItemGroup(TombsItemGroups.TOMBS_COMBAT, "Tombs and Treasures Combat");

        addCuriosIdentifier("dungeon_artifact", "Dungeon Artifact");
    }
}
