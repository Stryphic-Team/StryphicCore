package com.stryphic.stryphiccore.blocks;

import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.init.CoreBlocks;
import com.stryphic.stryphiccore.init.CoreItems;
import com.stryphic.stryphiccore.util.CommonUtils;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public abstract class BlockFallingBase extends BlockFalling implements IModBlockBase{
    protected String name;
    /**
     * Should be the default super constructor for most blocks
     * @param name
     */
    public BlockFallingBase(String name) {
        this(name,Material.ROCK);
    }

    /**
     * Can be used in the case that you would like to base a material through
     * @param name
     * @param material
     */
    public BlockFallingBase(String name,Material material){
        super(material);
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(Reference.STRYPHIC_CORE_TAB);
        addItemToRegistry();
        addBlockToRegistry();
    }
    @Override
    public void addItemToRegistry() {
        CoreItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.name));
    }

    @Override
    public void addBlockToRegistry() {
        CoreBlocks.BLOCKS.add(this);
    }

    @Override
    public void registerModels() {
        StryphicCore.proxy.registerModel(Item.getItemFromBlock(this),0);
    }
}
