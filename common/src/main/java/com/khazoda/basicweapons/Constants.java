package com.khazoda.basicweapons;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	public static final String MOD_ID = "basicweapons";
	public static final String MOD_NAME = "Basic Weapons";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	public static ResourceLocation ID(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}