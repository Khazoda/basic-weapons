package com.khazoda.basicweapons.mixin.client;

import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/* Remove in 1.21.4 in favour of new item model loading system */
@Mixin(ItemRenderer.class)
public interface ItemRendererAccessor {
  @Accessor("itemModelShaper")
  ItemModelShaper bw$getItemModelShaper();
}