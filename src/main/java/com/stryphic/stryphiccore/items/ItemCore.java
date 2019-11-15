package com.stryphic.stryphiccore.items;

import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.init.CoreItems;
import com.stryphic.stryphiccore.proxy.IProxy;
import com.stryphic.stryphiccore.util.CommonUtils;
import com.stryphic.stryphiccore.util.IHasModel;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;

public abstract class ItemCore extends Item implements IHasModel {
    public static CreativeTabs creativeTab;
    public static String nameSpace;
    public static ArrayList<Item> itemArray;
    public static IProxy proxy;

    public ItemCore(String name){
        this(name,true);
    }
    public ItemCore(String name, boolean addToCreativeTab){
        setRegistryName(name);
        setUnlocalizedName(nameSpace + ":" + name);
        if(addToCreativeTab) {
            this.setCreativeTab(creativeTab);
        }
        itemArray.add(this);
    }
    @Override
    public void registerModels()
    {
        proxy.registerModel(this,0);

    }
}
