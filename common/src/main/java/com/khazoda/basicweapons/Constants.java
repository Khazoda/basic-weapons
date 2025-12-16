package com.khazoda.basicweapons;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Constants {

	public static final String MOD_ID = "basicweapons";
	public static final String MOD_NAME = "Basic Weapons";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	public static Identifier ID(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static <T> ResourceKey<T> RK(ResourceKey<Registry<T>> registry, Identifier location) {
		return ResourceKey.create(registry, location);
	}

	public static final String PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID = "74a196e4-cd9e-4c93-8606-8e7f0afdc959";
}