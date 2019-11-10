package com.stryphic.stryphiccore.client;

import com.stryphic.stryphiccore.util.CommonUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

public class ModStateMapper extends StateMapperBase {
    private String name;
    public ModStateMapper(String name){
        this.name = name;
    }
    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
        return new ModelResourceLocation(CommonUtils.createUnlocalizedName(name), "fluid");
    }
}
