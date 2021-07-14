package com.bconlon.tombsandtreasures.core.data.provider;

import com.bconlon.tombsandtreasures.TombsAndTreasures;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.loaders.SeparatePerspectiveModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public abstract class TombsItemModelProvider extends ItemModelProvider
{
    public TombsItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TombsAndTreasures.MODID, existingFileHelper);
    }

    public ItemModelBuilder handheldItem(Supplier<? extends Item> item, String location) {
        return withExistingParent(item.get().getRegistryName().getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + location + item.get().getRegistryName().getPath()));
    }

    public ItemModelBuilder separatePerspectiveItem(Supplier<? extends Item> item, String location) {
        return withExistingParent(item.get().getRegistryName().getPath(), "forge:item/default")
                .customLoader(SeparatePerspectiveModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc("item/handheld_double"))).texture("layer0", modLoc("item/" + location + "held/" + item.get().getRegistryName().getPath() + "_held")))
                .perspective(ItemCameraTransforms.TransformType.GUI, nested().parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", modLoc("item/" + location + item.get().getRegistryName().getPath())))
                .end();
    }
}
