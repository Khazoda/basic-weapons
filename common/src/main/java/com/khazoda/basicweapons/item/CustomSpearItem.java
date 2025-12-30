package com.khazoda.basicweapons.item;

import com.khazoda.basicweapons.platform.ItemExtension;
import com.khazoda.basicweapons.registry.TagRegistry;
import com.khazoda.basicweapons.utils.AllowDenyPass;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.enchantment.Enchantment;

// Todo: make this extensible someday for all spear stats
public class CustomSpearItem extends Item implements ItemExtension {
    public CustomSpearItem(ToolMaterial material, float attackDamage, float attackSpeed, double reach,
            Item.Properties properties) {
        super(properties);
    }

    @Override
    public AllowDenyPass bw$canEnchant(ItemStack itemstack, Holder<Enchantment> enchantment) {
        // The item can't be enchanted by enchantments listed here
        return enchantment.is(TagRegistry.MIGHT_ENCHANTABLE) ? AllowDenyPass.DENY : AllowDenyPass.PASS;
    }
}
