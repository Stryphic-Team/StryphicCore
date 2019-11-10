package com.stryphic.stryphiccore.proxy;

import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.init.CoreBlocks;
import com.stryphic.stryphiccore.init.CoreItems;
import com.stryphic.stryphiccore.util.Reference;
import com.stryphic.stryphiccore.util.handlers.CommonEventHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy implements IProxy{
    public void registerItemRenderer(Item item, int meta, String id) {}

    @Override
    public void registerModel(Item item, int metadata) {

    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ConfigManager.load(Reference.MODID, Config.Type.INSTANCE);
        StryphicCore.logger.info("Pre Intializing");
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());

        CoreBlocks.init();
        CoreItems.init();

        CoreBlocks.initOreDictionary();
        CoreItems.initOreDictionary();


    }

    @Override
    public void init(FMLInitializationEvent event) {

        StryphicCore.logger.info("Intializing");
        ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);

        //NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
        //PacketHandler.init();

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {



    }
}
