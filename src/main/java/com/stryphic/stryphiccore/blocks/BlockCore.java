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





    /**
     * Should be the default super constructor for most blocks
     * @param name
     */
    public BlockCore(String name) {
        this(name,Material.ROCK);
    }

    /**
     *
     * @param name
     * @param material
     */
    public BlockCore(String name, Material material){
        this(name,Reference.MODID,material,Reference.STRYPHIC_CORE_TAB);
    }

    /**
     *
     * @param name
     * @param modid
     * @param material
     * @param creativeTab
     */
    public BlockCore(String name, String modid,Material material,CreativeTabs creativeTab){
        super(material);
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(modid + ":" + name);
        setCreativeTab(creativeTab);
        addItemToRegistry();
        addBlockToRegistry();

    }



    public abstract void addItemToRegistry();
    public abstract void addBlockToRegistry();
    public abstract void registerModels();

}
