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


    public ItemCore(String name){
        this(name,Reference.MODID,Reference.STRYPHIC_CORE_TAB);
    }
    public ItemCore(String name, String modid,CreativeTabs creativeTab){
        setRegistryName(name);
        setUnlocalizedName(modid + ":" + name);
        this.setCreativeTab(creativeTab);
        addToRegistry();
    }
    @Override
    public abstract void registerModels();
    public abstract void addToRegistry();
}
