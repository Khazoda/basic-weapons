package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.item.*;
import com.seacroak.basicweapons.material.BronzeToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import static com.seacroak.basicweapons.registry.MainRegistry.bronze_mod_loaded;

public class BWItems {
    public static final int daggerDamage = 1;
    public static final float daggerSpeed = -2.0f;
    public static final int hammerDamage = 7;
    public static final float hammerSpeed = -3.4f;
    public static final int clubDamage = 5;
    public static final float clubSpeed = -3.0f;
    public static final int spearDamage = 2;
    public static final float spearSpeed = -2.8f;
    public static final int quarterstaffDamage = 1;
    public static final float quarterstaffSpeed = -2.3f;

    /* When referencing items, use registeredItems from MainRegistry, not items.
    Indices are the same for both constants */
    public static List<UnregisteredItemInfo> items = new LinkedList<>();

    public static void init() {
        /* Daggers */
        items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.WOOD, "wooden_dagger",
                () -> new DaggerItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
                        ToolMaterials.WOOD, daggerDamage, daggerSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.STONE, "stone_dagger",
                () -> new DaggerItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
                        ToolMaterials.STONE, daggerDamage, daggerSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.IRON, "iron_dagger",
                () -> new DaggerItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
                        ToolMaterials.IRON, daggerDamage, daggerSpeed)))));

        if (bronze_mod_loaded)
            items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.BRONZE, "bronze_dagger",
                    () -> new DaggerItem(BronzeToolMaterial.INSTANCE, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
                            BronzeToolMaterial.INSTANCE, daggerDamage, daggerSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.GOLD, "golden_dagger",
                () -> new DaggerItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
                        ToolMaterials.GOLD, daggerDamage - 1, daggerSpeed + 1)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.DIAMOND, "diamond_dagger",
                () -> new DaggerItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
                        ToolMaterials.DIAMOND, daggerDamage, daggerSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.NETHERITE, "netherite_dagger",
                () -> new DaggerItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
                        ToolMaterials.NETHERITE, daggerDamage, daggerSpeed)).fireproof())));

        /* Hammers */
        items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.WOOD, "wooden_hammer",
                () -> new HammerItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
                        ToolMaterials.WOOD, hammerDamage - 6, hammerSpeed + 0.4f)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.STONE, "stone_hammer",
                () -> new HammerItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
                        ToolMaterials.STONE, hammerDamage - 3, hammerSpeed + 0.2f)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.IRON, "iron_hammer",
                () -> new HammerItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
                        ToolMaterials.IRON, hammerDamage, hammerSpeed + 0.1f)))));

        if (bronze_mod_loaded)
            items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.BRONZE, "bronze_hammer",
                    () -> new HammerItem(BronzeToolMaterial.INSTANCE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
                            BronzeToolMaterial.INSTANCE, hammerDamage, hammerSpeed + 0.1f)))));
        items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.GOLD, "golden_hammer",
                () -> new HammerItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
                        ToolMaterials.GOLD, hammerDamage - 3, hammerSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.DIAMOND, "diamond_hammer",
                () -> new HammerItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
                        ToolMaterials.DIAMOND, hammerDamage - 1, hammerSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.NETHERITE, "netherite_hammer",
                () -> new HammerItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
                        ToolMaterials.NETHERITE, hammerDamage - 1, hammerSpeed)).fireproof())));

        /* Clubs */
        items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.WOOD, "wooden_club",
                () -> new ClubItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
                        ToolMaterials.WOOD, clubDamage, clubSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.STONE, "stone_club",
                () -> new ClubItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
                        ToolMaterials.STONE, clubDamage, clubSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.IRON, "iron_club",
                () -> new ClubItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
                        ToolMaterials.IRON, clubDamage, clubSpeed)))));

        if (bronze_mod_loaded)
            items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.BRONZE, "bronze_club",
                    () -> new ClubItem(BronzeToolMaterial.INSTANCE, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
                            BronzeToolMaterial.INSTANCE, clubDamage, clubSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.GOLD, "golden_club",
                () -> new ClubItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
                        ToolMaterials.GOLD, clubDamage, clubSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.DIAMOND, "diamond_club",
                () -> new ClubItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
                        ToolMaterials.DIAMOND, clubDamage, clubSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.NETHERITE, "netherite_club",
                () -> new ClubItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
                        ToolMaterials.NETHERITE, clubDamage, clubSpeed)).fireproof())));

        /* Spears */
        items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.WOOD, "wooden_spear",
                () -> new SpearItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
                        ToolMaterials.WOOD, spearDamage, spearSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.STONE, "stone_spear",
                () -> new SpearItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
                        ToolMaterials.STONE, spearDamage, spearSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.IRON, "iron_spear",
                () -> new SpearItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
                        ToolMaterials.IRON, spearDamage, spearSpeed)))));

        if (bronze_mod_loaded)
            items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.BRONZE, "bronze_spear",
                    () -> new SpearItem(BronzeToolMaterial.INSTANCE, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
                            BronzeToolMaterial.INSTANCE, spearDamage, spearSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.GOLD, "golden_spear",
                () -> new SpearItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
                        ToolMaterials.GOLD, spearDamage, spearSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.DIAMOND, "diamond_spear",
                () -> new SpearItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
                        ToolMaterials.DIAMOND, spearDamage, spearSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.NETHERITE, "netherite_spear",
                () -> new SpearItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
                        ToolMaterials.NETHERITE, spearDamage, spearSpeed)).fireproof())));

        /* Quarterstaves */
        items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.WOOD, "wooden_quarterstaff",
                () -> new QuarterstaffItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
                        ToolMaterials.WOOD, quarterstaffDamage, quarterstaffSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.STONE, "stone_quarterstaff",
                () -> new QuarterstaffItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
                        ToolMaterials.STONE, quarterstaffDamage, quarterstaffSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.IRON, "iron_quarterstaff",
                () -> new QuarterstaffItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
                        ToolMaterials.IRON, quarterstaffDamage, quarterstaffSpeed)))));

        if (bronze_mod_loaded)
            items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.BRONZE, "bronze_quarterstaff",
                    () -> new QuarterstaffItem(BronzeToolMaterial.INSTANCE, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
                            BronzeToolMaterial.INSTANCE, quarterstaffDamage, quarterstaffSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.GOLD, "golden_quarterstaff",
                () -> new QuarterstaffItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
                        ToolMaterials.GOLD, quarterstaffDamage, quarterstaffSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.DIAMOND, "diamond_quarterstaff",
                () -> new QuarterstaffItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
                        ToolMaterials.DIAMOND, quarterstaffDamage, quarterstaffSpeed)))));

        items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.NETHERITE, "netherite_quarterstaff",
                () -> new QuarterstaffItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
                        ToolMaterials.NETHERITE, quarterstaffDamage, quarterstaffSpeed)).fireproof())));
    }

    public enum WEAPON_TYPE {
        DAGGER,
        CLUB,
        HAMMER,
        SPEAR,
        QUARTERSTAFF
    }

    public enum WEAPON_MATERIAL {
        WOOD,
        STONE,
        IRON,
        BRONZE,
        GOLD,
        DIAMOND,
        NETHERITE
    }

    /*** This class holds information about all items.
     It is accessed by forge & fabric methods
     for automatic registration ***/
    public static class UnregisteredItemInfo {
        public final WEAPON_TYPE weaponType;
        public final WEAPON_MATERIAL weaponMaterial;
        public final String name;
        public final Supplier<Item> itemSupplier;

        public UnregisteredItemInfo(WEAPON_TYPE weaponType, WEAPON_MATERIAL weaponMaterial, String name, Supplier<Item> itemSupplier) {
            this.weaponType = weaponType;
            this.weaponMaterial = weaponMaterial;
            this.name = name;
            this.itemSupplier = itemSupplier;
        }
    }


}
