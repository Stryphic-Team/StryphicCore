package com.stryphic.stryphiccore.blocks;

import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.init.CoreBlocks;
import com.stryphic.stryphiccore.init.CoreItems;
import com.stryphic.stryphiccore.util.CommonUtils;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.block.BlockTNT;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public abstract class BlockExplodingBase extends BlockTNT implements IModBlockBase {

    protected String name;

    /**
     * Can be used in the case that you would like to base a material through
     * @param name
     */

    public BlockExplodingBase(String name){
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
