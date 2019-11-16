package com.stryphic.stryphiccore.blocks;

import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.init.CoreBlocks;
import com.stryphic.stryphiccore.init.CoreItems;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockGreen extends BlockCore{
    public BlockGreen(String name) {
        super(name);
    }

    @Override
    public void addItemToRegistry() {
        CoreItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
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
