package com.bconlon.tombsandtreasures.core.data.provider;

import com.bconlon.tombsandtreasures.TombsAndTreasures;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
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
                .base(nested().parent(getExistingFile(modLoc("item/melee_handheld_double"))).texture("layer0", modLoc("item/" + location + "held/" + item.get().getRegistryName().getPath() + "_held")))
                .perspective(ItemCameraTransforms.TransformType.GUI, nested().parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", modLoc("item/" + location + item.get().getRegistryName().getPath())))
                .end();
    }

    public ItemModelBuilder longbowItem(Supplier<? extends Item> item, String location) {
        return separatePerspectiveBowItem(item, location, modLoc("item/longbow_base"), mcLoc("item/generated"));
    }

    public ItemModelBuilder separatePerspectiveBowItem(Supplier<? extends Item> item, String location, ResourceLocation heldParent, ResourceLocation guiParent) {
        bowPullingModel(item, location, heldParent, guiParent, 0);
        bowPullingModel(item, location, heldParent, guiParent, 1);
        bowPullingModel(item, location, heldParent, guiParent, 2);
        return withExistingParent(item.get().getRegistryName().getPath(), "forge:item/default")
                .customLoader(SeparatePerspectiveModelBuilder::begin)
                .base(nested().parent(getExistingFile(heldParent)).texture("layer0", modLoc("item/" + location + "held/" + item.get().getRegistryName().getPath() + "_held")))
                .perspective(ItemCameraTransforms.TransformType.GUI, nested().parent(getExistingFile(guiParent)).texture("layer0", modLoc("item/" + location + item.get().getRegistryName().getPath())))
                .end()
                .override().predicate(new ResourceLocation("pulling"), 1).model(getExistingFile(modLoc("item/" + item.get().getRegistryName().getPath() + "_pulling_0"))).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.65F).model(getExistingFile(modLoc("item/" + item.get().getRegistryName().getPath() + "_pulling_1"))).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.9F).model(getExistingFile(modLoc("item/" + item.get().getRegistryName().getPath() + "_pulling_2"))).end();
    }

    private ItemModelBuilder bowPullingModel(Supplier<? extends Item> item, String location, ResourceLocation heldParent, ResourceLocation guiParent, int stage) {
        return withExistingParent(item.get().getRegistryName().getPath() + "_pulling_" + stage, "forge:item/default")
                .customLoader(SeparatePerspectiveModelBuilder::begin)
                .base(nested().parent(getExistingFile(heldParent)).texture("layer0", modLoc("item/" + location + "held/" + item.get().getRegistryName().getPath() + "_pulling_" + stage + "_held")))
                .perspective(ItemCameraTransforms.TransformType.GUI, nested().parent(getExistingFile(guiParent)).texture("layer0", modLoc("item/" + location + item.get().getRegistryName().getPath() + "_pulling_" + stage)))
                .end();
    }
}
