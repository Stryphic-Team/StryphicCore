package com.stryphic.stryphiccore.util.handlers;

import com.stryphic.stryphiccore.blocks.ICoreBlock;
import com.stryphic.stryphiccore.entityproperties.CapabilityProvider;
import com.stryphic.stryphiccore.entityproperties.CorePlayerCapability;
import com.stryphic.stryphiccore.init.CoreBlocks;
import com.stryphic.stryphiccore.init.CoreItems;
import com.stryphic.stryphiccore.util.IHasModel;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.getModID().equals(Reference.MODID)){
            ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
        }

    }
    /**
     * Registers models for items and blocks
     * @param event
     */
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : CoreItems.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : CoreBlocks.BLOCKS)
        {
            if(block instanceof ICoreBlock)
            {
                ((ICoreBlock)block).registerModels();
            }
        }
    }


    @SubscribeEvent
    public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) event.getObject();
            if (!entityPlayerMP.hasCapability(CorePlayerCapability.PLAYER_PROPERTIES, null)) {
                event.addCapability(new ResourceLocation(Reference.MODID, "player_data"), new CapabilityProvider());
            }
        }else if(event.getObject() instanceof EntityPlayer){
            if (!event.getObject().hasCapability(CorePlayerCapability.PLAYER_PROPERTIES, null)) {
                event.addCapability(new ResourceLocation(Reference.MODID, "player_data"), new CapabilityProvider());
            }
        }
    }
}
