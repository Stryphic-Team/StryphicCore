package com.stryphic.stryphiccore.init;

import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.blocks.BlockCore;
import com.stryphic.stryphiccore.blocks.BlockGreen;
import com.stryphic.stryphiccore.blocks.IOreDictBlock;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

public class CoreBlocks {
    public static ArrayList<Block> BLOCKS = new ArrayList<Block>();
    static{
        BlockCore.blockArray = BLOCKS;
        BlockCore.itemArray = CoreItems.ITEMS;
        BlockCore.proxy = StryphicCore.proxy;
        BlockCore.nameSpace = Reference.MODID;
        BlockCore.creativeTab = Reference.STRYPHIC_CORE_TAB;
    }
    public static BlockCore BLOCK_GREEN = new BlockGreen("green_block");

    public static void init(){

        for(Block block:BLOCKS){
            ForgeRegistries.BLOCKS.register(block);
        }
    }
    public static void initOreDictionary(){
        for(Block block:BLOCKS){
            if(block instanceof IOreDictBlock){
                ((IOreDictBlock)block).initOreDict();
            }
        }
    }
}
