package com.stryphic.stryphiccore.blocks;

import cofh.core.gui.CreativeTabCore;
import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.init.CoreBlocks;
import com.stryphic.stryphiccore.init.CoreItems;
import com.stryphic.stryphiccore.items.ItemCore;
import com.stryphic.stryphiccore.proxy.IProxy;
import com.stryphic.stryphiccore.util.CommonUtils;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;


public abstract class BlockCore extends Block implements ICoreBlock {
    protected String name;
    public static CreativeTabs creativeTab;
    public static String nameSpace;
    public static ArrayList<Block> blockArray;
    public static ArrayList<Item> itemArray;
    public static IProxy proxy;



    /**
     * Should be the default super constructor for most blocks
     * @param name
     */
    public BlockCore(String name) {
        this(name,Material.ROCK);
    }

    /**
     * Can be used in the case that you would like to base a material through
     * @param name
     * @param material
     */
    public BlockCore(String name, Material material){
        super(material);
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(nameSpace + ":" + name);
        setCreativeTab(creativeTab);
        addBlockToRegistry();
        addItemToRegistry();
    }


    @Override
    public void addItemToRegistry() {
        itemArray.add(new ItemBlock(this).setRegistryName(this.name));
    }

    @Override
    public void addBlockToRegistry() {
        blockArray.add(this);
    }

    @Override
    public void registerModels() {
        proxy.registerModel(Item.getItemFromBlock(this),0);
    }

}
