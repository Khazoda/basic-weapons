package com.khazoda.basicweapons.datagen;

import com.khazoda.basicweapons.registry.WeaponRegistry;
import com.khazoda.basicweapons.struct.WeaponType;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BasicWeaponsModelProvider extends FabricModelProvider {

  public BasicWeaponsModelProvider(FabricDataOutput output) {
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
  }

  @Override
  public void generateItemModels(ItemModelGenerators itemModelGenerators) {
    for (WeaponType weaponType : WeaponType.values()) {
      WeaponRegistry.getItemsByType(weaponType).forEach(item -> {
        if (needsDisplayContext(weaponType)) {
          generateDisplayContextModel(itemModelGenerators, item, weaponType);
        } else {
          itemModelGenerators.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM);
        }
      });
    }
  }

  private boolean needsDisplayContext(WeaponType weaponType) {
    return weaponType == WeaponType.SPEAR ||
        weaponType == WeaponType.QUARTERSTAFF ||
        weaponType == WeaponType.GLAIVE;
  }

  private void generateDisplayContextModel(ItemModelGenerators itemModelGenerators, Item item, WeaponType weaponType) {
    ResourceLocation baseModel = itemModelGenerators.createFlatItemModel(item, item, ModelTemplates.FLAT_HANDHELD_ITEM);
    ResourceLocation heldModel = createBigHeldModel(itemModelGenerators, item, weaponType);
    ItemModel.Unbaked displayContextModel = getUnbaked(baseModel, heldModel);
    itemModelGenerators.itemModelOutput.accept(item, displayContextModel);
  }

  private ResourceLocation createBigHeldModel(ItemModelGenerators itemModelGenerators, Item item, WeaponType weaponType) {
    String parentModel;
    switch (weaponType) {
      case SPEAR -> parentModel = "handheld_big_spear";
      case QUARTERSTAFF -> parentModel = "handheld_big_quarterstaff";
      case GLAIVE -> parentModel = "handheld_big_glaive";
      default -> parentModel = "handheld_big_spear";
    }
    ModelTemplate bigWeaponTemplate = new ModelTemplate(
        Optional.of(ResourceLocation.fromNamespaceAndPath("basicweapons", "item/" + parentModel)),
        Optional.empty(),
        TextureSlot.LAYER0
    );

    ResourceLocation heldModelId = ModelLocationUtils.getModelLocation(item, "_held");
    TextureMapping heldTextureMapping = TextureMapping.layer0(TextureMapping.getItemTexture(item, "_held"));
    return bigWeaponTemplate.create(heldModelId, heldTextureMapping, itemModelGenerators.modelOutput);
  }

  private static ItemModel.@NotNull Unbaked getUnbaked(ResourceLocation baseModel, ResourceLocation heldModel) {
    ItemModel.Unbaked baseUnbaked = ItemModelUtils.plainModel(baseModel);
    ItemModel.Unbaked heldUnbaked = ItemModelUtils.plainModel(heldModel);
    return ItemModelUtils.select(
        new DisplayContext(),
        heldUnbaked,
        ItemModelUtils.when(
            java.util.List.of(
                ItemDisplayContext.GUI,
                ItemDisplayContext.GROUND,
                ItemDisplayContext.FIXED,
                ItemDisplayContext.ON_SHELF
            ),
            baseUnbaked
        ));
  }
}