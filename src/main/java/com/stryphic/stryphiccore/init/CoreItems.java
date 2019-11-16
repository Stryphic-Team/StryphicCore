package com.stryphic.stryphiccore.init;

import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.blocks.BlockCore;
import com.stryphic.stryphiccore.items.IOreDictItem;
import com.stryphic.stryphiccore.items.ItemCore;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

public class CoreItems {
    public static ArrayList<Item> ITEMS = new ArrayList<Item>();
    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (Item item: ITEMS) {
            registerRender(item);
        }
    }
    public static void init(){

        for(Item item:ITEMS){
            ForgeRegistries.ITEMS.register(item);
        }
    }
    public static void initOreDictionary(){
        for(Item item:ITEMS){
            if(item instanceof IOreDictItem) {
                ((IOreDictItem)item).initOreDict();
            }
        }
    }
    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 3, new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }
}
