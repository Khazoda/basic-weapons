package com.seacroak.basicweapons.util;

import com.seacroak.basicweapons.Constants;
import net.minecraft.util.Identifier;

public class ID {
  public static Identifier of(String path) {
    return Constants.BW_IDENTIFIER.withPath(path);
  }
}
