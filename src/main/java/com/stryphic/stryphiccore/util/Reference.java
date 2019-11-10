package com.stryphic.stryphiccore.util;

import com.stryphic.stryphiccore.init.CoreBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class Reference {
    public static final String MODID = "stryphiccore";
    public static final String NAME = "Stryphic Core";
    public static final String VERSION = "0.0.1";

    public static final String CLIENT_PROXY_CLASS = "com.stryphic.stryphiccore.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.stryphic.stryphiccore.CommonProxy";
    public static final String SERVER_PROXY_CLASS = "com.stryphic.stryphiccore.ServerProxy";

    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";

    public static final net.minecraft.creativetab.CreativeTabs STRYPHIC_CORE_TAB = new net.minecraft.creativetab.CreativeTabs(Reference.MODID.toLowerCase()) {
        @Override
        public ItemStack getTabIconItem() {
            ItemStack itemStack = new ItemStack(CoreBlocks.BLOCK_GREEN);
            return itemStack;

        }


    };

}
