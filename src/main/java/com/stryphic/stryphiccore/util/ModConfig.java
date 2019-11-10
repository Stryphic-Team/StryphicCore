package com.stryphic.stryphiccore.util;

import net.minecraftforge.common.config.Config;


@Config(modid = Reference.MODID)
public class ModConfig {
    @Config.Name("Debug Mode")
    public static boolean IS_DEBUG = false;

}
